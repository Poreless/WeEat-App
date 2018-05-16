package com.client.weeat.friend.bean;

import java.util.List;

/**
 * @author Admin好友主页的bean
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FriendMessageBean {


    /**
     * msg : success
     * result : {"friend_info":{"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"active_comment":[{"commentlist":[{"date":"2018-04-18 01:52:01","text":"主办很可爱，很细心，赞一个！！","active_id":4,"user":{"userSex":"男","userPwd":"006","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},"user_id":"14211160106","num":4}],"active_icon":"/4.jpg","active_id":4,"create_time":"2018-01-01 00:00:00","active_depict":"不醉不归","active_name":"公司庆功宴","active_place":"西城酒楼","maxnum":6,"user":{"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-01-01 02:30:00"},{"commentlist":[],"active_id":11,"create_time":"2018-04-12 03:24:00","active_depict":"活动描述1","active_name":"这是标题","active_place":"某饭店","maxnum":5,"user":{"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-04-17 16:17:40"}]}
     * code : 200
     * tag : [{"name":"沙拉"}]
     */

    private String msg;
    private ResultBean result;
    private int code;
    private String tag;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static class ResultBean {
        /**
         * friend_info : {"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"}
         * active_comment : [{"commentlist":[{"date":"2018-04-18 01:52:01","text":"主办很可爱，很细心，赞一个！！","active_id":4,"user":{"userSex":"男","userPwd":"006","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},"user_id":"14211160106","num":4}],"active_icon":"/4.jpg","active_id":4,"create_time":"2018-01-01 00:00:00","active_depict":"不醉不归","active_name":"公司庆功宴","active_place":"西城酒楼","maxnum":6,"user":{"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-01-01 02:30:00"},{"commentlist":[],"active_id":11,"create_time":"2018-04-12 03:24:00","active_depict":"活动描述1","active_name":"这是标题","active_place":"某饭店","maxnum":5,"user":{"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-04-17 16:17:40"}]
         */

        private FriendInfoBean friend_info;
        private List<ActiveCommentBean> active_comment;

        public FriendInfoBean getFriend_info() {
            return friend_info;
        }

        public void setFriend_info(FriendInfoBean friend_info) {
            this.friend_info = friend_info;
        }

        public List<ActiveCommentBean> getActive_comment() {
            return active_comment;
        }

        public void setActive_comment(List<ActiveCommentBean> active_comment) {
            this.active_comment = active_comment;
        }

        public static class FriendInfoBean {
            /**
             * userSex : 女
             * userPwd : 004
             * userTel : 18757708103
             * userIcon : /14211160104.png
             * userName : 赵梦
             * userID : 14211160104
             */

            private String userSex;
            private String userPwd;
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
            public String getUserSex() {
                return userSex;
            }

            public void setUserSex(String userSex) {
                this.userSex = userSex;
            }

            public String getUserPwd() {
                return userPwd;
            }

            public void setUserPwd(String userPwd) {
                this.userPwd = userPwd;
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

        public static class ActiveCommentBean {
            /**
             * commentlist : [{"date":"2018-04-18 01:52:01","text":"主办很可爱，很细心，赞一个！！","active_id":4,"user":{"userSex":"男","userPwd":"006","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},"user_id":"14211160106","num":4}]
             * active_icon : /4.jpg
             * active_id : 4
             * create_time : 2018-01-01 00:00:00
             * active_depict : 不醉不归
             * active_name : 公司庆功宴
             * active_place : 西城酒楼
             * maxnum : 6
             * user : {"userSex":"女","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"}
             * finish_time : 2018-01-01 02:30:00
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
                 * userSex : 女
                 * userPwd : 004
                 * userTel : 18757708103
                 * userIcon : /14211160104.png
                 * userName : 赵梦
                 * userID : 14211160104
                 */

                private String userSex;
                private String userPwd;
                private String userTel;
                private String userIcon;
                private String userName;
                private String userID;

                public String getUserSex() {
                    return userSex;
                }

                public void setUserSex(String userSex) {
                    this.userSex = userSex;
                }

                public String getUserPwd() {
                    return userPwd;
                }

                public void setUserPwd(String userPwd) {
                    this.userPwd = userPwd;
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
                 * date : 2018-04-18 01:52:01
                 * text : 主办很可爱，很细心，赞一个！！
                 * active_id : 4
                 * user : {"userSex":"男","userPwd":"006","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"}
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
                     * userSex : 男
                     * userPwd : 006
                     * userTel : 18757708103
                     * userIcon : /14211160106.png
                     * userName : 季鹏
                     * userID : 14211160106
                     */

                    private String userSex;
                    private String userPwd;
                    private String userTel;
                    private String userIcon;
                    private String userName;
                    private String userID;

                    public String getUserSex() {
                        return userSex;
                    }

                    public void setUserSex(String userSex) {
                        this.userSex = userSex;
                    }

                    public String getUserPwd() {
                        return userPwd;
                    }

                    public void setUserPwd(String userPwd) {
                        this.userPwd = userPwd;
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
