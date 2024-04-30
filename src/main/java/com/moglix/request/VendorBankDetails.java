package com.moglix.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class VendorBankDetails {

	private String vendorCode;
	private String bankCountryKey;
	private String bankKey;
	private String accountNo;
	private String accountHolder;
	private String bankName;
	private String partnerBankType;
	private String referenceDetails;
	private String bankBranch;

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getBankCountryKey() {
		return bankCountryKey;
	}

	public void setBankCountryKey(String bankCountryKey) {
		this.bankCountryKey = bankCountryKey;
	}

	public String getBankKey() {
		return bankKey;
	}

	public void setBankKey(String bankKey) {
		this.bankKey = bankKey;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPartnerBankType() {
		return partnerBankType;
	}

	public void setPartnerBankType(String partnerBankType) {
		this.partnerBankType = partnerBankType;
	}

	public String getReferenceDetails() {
		return referenceDetails;
	}

	public void setReferenceDetails(String referenceDetails) {
		this.referenceDetails = referenceDetails;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
}
