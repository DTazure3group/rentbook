package book.rental.system;

public class BookstatusChanged extends AbstractEvent {

    private Long bookId;
    private Long price;
    private Boolean available;

    public BookstatusChanged(){
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
