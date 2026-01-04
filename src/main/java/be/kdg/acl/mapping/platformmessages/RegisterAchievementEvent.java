package be.kdg.acl.mapping.platformmessages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAchievementEvent {
    String externalAchId;
    String title;
    String description;
    String imageUrl;
}
