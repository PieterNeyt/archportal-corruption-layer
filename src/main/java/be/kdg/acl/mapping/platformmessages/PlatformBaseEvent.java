package be.kdg.acl.mapping.platformmessages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PlatformBaseEvent {
    private String eventType;   // bv "PLATFORM_ACHIEVEMENT_UNLOCKED"
    private String timestamp;   // ISO string
}
