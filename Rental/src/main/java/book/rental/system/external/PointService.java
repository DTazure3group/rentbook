package book.rental.system.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="Point", url="http://Point:8080")
//@FeignClient(name="Point", url="http://localhost:8086")
public interface PointService {
    @RequestMapping(method= RequestMethod.GET, path="/points/checkPoint")
    public boolean checkPoint(@RequestParam Long customerId, @RequestParam Long price);
    

}

