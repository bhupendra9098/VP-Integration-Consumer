package com.moglix.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class VendorDataRequest {

	@NotNull
	@NotBlank
	private String vendorCode;
	private String name;
	private String loevm;
	private String sperq;
	private boolean isDeleted;

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoevm() {
		return loevm;
	}

	public void setLoevm(String loevm) {
		this.loevm = loevm;
	}

	public String getSperq() {
		return sperq;
	}

	public void setSperq(String sperq) {
		this.sperq = sperq;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setiSDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
