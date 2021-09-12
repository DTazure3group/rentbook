package book.rental.system;

import book.rental.system.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired RentalRepository rentalRepository;



    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
