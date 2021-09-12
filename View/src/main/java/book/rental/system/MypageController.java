package book.rental.system;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MypageController {

    @Autowired MypageRepository mypageRepository;
    
    @GetMapping("/mypage/{customerid}")
    Mypage myInfo(@PathVariable(value = "customerid") Long customerID) {
        System.out.println(">>>>>>>>>>>>>>>>  customerID : "+customerID);

        Optional<Mypage> mypageOptional = mypageRepository.findByCustomerid(customerID);
        Mypage mypage = mypageOptional.get();

        return mypage;
    }
}
