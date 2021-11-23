package pt.codemaster.backoffice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class ApiRest {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping(value = "/analytics.json", produces = APPLICATION_JSON)
    public String analytics() {
        return "{}";
    }

    @GetMapping(value = "/config.json", produces = APPLICATION_JSON)
    public String config() {
        return "{}";
    }

    @GetMapping(value = "/deployment", produces = APPLICATION_JSON)
    public String deployment() {
        return "{}";
    }
}
