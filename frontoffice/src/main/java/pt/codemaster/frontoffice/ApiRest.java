package pt.codemaster.frontoffice;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.ActivityUnitTest;
import pt.codemaster.adt.activity.BibliographicReference;
import pt.codemaster.adt.activity.CollaborativeEvaluationActivity;

import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@CrossOrigin(origins = "*")
public class ApiRest {
    @GetMapping(value = "/cea/{id}/{userId}", produces = APPLICATION_JSON)
    public ActivityInstance getCea(@PathVariable("id") String id, @PathVariable("userId") String userId) {
        CollaborativeEvaluationActivity activity = new CollaborativeEvaluationActivity();
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
        activity.setActivityUnitTestList(Set.of(unitTest1, unitTest2));

        ActivityUnitTest unitTest3= new ActivityUnitTest();
        unitTest3.setId(3L);
        unitTest3.setScore(90);
        unitTest3.setCode(new Code(LanguageEnum.JAVASCRIPT, "assert('RESULT_SUM==274')"));
        activity.setActivityUnitTestList(Set.of(unitTest1, unitTest3));

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

        activity.setSolution(Set.of(solution1, solution2, solution3));

        activity.setReferenceSet(Set.of(new BibliographicReference("Test1", "http://www.test1.com"),
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
        del1.getCode().addComment(1, "Resultado parcial. Preciso de ajuda para resolver", user1);


        EndUser user2 = new EndUser(2L);
        user2.setName("John");
        del2.setAuthor(user2);
        del2.setCode(new Code(LanguageEnum.JAVASCRIPT, "const RESULT_MIN = 1;"));
        del2.getCode().addComment(1, "Resultado incorreto! Help!", user2);
        del2.getCode().getCommentList().get(0).addReply("Sim, não está bem. Tens que utilizar a função Math.min", user1);

        EndUser user3 = new EndUser(2L);
        user3.setName("Jennifer");
        del3.setAuthor(user3);
        del3.setCode(new Code(LanguageEnum.JAVASCRIPT, ""));
        del3.getCode().addComment(1, "Nem sei por onde começar...", user3);

        del1.setReadOnly(!userId.equals("1"));
        del2.setReadOnly(!userId.equals("2"));
        del3.setReadOnly(!userId.equals("3"));

        //REVER - dá para criar os deliverables com a activity passada por parâmetro
        activityInstance.getDeliverable().add(del1);
        activityInstance.getDeliverable().add(del2);
        activityInstance.getDeliverable().add(del3);


        return activityInstance;
    }
}
