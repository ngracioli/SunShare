package sunshare.entities.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {

    private String userUuid;
    private String message;
    private String uuid;

    @JsonCreator
    public Notification(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("userUuid") String userUuid,
            @JsonProperty("message") String message) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.message = message;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}