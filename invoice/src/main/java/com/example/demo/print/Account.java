package com.example.demo.print;

public class Account {
	String template;
	String inputCSV;
	Integer startInvoiceNo;
	
	Integer standerdHr;
	String month;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getStanderdHr() {
		return standerdHr;
	}
	public void setStanderdHr(Integer standerdHr) {
		this.standerdHr = standerdHr;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getInputCSV() {
		return inputCSV;
	}
	public void setInputCSV(String inputCSV) {
		this.inputCSV = inputCSV;
	}
	public Integer getStartInvoiceNo() {
		return startInvoiceNo;
	}
	public void setStartInvoiceNo(Integer startInvoiceNo) {
		this.startInvoiceNo = startInvoiceNo;
	}
	
}
