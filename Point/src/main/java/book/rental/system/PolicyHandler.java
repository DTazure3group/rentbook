package book.rental.system;

import book.rental.system.config.kafka.KafkaProcessor;
import java.util.Optional;
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

        // Point 차감 
        Long customerid = pointPaid.getCustomerId();
        Long pointAmt      = pointPaid.getPoint();

        Optional<Point> mypageOptional = pointRepository.findByCustomerId(customerid);
        Point point = mypageOptional.get();
        point.setPoint(point.getPoint()-pointAmt);
        pointRepository.save(point);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

}
