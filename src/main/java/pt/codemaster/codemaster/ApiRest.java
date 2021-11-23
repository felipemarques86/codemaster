package pt.codemaster.codemaster;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRest {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
