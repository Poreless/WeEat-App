package com.client.weeat.message.bean;

import java.io.Serializable;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FreshActInfoBean implements Serializable {
    /**
     * active_id : 5
     * create_time : 2018-03-16 10:22:00.0
     * active_depict : 能帮忙复习一下大学物理吗^-^
     * active_name : 有杭电的校友吗
     * maxnum : 3
     * active_place : 地点啊
     */

    private int active_id;
    private String create_time;
    private String active_depict;
    private String active_name;
    private int maxnum;
    private String active_place;
    private String active_theme;

    public String getActive_theme(){
        return active_theme;
    }
    public void setActive_theme(String active_theme) {
        this.active_theme = active_theme;
    }
    public int getActive_id() {
        return active_id;
    }

    public void setActive_id(int active_id) {
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

    public int getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(int maxnum) {
        this.maxnum = maxnum;
    }

    public String getActive_place() {
        return active_place;
    }

    public void setActive_place(String active_place) {
        this.active_place = active_place;
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
