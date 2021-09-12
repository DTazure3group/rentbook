package book.rental.system;

public class BookRegistered extends AbstractEvent {

    private Long bookId;
    private Long price;
    private Boolean available;

    public BookRegistered(){
        super();
    }

    public Long getId() {
        return bookId;
    }

    public void setId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
