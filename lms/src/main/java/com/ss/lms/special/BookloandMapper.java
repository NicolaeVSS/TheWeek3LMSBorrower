package com.ss.lms.special;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ss.lms.entity.BookLoan;

public class BookloandMapper implements RowMapper<BookLoan>{

	@Override
	public BookLoan mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BookLoan bookLoan = new BookLoan();
		bookLoan.setBookId(rs.getInt("bookId"));
		bookLoan.setBranchId(rs.getInt("branchId"));
		bookLoan.setCardNo(rs.getInt("cardNo"));
		bookLoan.setDateOut(rs.getTimestamp("dateOut"));
		bookLoan.setDueDate(rs.getTimestamp("dueDate"));
		return bookLoan;
	}

}
