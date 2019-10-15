package com.ss.lms.dao;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;

@Component
public interface BookLoanDataAccess extends CrudRepository<BookLoan, BookLoanCompositeKey> 
{
	@Query("SELECT * FROM library.tbl_book_loans WHERE cardNo  = ?")
	public Iterable<BookLoan> findByCardNo(Integer cardNo);
}
