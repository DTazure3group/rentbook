package book.rental.system;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Mypage_table")
public class Mypage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long customerId;
        private Long rentId;
        private Long bookId;
        private String bookName;
        private Long price;
        private Long point;
        private String rentStatus;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }
        public Long getRentId() {
            return rentId;
        }

        public void setRentId(Long rentId) {
            this.rentId = rentId;
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
        public Long getPoint() {
            return point;
        }

        public void setPoint(Long point) {
            this.point = point;
        }
        public String getRentStatus() {
            return rentStatus;
        }

        public void setRentStatus(String rentStatus) {
            this.rentStatus = rentStatus;
        }

}
