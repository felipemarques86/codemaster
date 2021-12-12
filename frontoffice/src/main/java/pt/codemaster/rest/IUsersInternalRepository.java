package pt.codemaster.rest;

import pt.codemaster.adt.EndUser;

public interface IUsersInternalRepository {
    EndUser saveEndUser(EndUser endUser);
}
