package book.rental.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

 @RestController
 public class RentalController {

    @Autowired RentalRepository rentalRepositroy;

    @PostMapping("rentals/rent")
    Rental bookRentalCreate(@RequestBody String data) {
        System.out.println("########### bookRentalCreate data :" +data);

        ObjectMapper mapper = new ObjectMapper();
        Rental rental = null;
        try {
             rental = mapper.readValue(data, Rental.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rentalRepositroy.save(rental);
    }

 }