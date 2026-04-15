package com.example.Quora.producer;


import com.example.Quora.config.KafkaConfig;
import com.example.Quora.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {


    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent){
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME,viewCountEvent.getTargetId(),viewCountEvent)
                .whenComplete((result,err)->{
                    if(err!=null) System.out.println("Error publishing view count event: "+err.getMessage());
                });
    }


}
