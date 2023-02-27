package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;

import java.util.List;

public interface TransferStatusDao {

    List<TransferStatus> listAll();
    TransferStatus getStatusById(int id);
    TransferStatus getStatusByName(String name);
}
