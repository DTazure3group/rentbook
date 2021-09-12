package book.rental.system;

public class PointPaid extends AbstractEvent {

//    private Long id;
    private Long paymentID;
    private Long customerId;
    private Long point;
    private Long bookId;
    private Long rentalId;

    public PointPaid(){
        super();
    }
/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
*/    
    public Long getPaymentId() {
        return paymentID;
    }

    public void setPaymentId(Long paymentID) {
        this.paymentID = paymentID;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
}
