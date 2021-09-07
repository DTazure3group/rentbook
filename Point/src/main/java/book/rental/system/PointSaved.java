package book.rental.system;

public class PointSaved extends AbstractEvent {

    private Long id;

    public PointSaved(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
