package com.spartified.assignment.listener;

import com.spartified.assignment.dto.CdrDto;
import com.spartified.assignment.service.CdrBatchProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CdrBatchListener {

    private static final Logger log = LoggerFactory.getLogger(CdrBatchListener.class);

    private final CdrBatchProcessor processor;

    public CdrBatchListener(CdrBatchProcessor processor) {
        this.processor = processor;
    }

    // Listen to topic, receive List<CdrDto> in batch
    @KafkaListener(topics = "${app.kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<CdrDto> messages, Acknowledgment ack) {
        if (messages == null || messages.isEmpty()) {
            return;
        }
        log.info("Received batch of size {}", messages.size());
        try {
            processor.processBatch(messages);
            // manual ack after successful processing
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Error processing batch", e);
            // depending on requirement: could not ack -> Kafka will re-deliver; or send to DLQ
            // For now, do not ack so that Kafka can re-deliver. You may implement DLQ or retries.
        }
    }
}
