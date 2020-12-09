package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Transactional
	@Override
	public UserDto register(UserDto userDto) {
		// TODO Auto-generated method stub
		
		if(userDto!=null) {
			if(usersRepository.existsByEmail(userDto.getEmail())) {
				throw new InvalidDataException("Customer#"+userDto.getEmail()+" already exists");
			}
			userDto = EMParser.uparse(usersRepository.save(EMParser.uparse(userDto)));
		}
		return userDto;
	}
	
	@Transactional
    @Override
	public LoanOutputDto applyLoan(Long customerId, LoanDto loanDto) {
	
		// TODO Auto-generated method stub
		
		LoanOutputDto loanOutputDto=new LoanOutputDto();
		if (loanDto!=null) {
			if(usersRepository.existsById(customerId)) {
				loanOutputDto=EMParser.loanParse(loanRepository.save(EMParser.loanParse(loanDto)));
			}			
		}
		return loanOutputDto;
	}



	@Override
	public LoanOutputDto getStatus(Long loanAppId) {
		// TODO Auto-generated method stub
		if(!loanRepository.existsById(loanAppId)){
			throw new LoanNotFoundException("Loan#"+loanAppId+" does not exists");
		}
		
		return EMParser.loanParse(loanRepository.findById(loanAppId).get());
	}

	@Override
	public List<LoanOutputDto> getStatusAll(Long customerId) {
		// TODO Auto-generated method stub
		return loanRepository.findAllByCustomerId((customerId)).stream().map(e -> EMParser.loanParse(e)).collect(Collectors.toList());
	}
	
}