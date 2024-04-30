package com.moglix.models;

public class StorageLocation {

    private String plantCode;
    private String slCode;
    private String description;
    private String material;
    private String materialName;

    public String getPlantCode() { return plantCode; }

    public void setPlantCode(String plantCode) { this.plantCode = plantCode; }

    public String getSlCode() { return slCode; }

    public void setSlCode(String slCode) { this.slCode = slCode; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getMaterial() { return material; }

    public void setMaterial(String material) { this.material = material; }

    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public String getMaterialName() { return materialName; }
}
