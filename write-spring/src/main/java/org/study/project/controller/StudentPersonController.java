package org.study.project.controller;


import org.study.project.annotation.*;
import org.study.project.dao.PersonDao;
import org.study.project.service.PersonService;
import org.study.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@CyController
@CyRequestMapping("/student/")
public class StudentPersonController {

    @CyAutowired
    private PersonService personService;
    @CyAutowired
    private PersonDao personDao;
    @CyAutowired
    private UserService userService;

    @CyRequestMapping("/login.do/")
    public String login(String username,String password){

        new HashMap<>();
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        personService.pringName();

        return "username:"+username+"  password:"+password;
    }
}
