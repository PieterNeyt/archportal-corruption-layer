package be.kdg.acl.messaging.sender;

import be.kdg.acl.mapping.platformmessages.PlatformBaseEvent;
import be.kdg.acl.messaging.config.RabbitMQTopology;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlatformEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(String routingKey, PlatformBaseEvent event) {

        log.info("Publishing platform event to exchange={} routingKey={} eventType={}",
                RabbitMQTopology.PLATFORM_EXCHANGE,
                routingKey, event.getEventType());

        rabbitTemplate.convertAndSend(
                RabbitMQTopology.PLATFORM_EXCHANGE,
                routingKey,
                event
        );
    }
}
