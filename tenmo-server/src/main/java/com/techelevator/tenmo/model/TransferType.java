package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TransferType {
    @NotNull
    private int transferTypeId;
    @NotNull
    private String transferTypeDesc;

    public TransferType() {
    }

    public TransferType(int transferTypeId, String transferTypeDesc) {
        this.transferTypeId = transferTypeId;
        this.transferTypeDesc = transferTypeDesc;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == null) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TransferType type = (TransferType) obj;
        return transferTypeId == type.transferTypeId &&
                Objects.equals(transferTypeDesc, type.transferTypeDesc);
    }
}
