package com.jasper.report;

public class ReceiptData {
	private String slNo;
	private Integer amountDigit;
	private String amountText;
	private String aptNo;
	private String paymentType;
	private String paymentFor;
	private String paymentDate;
	private String chequeNo;
	
	
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public Integer getAmountDigit() {
		return amountDigit;
	}
	public void setAmountDigit(Integer amountDigit) {
		this.amountDigit = amountDigit;
	}
	public String getAmountText() {
		return amountText;
	}
	public void setAmountText(String amountText) {
		this.amountText = amountText;
	}
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentFor() {
		return paymentFor;
	}
	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	@Override
	public String toString() {
		return "ReceiptData [slNo=" + slNo + ", amountDigit=" + amountDigit + ", amountText=" + amountText + ", aptNo="
				+ aptNo + ", paymentType=" + paymentType + ", paymentFor=" + paymentFor + ", paymentDate=" + paymentDate
				+ ", chequeNo=" + chequeNo + "]";
	}
	
	

}
