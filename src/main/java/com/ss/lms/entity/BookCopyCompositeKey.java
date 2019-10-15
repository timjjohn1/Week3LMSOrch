package com.ss.lms.entity;
import java.io.Serializable;

public class BookCopyCompositeKey implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3189581310335082008L;
    private Integer bookId;
    private Integer branchId;
    
    public BookCopyCompositeKey() {
    }
    
    
    public BookCopyCompositeKey(Integer bookId, Integer branchId) {
        this.bookId = bookId;
        this.branchId = branchId;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
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
        BookCopyCompositeKey other = (BookCopyCompositeKey) obj;
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
        return true;
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
    @Override
    public String toString() {
        return "BookCopyId [bookId=" + bookId + ", branchId=" + branchId + "]";
    }
}