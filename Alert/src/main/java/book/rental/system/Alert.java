package book.rental.system;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Alert_table")
public class Alert {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long rentalId;
    private Long customerId;
    private String customerPhoneNo;
    private String returnDate;

    @PostPersist
    public void onPostPersist(){
        AlertSent alertSent = new AlertSent();
        BeanUtils.copyProperties(this, alertSent);
        alertSent.publishAfterCommit();

    }
    // todo
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRentalid() {
        return rentalId;
    }

    public void setRentalid(Long rentalId) {
        this.rentalId = rentalId;
    }
    public Long getCustomerid() {
        return customerId;
    }

    public void setCustomerid(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomrPhnoeNo() {
        return customerPhoneNo;
    }

    public void setCustomrPhnoeNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }
    public String getReturndate() {
        return returnDate;
    }

    public void setReturndate(String returnDate) {
        this.returnDate = returnDate;
    }




}