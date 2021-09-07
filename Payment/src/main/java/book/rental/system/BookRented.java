package book.rental.system;

public class BookRented extends AbstractEvent {

    private Long id;
    private Integer bookId;
    private Integer price;
    private Integer customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getBookid() {
        return bookId;
    }

    public void setBookid(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}