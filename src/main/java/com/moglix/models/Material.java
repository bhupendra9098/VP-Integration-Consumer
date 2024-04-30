package com.moglix.models;

public class Material {

    private String materialCode;
    private String materialName;
    private String materialType;
    private String hsnCode;
    private String price;
    private String uom;
    private Long createdOn= System.currentTimeMillis();
    private Long updatedOn= System.currentTimeMillis();

    public String getMaterialCode() { return materialCode; }

    public void setMaterialCode(String materialCode) { this.materialCode = materialCode; }

    public String getMaterialName() { return materialName; }

    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public String getMaterialType() { return materialType; }

    public void setMaterialType(String materialType) { this.materialType = materialType; }

    public String getHsnCode() { return hsnCode; }

    public void setHsnCode(String hsnCode) { this.hsnCode = hsnCode; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getUom() { return uom; }

    public void setUom(String uom) { this.uom = uom; }

    public Long getCreatedOn() { return createdOn; }

    public void setCreatedOn(Long createdOn) { this.createdOn = createdOn; }

    public Long getUpdatedOn() { return updatedOn; }

    public void setUpdatedOn(Long updatedOn) { this.updatedOn = updatedOn; }
}