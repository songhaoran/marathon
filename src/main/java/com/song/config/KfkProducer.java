package com.song.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Song on 2019/08/21.
 */
@Component
@Slf4j
public class KfkProducer {

    @Value("${kafka.server.config}")
    public static String SERVERS_CONFIG;

    @Bean
    public KafkaProducer getKafkaProducer(){
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS_CONFIG);//kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        /**
         *
         * acks=0 If set to zero then the producer will not wait for any acknowledgment from the server
         * at all. The record will be immediately added to the socket buffer and considered sent.
         * No guarantee can be made that the server has received the record in this case,
         * and the retries configuration will not take effect (as the client won't generally
         * know of any failures). The offset given back for each record will always be set to -1.
         *
         * acks=1 This will mean the leader will write the record to its local log but will respond without
         * awaiting full acknowledgement from all followers. In this case should the leader fail immediately
         * after acknowledging the record but before the followers have replicated it then the record
         * will be lost.
         *
         * acks=all This means the leader will wait for the full set of in-sync replicas to
         * acknowledge the record.This guarantees that the record will not be lost as long as
         * at least one in-sync replica remains alive.
         * This is the strongest available guarantee. This is equivalent to the acks=-1 setting.
         *
         */
        p.put(ProducerConfig.ACKS_CONFIG, "all");

        /**
         *
         * When set to 'true', the producer will ensure that exactly one copy of each message is written
         * in the stream. If 'false', producer retries due to broker failures,
         * etc., may write duplicates of the retried message in the stream.
         * Note that enabling idempotence requires max.in.flight.requests.per.connection to be
         * less than or equal to 5, retries to be greater than 0 and acks must be 'all'.
         * If these values are not explicitly set by the user, suitable values will be chosen.
         * If incompatible values are set, a ConfigException will be thrown.
         *
         */
        p.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        return kafkaProducer;
    }
}
