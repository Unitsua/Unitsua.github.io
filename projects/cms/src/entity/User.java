package entity;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.Date;

public class User {
    private int uid;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String uname;

    public String getUname () {
        return uname;
    }

    public void setUname (String uname) {
        this.uname = uname;
    }

    public int getUid () {
        return uid;
    }

    public void setUid (int uid) {
        this.uid = uid;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
        this.updateTime = updateTime;
    }
}
