package com.moglix.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Created by nitesh on 10/11/17.
 */
public class Grn {
	@NotNull
	@NotBlank
	private String grnNo;
	private String grnYear;
	private String geNo;
	private String purchasingGroup;
	private String companyCode;
	private Long documentDate;
	private Long postingDate;
	private String vendorCode;
	private String invoiceNo;
	private Long invoiceDate;
	private Double invoiceValue;
	private String deliveryNote;
	private String billOfLading;
	private String text;
	private String deletionIndicator;
	private List<GrnItem> items;

	private String docNo;
	private String oldDocNo;
	private Long oldPostingDate;
	private String movementType;


	public String getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public String getGrnYear() {
		return grnYear;
	}

	public void setGrnYear(String grnYear) {
		this.grnYear = grnYear;
	}

	public String getGeNo() {
		return geNo;
	}

	public void setGeNo(String geNo) {
		this.geNo = geNo;
	}

	public String getPurchasingGroup() {
		return purchasingGroup;
	}

	public void setPurchasingGroup(String purchasingGroup) {
		this.purchasingGroup = purchasingGroup;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Long getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Long documentDate) {
		this.documentDate = documentDate;
	}

	public Long getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Long postingDate) {
		this.postingDate = postingDate;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Long getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(Double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public String getBillOfLading() {
		return billOfLading;
	}

	public void setBillOfLading(String billOfLading) {
		this.billOfLading = billOfLading;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDeletionIndicator() {
		return deletionIndicator;
	}

	public void setDeletionIndicator(String deletionIndicator) {
		this.deletionIndicator = deletionIndicator;
	}

	public List<GrnItem> getItems() {
		return items;
	}

	public void setItems(List<GrnItem> items) {
		this.items = items;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getOldDocNo() {
		return oldDocNo;
	}

	public void setOldDocNo(String oldDocNo) {
		this.oldDocNo = oldDocNo;
	}

	public Long getOldPostingDate() {
		return oldPostingDate;
	}

	public void setOldPostingDate(Long oldPostingDate) {
		this.oldPostingDate = oldPostingDate;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

}