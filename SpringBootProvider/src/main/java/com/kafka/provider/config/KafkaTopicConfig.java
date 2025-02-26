package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de configuración para la creación de tópicos en Apache Kafka.
 * Define un bean que crea y configura un tópico llamado "FirstTopic-Topic".
 */

@Configuration
public class KafkaTopicConfig {

    /**
     * Define y configura un nuevo tópico en Kafka.
     *
     * @return Un objeto {@link NewTopic} con la configuración especificada.
     */
    @Bean
    public NewTopic generateTopic() {

        // Configuración adicional del tópico (actualmente no utilizada)
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);// delete elimina los mensajes despues de cierto tiempo
        // compact mantiene el ultimo mensaje
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // tiempo de retencion de mensajes
        configs.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824"); // tamaño maximo del segmento -1GB
        configs.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1100012"); // tamaño maximo de cada mensaje - defecto 1MB

        // Creación del tópico con nombre, particiones y réplicas
        return TopicBuilder.name("FirstTopic-Topic")
                .partitions(2)
                .replicas(2)
                .configs(configs)
                .build();
    }

}
