package com.yicen.flutter_service_demo.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public  static  final  String kTopicExchange = "item_topic_exchange";
    public  static  final  String kQueue = "item_topic_queue";
    public  static  final  String kRoutingKey = "item.add";

    @Bean("item_topic_exchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(kTopicExchange).durable(true).build();
    }

    @Bean(kQueue)
    public Queue itemQueue(){
        return QueueBuilder.durable(kQueue).build();
    }

    @Bean
    public Binding itemQueueExchange(@Qualifier(kQueue) Queue queue, @Qualifier(kTopicExchange) Exchange exchange){
        return  BindingBuilder.bind(queue).to(exchange).with(kRoutingKey).noargs();
    }
}
