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
import java.util.List;

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
        Activity activity = testUtils.createTestActivity(1L, "Activity Test");
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
        Activity activity = testUtils.createTestActivity(1L, "Activity Test");

        Activity activityDb = activityService.getActivity(activity.getId());
        assertThat(activityDb).isEqualTo(activity);
    }

    @Test
    @Rollback
    void testSubmit() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(1L, "Activity Test");
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

    @Test
    @Transactional
    @Rollback
    void testAddComment() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(1L, "Activity Test");
        ActivityInstance activityInstance = activityService.createInstance(activity.getId(), user.getId(), activity);

        assertThat(activityInstance).isNotNull();

        Deliverable deliverable = activityInstance.getDeliverable().get(0);
        Code deliverableCode = new Code();
        deliverableCode.setCode("alert('Work in progress!');");
        deliverableCode.setLanguage(LanguageEnum.JAVASCRIPT);
        deliverableCode.setDeliverable(deliverable);
        activityService.saveCode(deliverableCode);

        deliverable.setCode(deliverableCode);
        deliverable.setAuthor(user);


        Comment comment = new Comment();
        comment.setContent("Test comment");
        comment.setCode(deliverableCode);
        Code dbCode = activityService.addComment(deliverableCode.getId(), user.getId(), 1L, comment);
        assertThat(dbCode.getCode()).contains("/**<comment>");

        Code dbCode2 = activityService.getCode(dbCode.getId());
        assertThat(dbCode2).isEqualTo(dbCode);
    }

    @Test
    @Transactional
    @Rollback
    void testGetDeliverable() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(1L, "Activity Test");
        ActivityInstance activityInstance = activityService.createInstance(activity.getId(), user.getId(), activity);

        assertThat(activityInstance).isNotNull();

        Deliverable deliverable = activityInstance.getDeliverable().get(0);
        Code deliverableCode = new Code();
        deliverableCode.setCode("alert('Work in progress 2!');");
        deliverableCode.setLanguage(LanguageEnum.JAVASCRIPT);
        deliverableCode.setDeliverable(deliverable);
        activityService.saveCode(deliverableCode);

        Deliverable deliverableDb = activityService.getDeliverable(deliverable.getId());
        assertThat(deliverableDb).isEqualTo(deliverable);
    }

    @Test
    @Transactional
    @Rollback
    void testGetInstances() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(1L, "Activity Test 1");
        ActivityInstance instance1 = activityService.createInstance(1L, user.getId(), activity);

        Activity activity2 = testUtils.createTestActivity(2L, "Activity Test 2");
        ActivityInstance instance2 = activityService.createInstance(2L, user.getId(), activity2);

        List<ActivityInstance> instances = activityService.getInstances(user);
        assertThat(instances).isNotNull().contains(instance1, instance2);
    }

    @Test
    @Transactional
    @Rollback
    void testReplyComment() {
        EndUser user = testUtils.createUser("user1234");
        Activity activity = testUtils.createTestActivity(1L, "Activity Test");
        ActivityInstance activityInstance = activityService.createInstance(activity.getId(), user.getId(), activity);

        assertThat(activityInstance).isNotNull();

        Deliverable deliverable = activityInstance.getDeliverable().get(0);
        Code deliverableCode = new Code();
        deliverableCode.setCode("alert('Work in progress!');");
        deliverableCode.setLanguage(LanguageEnum.JAVASCRIPT);
        deliverableCode.setDeliverable(deliverable);
        activityService.saveCode(deliverableCode);

        deliverable.setCode(deliverableCode);
        deliverable.setAuthor(user);


        Comment comment = new Comment();
        comment.setContent("Test comment");
        comment.setCode(deliverableCode);
        activityService.addComment(deliverableCode.getId(), user.getId(), 1L, comment);

        EndUser user2 = testUtils.createUser("user4321");
        activityService.createInstance(activity.getId(), user2.getId(), activity);

        Comment comment2 = new Comment();
        comment2.setContent("Reply to Test comment");
        comment2.setCode(deliverableCode);

        assertThat(comment.getId()).isNotNull().isNotEqualTo(0L);

        activityService.replyComment(user2.getId(), comment.getId(), comment2);

    }
}
