package pt.codemaster.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.adt.activity.ActivityUnitTest;
import pt.codemaster.adt.activity.BibliographicReference;

import java.util.Arrays;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@CrossOrigin
public class ApiRest {

    @Autowired
    private ActivityService activityService;


    @PostMapping(value="/activity", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Activity saveActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }

    @PostMapping(value="/code", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Code updateCode(@RequestBody Code code) {
        return activityService.saveCode(code);
    }

    @PostMapping(value="/code/{codeId}/user/{userId}/comment/{line}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Code addComment(@RequestBody Comment comment, @PathVariable("codeId") Long codeId,
                           @PathVariable("userId") Long userId, @PathVariable("line") Long line) {
        return activityService.addComment(codeId, userId, line, comment);
    }

    @PostMapping(value="/user", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public EndUser saveEndUser(@RequestBody EndUser endUser) {
        return activityService.saveEndUser(endUser);
    }

    @GetMapping(value="/code/{id}", produces = APPLICATION_JSON)
    public Code getCode(@PathVariable("id") Long id) {
        return activityService.getCode(id);
    }

    @GetMapping(value="/activity/{id}", produces = APPLICATION_JSON)
    public Activity getActivity(@PathVariable("id") Long id) {
        return activityService.getActivity(id);
    }

    @GetMapping(value = "/cea/{id}/{userId}", produces = APPLICATION_JSON)
    public ActivityInstance createCea(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        return activityService.createInstance(id, userId);
    }

    //@GetMapping(value = "/cea/{id}/{userId}", produces = APPLICATION_JSON)
    public ActivityInstance getCea(@PathVariable("id") String id, @PathVariable("userId") String userId) {
        Activity activity = new Activity();
        activity.setId(1L);
        activity.setName("Introdução ao Javascript - Funções matemáticas");
        activity.setDescription("Nesta atividade terão de identificar o maior, menor e o somatório números dentro da seguinte lista: 35, 26, 58, 32, 60, -1, 64 e guardar nas variáveis RESULT_MAX, RESULT_MIN e RESULT_SUM");


        ActivityUnitTest unitTest1 = new ActivityUnitTest();
        unitTest1.setId(1L);
        unitTest1.setScore(90);
        unitTest1.setCode(new Code(LanguageEnum.JAVASCRIPT, "assert('RESULT_MAX==64')"));

        ActivityUnitTest unitTest2 = new ActivityUnitTest();
        unitTest2.setId(2L);
        unitTest2.setScore(90);
        unitTest2.setCode(new Code(LanguageEnum.JAVASCRIPT, "assert('RESULT_MIN==-1')"));
        activity.setActivityUnitTestList(Arrays.asList(unitTest1, unitTest2));

        ActivityUnitTest unitTest3= new ActivityUnitTest();
        unitTest3.setId(3L);
        unitTest3.setScore(90);
        unitTest3.setCode(new Code(LanguageEnum.JAVASCRIPT, "assert('RESULT_SUM==274')"));
        activity.setActivityUnitTestList(Arrays.asList(unitTest1, unitTest3));

        Solution solution1 = new Solution();
        solution1.setId(1L);
        solution1.add(unitTest1);
        Code code1 = new Code(LanguageEnum.JAVASCRIPT, "RESULT_MAX = Math.max(35, 26, 58, 32, 60, -1, 64);");
        code1.setScore(0);
        solution1.setCode(code1);

        Solution solution2 = new Solution();
        solution2.setId(2L);
        solution2.add(unitTest2);
        Code code2 = new Code(LanguageEnum.JAVASCRIPT, "RESULT_MIN = Math.min(35, 26, 58, 32, 60, -1, 64);");
        code2.setScore(0);
        solution2.setCode(code2);

        Solution solution3 = new Solution();
        solution3.setId(3L);
        solution3.add(unitTest3);
        Code code3 = new Code(LanguageEnum.JAVASCRIPT, "RESULT_SUM = Array(35, 26, 58, 32, 60, -1, 64).reduce((partial_sum, a) => partial_sum + a, 0);");
        code3.setScore(0);
        solution3.setCode(code3);

        activity.setSolution(Arrays.asList(solution1, solution2, solution3));

        activity.setBibliographicReferenceList(Arrays.asList(new BibliographicReference("Test1", "http://www.test1.com"),
                new BibliographicReference("Test2", "http://www.test2.com"),
                new BibliographicReference("Test3", "http://www.test3.com")));

        //Resolve a CEA

        ActivityInstance activityInstance = new ActivityInstance(activity);
        activityInstance.setId(1L);
        var del1 = new Deliverable(solution1);
        var del2 = new Deliverable(solution2);
        var del3 = new Deliverable(solution3);


        EndUser user1 = new EndUser(1L);
        user1.setName("Mary");
        del1.setAuthor(user1);
        del1.setCode(new Code(LanguageEnum.JAVASCRIPT, "const RESULT_MAX = 64;"));
       // del1.getCode().addComment(1, "Resultado parcial. Preciso de ajuda para resolver", user1);


        EndUser user2 = new EndUser(2L);
        user2.setName("John");
        del2.setAuthor(user2);
        del2.setCode(new Code(LanguageEnum.JAVASCRIPT, "const RESULT_MIN = 1;"));
      //  del2.getCode().addComment(1, "Resultado incorreto! Help!");
       // del2.getCode().getCommentList().get(0).addReply("Sim, não está bem. Tens que utilizar a função Math.min", user1);

        EndUser user3 = new EndUser(2L);
        user3.setName("Jennifer");
        del3.setAuthor(user3);
        del3.setCode(new Code(LanguageEnum.JAVASCRIPT, ""));
       // del3.getCode().addComment(1, "Nem sei por onde começar...");

        del1.setReadOnly(!userId.equals("1"));
        del2.setReadOnly(!userId.equals("2"));
        del3.setReadOnly(!userId.equals("3"));

        //REVER - dá para criar os deliverables com a activity passada por parâmetro
        activityInstance.getDeliverable().add(del1);
        activityInstance.getDeliverable().add(del2);
        activityInstance.getDeliverable().add(del3);


        return activityInstance;
    }

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
