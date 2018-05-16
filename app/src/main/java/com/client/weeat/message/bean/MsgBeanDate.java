package com.client.weeat.message.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MsgBeanDate {

    /**
     * msg : success
     * result : {"active_info":[{"active_icon":"/2.jpg","active_id":2,"create_time":"2018-01-01-00:00","active_depict":"来个掏钱的","active_name":"生日聚会","active_place":"世纪大酒店","maxnum":3,"user":{"userPwd":"001","userSex":"女","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"},"finish_time":"2018-01-01-02:30"}],"request_act_info":[{"date":"2018-04-10 01:52:40.0","active_id":"2","user":{"userPwd":"001","userSex":"女","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"},"active_name":"生日聚会","num":"0"}]}
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
        private List<ActiveInfoBean> active_info;
        private List<RequestActInfoBean> request_act_info;

        public List<ActiveInfoBean> getActive_info() {
            return active_info;
        }

        public void setActive_info(List<ActiveInfoBean> active_info) {
            this.active_info = active_info;
        }

        public List<RequestActInfoBean> getRequest_act_info() {
            return request_act_info;
        }

        public void setRequest_act_info(List<RequestActInfoBean> request_act_info) {
            this.request_act_info = request_act_info;
        }

        public static class ActiveInfoBean {
            /**
             * active_icon : /2.jpg
             * active_id : 2
             * create_time : 2018-01-01-00:00
             * active_depict : 来个掏钱的
             * active_name : 生日聚会
             * active_place : 世纪大酒店
             * maxnum : 3
             * user : {"userPwd":"001","userSex":"女","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"}
             * finish_time : 2018-01-01-02:30
             */

            private String active_icon;
            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private String active_place;
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

            public String getActive_place() {
                return active_place;
            }

            public void setActive_place(String active_place) {
                this.active_place = active_place;
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
                 * userPwd : 001
                 * userSex : 女
                 * userTel : 18757708103
                 * userIcon : /14211160101.png
                 * userName : 杨莉
                 * userID : 14211160101
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

        public static class RequestActInfoBean {
            /**
             * date : 2018-04-10 01:52:40.0
             * active_id : 2
             * user : {"userPwd":"001","userSex":"女","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"}
             * active_name : 生日聚会
             * num : 0
             */

            private String date;
            private String active_id;
            private UserBeanX user;
            private String active_name;
            private String num;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public static class UserBeanX {
                /**
                 * userPwd : 001
                 * userSex : 女
                 * userTel : 18757708103
                 * userIcon : /14211160101.png
                 * userName : 杨莉
                 * userID : 14211160101
                 */

                private String userPwd;
                private String userSex;
                private String userTel;
                private String userIcon;
                private String userName;
                private String userID;

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
