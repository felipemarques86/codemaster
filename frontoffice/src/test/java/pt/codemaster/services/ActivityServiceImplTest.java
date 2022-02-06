package pt.codemaster.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import pt.codemaster.adt.*;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.frontoffice.CodeMasterApplication;
import pt.codemaster.services.impl.ActivityServiceImpl;
import pt.codemaster.util.TestUtilsService;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CodeMasterApplication.class})
@ActiveProfiles("test")
public class ActivityServiceImplTest {

    @Autowired
    private ActivityServiceImpl activityService;

    @Autowired
    private TestUtilsService testUtils;

    @Test
    void contextLoads() {
        assertThat(activityService).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    void testGetInstance() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(user.getId());
        ActivityInstance activityInstance = activityService.createInstance(activity.getId(), user.getId(), activity);

        ActivityInstance activityInstanceDb = activityService.getInstance(activityInstance.getId());
        assertThat(activityInstanceDb).isEqualTo(activityInstance);

        try {
            activityService.getInstance(null);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).contains("The given id must not be null");
        }

        ActivityInstance instance = activityService.getInstance(300L);


    }

    @Test
    @Transactional
    @Rollback
    void testGetActivity() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(user.getId());

        Activity activityDb = activityService.getActivity(activity.getId());
        assertThat(activityDb).isEqualTo(activity);
    }

    @Test
    @Rollback
    void testSubmit() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(user.getId());
        ActivityInstance activityInstance = activityService.createInstance(activity.getId(), user.getId(), activity);

        assertThat(activityInstance).isNotNull();

        Long ceaId = activityInstance.getId();
        Deliverable deliverable = activityInstance.getDeliverable().get(0);
        Code code2 = new Code();
        code2.setCode("alert('Work in progress!');");
        code2.setLanguage(LanguageEnum.JAVASCRIPT);
        code2.setDeliverable(deliverable);
        activityService.saveCode(code2);

        deliverable.setCode(code2);
        deliverable.setAuthor(user);


        Deliverable deliverable1 = activityService.submit(ceaId, deliverable);
        assertThat(deliverable1).isNotNull();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            activityService.submit(ceaId, deliverable1);
        } catch(Exception ex) {
            assertThat(ex.getMessage()).contains("Já efetuou a submissão");
        }
    }
}
