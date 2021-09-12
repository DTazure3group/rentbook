package book.rental.system;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Book_table")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bookId;
    private String bookName;
    private Long price;
    private Boolean available;

    @PostPersist
    public void onPostPersist(){
        BookRegistered bookRegistered = new BookRegistered();
        BeanUtils.copyProperties(this, bookRegistered);
        bookRegistered.publishAfterCommit();

        BookstatusChanged bookstatusChanged = new BookstatusChanged();
        BeanUtils.copyProperties(this, bookstatusChanged);
        bookstatusChanged.publishAfterCommit();

    }
    @PostRemove
    public void onPostRemove(){
        BookDeleted bookDeleted = new BookDeleted();
        BeanUtils.copyProperties(this, bookDeleted);
        bookDeleted.publishAfterCommit();

    }

    public Long getId() {
        return bookId;
    }

    public void setId(Long bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }




}