package book.rental.system;

public class PointPaid extends AbstractEvent {

    private Long id;
    private Long paymentID;
    private Integer customerID;
    private Integer price;
    private Integer bookID;
    private Integer rentalID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPaymentId() {
        return paymentID;
    }

    public void setPaymentId(Long paymentID) {
        this.paymentID = paymentID;
    }
    public Integer getCustomerid() {
        return customerID;
    }

    public void setCustomerid(Integer customerID) {
        this.customerID = customerID;
    }
    public Integer getPoint() {
        return price;
    }

    public void setPoint(Integer price) {
        this.price = price;
    }
    public Integer getBookid() {
        return bookID;
    }

    public void setBookid(Integer bookID) {
        this.bookID = bookID;
    }
    public Integer getRentalId() {
        return rentalID;
    }

    public void setRentalId(Integer rentalID) {
        this.rentalID = rentalID;
    }
}