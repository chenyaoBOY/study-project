package zk.org.study.globalconfig;

import java.io.Serializable;

/**
 * Author: chenyao
 * Date: 2019/4/10 15:09
 * Description:配置管理的类 存储配置信息
 */
public class Config implements Serializable {
    private static final long serialVersionUID = 8874038706993291017L;

    private String username;
    private String password;
    private String ip;
    private int poolSize;
    private int coreSize;

    public Config() {
    }

    public Config(String username, String password, String ip, int poolSize, int coreSize) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.poolSize = poolSize;
        this.coreSize = coreSize;
    }

    @Override
    public String toString() {
        return "Config{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", poolSize=" + poolSize +
                ", coreSize=" + coreSize +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }
}
