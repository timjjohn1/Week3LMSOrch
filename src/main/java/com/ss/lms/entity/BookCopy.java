package com.ss.lms.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(BookCopyCompositeKey.class)
@Table(name = "tbl_book_copies")
public class BookCopy implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7441409147455698231L;
    //Book book;
    @Id
    private Integer bookId;
    @Id
    private Integer branchId;
    @Column(name = "noOfCopies")
    private Integer noOfCopies;
    
    public BookCopy() {
        super();
    }
    public BookCopy(Integer bookId, Integer branchId, Integer noOfCopies) {
        super();
        this.bookId = bookId;
        this.branchId = branchId;
        this.noOfCopies = noOfCopies;
    }
    /**
     * @return the bookId
     */
    public Integer getBookId() {
        return bookId;
    }
    /**
     * @param bookId the bookId to set
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    /**
     * @return the branchId
     */
    public Integer getBranchId() {
        return branchId;
    }
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }
    /**
     * @return the noOfCopies
     */
    public Integer getNoOfCopies() {
        return noOfCopies;
    }
    /**
     * @param noOfCopies the noOfCopies to set
     */
    public void setNoOfCopies(Integer noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
        result = prime * result + ((noOfCopies == null) ? 0 : noOfCopies.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookCopy other = (BookCopy) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        if (branchId == null) {
            if (other.branchId != null)
                return false;
        } else if (!branchId.equals(other.branchId))
            return false;
        if (noOfCopies == null) {
            if (other.noOfCopies != null)
                return false;
        } else if (!noOfCopies.equals(other.noOfCopies))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "BookCopy [bookId=" + bookId + ", branchId=" + branchId + ", noOfCopies=" + noOfCopies + "]";
    }
    
    
    
    
    
}