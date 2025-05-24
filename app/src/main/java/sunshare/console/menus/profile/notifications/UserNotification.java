package sunshare.console.menus.profile.notifications;

import java.util.Scanner;
import java.util.List;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;
import sunshare.services.NotificationService;
import sunshare.entities.notification.Notification;

public class UserNotification {
    public UserNotification(Scanner scanner, User user) {

        NotificationService notificationService = new NotificationService();
        final List<Notification> notifications = notificationService.getNotificationsFromUser(user.getUuid())
                .reversed();
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Suas Notificações --");
        ConsoleUtils.printError("Pressione ENTER para voltar\n");
        if (notifications.isEmpty()) {
            ConsoleUtils.printError("Nenhuma notificação encontrada.");
        } else {
            for (int i = 0; i < notifications.size(); i++) {
                Notification notification = notifications.get(i);
                ConsoleUtils.printSuccess(notification.getMessage() + "\n");
                notificationService.setNotificationsAsRead(notification.getUuid());
            }
        }
        scanner.nextLine();
    }
}