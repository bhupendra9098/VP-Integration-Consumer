package com.moglix.models;

/**
 * Created by nitesh on 10/11/17.
 */
public class GrnItem {
    private String itemNo;
    private String materialCode;
    private String materialName;
    private String plantCode;
    private String plantName;
    private Double quantity;
    private String uom;
    private String poNo;
    private String poItemNo;
    private String storageLocation;

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getPoItemNo() {
        return poItemNo;
    }

    public void setPoItemNo(String poItemNo) {
        this.poItemNo = poItemNo;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    @Override
    public String toString() {
        return "{" +
                "itemNo='" + itemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", plantCode='" + plantCode + '\'' +
                ", plantName='" + plantName + '\'' +
                ", quantity=" + quantity +
                ", uom='" + uom + '\'' +
                ", poNo='" + poNo + '\'' +
                ", poItemNo='" + poItemNo + '\'' +
                ", storageLocation='" + storageLocation + '\'' +
                '}';
    }
}
