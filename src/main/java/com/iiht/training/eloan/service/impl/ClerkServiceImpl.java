package com.iiht.training.eloan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.exception.AlreadyProcessedException;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public List<LoanOutputDto> allAppliedLoans() {
		// TODO Auto-generated method stub
		 List<LoanOutputDto> u1 = null;
		 u1 = loanRepository.findAll().stream().map(e -> EMParser.loanParse(e)).collect(Collectors.toList());
		 for(int i =0;i<=u1.size();i++) {

				if(!((u1.get(i)).getRemark().equalsIgnoreCase("Applied"))) {
					u1.remove(i);
				}
				}
		 return u1;
	}

	@Override
	public ProcessingDto processLoan(Long clerkId, Long loanAppId, ProcessingDto processingDto) {
		// TODO Auto-generated method stub
		if (usersRepository.existsById(clerkId)) {
			if (!loanRepository.existsById(loanAppId)) {
				throw new LoanNotFoundException("Loan Id # " + loanAppId + " doesnot exists");
			}
		} else {
			throw new ClerkNotFoundException("Clerk Id # " + clerkId + " doesnot exists");
		}
		
		Integer status=loanRepository.getStatus(loanAppId);
		
		if(status==0)
		{
			loanRepository.updateLoan(loanAppId);
			processingDto=EMParser.Pparse(pProcessingInfoRepository.save(EMParser.Pparse(processingDto,clerkId,loanAppId)));
		}
		else {
			throw new AlreadyProcessedException("Loan Id # "+loanAppId+" already processed");
		}
		return processingDto;
	}

}
