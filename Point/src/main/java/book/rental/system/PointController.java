package book.rental.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

 @RestController
 public class PointController {

    @Autowired
    PointRepository pointRepository;

    @RequestMapping(value = "/points/checkPoint",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")
    public boolean checkPoint(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("##### /point/checkPoint  called #####");
                
        boolean status = false;

        Long customerId = Long.valueOf(request.getParameter("customerId"));
        Long price = Long.valueOf(request.getParameter("price"));

        System.out.println(">>>>> customerId : "+customerId);
        System.out.println(">>>>> price : "+price);  
        
        Optional<Point> point = pointRepository.findByCustomerId(customerId);
        if(point.isPresent()){
            Point pointValue = point.get();
            System.out.println("##### /point/checkPoint  pointValue.getPoint() : #####"+pointValue.getPoint());
            //Point 가 차감포인트 보다 큰지 점검 
            if(pointValue.getPoint() - price > 0) {
                //예약 가능하면 예약수량 증가
                status = true;
            }
        }

        return status;
    }

    @PostMapping("/points/save")
    Point PointSave(@RequestBody String data) {
        System.out.println(data);

        ObjectMapper mapper = new ObjectMapper();
        Point point = null;
        try {
            point = mapper.readValue(data, Point.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pointRepository.save(point);
    }

 }