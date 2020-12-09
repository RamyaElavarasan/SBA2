package com.iiht.training.eloan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDto registerClerk(UserDto userDto) {
		// TODO Auto-generated method stub
		if(userDto!=null) {
			if(usersRepository.existsByEmail(userDto.getEmail())) {
				throw new InvalidDataException("Customer#"+userDto.getEmail()+" already exists");
			}
			userDto = EMParser.uparse(usersRepository.save(EMParser.uparse(userDto)));
		}
		return userDto;
	}

	@Override
	public UserDto registerManager(UserDto userDto) {
		// TODO Auto-generated method stub
		if(userDto!=null) {
			if(usersRepository.existsByEmail(userDto.getEmail())) {
				throw new InvalidDataException("Customer#"+userDto.getEmail()+" already exists");
			}
			userDto = EMParser.uparse(usersRepository.save(EMParser.uparse(userDto)));
		}
		return userDto;
	}

	@Override
	public List<UserDto> getAllManagers(String role) {
		// TODO Auto-generated method stub
		return usersRepository.findByRole("Clerk").stream().map(e->EMParser.uparse(e)).collect(Collectors.toList());
	}

	@Override
	public List<UserDto> getAllClerks(String role) {
		// TODO Auto-generated method stub
		return usersRepository.findByRole("Manager").stream().map(e->EMParser.uparse(e)).collect(Collectors.toList());
	}
	
	
}
