package be.kdg.acl.messaging.receiver;

import be.kdg.acl.mapping.ChessToPlatformMapper;
import be.kdg.acl.mapping.messages.ChessBaseMessage;
import be.kdg.acl.messaging.config.RabbitMQTopology;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChessEventListener {
//    private final PlatformEventService platformEventService;
    private final ChessToPlatformMapper chessToPlatformMapper;

    public ChessEventListener(ChessToPlatformMapper chessToPlatformMapper) {
        this.chessToPlatformMapper = chessToPlatformMapper;
    }

    @RabbitListener(queues = RabbitMQTopology.CHESS_QUEUE_NAME)
    public void onChessEvent(ChessBaseMessage message) {
        log.info("Received Chess Message from RabbitMQ");
        chessToPlatformMapper.map(message);
    }
}
