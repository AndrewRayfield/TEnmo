package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * This class represents a list of details belonging to a transfer. While it is categorized as a data transfer
 * object, the object is only ever used on the client-side.
 *
 * @author Dustin
 */
public class TransferDetailsDto {
    /**
     * The id number of the transfer.
     */
    private long transferId;
    /**
     * The name of the user that the transfer was sent from.
     */
    private String accountFrom;
    /**
     * The name of the user that the transfer was sent to.
     */
    private String accountTo;
    /**
     * The transfer's type description. Currently, the only implemented type is "Send".
     */
    private String transferType;
    /**
     * The transfer's status description. Currently, the only implemented status is "Approved".
     */
    private String transferStatus;
    /**
     * The amount of money that was transferred.
     */
    private BigDecimal amount;

    public TransferDetailsDto() {

    }

    /**
     * Constructs a transfer with all the following parameters. This constructor was used, and implemented, at one
     * point, however the IDE preferred the use of a default constructor (above). Re-implementation to be decided at
     * a later date.
     *
     * @param transferId The id number of the transfer.
     * @param accountFrom The name of the user that the transfer was sent from.
     * @param accountTo The name of the user that the transfer was sent to.
     * @param transferType The transfer's type description. Currently, the only implemented type is "Send".
     * @param transferStatus The transfer's status description. Currently, the only implemented status is "Approved".
     * @param amount The amount of money that was transferred.
     */
    public TransferDetailsDto(long transferId, String accountFrom, String accountTo, String transferType,
                              String transferStatus, BigDecimal amount) {
        this.transferId = transferId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.amount = amount;
    }

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Returns a string representation of the transfer details.
     *
     * @return A string containing transfer details.
     */
    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#,##0.00"); /* format pattern for a numeric String */
        String amountString = format.format(getAmount()); /* Formats our BigDecimal (as a String) with commas and a
                                                             decimal that has a precision of 2 */
        String detailString = "--------------------------------------------\n" +
                "Transfer Details\n" +
                "--------------------------------------------\n" +
                "Id: " + getTransferId() + "\n" +
                "From: " + getAccountFrom() + "\n" +
                "To: " + getAccountTo() + "\n" +
                "Type: " + getTransferType() + "\n" +
                "Status: " + getTransferStatus() + "\n" +
                "Amount: $" + amountString + "\n" +
                "--------------------------------------------";
        return detailString;
    }
}
