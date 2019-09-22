package com.jasper.report;

public class VoucherData {

	@Override
	public String toString() {
		return "VoucherData [vDate=" + vDate + ", vAmountText=" + vAmountText + ", vAmountDigit=" + vAmountDigit
				+ ", vCreditOrDebit=" + vCreditOrDebit + ", vOnAccountOf=" + vOnAccountOf + "]";
	}
	private String vDate;
	private String vAmountText;
	private Integer vAmountDigit;
	private String vCreditOrDebit;
	private String vOnAccountOf;
	
	
	public String getvDate() {
		return vDate;
	}
	public void setvDate(String vDate) {
		this.vDate = vDate;
	}
	public String getvAmountText() {
		return vAmountText;
	}
	public void setvAmountText(String vAmountText) {
		this.vAmountText = vAmountText;
	}
	public Integer getvAmountDigit() {
		return vAmountDigit;
	}
	public void setvAmountDigit(Integer vAmountDigit) {
		this.vAmountDigit = vAmountDigit;
	}
	public String getvCreditOrDebit() {
		return vCreditOrDebit;
	}
	public void setvCreditOrDebit(String vCreditOrDebit) {
		this.vCreditOrDebit = vCreditOrDebit;
	}
	public String getvOnAccountOf() {
		return vOnAccountOf;
	}
	public void setvOnAccountOf(String vOnAccountOf) {
		this.vOnAccountOf = vOnAccountOf;
	}
	
	
	
	
	
}
