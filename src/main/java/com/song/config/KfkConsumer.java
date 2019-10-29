package com.song.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Song on 2019/08/21.
 */
@Component
@Slf4j
public class KfkConsumer {

    @Value("${kafka.consumer.group.id01}")
    private String GROUPID;

    @Value("${kafka.server.config}")
    public static String SERVERS_CONFIG;

    @Bean
    public KafkaConsumer getKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", SERVERS_CONFIG);

        //每个消费者分配独立的组号
        props.put("group.id", GROUPID);

        //如果value合法，则自动提交偏移量
        /**
         * If true the consumer's offset will be periodically committed in the background.
         */
        props.put("enable.auto.commit", "true");

        //设置多久一次更新被消费消息的偏移量
        /**
         * The frequency in milliseconds that the consumer offsets are auto-committed to Kafka
         * if enable.auto.commit is set to true.
         */
        props.put("auto.commit.interval.ms", "1000");

        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        props.put("session.timeout.ms", "30000");

        /**
         *
         * What to do when there is no initial offset in Kafka or if the current offset does not exist
         * any more on the server (e.g. because that data has been deleted):
         *
         * earliest: automatically reset the offset to the earliest offset
         * latest: automatically reset the offset to the latest offset
         * none: throw exception to the consumer if no previous offset is found for the consumer's group
         * anything else: throw exception to the consumer.
         *
         */
        props.put("auto.offset.reset","earliest");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);

        /**
         * The maximum number of records returned in a single call to poll().
         */
        props.put("max.poll.records", 1);
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(props);
        return kafkaConsumer;
    }
}
