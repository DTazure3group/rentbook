package book.rental.system;

public class AlertSent extends AbstractEvent {

    private Long id;

    public AlertSent(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
