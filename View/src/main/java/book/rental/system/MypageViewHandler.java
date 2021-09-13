package book.rental.system;

import book.rental.system.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;


    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookRented_then_UPDATE_1(@Payload BookRented bookRented) {
           
        try{
            if (!bookRented.validate()) return;
            // view 객체 조회 
            System.out.println(">>>>>>>>>>>>>bookRented.getBookid() : " + bookRented.getBookId());
            System.out.println(">>>>>>>>>>>>>bookRented.getCustomerId() : " + bookRented.getCustomerId());
            System.out.println(">>>>>>>>>>>>>bookRented.getRentalId() : " + bookRented.getRentalId());

            Mypage mypage = new Mypage();
            mypage.setBookId(bookRented.getBookId());
            mypage.setCustomerId(bookRented.getCustomerId());
            mypage.setPrice(bookRented.getPrice());
            mypage.setRentId(bookRented.getRentalId());
            mypage.setRentStatus("RENT");
        // view 레파지 토리에 save
            mypageRepository.save(mypage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookReturned_then_UPDATE_2(@Payload BookReturned bookReturned) {
        try {
            if (!bookReturned.validate()) return;
                // view 객체 조회

                Optional<Mypage> mypageOptional  = mypageRepository.findByRentId(bookReturned.getRentalId());

                if( mypageOptional.isPresent()) {
                    Mypage mypage = mypageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                        mypage.setRentStatus("RETURN");
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointPaid_then_CREATE_1 (@Payload PointPaid pointPaid) {
/*        try {

            if (!pointPaid.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setCustomerid(pointPaid.getCustomerid());
            mypage.setRentid(pointPaid.getRentalId());
            mypage.setBookid(pointPaid.getBookid());
            mypage.setPoint(pointPaid.getPoint());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);

        }catch (Exception e){
            e.printStackTrace();
        }
        */
    }

}

