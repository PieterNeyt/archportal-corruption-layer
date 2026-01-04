package be.kdg.acl.mapping;

import be.kdg.acl.mapping.chessmessages.*;
import be.kdg.acl.mapping.platformmessages.PlatformAchievementUnlockedEvent;
import be.kdg.acl.mapping.platformmessages.PlatformGameRegisteredEvent;
import be.kdg.acl.messaging.sender.PlatformEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChessToPlatformMapper {

    private final PlatformEventPublisher platformEventPublisher;
    private final ChessAchievementMapper chessAchievementMapper;

    public ChessToPlatformMapper(PlatformEventPublisher platformEventPublisher, ChessAchievementMapper chessAchievementMapper) {
        this.platformEventPublisher = platformEventPublisher;
        this.chessAchievementMapper = chessAchievementMapper;
    }


    public void map(ChessBaseMessage msg) {
        log.info("msg type: {}", msg.getMessageType());

        switch (msg.getMessageType()) {
            case ACHIEVEMENT_ACQUIRED -> mapAchievementAcquired((AchievementAcquiredMessage) msg);
            case GAME_REGISTERED -> mapGameRegistered((GameRegisteredMessage) msg);
            default -> log.warn("Unsupported chess message type: {}", msg.getMessageType());
        }
    }

    private void mapGameRegistered(GameRegisteredMessage msg) {
        log.info("msg type: {}", msg.getMessageType());

        var event = new PlatformGameRegisteredEvent();
        event.setTitle("Chess");
        event.setEventType("PLATFORM_GAME_REGISTERED");
        event.setTimestamp(msg.getTimestamp());
        event.setDescription("No description set");
        event.setImageUrl(msg.getPictureUrl());
        event.setGameUrl(msg.getFrontendUrl());
        event.setPrice("10.00");
        event.setGenre("STRATEGY");
        event.setMaxLobbySize(2);
        event.setAchievements(chessAchievementMapper.map(msg.getAvailableAchievements()));

        platformEventPublisher.publish("platform.game.registered", event);
    }

    private void mapAchievementAcquired(AchievementAcquiredMessage msg) {
        log.info("Achievement acquired: playerId={}, type={}, name={}",
                 msg.getPlayerId(), msg.getAchievementType(), msg.getPlayerName());

        var event = new PlatformAchievementUnlockedEvent();
        event.setEventType("PLATFORM_ACHIEVEMENT_UNLOCKED");
        event.setTimestamp(msg.getTimestamp());
        event.setPlayerId(msg.getPlayerId());

        event.setExternalAchId(msg.getAchievementType());

        platformEventPublisher.publish("platform.achievement.unlocked", event);
    }


}
