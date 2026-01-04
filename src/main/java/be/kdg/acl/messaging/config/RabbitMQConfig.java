package be.kdg.acl.messaging.config;

import be.kdg.acl.mapping.chessmessages.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class  RabbitMQConfig {

    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setJavaTypeMapper(typeMapper());
        return converter;
    }

    @Bean
    public DefaultJackson2JavaTypeMapper typeMapper() {
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

        typeMapper.setTrustedPackages("be.kdg.i5.chess.messaging.messages", "be.kdg.acl.*");

        Map<String, Class<?>> idClassMapping = new HashMap<>();

        idClassMapping.put("be.kdg.i5.chess.messaging.messages.GameCreatedMessage", GameCreatedMessage.class);
        idClassMapping.put("be.kdg.i5.chess.messaging.messages.MoveMadeMessage", MoveMadeMessage.class);
        idClassMapping.put("be.kdg.i5.chess.messaging.messages.GameEndedMessage", GameEndedMessage.class);
        idClassMapping.put("be.kdg.i5.chess.messaging.messages.GameRegisteredMessage", GameRegisteredMessage.class);
        idClassMapping.put("be.kdg.i5.chess.messaging.messages.AchievementAcquiredMessage", AchievementAcquiredMessage.class);

        typeMapper.setIdClassMapping(idClassMapping);
        return typeMapper;
    }
}