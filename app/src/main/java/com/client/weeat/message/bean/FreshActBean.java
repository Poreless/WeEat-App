package com.client.weeat.message.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FreshActBean {

    /**
     * msg : success
     * result : {"fresh_list":[{"active_id":5,"create_time":"2018-03-16 10:22:00.0","active_depict":"能帮忙复习一下大学物理吗^-^","active_name":"有杭电的校友吗","maxnum":3,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"}},{"active_id":12,"create_time":"2018-04-16 07:20:00.0","active_depict":"描述啊","active_name":"标题啊","active_place":"地点啊","maxnum":5,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"}}]}
     * code : 200
     */

    private String msg;
    private ResultBean result;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        private List<FreshListBean> fresh_list;

        public List<FreshListBean> getFresh_list() {
            return fresh_list;
        }

        public void setFresh_list(List<FreshListBean> fresh_list) {
            this.fresh_list = fresh_list;
        }

        public static class FreshListBean {
            /**
             * active_id : 5
             * create_time : 2018-03-16 10:22:00.0
             * active_depict : 能帮忙复习一下大学物理吗^-^
             * active_name : 有杭电的校友吗
             * maxnum : 3
             * user : {"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"}
             * active_place : 地点啊
             */

            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private int maxnum;
            private UserBean user;
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getActive_place() {
                return active_place;
            }

            public void setActive_place(String active_place) {
                this.active_place = active_place;
            }

            public static class UserBean {
                /**
                 * userPwd : 002
                 * userSex : 女
                 * userTel : 18757708103
                 * userIcon : /14211160102.png
                 * userName : 刘霞
                 * userID : 14211160102
                 */

                private String userPwd;
                private String userSex;
                private String userTel;
                private String userIcon;
                private String userName;
                private String userID;
                private String school;

                public String getSchool() {
                    return school;
                }

                public void setSchool(String school) {
                    this.school = school;
                }
                public String getUserPwd() {
                    return userPwd;
                }

                public void setUserPwd(String userPwd) {
                    this.userPwd = userPwd;
                }

                public String getUserSex() {
                    return userSex;
                }

                public void setUserSex(String userSex) {
                    this.userSex = userSex;
                }

                public String getUserTel() {
                    return userTel;
                }

                public void setUserTel(String userTel) {
                    this.userTel = userTel;
                }

                public String getUserIcon() {
                    return userIcon;
                }

                public void setUserIcon(String userIcon) {
                    this.userIcon = userIcon;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getUserID() {
                    return userID;
                }

                public void setUserID(String userID) {
                    this.userID = userID;
                }
            }
        }
    }
}
