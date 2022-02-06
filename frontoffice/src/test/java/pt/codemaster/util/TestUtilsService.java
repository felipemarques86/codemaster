package pt.codemaster.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.Code;
import pt.codemaster.adt.EndUser;
import pt.codemaster.adt.LanguageEnum;
import pt.codemaster.adt.Solution;
import pt.codemaster.adt.activity.Activity;
import pt.codemaster.services.impl.ActivityServiceImpl;
import pt.codemaster.services.impl.UsersServiceImpl;

import javax.transaction.Transactional;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class TestUtilsService {


    @Autowired
    private ActivityServiceImpl activityService;

    @Autowired
    private UsersServiceImpl usersService;

    @Transactional
    public Activity createTestActivity(Long activityId, String activityName) {

        Activity activity = new Activity();
        activity.setId(activityId);
        activity.setName(activityName);
        activity.setDescription("Test Activity Description");
        activity.setBibliographicReferenceList(Collections.emptyList());
        Solution solution1 = new Solution();
        Code code = new Code();
        code.setLanguage(LanguageEnum.JAVASCRIPT);
        code.setCode("alert('Hello World');");
        solution1.setCode(code);
        activity.getSolution().add(solution1);

        Solution solution2 = new Solution();
        Code code2 = new Code();
        code2.setLanguage(LanguageEnum.JAVASCRIPT);
        code2.setCode("alert('Hello World 2');");
        solution2.setCode(code2);
        activity.getSolution().add(solution2);

        activity = activityService.saveActivity(activity);

        return activity;
    }

    @Transactional
    public EndUser createUser(String userId) {
        EndUser user = usersService.getOrCreateUser(userId);
        EndUser user1 = usersService.getOrCreateUser(user.getId());
        assertThat(user).isEqualTo(user1);
        return user;
    }
}
