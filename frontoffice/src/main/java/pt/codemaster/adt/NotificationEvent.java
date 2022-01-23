package pt.codemaster.adt;

public class NotificationEvent {
    private NotificationType type;
    private String message;

    public NotificationEvent(NotificationType type, String message) {
        this.type = type;
        this.message = message;
    }

    public enum NotificationType {
        COMMENT_ADDED, COMMENT_REPLY, DELIVERABLE_SUBMITTED, CODE_SAVED
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
