package com.ss.lms.special;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class BookCopyDao implements BookCopyBase{
	JdbcTemplate mysqlTemplate;
	
	@Autowired
	public BookCopyDao(DataSource source) {
		mysqlTemplate = new JdbcTemplate(source);
	}
	
	
	@Override
	public List<BookCopyJoin> readAllCopyByBranch(Integer branchId) {
		return mysqlTemplate.query("SELECT library.tbl_book.bookId, library.tbl_book_copies.branchId, library.tbl_book.title,library.tbl_book_copies.noOfCopies  "
				+"From library.tbl_book_copies INNER JOIN library.tbl_book ON "
				+ "library.tbl_book.bookId = library.tbl_book_copies.bookId where branchId = ? "
				+ "and noOfCopies > 0",
				new Object[] {branchId}, new BookCopyMapper());	

	}

}
