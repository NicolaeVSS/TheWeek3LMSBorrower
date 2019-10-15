package com.ss.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.dao.AuthorDataAccess;
import com.ss.lms.dao.BookCopyDataAccess;
import com.ss.lms.dao.BookDataAccess;
import com.ss.lms.dao.BookLoanDataAccess;
import com.ss.lms.dao.BorrowerDataAccess;
import com.ss.lms.dao.LibraryBranchDataAccess;
import com.ss.lms.dao.PublisherDataAccess;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.BookCopyCompositeKey;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;
import com.ss.lms.special.BookCopyDao;
import com.ss.lms.special.BookCopyJoin;
import com.ss.lms.special.BookloanDao;

@Component
public class BorrowerService {

	@Autowired
	private AuthorDataAccess authorDao;
	@Autowired
	private PublisherDataAccess publisherDao;
	@Autowired
	private BookDataAccess bookDao;
	@Autowired
	private LibraryBranchDataAccess libraryBranchDao;
	@Autowired
	private BorrowerDataAccess borrowerDao;
	@Autowired
	private BookLoanDataAccess bookLoanDao;
	@Autowired
	private BookCopyDataAccess bookCopyDao;
	@Autowired
	private BookloanDao daoBookloan;
	@Autowired
	private BookCopyDao daoBookCopy;
	
	
	/*************************************************
	 * 
	 * ALL CREATE AND UPDATE OPERATIONS
	 * 
	 *************************************************/

	public Author saveAuthor(Author author)
	{
		return authorDao.save(author);
	}

	public Publisher savePublisher(Publisher publisher)
	{
		return publisherDao.save(publisher);
	}
	public Book createBook(Book book)
	{
		return bookDao.save(book);
	}

	public LibraryBranch saveLibraryBranch(LibraryBranch libraryBranch)
	{
		return libraryBranchDao.save(libraryBranch);
	}

	public Borrower saveBorrower(Borrower borrower)
	{
		return borrowerDao.save(borrower);
	}
	
	public BookCopy saveBookCopy(BookCopy copies) {
		return bookCopyDao.save(copies);
	}
	
	public BookLoan saveBookLoan(BookLoan bookLoan) 
	{
		return bookLoanDao.save(bookLoan);
	}
	
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	public Optional<Author> readAuthorById(Integer authorId){
		return authorDao.findById(authorId);
	}

	public Iterable<Author> readAuthorAll(){
		return authorDao.findAll();
	}

	public Optional<Publisher> readPublisherById(Integer publisherId){
		return publisherDao.findById(publisherId);
	}

	public Iterable<Publisher> readPublisherAll(){
		return publisherDao.findAll();
	}

	public Optional<Book> readBookById(Integer bookId){
		return bookDao.findById(bookId);
	}

	public Iterable<Book> readBookAll(){
		return bookDao.findAll();
	}

	public Optional<LibraryBranch> readLibraryBranchById(Integer branchId){
		return libraryBranchDao.findById(branchId);
	}

	public Iterable<LibraryBranch> readAllLibraryBranch(){
		return libraryBranchDao.findAll();
	}
	
	public Optional<Borrower> readBorrowerById(Integer cardNo){
		return borrowerDao.findById(cardNo);
	}

	public Iterable<Borrower> readBorrowerAll(){
		return borrowerDao.findAll();
	}

	public Optional<BookLoan> readBookLoanById(BookLoanCompositeKey bookLoanCompositeKey){
		return bookLoanDao.findById(bookLoanCompositeKey);
	}

	public Iterable<BookLoan> readAllBookLoan()
	{
		return bookLoanDao.findAll();
	}
	public List<BookLoan> readAllByCardNo(Integer cardNo){
		return daoBookloan.findByCardNo(cardNo);
	}
	
	public Optional<BookCopy> readBookCopyByBranchId(BookCopyCompositeKey bookCopyCompositeKey){
		return bookCopyDao.findById(bookCopyCompositeKey);
	}
	
	public Iterable<BookCopy> readAllCopy(){
		return bookCopyDao.findAll();
	}
	
	public List<BookCopyJoin> readAllCopyByBranch(Integer branchId){
		return daoBookCopy.readAllCopyByBranch(branchId);
	}
	/*************************************************
	 * 
	 * ALL DELETE OPERATIONS
	 * 
	 *************************************************/

	
	public void deleteBookloan(Integer bookId, Integer branchId, Integer cardNo) {
		bookLoanDao.deleteById(new BookLoanCompositeKey(bookId,branchId,cardNo));
	}

}
