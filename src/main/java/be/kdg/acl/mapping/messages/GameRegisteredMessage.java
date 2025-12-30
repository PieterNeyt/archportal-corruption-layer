package be.kdg.acl.mapping.messages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameRegisteredMessage extends ChessBaseMessage {
    private String registrationId;
    private String frontendUrl;
    private String pictureUrl;
    private List<AchievementDetail> availableAchievements;

    @Getter @Setter
    public static class AchievementDetail {
        private String code;
        private String description;
    }
}