package pt.codemaster.backoffice;

import org.springframework.web.bind.annotation.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class ApiRest {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping(value = "/analytics.json", produces = APPLICATION_JSON)
    public String analytics() {
        return "[\n" +
                "  {\n" +
                "    \"inveniraStdID\": 1001,\n" +
                "    \"quantAnalytics\": [\n" +
                "      {\n" +
                "        \"name\": \"Acedeu à atividade\",\n" +
                "        \"value\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Download documento 1\",\n" +
                "        \"value\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Evolução pela atividade (%)\",\n" +
                "        \"value\": \"33.3\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"qualAnalytics\": [\n" +
                "      {\n" +
                "        \"Student activity profile\": \"https://ActivityProvider/?APAnID=11111111\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Actitivy Heat Map\": \"https://ActivityProvider/?APAnID=21111111\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"inveniraStdID\": 1002,\n" +
                "    \"quantAnalytics\": [\n" +
                "      {\n" +
                "        \"name\": \"Acedeu à atividade\",\n" +
                "        \"value\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Download documento 1\",\n" +
                "        \"value\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Evolução pela atividade (%)\",\n" +
                "        \"value\": \"10.0\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"qualAnalytics\": [\n" +
                "      {\n" +
                "        \"Student activity profile\": \"https://ActivityProvider/?APAnID=11111112\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Actitivy Heat Map\": \"https://ActivityProvider/?APAnID=21111112\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";
    }

    @GetMapping(value = "/config.json", produces = APPLICATION_JSON)
    @ResponseBody
    public String config() {
        return "{\n" +
                "\"name\": \"<<name>>\",\n" +
                "\"description\": \"<<description>>\",\n" +
                "\"skeleton\": {\n" +
                "\"description\": \"<<description>>\",\n" +
                "\"code\": \"<<skeleton with placeholders>>\",\n" +
                "},\n" +
                "\"solution\": {\n" +
                "\"totalScore\": 5,\n" +
                "\"individualScore\": 1,\n" +
                "\"placeholders\": [\n" +
                "{\n" +
                "\"id\": \"1234\"\n" +
                "\"code\": \"<<source code>>\"\n" +
                "\"description\": \"<<description>>\",\n" +
                "\"result\": \"<<result>>\"\n" +
                "},\n" +
                "]\n" +
                "},\n" +
                "\"tests\": {\n" +
                "[\n" +
                "{\n" +
                "\"name\": \"<<name of the test>>\",\n" +
                "\"source\": \"<<source code>>\",\n" +
                "\"weight\": 1,\n" +
                "\"mandatory\": true,\n" +
                "\"showResult\": true,\n" +
                "\"performanceTest\": false\n" +
                "},\n" +
                "]\n" +
                "},\n" +
                "\"attempts\": 1,\n" +
                "\"blockCopyAndPaste\": true\n" +
                "}";
    }

    @PostMapping(value = "/deployment", produces = APPLICATION_JSON)
    public String deployment() {
        return "{\n" +
                "  \"activityID\": \"This string is the Inven!RA activity ID\",\n" +
                "  \"Inven!RAstdID\": \"This string is the student Inven!RA ID\",\n" +
                "  \"json_params\": {\n" +
                "    \"s1\" : \"Setting 1\",\n" +
                "    \"s2\" : \"Setting 2\",\n" +
                "    \"s3\" : \"Setting 3\"\n" +
                "  }\n" +
                "}";
    }
}
