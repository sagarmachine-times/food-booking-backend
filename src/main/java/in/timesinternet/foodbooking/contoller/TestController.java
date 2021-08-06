package in.timesinternet.foodbooking.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/api/restaurant")
    public String initialTest1() {
        return "test success";
    }

    @RequestMapping("/api")
    public String initialTest2() {
        return "test success";
    }
}
