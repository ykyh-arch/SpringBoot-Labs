package cn.iocoder.springboot.lab58.feigndemo.entity;

/**
 * @author jaquez
 * @date 2021/11/08 15:04
 **/
public class Contributor {

    String login;
    int contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }
}
