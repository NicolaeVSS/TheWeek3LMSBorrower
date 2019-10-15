package com.ss.lms.special;

import java.io.Serializable;

import javax.persistence.Id;

import com.ss.lms.entity.BookCopy;

public class BookCopyJoin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891799242436187457L;

	private Integer bookId;
	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	private Integer branchId;
	private Integer noOfCopies;
	private String title;
	
	public BookCopyJoin() {
	}
	
	public BookCopyJoin(Integer bookId, Integer branchId, Integer noOfCopies, String title) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.noOfCopies = noOfCopies;
		this.title = title;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((noOfCopies == null) ? 0 : noOfCopies.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		BookCopyJoin other = (BookCopyJoin) obj;
		if (bookId == null)
		{
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (branchId == null)
		{
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (noOfCopies == null)
		{
			if (other.noOfCopies != null)
				return false;
		} else if (!noOfCopies.equals(other.noOfCopies))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "BookCopy [branchId=" + branchId + ", Title=" + title + "]";
	}
	
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
