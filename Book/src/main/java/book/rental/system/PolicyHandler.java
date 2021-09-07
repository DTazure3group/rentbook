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
    @Autowired BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookReturned_StatusChange(@Payload BookReturned bookReturned){

        if(!bookReturned.validate()) return;

        System.out.println("\n\n##### listener StatusChange : " + bookReturned.toJson() + "\n\n");



        // Sample Logic //
        // Book book = new Book();
        // bookRepository.save(book);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPointPaid_StatusChange(@Payload PointPaid pointPaid){

        if(!pointPaid.validate()) return;

        System.out.println("\n\n##### listener StatusChange : " + pointPaid.toJson() + "\n\n");



        // Sample Logic //
        // Book book = new Book();
        // bookRepository.save(book);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
