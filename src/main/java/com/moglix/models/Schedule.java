package com.moglix.models;

import java.util.List;

public class Schedule {

	private String agreementNo;
	private List<SchedulingList> schedulingList;

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public List<SchedulingList> getSchedulingList() {
		return schedulingList;
	}

	public void setSchedulingList(List<SchedulingList> schedulingList) {
		this.schedulingList = schedulingList;
	}

}
