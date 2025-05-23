package sunshare.services;

import java.util.ArrayList;
import sunshare.entities.notification.Notification;
import sunshare.json.manager.JsonFiles;

public class NotificationService extends BaseService {

    public NotificationService() {
        super(JsonFiles.notifications);
    }

    public void setUserNotificationsAsRead(String userUuid) {
        // TODO implement
    }

    public ArrayList<Notification> getNotificationFromUser(String userUuid) {
        // TODO implement
        return null;
    }

}