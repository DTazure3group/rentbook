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
    private Integer rentalid;
    private String customerid;
    private String customrphnoeno;
    private Date returndate;

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
    public Integer getRentalid() {
        return rentalid;
    }

    public void setRentalid(Integer rentalid) {
        this.rentalid = rentalid;
    }
    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    public String getCustomrphnoeno() {
        return customrphnoeno;
    }

    public void setCustomrphnoeno(String customrphnoeno) {
        this.customrphnoeno = customrphnoeno;
    }
    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }




}