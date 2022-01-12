package com.yicen.flutter_service_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String kTopicExchange = "item_topic_exchange";
    public static final String kQueue = "item_topic_queue";
    public static final String kRoutingKey = "item.add";

    //创建一个交换机
    @Bean("item_topic_exchange")
    public Exchange topicExchange() {
        /**
         *  交换机名称 kTopicExchange
         *  是否序列化存储
         */
        return ExchangeBuilder.topicExchange(kTopicExchange).durable(true).build();
    }



    @Bean(kQueue)
    public Queue itemQueue() {
        return QueueBuilder.durable(kQueue).build();
    }

    @Bean("user_queue")
    public Queue registerQueue() {
        return QueueBuilder.durable("register_queue").build();
    }

    @Bean
    public Binding itemQueueExchange(@Qualifier(kQueue) Queue queue, @Qualifier(kTopicExchange) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(kRoutingKey).noargs();
    }

    @Bean
    public Binding itemQueueUserExchange(@Qualifier("user_queue") Queue queue, @Qualifier(kTopicExchange) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("user.*").noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory( connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
