package com.ss.lms.special;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ss.lms.entity.BookCopy;

public class BookCopyMapper implements RowMapper<BookCopyJoin> {

	@Override
	public BookCopyJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BookCopyJoin bookCopy = new BookCopyJoin();
		bookCopy.setBookId(rs.getInt("bookId"));
		bookCopy.setBranchId(rs.getInt("branchId"));
		bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
		bookCopy.setTitle(rs.getString("title"));
		return bookCopy;
	}

}
