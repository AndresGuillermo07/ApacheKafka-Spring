package com.kafka.provider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // cofiguracion de proveedores de mensajes de kafka
    private Map<String, Object> providerConfig(){

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    /**
     * Crea una fábrica de productores para enviar mensajes a Kafka.
     *
     * @return Un objeto {@link ProducerFactory} que configura y proporciona instancias de productores.
     */
    @Bean
    public ProducerFactory<String, String> providerFactory(){
        return new DefaultKafkaProducerFactory<>(providerConfig());
    }

    /**
     * Crea un {@link KafkaTemplate} para facilitar el envío de mensajes a Kafka.
     *
     * @param producerFactory La fábrica de productores utilizada para crear instancias de productores de Kafka.
     * @return Un objeto {@link KafkaTemplate} configurado con la fábrica de productores proporcionada.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
