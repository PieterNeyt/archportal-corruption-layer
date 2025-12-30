package be.kdg.acl.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {
    public static final String CHESS_EXCHANGE_NAME = "gameExchange";
    public static final String CHESS_QUEUE_NAME = "test.queue";

    @Bean
    TopicExchange gameExchange() {
        return new TopicExchange(CHESS_EXCHANGE_NAME);
    }

    @Bean
    Queue testQueue() {
        return QueueBuilder.durable(CHESS_QUEUE_NAME).build();
    }

    @Bean
    Binding tttQueueToTttExchangeBinding() {
        return BindingBuilder.bind(testQueue()).to(gameExchange()).with("#");
    }
}