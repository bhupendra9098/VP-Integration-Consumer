package com.moglix.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class VendorRequest {

	@NotNull
	@NotBlank
	private String vendorCode;
	private String name;
	private String name2;
	private String email;
	private String city;
	private String district;
	private String postalCode;
	private String region;
	private String street;
	private String phone;
	private boolean isBlocked;
	private boolean isDeleted;
	private String gstn;
	private String panNo;
//	private String businessAddress; // to be removed, not maintained in SAP
//	private String billingAddress; // to be removed, not maintained in SAP
//	private String corAddress; // to be removed, not maintained in SAP
	private Object bankDetails;
	private Object mappedPlantDetails;

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getName() {
		return name;
	}

	public void setName1(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

//	public String getBusinessAddress() {
//		return businessAddress;
//	}
//
//	public void setBusinessAddress(String businessAddress) {
//		this.businessAddress = businessAddress;
//	}
//
//	public String getBillingAddress() {
//		return billingAddress;
//	}
//
//	public void setBillingAddress(String billingAddress) {
//		this.billingAddress = billingAddress;
//	}
//
//	public String getCorAddress() {
//		return corAddress;
//	}
//
//	public void setCorAddress(String corAddress) {
//		this.corAddress = corAddress;
//	}

	public Object getMappedPlantDetails() {
		return mappedPlantDetails;
	}

	public void setMappedPlantDetails(Object mappedPlantDetails) {
		this.mappedPlantDetails = mappedPlantDetails;
	}

	public Object getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(Object bankDetails) {
		this.bankDetails = bankDetails;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
}
