package be.kdg.acl.mapping.platformmessages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatformAchievementUnlockedEvent extends PlatformBaseEvent {
    private String platformGameId;     // of sessionId als jij dat wil
    private String ExternalAchId;
}
