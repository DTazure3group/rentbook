package book.rental.system;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Rental_table")
public class Rental {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long rentalId;
    private Long bookId;
    private String bookName;
    private Long price;
    private String startDate;
    private String returnDate;
    private Long customerId;
    private String customerPhoneNo;
    private String rentStatus;

    @PostPersist
    public void onPostPersist() throws Exception{

        System.out.println(">>>>>>>>>>>>>>>>>>>   checkPoint  CustomerID : " + this.customerId);
        System.out.println(">>>>>>>>>>>>>>>>>>>   checkPoint  BookId : " + this.bookId);
        if(RentalApplication.applicationContext.getBean(book.rental.system.external.PointService.class)
        .checkPoint(this.customerId, this.price)){
            //  서적 대여 시 상태변경 후 Publish 
            BookRented bookRented = new BookRented();
            BeanUtils.copyProperties(this, bookRented);
            bookRented.publishAfterCommit();
            System.out.println(">>>>>>>>>>>>>>>>>>>   checkPoint  bookRented.getBookId : " +bookRented.getBookId());
        }
        else{
            System.out.println(">>>>>>>>>>>>>>>>>>>   Out of Customer point Exception Raised.");
            throw new Exception(" Out of Customer point Exception Raised.");
        }

    }

    @PostUpdate 
    public void onPostUpdate(){

        if("RETURN".equals(this.rentStatus)){           // 반납 처리 Publish
            BookReturned bookReturned = new BookReturned();
            BeanUtils.copyProperties(this, bookReturned);
            bookReturned.publishAfterCommit();

        } else if("DELAY".equals(this.rentStatus)){     // 반납지연 Publish
            ReturnDelayed returnDelayed = new ReturnDelayed();
            BeanUtils.copyProperties(this, returnDelayed);
            returnDelayed.publishAfterCommit();
        }
    }    

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }
    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }




}