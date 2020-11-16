package org.cyao.ssm.service;

import org.cyao.ssm.dao.bean.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FeebackService {

    List<Feedback> getList();

    void saveTest4Transaction();
    void insertTest4Transaction();
}
