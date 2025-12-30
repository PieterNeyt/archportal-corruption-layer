package be.kdg.acl.mapping.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementAcquiredMessage extends ChessBaseMessage {
    private String gameId;
    private String playerId;
    private String playerName;
    private String achievementType;
    private String achievementDescription;
}