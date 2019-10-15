package com.ss.lms.special;

import java.util.List;

import com.ss.lms.entity.BookCopy;

public interface BookCopyBase {

	public List<BookCopyJoin> readAllCopyByBranch(Integer branchId);
}
