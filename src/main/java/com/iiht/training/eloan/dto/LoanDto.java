package com.iiht.training.eloan.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoanDto {

	@NotNull(message="Loan Name is not Null")
    @Size(min=3,max=100,message = "Loan Name must be 3 to 100 characters length")
	private String loanName;
	@NotNull(message="Loan Amount is not null")
	@DecimalMin("1.0")
	private Double loanAmount;
	private String loanApplicationDate;
	private String businessStructure;
	private String billingIndicator;
	private String taxIndicator;
	private int status;
	private String remark;
	
	public String getLoanName() {
		return loanName;
	}
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanApplicationDate() {
		return loanApplicationDate;
	}
	public void setLoanApplicationDate(String loanApplicationDate) {
		this.loanApplicationDate = loanApplicationDate;
	}
	public String getBusinessStructure() {
		return businessStructure;
	}
	public void setBusinessStructure(String businessStructure) {
		this.businessStructure = businessStructure;
	}
	public String getBillingIndicator() {
		return billingIndicator;
	}
	public void setBillingIndicator(String billingIndicator) {
		this.billingIndicator = billingIndicator;
	}
	public String getTaxIndicator() {
		return taxIndicator;
	}
	public void setTaxIndicator(String taxIndicator) {
		this.taxIndicator = taxIndicator;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
