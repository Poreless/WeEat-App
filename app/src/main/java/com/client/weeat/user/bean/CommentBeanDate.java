package com.client.weeat.user.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * 数据类，用户创建的活动汇总，以及评价
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class CommentBeanDate {


    /**
     * msg : success
     * result : {"active_comment":[{"commentlist":[{"date":"2018-04-18 01:08:01","text":"123","active_id":1,"user":{"userPwd":"006","userSex":"男","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},"user_id":"14211160106","num":4}],"active_icon":"/1.jpg","active_id":1,"create_time":"2018-03-16 10:22:00","active_depict":"尝尝新菜品","active_name":"新生报到","active_place":"A区食堂","maxnum":5,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},"finish_time":"2018-03-16 12:22:00"},{"commentlist":[],"active_id":5,"create_time":"2018-03-16 10:22:00","active_depict":"能帮忙复习一下大学物理吗^-^","active_name":"有杭电的校友吗","maxnum":3,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},"finish_time":"2018-04-17 02:42:19"},{"commentlist":[],"active_id":12,"create_time":"2018-04-16 07:20:00","active_depict":"描述啊","active_name":"标题啊","active_place":"地点啊","maxnum":5,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},"finish_time":"2018-04-17 03:09:30"},{"commentlist":[],"active_id":13,"create_time":"2018-04-17 01:24:00","active_depict":"测试结束活动","active_name":"测试结束活动","active_place":"测试","maxnum":5,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},"finish_time":"2018-04-17 03:18:01"},{"commentlist":[],"active_id":14,"create_time":"2018-04-17 02:15:00","active_depict":"还是结束活动功能","active_name":"测试2","active_place":"测试","maxnum":5,"user":{"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},"finish_time":"2018-04-17 03:18:20"}]}
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
        private List<ActiveCommentBean> active_comment;

        public List<ActiveCommentBean> getActive_comment() {
            return active_comment;
        }

        public void setActive_comment(List<ActiveCommentBean> active_comment) {
            this.active_comment = active_comment;
        }

        public static class ActiveCommentBean {
            /**
             * commentlist : [{"date":"2018-04-18 01:08:01","text":"123","active_id":1,"user":{"userPwd":"006","userSex":"男","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},"user_id":"14211160106","num":4}]
             * active_icon : /1.jpg
             * active_id : 1
             * create_time : 2018-03-16 10:22:00
             * active_depict : 尝尝新菜品
             * active_name : 新生报到
             * active_place : A区食堂
             * maxnum : 5
             * user : {"userPwd":"002","userSex":"女","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"}
             * finish_time : 2018-03-16 12:22:00
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
            private List<CommentlistBean> commentlist;

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

            public List<CommentlistBean> getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(List<CommentlistBean> commentlist) {
                this.commentlist = commentlist;
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

            public static class CommentlistBean {
                /**
                 * date : 2018-04-18 01:08:01
                 * text : 123
                 * active_id : 1
                 * user : {"userPwd":"006","userSex":"男","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"}
                 * user_id : 14211160106
                 * num : 4
                 */

                private String date;
                private String text;
                private int active_id;
                private UserBeanX user;
                private String user_id;
                private int num;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getActive_id() {
                    return active_id;
                }

                public void setActive_id(int active_id) {
                    this.active_id = active_id;
                }

                public UserBeanX getUser() {
                    return user;
                }

                public void setUser(UserBeanX user) {
                    this.user = user;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public static class UserBeanX {
                    /**
                     * userPwd : 006
                     * userSex : 男
                     * userTel : 18757708103
                     * userIcon : /14211160106.png
                     * userName : 季鹏
                     * userID : 14211160106
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
}
