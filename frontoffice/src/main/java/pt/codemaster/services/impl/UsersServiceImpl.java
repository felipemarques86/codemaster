package pt.codemaster.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.EndUser;
import pt.codemaster.repositories.UserRepository;
import pt.codemaster.services.IUserService;

import java.util.Optional;

@Service
public class UsersServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public EndUser getOrCreateUser(String userId) {
        Optional<EndUser> optionalEndUser = userRepository.findById(userId);
        if(!optionalEndUser.isPresent()) {
            EndUser endUser = new EndUser();
            endUser.setId(userId);
            return userRepository.save(endUser);
        } else {
            return optionalEndUser.get();
        }
    }

    @Override
    public EndUser saveEndUser(EndUser endUser) {
        return userRepository.save(endUser);
    }

}
