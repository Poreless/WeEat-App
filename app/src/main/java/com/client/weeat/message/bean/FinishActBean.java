package com.client.weeat.message.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * 在结构上和主页的msgBean以及freshbean差不多，可以省去
 * 额外创建一个指示参数，用于标记用户操作的不必等用户回传的数据
 * @updateDes ${TODO}
 */
public class FinishActBean {

    /**
     * msg : success
     * result : {"active_finish_info":[{"active_icon":"/1.jpg","active_id":1,"create_time":"2018-03-16 10:22:00","active_depict":"尝尝新菜品","active_name":"有温大的妹子吗","active_theme":"奶茶/果汁","maxnum":5,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},"finish_time":"2018-03-16 12:22:00"},{"active_icon":"/3.jpg","active_id":3,"create_time":"2018-01-01 00:00:00","active_depict":"AA制","active_name":"吃饭顺便交友","active_theme":"自助餐","maxnum":6,"user":{"userPwd":"003","userSex":"男","userTel":"18757708103","userIcon":"/14211160103.png","userName":"刘伟","userID":"14211160103"},"finish_time":"2018-01-01 02:30:00"},{"active_icon":"/4.jpg","active_id":4,"create_time":"2018-01-01 00:00:00","active_depict":"不醉不归","active_name":"公司庆功宴","active_theme":"快餐","maxnum":6,"user":{"userPwd":"004","userSex":"女","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-01-01 02:30:00"}]}
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
        private List<ActiveFinishInfoBean> active_finish_info;

        public List<ActiveFinishInfoBean> getActive_finish_info() {
            return active_finish_info;
        }

        public void setActive_finish_info(List<ActiveFinishInfoBean> active_finish_info) {
            this.active_finish_info = active_finish_info;
        }

        public static class ActiveFinishInfoBean {
            /**
             * active_icon : /1.jpg
             * active_id : 1
             * create_time : 2018-03-16 10:22:00
             * active_depict : 尝尝新菜品
             * active_name : 有温大的妹子吗
             * active_theme : 奶茶/果汁
             * maxnum : 5
             * user : {"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"}
             * finish_time : 2018-03-16 12:22:00
             */

            private String active_icon;
            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private String active_theme;
            private int maxnum;
            private UserBean user;
            private String finish_time;

            public String getActive_icon() {
                return active_icon;
            }

            public void setActive_icon(String active_icon) {
                this.active_icon = active_icon;
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

            public String getActive_theme() {
                return active_theme;
            }

            public void setActive_theme(String active_theme) {
                this.active_theme = active_theme;
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

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
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
