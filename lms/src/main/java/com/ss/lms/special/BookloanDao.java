package com.ss.lms.special;


import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.ss.lms.entity.BookLoan;




@Component
public class BookloanDao implements BookLoanBase{
	JdbcTemplate mysqlTemplate;
	
	@Autowired
	public BookloanDao(DataSource source) {
		mysqlTemplate = new JdbcTemplate(source);
	}

	public List<BookLoan> findByCardNo(Integer cardNo){
		return mysqlTemplate.query("SELECT * FROM library.tbl_book_loans WHERE cardNo  = ?", new Object[] {cardNo}, new BookloandMapper());	
	}

}
