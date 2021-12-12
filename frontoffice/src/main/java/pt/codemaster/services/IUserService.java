package pt.codemaster.services;

import pt.codemaster.adt.EndUser;

public interface IUserService {
    EndUser saveEndUser(EndUser endUser);
    EndUser getOrCreateUser(String userId);
}
