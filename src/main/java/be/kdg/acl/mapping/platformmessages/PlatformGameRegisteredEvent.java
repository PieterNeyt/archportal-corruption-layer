package be.kdg.acl.mapping.platformmessages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlatformGameRegisteredEvent extends PlatformBaseEvent {
    String title;
    String description;
    String imageUrl;
    String gameUrl;
    String price;
    String genre;
    int maxLobbySize;
    List<RegisterAchievementEvent> achievements;
}
