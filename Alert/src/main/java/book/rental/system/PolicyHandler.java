package book.rental.system;

import book.rental.system.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired AlertRepository alertRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturnDelayed_Alert(@Payload ReturnDelayed returnDelayed){

        if(!returnDelayed.validate()) return;

        System.out.println("\n\n##### listener Alert : " + returnDelayed.toJson() + "\n\n");



        // Sample Logic //
        // Alert alert = new Alert();
        // alertRepository.save(alert);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
