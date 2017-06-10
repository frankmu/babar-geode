package com.babar.geode.kafka.consumer.channel;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

public class BabarKafkaConsumerHandler {
    public CountDownLatch countDownLatch0 = new CountDownLatch(3);
    public CountDownLatch countDownLatch1 = new CountDownLatch(3);
    public CountDownLatch countDownLatch2 = new CountDownLatch(3);
 
    @KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = "replicate", partitions = { "0" }) })
    public void listenPartition0(ConsumerRecord<?, ?> record) {
        System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        countDownLatch0.countDown();
    }
 
    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = "replicate", partitions = { "1" }) })
    public void listenPartition1(ConsumerRecord<?, ?> record) {
        System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        countDownLatch1.countDown();
    }
}