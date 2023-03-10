package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * This class is a model representing a history of transfers. It is used to show the user their transfer history.
 * The when the client sends a request for the history, it receives an array of this object.
 *
 * @author Dustin
 */
public class TransferHistoryDto {
    /**
     * The transfer's id number.
     */
    private long transferId;
    /**
     * The name of the user the transfer was sent from.
     */
    private String userFrom;
    /**
     * The name of the user the transfer was sent to.
     */
    private String userTo;
    /**
     * The amount of money that was sent with the transfer.
     */
    private BigDecimal amount;

    public TransferHistoryDto() {}

    /**
     * Constructs a transfer history with the following variables. Not used, as the IDE prefers deserializing opjects
     * with default constructor.
     *
     * @param transferId The id number of the transfer.
     * @param userFrom The name of the user that the transfer was sent from.
     * @param userTo The name of the user that the transfer was sent to.
     * @param amount The amount of money that was transferred.
     */
    public TransferHistoryDto(long transferId, String userFrom, String userTo, BigDecimal amount) {
        this.transferId = transferId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Converts the transfer history into string format. A format pattern is used to ensure that the amount is
     * printed with the proper comma and decimal spacing. Also checks to see if the current user was the userTo or
     * userFrom of the transaction, returning a string based on the outcome of that check. If they were the userFrom,
     * then it returns a string saying who the transaction was to. Otherwise, it returns a string saying who the
     * transaction was from.
     *
     * @param currentUser The user that is currently logged in.
     */

    public String toString(AuthenticatedUser currentUser) {
        DecimalFormat format = new DecimalFormat("#,##0.00"); /* format pattern for a numeric String */
        String amountString = format.format(getAmount()); /* Formats our BigDecimal (as a String) with commas and a
                                                             decimal that has a precision of 2 */
        String fString = null;

        if (currentUser.getUser().getUsername().equals(getUserFrom())) {
            fString = String.format("| %-5d |   To: %-50s | $ %15s |", getTransferId(), getUserTo(), amountString);
        } else {
            fString = String.format("| %-5d | From: %-50s | $ %15s |", getTransferId(), getUserFrom(), amountString);
        }

        return fString;
    }
}
