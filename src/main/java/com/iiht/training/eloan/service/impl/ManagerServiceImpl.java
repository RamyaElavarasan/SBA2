package com.iiht.training.eloan.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.RejectDto;
import com.iiht.training.eloan.dto.SanctionDto;
import com.iiht.training.eloan.dto.SanctionOutputDto;
import com.iiht.training.eloan.exception.AlreadyFinalizedException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.exception.ManagerNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public List<LoanOutputDto> allProcessedLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RejectDto rejectLoan(Long managerId, Long loanAppId, RejectDto rejectDto) {
		// TODO Auto-generated method stub
		if (usersRepository.existsById(managerId)) {
			if (!loanRepository.existsById(loanAppId)) {
				throw new LoanNotFoundException("Loan Number #" + loanAppId + " does not exists");
			}
		} else {
			throw new ManagerNotFoundException("Manager Id#" + managerId + " does not exists");
		}
		
		if(loanRepository.getStatus(loanAppId)==1) {
			loanRepository.rejectLoan(loanAppId);
		}else if(loanRepository.getStatus(loanAppId)==-1){
			throw new AlreadyFinalizedException("Loan Number #"+loanAppId+" already rejected");
		}else if(loanRepository.getStatus(loanAppId)==2){
			throw new AlreadyFinalizedException("Loan Number #"+loanAppId+" already sanctioned");
		}
		
		return rejectDto;
	}

	@Override
	public SanctionOutputDto sanctionLoan(Long managerId, Long loanAppId, SanctionDto sanctionDto) {
		// TODO Auto-generated method stub
		if (usersRepository.existsById(managerId)) {
			if (!loanRepository.existsById(loanAppId)) {
				throw new LoanNotFoundException("Loan Number #" + loanAppId + " does not exists");
			}
		} else {
			throw new ManagerNotFoundException("Manager Id#" + managerId + " does not exists");
		}
		if(loanRepository.getStatus(loanAppId)==1) {
		
			SanctionOutputDto sanctionOutputDto=new SanctionOutputDto();
			
			double amount=sanctionDto.getLoanAmountSanctioned();
			double term=sanctionDto.getTermOfLoan();
			double TermPaymentAmount= ( amount* (1 + (0.15*(term/12))));
			double EMI = TermPaymentAmount/term;
			LocalDate paymentStartDate= LocalDate.now();
			LocalDate loanClosureDate = paymentStartDate.plusYears((long) term);
			
			
			sanctionOutputDto.setLoanAmountSanctioned(amount);
			sanctionOutputDto.setLoanClosureDate(loanClosureDate.toString());
			sanctionOutputDto.setMonthlyPayment(EMI);
			sanctionOutputDto.setPaymentStartDate(paymentStartDate.toString());
			sanctionOutputDto.setTermOfLoan(term);
			
			loanRepository.sanctionLoan(loanAppId);		
			return EMParser.sparse(sanctionInfoRepository.save(EMParser.sparse(sanctionOutputDto,managerId,loanAppId)));
		
		
		}else if(loanRepository.getStatus(loanAppId)==-1){
			throw new AlreadyFinalizedException("Loan Number #"+loanAppId+" already rejected");
		}else if(loanRepository.getStatus(loanAppId)==2){
			throw new AlreadyFinalizedException("Loan Number #"+loanAppId+" already sanctioned");
		}else
		{
			throw new InvalidDataException("Loan Number #"+loanAppId+" is not processed by the Clerk");
		}
	}
	}


