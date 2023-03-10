package com.techelevator.tenmo.model;

import java.util.Objects;

/**
 * This class represents a transfer status. We use it to deserialize transfer statuses when we request them form the
 * server.
 *
 * @author Dustin
 */
public class TransferStatus {
    private long transferStatusId;
    private String transferStatusDesc;

    public TransferStatus() {}

    /**
     * Constructs a new transfer status.
     *
     * @param transferStatusId The id number of the status.
     * @param transferStatusDesc A description or name for the status. (Currently, only "Approved" is used.)
     */
    public TransferStatus(int transferStatusId, String transferStatusDesc) {
        this.transferStatusId = transferStatusId;
        this.transferStatusDesc = transferStatusDesc;
    }

    public long getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }

    @Override
    public String toString() {
        return "Transfer Status { id = " + transferStatusId + " | description = " + transferStatusDesc + " }";
    }

    /**
     * An override of the equals() method. Used to check one status against another.
     *
     * @param obj The status being checked against.
     * @return A boolean. True if the statuses are the same. False if they are not the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == null) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TransferStatus status = (TransferStatus) obj;
        return transferStatusId == status.transferStatusId &&
                Objects.equals(transferStatusDesc, status.transferStatusDesc);
    }
}
