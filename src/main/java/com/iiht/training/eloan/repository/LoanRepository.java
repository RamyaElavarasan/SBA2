package com.iiht.training.eloan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

	
	//LoanOutputDto apply(Long customerId, LoanDto loanDto);

	LoanOutputDto save(LoanOutputDto parse);
	List<Loan> findAllByCustomerId(Long customerId);
	
	@Query(value="select l.status from Loan l where l.id=:id",nativeQuery=true)
	Integer getStatus(@Param("id") Long id);
	 
	@Modifying
	@Transactional
	@Query(value="update Loan l set l.status=1,l.remark='Processed' where l.id=:id",nativeQuery=true)
	void updateLoan(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query(value="update Loan l set l.status=-1,l.remark='Rejected' where l.id=:id",nativeQuery=true)	
	void rejectLoan(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query(value="update Loan l set l.status=2,l.remark='Sanctioned' where l.id=:id",nativeQuery=true)	
	void sanctionLoan(@Param("id") Long id);
}
