package book.rental.system.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="Point", url="http://Point:8080")
public interface PointService {
    @RequestMapping(method= RequestMethod.GET, path="/points")
    public void checkPoint(@RequestBody Point point);

}

