package book.rental.system;

public class PointSpent extends AbstractEvent {

    private Long id;

    public PointSpent(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
