package be.kdg.acl.mapping;

import be.kdg.acl.mapping.messages.ChessBaseMessage;
import be.kdg.acl.mapping.messages.GameCreatedMessage;
import be.kdg.acl.mapping.messages.GameEndedMessage;
import be.kdg.acl.mapping.messages.MoveMadeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChessToPlatformMapper {

    public void map(ChessBaseMessage msg) {
        log.info("msg type: {}", msg.getMessageType());

//        return switch (msg.getMessageType()) {
//            case GAME_CREATED -> mapGameCreated((GameCreatedMessage) msg);
////            case MOVE_MADE -> mapMove((MoveMadeMessage) msg);
////            case GAME_ENDED -> mapGameEnded((GameEndedMessage) msg);
////            default -> throw new UnsupportedEventException();
//        };
    }

//    private PlatformGameStarted mapGameCreated(GameCreatedMessage msg) {
//        return new PlatformGameStarted(
//                PlatformGameId.fromExternal(msg.getGameId()),
//                msg.getWhitePlayer(),
//                msg.getBlackPlayer()
//        );
//    }
}
