package pt.codemaster.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.codemaster.adt.EndUser;
import pt.codemaster.repositories.EndUserRepository;
import pt.codemaster.services.IUserService;

import java.util.Optional;

@Service
public class UsersServiceImpl implements IUserService {
    @Autowired
    private EndUserRepository endUserRepository;

    public EndUser getOrCreateUser(String userId) {
        Optional<EndUser> optionalEndUser = endUserRepository.findById(userId);
        if(!optionalEndUser.isPresent()) {
            EndUser endUser = new EndUser();
            endUser.setId(userId);
            return endUserRepository.save(endUser);
        } else {
            return optionalEndUser.get();
        }
    }

    @Override
    public EndUser saveEndUser(EndUser endUser) {
        return endUserRepository.save(endUser);
    }

}
