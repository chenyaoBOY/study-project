package org.study.project.controller;

import org.study.project.annotation.*;

@CyController
@CyRequestMapping("/teacher/")
public class TeacherPersonController {


    @CyRequestMapping("/show.do")
    @CyResponseBody
    public String show(@CyRequestParam(value = "name",defaultValue = "admin") String username,
                       String password, int age){

        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("age:"+age);

        return "";
    }
}
