package book.rental.system;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Mypage_table")
public class Mypage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Integer customerid;
        private Integer rentid;
        private Integer bookid;
        private String bookName;
        private Boolean price;
        private Integer point;
        private String rentStatus;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Integer getCustomerid() {
            return customerid;
        }

        public void setCustomerid(Integer customerid) {
            this.customerid = customerid;
        }
        public Integer getRentid() {
            return rentid;
        }

        public void setRentid(Integer rentid) {
            this.rentid = rentid;
        }
        public Integer getBookid() {
            return bookid;
        }

        public void setBookid(Integer bookid) {
            this.bookid = bookid;
        }
        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }
        public Boolean getPrice() {
            return price;
        }

        public void setPrice(Boolean price) {
            this.price = price;
        }
        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }
        public String getRentStatus() {
            return rentStatus;
        }

        public void setRentStatus(String rentStatus) {
            this.rentStatus = rentStatus;
        }

}
