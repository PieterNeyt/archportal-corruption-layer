package be.kdg.acl.mapping.chessmessages;

import be.kdg.acl.mapping.MessageType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "messageType",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = GameCreatedMessage.class, name = "GAME_CREATED"),
    @JsonSubTypes.Type(value = GameEndedMessage.class, name = "GAME_ENDED"),
    @JsonSubTypes.Type(value = MoveMadeMessage.class, name = "MOVE_MADE"),
    @JsonSubTypes.Type(value = GameRegisteredMessage.class, name = "GAME_REGISTERED"),
    @JsonSubTypes.Type(value = AchievementAcquiredMessage.class, name = "ACHIEVEMENT_ACQUIRED")
})
public abstract class ChessBaseMessage {
    private MessageType messageType;
    private String timestamp;
}