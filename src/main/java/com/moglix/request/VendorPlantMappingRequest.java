package com.moglix.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class VendorPlantMappingRequest {

	@NotNull
	@NotBlank
	private String vendorCode;
	@NotNull
	@NotBlank
	private String plantCode;

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	
}
