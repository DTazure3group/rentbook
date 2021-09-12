package book.rental.system;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Point_table")
public class Point {

    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerId;
    private Long point;

    @PostPersist
    public void onPostPersist(){
        PointSpent pointSpent = new PointSpent();
        BeanUtils.copyProperties(this, pointSpent);
        pointSpent.publishAfterCommit();

        PointSaved pointSaved = new PointSaved();
        BeanUtils.copyProperties(this, pointSaved);
        pointSaved.publishAfterCommit();    

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




}