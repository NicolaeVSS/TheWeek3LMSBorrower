package com.ss.lms.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;


@Component
public interface BookLoanDataAccess extends CrudRepository<BookLoan, BookLoanCompositeKey> {

	//@Query("SELECT b, c FROM tbl_book_loans WHERE tbl_book_loans.cardNo =: cardNo ")
	//public Iterable<BookLoan> findByCardNo(@Param("cardNo") Integer cardNo);
	
	
	default List<BookLoan> findByCard(Integer cardNo) {
	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?useSSL=false","root","Bijon128");		
		
			PreparedStatement query = null;
			String sql = "SELECT * FROM library.tbl_book_loans WHERE cardNo  = ?";
			query = con.prepareStatement(sql);
			query.setInt(1,cardNo);
		
			ResultSet result = null;
			result = query.executeQuery();
			con.close();
			return packageResultSet(result);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return null;
	}
	
	
	default public ArrayList<BookLoan> packageResultSet(ResultSet result) throws SQLException {
		ArrayList<BookLoan> bookLoans = new ArrayList<>();
		while(result.next()) {
		BookLoan bookLoan = new BookLoan();
		bookLoan.setBookId(result.getInt("bookId"));
		bookLoan.setBranchId(result.getInt("branchId"));
		bookLoan.setCardNo(result.getInt("cardNo"));
		bookLoan.setDateOut(result.getTimestamp("dateOut"));
		bookLoan.setDueDate(result.getTimestamp("dueDate"));
		bookLoans.add(bookLoan);
		}
		return bookLoans;
	}
}
