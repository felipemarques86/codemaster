package pt.codemaster.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.codemaster.adt.EndUser;
import pt.codemaster.rest.IUsersInternalRepository;
import pt.codemaster.services.IUserService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class Users implements IUsersInternalRepository {

    @Autowired
    private IUserService userService;

    @PostMapping(value="/user", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public EndUser saveEndUser(@RequestBody EndUser endUser) {
        return userService.saveEndUser(endUser);
    }
}
