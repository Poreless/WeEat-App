package com.client.weeat.home.bean;

import java.io.Serializable;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}活动详情的bean
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActivesBean implements Serializable {
    /**
     * active_icon : /1478770583834.png
     * active_id : 1
     * create_time : 2018-03-16-10:22
     * active_depict : 尝尝新菜品
     * active_name : 有温大的妹子吗
     * active_place : A区食堂
     * maxnum : 5
     * user : {"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.jpg","userName":"刘霞","userID":"14211160102"}
     * finish_time : 2018-03-16-12::22
     */

    private String active_icon;
    private String active_id;
    private String create_time;
    private String active_depict;
    private String active_name;
    private String active_place;
    private String maxnum;
    private UserBean user;
    private String finish_time;
    private String actshow_userid;
    private String actshow_username;
    private String active_theme;   //2018-4-12
    public String getActive_theme() {
        return active_theme;
    }

    public void setActive_theme(String active_theme) {
        this.active_theme = active_theme;
    }
    public void setActshow_userid(String actshow_userid) {
        this.actshow_userid = actshow_userid;
    }

    public void setActshow_username(String actshow_username) {
        this.actshow_username = actshow_username;
    }

    public void setActshow_usericon(String actshow_usericon) {
        this.actshow_usericon = actshow_usericon;
    }

    public String getActshow_userid() {
        return actshow_userid;
    }

    public String getActshow_username() {
        return actshow_username;
    }

    public String getActshow_usericon() {
        return actshow_usericon;
    }

    private String actshow_usericon;


    public String getActive_icon() {
        return active_icon;
    }

    public void setActive_icon(String active_icon) {
        this.active_icon = active_icon;
    }

    public String getActive_id() {
        return active_id;
    }

    public void setActive_id(String active_id) {
        this.active_id = active_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getActive_depict() {
        return active_depict;
    }

    public void setActive_depict(String active_depict) {
        this.active_depict = active_depict;
    }

    public String getActive_name() {
        return active_name;
    }

    public void setActive_name(String active_name) {
        this.active_name = active_name;
    }

    public String getActive_place() {
        return active_place;
    }

    public void setActive_place(String active_place) {
        this.active_place = active_place;
    }

    public String getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(String maxnum) {
        this.maxnum = maxnum;
    }


    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }



    public String toString() {
        return "ActivesBean{" +
                "active_name='" + active_name + '\'' +
                ", active_depict='" + active_depict + '\'' +
                ", create_time='" + create_time + '\'' +
                ", active_place='" + active_place +
                '}';
    }
}