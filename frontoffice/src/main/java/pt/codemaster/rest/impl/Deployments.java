package pt.codemaster.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.activity.ActivityDeployRequest;
import pt.codemaster.rest.IConfigProvider;
import pt.codemaster.services.IDeploymentService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@CrossOrigin
public class Deployments implements IConfigProvider {

    @Autowired
    private IDeploymentService deploymentService;

    @PostMapping(value = "/deployment", consumes = APPLICATION_JSON)
    public ModelAndView deployment(@RequestBody ActivityDeployRequest deployRequest) {
        ActivityInstance instance = this.deploymentService.createInstance(Long.parseLong(deployRequest.getActivityID()), deployRequest.getInveniRAstdID(), deployRequest.getJson_params());
        System.out.println("#/activity/" + instance.getId() + "/user/" + deployRequest.getInveniRAstdID() + "/index.html");
        return new ModelAndView("redirect: #/activity/" + instance.getId() + "/user/" + deployRequest.getInveniRAstdID() + "/index.html");
    }

    @Override
    @GetMapping(value = "/config")
    @ResponseBody
    public ModelAndView config() {
        return new ModelAndView("redirect:" + "#/config.html");
    }
}
