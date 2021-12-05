package pt.codemaster.adt.activity;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import pt.codemaster.adt.*;

import java.util.Arrays;

class ActivityTest {

    @Test
    void createCEADefinition() {
        Activity activity = new Activity();
        activity.setId(1L);
        activity.setName("Introduction to javascript - Math functions");
        activity.setDescription("In this activity the user identify which number is the largest & smallest in this list: 35, 26, 58, 32, 60, -1, 64 and store in the variable RESULT_MAX e RESULT_MIN");


        ActivityUnitTest unitTest1 = new ActivityUnitTest();
        unitTest1.setScore(90);
        unitTest1.setCode(new Code(LanguageEnum.JAVASCRIPT, "assert('RESULT_MAX==64')"));

        ActivityUnitTest unitTest2 = new ActivityUnitTest();
        unitTest2.setScore(90);
        unitTest2.setCode(new Code(LanguageEnum.JAVASCRIPT, "assert('RESULT_MIN==-1')"));
        activity.setActivityUnitTestList(Arrays.asList(unitTest1, unitTest2));

        Solution solution1 = new Solution();
        solution1.setId(1L);
        solution1.add(unitTest1);
        Code code1 = new Code(LanguageEnum.JAVASCRIPT, "RESULT_MAX = Math.max(35, 26, 58, 32, 60, -1, 64);");
        code1.setScore(0);
        solution1.setCode(code1);

        Solution solution2 = new Solution();
        solution1.setId(2L);
        solution2.add(unitTest2);
        Code code2 = new Code(LanguageEnum.JAVASCRIPT, "RESULT_MIN = Math.min(35, 26, 58, 32, 60, -1, 64);");
        code2.setScore(0);
        solution2.setCode(code2);

        activity.setSolution(Arrays.asList(solution1, solution2));

        activity.setReferenceSet(Arrays.asList(new BibliographicReference("Test1", "http://www.test1.com"),
                new BibliographicReference("Test2", "http://www.test2.com"),
                new BibliographicReference("Test3", "http://www.test3.com")));

        //Resolve a CEA

        ActivityInstance<Activity> activityInstance = new ActivityInstance<>(activity);
        activityInstance.setId(1L);
        var del1 = new Deliverable(solution1);
        var del2 = new Deliverable(solution2);
        //REVER - dá para criar os deliverables com a activity passada por parâmetro
        activityInstance.getDeliverable().add(del1);
        activityInstance.getDeliverable().add(del2);

        EndUser john = new EndUser(1L);

        del1.setAuthor(john);
        del1.setCode(new Code(LanguageEnum.JAVASCRIPT, "RESULT = 64;"));
        del1.getCode().addComment( 1, "Resultado parcial. Preciso de ajuda para resolver", john);

        System.out.println(del1.getCode().getCode());



    }
}
