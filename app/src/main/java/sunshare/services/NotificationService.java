package sunshare.services;

import java.util.ArrayList;
import sunshare.entities.notification.Notification;
import sunshare.json.manager.JsonFiles;

public class NotificationService extends BaseService {

    public NotificationService() {
        super(JsonFiles.notifications);
    }

    public void setUserNotificationsAsRead(String userUuid) {
        jsonManager.delete(Notification.class, m -> {
            return m.getUserUuid().equals(userUuid);
        });
    }

    public ArrayList<Notification> getNotificationFromUser(String userUuid) {
        return jsonManager.select(Notification.class, m -> {
            return m.getUserUuid().equals(userUuid);
        });
    }

    public void createNotification(String userUuid, String message) {
        final var notification = new Notification(generateUuid(), userUuid, message);

        jsonManager.insert(Notification.class, jsonManager.toJsonNode(notification));
    }
}