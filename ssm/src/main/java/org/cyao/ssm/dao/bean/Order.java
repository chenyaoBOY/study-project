package org.cyao.ssm.dao.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
/**
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `code` varchar(255) NOT NULL,
 *   `create_time` datetime DEFAULT NULL,
 *   `user_Id` int(11) NOT NULL,
 */
    private int id;
    private String code;
    private Date create_time;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
