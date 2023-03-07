package com.techelevator.tenmo.services;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.CustomerBalanceDto;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class RestAccountService implements AccountService {


    public RestAccountService() {}
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;
    @Override
    public ResponseEntity<Object> ListAllUserAccounts(String userName) {
        List<Account> accounts = accountDao.accountsByUserName(userName);
        if(accounts==null ){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error : Something wrong happened!");
        }
        return ResponseEntity.ok(accounts);
    }

    @Override
    public ResponseEntity<Object> getUserGeneralBalance(String userName) {
        User user = userDao.findByUsername(userName);
        BigDecimal balance = accountDao.getGeneralBalance(userName);
        if(user==null || balance==null ){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error : Something wrong happened!");
        }
        return ResponseEntity.ok(new CustomerBalanceDto(user,balance));
    }

    @Override
    public ResponseEntity<Object> getBalanceByAccount(String userName, long accountId) {

        User user = userDao.findByUsername(userName);
        BigDecimal balance = accountDao.getBalanceByAccount(userName,accountId);
        if(user==null || balance==null ){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error : Something wrong happened!");
        }
        return ResponseEntity.ok(new CustomerBalanceDto(user,balance));
    }

    @Override
    public ResponseEntity<Object> customerMoneyTransfer(long from, long to, BigDecimal amount) {
        User userFrom = userDao.getUserById(from);
        User userTo = userDao.getUserById(to);
        // make sure the user are exist
        if(userTo==null || userFrom==null) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("both or one of the users info " +
                    "not correct");
        }
        // transfer to self not allowed
        if(to==from){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this operation can't be completed");
        }
        // over draft not allowed
        BigDecimal balance = accountDao.getGeneralBalance(userFrom.getUsername()).subtract(amount);
        if(balance.compareTo(BigDecimal.ZERO)<0){
            return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You can not overdraft your accounts");
        }

        Boolean isMoneyTransferred = accountDao.accountsUpdate(from, to, amount);
        if(isMoneyTransferred){
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("money was transferred");
        }else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("money was not transferred due too internal issue error");
        }
    }

    @Override
    public ResponseEntity<Long> getAccountIdByUserId(long userId) {
        long accountId = accountDao.getAccountIdByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(accountId);
    }
}
