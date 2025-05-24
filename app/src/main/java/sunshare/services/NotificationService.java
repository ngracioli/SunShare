package sunshare.services;

import java.util.ArrayList;
import sunshare.entities.notification.Notification;
import sunshare.json.manager.JsonFiles;

public class NotificationService extends BaseService {

    public NotificationService() {
        super(JsonFiles.notifications);
    }

    public int getQuantityOfNotifications(String userUuid) {
        final var result = jsonManager.select(Notification.class, m -> {
            return m.getUserUuid().equals(userUuid);
        });

        return result.size();
    }

    public void setNotificationsAsRead(String uuid) {
        jsonManager.delete(Notification.class, m -> {
            return m.getUuid().equals(uuid);
        });
    }

    public ArrayList<Notification> getNotificationsFromUser(String userUuid) {
        return jsonManager.select(Notification.class, m -> {
            return m.getUserUuid().equals(userUuid);
        });
    }

    public void createNotification(String userUuid, String message, Object... args) {
        final String formattedMessage = String.format(message, args);
        final var notification = new Notification(generateUuid(), userUuid, formattedMessage);

        jsonManager.insert(Notification.class, jsonManager.toJsonNode(notification));
    }
}