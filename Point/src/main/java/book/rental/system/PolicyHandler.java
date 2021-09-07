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
    @Autowired PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPointPaid_Spend(@Payload PointPaid pointPaid){

        if(!pointPaid.validate()) return;

        System.out.println("\n\n##### listener Spend : " + pointPaid.toJson() + "\n\n");



        // Sample Logic //
        // Point point = new Point();
        // pointRepository.save(point);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
