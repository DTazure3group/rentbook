package book.rental.system;

public class PointPaid extends AbstractEvent {

    private Long id;
    private Long paymentID;
    private Integer customerid;
    private Integer point;
    private Integer bookid;
    private Integer rentalId;

    public PointPaid(){
        super();
    }

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
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }
    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }
}
