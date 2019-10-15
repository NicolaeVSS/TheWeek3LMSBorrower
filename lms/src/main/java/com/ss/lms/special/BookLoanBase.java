package com.ss.lms.special;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.entity.BookLoan;

public interface BookLoanBase {

	public List<BookLoan> findByCardNo(Integer cardNo);
	
}
