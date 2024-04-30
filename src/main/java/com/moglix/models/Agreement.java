package com.moglix.models;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Agreement {

	@NotNull
	@NotBlank
	private String agreementNo; // no
	private String agreementType; //sa or po
	private String docType; //type of po
	private String purchaseGroup;
	private String pmtTerms; // to do
	private String pmtTermDesc; // to do
	private String startDate; // yyyyMMdd
	private String endDate; // yyyyMMdd
	private String agreementStatus; // OPEN
	private String vendorCode; // 10 characters field 
	private String vendorName; // name
	private String companyCode; // done
	private String companyName; // done
	private String shippingPlantCode; // done
	private String shippingPlantName; // done
	private String currency;
	private String releaseStatus;
	private List<AgreementItem> items;

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getPurchaseGroup() {
		return purchaseGroup;
	}

	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}

	public String getPmtTerms() {
		return pmtTerms;
	}

	public void setPmtTerms(String pmtTerms) {
		this.pmtTerms = pmtTerms;
	}

	public String getPmtTermDesc() {
		return pmtTermDesc;
	}

	public void setPmtTermDesc(String pmtTermDesc) {
		this.pmtTermDesc = pmtTermDesc;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(String agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShippingPlantCode() {
		return shippingPlantCode;
	}

	public void setShippingPlantCode(String shippingPlantCode) {
		this.shippingPlantCode = shippingPlantCode;
	}

	public String getShippingPlantName() {
		return shippingPlantName;
	}

	public void setShippingPlantName(String shippingPlantName) {
		this.shippingPlantName = shippingPlantName;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public List<AgreementItem> getItems() {
		return items;
	}

	public void setItems(List<AgreementItem> items) {
		this.items = items;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
