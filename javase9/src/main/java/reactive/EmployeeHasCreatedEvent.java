package reactive;

public class EmployeeHasCreatedEvent {

    private String message;

    public EmployeeHasCreatedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
