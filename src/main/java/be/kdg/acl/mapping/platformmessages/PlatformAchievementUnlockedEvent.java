package be.kdg.acl.mapping.platformmessages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatformAchievementUnlockedEvent extends PlatformBaseEvent {
    private String externalAchId;
    private String playerId;
    private String gameId;
}
