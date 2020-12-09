package com.iiht.training.eloan.service.impl;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.dto.SanctionOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.SanctionInfo;
import com.iiht.training.eloan.entity.Users;

public class EMParser {
	
	public static Users uparse(UserDto source) {
		Users target = new Users();
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setMobile(source.getMobile());
		target.setRole(source.getRole());
		return target;
	}
	
	public static UserDto uparse(Users source) {
		UserDto target = new UserDto();
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setMobile(source.getMobile());
		target.setRole(source.getRole());
		return target;
	}
	
public static LoanOutputDto loanParse(Loan loanInf) {
		
		LoanDto loanDto = new LoanDto();
		loanDto.setLoanAmount(loanInf.getLoanAmount());
		loanDto.setLoanName(loanInf.getLoanName());
		loanDto.setBillingIndicator(loanInf.getBillingIndicator());
		loanDto.setBusinessStructure(loanInf.getBusinessStructure());
		loanDto.setTaxIndicator(loanInf.getTaxIndicator());
		
		
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(loanInf.getCustomerId());
		loanOutputDto.setLoanAppId(loanInf.getId());
		loanOutputDto.setLoanDto(loanDto);
		loanOutputDto.setStatus(loanInf.getStatus());
		loanOutputDto.setRemark(loanInf.getRemark());
		return loanOutputDto;
	}
	
	public static Loan loanParse(LoanDto loanDto) {
		Loan loanInf = new Loan();
		//loanInf.setCustomerId(loanDto.
		loanInf.setLoanName(loanDto.getLoanName());
		loanInf.setLoanAmount(loanDto.getLoanAmount());
		loanInf.setLoanApplicationDate(loanDto.getLoanApplicationDate());
		loanInf.setBusinessStructure(loanDto.getBusinessStructure());
		loanInf.setBillingIndicator(loanDto.getBillingIndicator());
		loanInf.setTaxIndicator(loanDto.getTaxIndicator());
		loanInf.setStatus(0);
		loanInf.setRemark("Applied");
		return loanInf;
	}
     
	public static ProcessingDto Pparse(ProcessingInfo source) {
		ProcessingDto target = new ProcessingDto();
		target.setAcresOfLand(source.getAcresOfLand());
		target.setAddressOfProperty(source.getAddressOfProperty());
		target.setAppraisedBy(source.getAppraisedBy());
		target.setLandValue(source.getLandValue());
		target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
		target.setValuationDate(source.getValuationDate());
		return target;
	}
	
	public static ProcessingInfo Pparse(ProcessingDto source,Long clerkId,Long loanAppId) {
		ProcessingInfo target = new ProcessingInfo();
		target.setAcresOfLand(source.getAcresOfLand());
		target.setAddressOfProperty(source.getAddressOfProperty());
		target.setAppraisedBy(source.getAppraisedBy());
		target.setLandValue(source.getLandValue());
		target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
		target.setValuationDate(source.getValuationDate());
		target.setLoanAppId(loanAppId);
		target.setLoanClerkId(clerkId);
		return target;
	}

	public static SanctionInfo sparse(SanctionOutputDto source, Long managerId, Long loanAppId) {
		SanctionInfo target=new SanctionInfo();
		target.setLoanAmountSanctioned(source.getLoanAmountSanctioned());
		target.setLoanAppId(loanAppId);
		target.setLoanClosureDate(source.getLoanClosureDate());
		target.setManagerId(managerId);
		target.setMonthlyPayment(source.getMonthlyPayment());
		target.setPaymentStartDate(source.getPaymentStartDate());
		target.setTermOfLoan(source.getTermOfLoan());
		return target;
	}
	
	public static SanctionOutputDto sparse(SanctionInfo source) {
		SanctionOutputDto target=new SanctionOutputDto();
		target.setLoanAmountSanctioned(source.getLoanAmountSanctioned());
		target.setLoanClosureDate(source.getLoanClosureDate());
		target.setMonthlyPayment(source.getMonthlyPayment());
		target.setPaymentStartDate(source.getPaymentStartDate());
		target.setTermOfLoan(source.getTermOfLoan());
		return target;
	}

}


