package pt.codemaster.adt;

public interface IPublisher {
    void notifySubscribers(NotificationEvent event);
    void subscribe(EndUser user);
}
