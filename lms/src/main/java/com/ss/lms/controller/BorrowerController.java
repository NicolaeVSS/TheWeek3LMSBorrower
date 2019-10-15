package com.ss.lms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.special.*;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.BookCopyCompositeKey;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.service.BorrowerService;

@RestController
@RequestMapping(value = "/lms/borrower")
public class BorrowerController {

	@Autowired
	BorrowerService borrow;
	
	/************************************************
	 *												* 
	 *           CREATE OPERATION					*
	 * 												*
	 ************************************************/
	
	@PostMapping(path = "/bookloan", produces = "application/json", consumes="application/json")
	public ResponseEntity<BookLoan> createBookLoan(@RequestBody BookLoan bookloan){
		System.out.println("i made it");
		if(bookloan.getCardNo() == null || bookloan.getBookId() == null || bookloan.getBranchId() == null || 
				bookloan.getDateOut() == null || bookloan.getDueDate() == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		}
		BookLoanCompositeKey loanKey = new BookLoanCompositeKey(bookloan.getBookId(), bookloan.getBranchId(), bookloan.getCardNo());
		if(borrow.readBookLoanById(loanKey).isPresent()){
//double check
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		}
		BookCopyCompositeKey bookCopyCompositeKey = new BookCopyCompositeKey(bookloan.getBookId(), bookloan.getBranchId());
		if(!borrow.readBookCopyByBranchId(bookCopyCompositeKey).isPresent())
			{
//double check
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		}
		int numOfCopies = borrow.readBookCopyByBranchId(bookCopyCompositeKey).get().getNoOfCopies();
		if(numOfCopies < 1) {
//double check
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		}
			
		BookCopy bookCopy = new BookCopy(bookloan.getBookId(), bookloan.getBranchId(), numOfCopies - 1);
		borrow.saveBookCopy(bookCopy);
		
		return new ResponseEntity<BookLoan>(borrow.saveBookLoan(bookloan),HttpStatus.CREATED);
			
	}
	
	
	/************************************************
	 *												* 
	 *           Remove OPERATION					*
	 * 												*
	 ************************************************/
	
	
	
	@DeleteMapping(value = "/bookloan/{cardNo}/branch/{branchId}/bookId/{bookId}")
	public ResponseEntity<HttpStatus> deleteBorrower(@PathVariable Integer cardNo, @PathVariable Integer branchId,@PathVariable Integer bookId)
	{
		BookLoanCompositeKey loanKey = new BookLoanCompositeKey(bookId, branchId, cardNo);
		if(!borrow.readBookLoanById(loanKey).isPresent())
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		
		BookCopyCompositeKey bookCopyCompositeKey = new BookCopyCompositeKey(bookId, branchId);
		int numOfCopies = borrow.readBookCopyByBranchId(bookCopyCompositeKey).get().getNoOfCopies();
		BookCopy bookCopy = new BookCopy(bookId, branchId, numOfCopies + 1);
		borrow.saveBookCopy(bookCopy);
		
		borrow.deleteBookloan(bookId, branchId, cardNo);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		
	}
	
	
	
	/************************************************
	 *												* 
	 *           Read OPERATION						*
	 * 												*
	 ************************************************/
	
	
	@GetMapping(value = "/bookloan/{cardNo}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<BookLoan>> readBookLoanByAllId(@PathVariable("cardNo") Integer cardNo)
	{
		
		AnnotationConfigApplicationContext text = new AnnotationConfigApplicationContext(BorrowerConfig.class);
		
		BookLoanBase blb = text.getBean(BookLoanBase.class);
		
		
		ArrayList<BookLoan> result;
		result = (ArrayList<BookLoan>) blb.findByCardNo(cardNo);

				
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			text.close();
			return new ResponseEntity<Iterable<BookLoan>>(HttpStatus.NOT_FOUND);
		}
		else
		{
			text.close();
			return new ResponseEntity<Iterable<BookLoan>>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/branch", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<LibraryBranch>> readAllLibraryBranch()
	{
		Iterable<LibraryBranch> result = borrow.readAllLibraryBranch();
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<LibraryBranch>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<LibraryBranch>>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/BookCopy/{branchId}", produces = "application/json")
	public ResponseEntity<Iterable<BookCopyJoin>> readAllBookCopies(@PathVariable Integer branchId){
		
		AnnotationConfigApplicationContext text = new AnnotationConfigApplicationContext(BorrowerConfig.class);
		BookCopyBase bcb = text.getBean(BookCopyBase.class);
		
		ArrayList<BookCopyJoin> result;
		result = (ArrayList<BookCopyJoin>) bcb.readAllCopyByBranch(branchId); 

		if(!result.iterator().hasNext()) 
		{
			text.close();
			return new ResponseEntity<Iterable<BookCopyJoin>>(HttpStatus.NOT_FOUND);
		}
		else
		{
			text.close();
			return new ResponseEntity<Iterable<BookCopyJoin>>(result, HttpStatus.OK);
		}
	}
		
	

	
}
