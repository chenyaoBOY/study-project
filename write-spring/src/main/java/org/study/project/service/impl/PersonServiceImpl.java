package org.study.project.service.impl;

import org.study.project.annotation.CyService;
import org.study.project.service.PersonService;

@CyService
public class PersonServiceImpl implements PersonService {


    public void pringName() {
        System.out.println("printName================");
    }
}
