package com.client.weeat.home.bean;

import java.util.List;

/**
 * @author Admin
 * @version 使用GsonFormat实例化jason
 * @des ${TODO}
 * @updateAuthor $Author$ 用于显示主页RecyclerView的显示信息
 * @updateDes ${TODO}
 */
public class ResultBeanDate {


    /**
     * msg : success
     * result : {"active_school":[{"active_id":11,"create_time":"2018-05-07 03:24:00","active_depict":"活动描述1","active_name":"这是标题","active_theme":"主题1","active_place":"某饭店","maxnum":5,"user":{"userSex":"女","school":"温州大学","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-04-17 16:17:40"},{"active_id":17,"create_time":"2018-04-28 16:18:00","active_depict":"测试","active_name":"测试5","active_theme":"555","active_place":"测试","maxnum":5,"user":{"userSex":"男","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userID":"14211160102"}},{"active_id":18,"create_time":"2018-05-10 03:05:00","active_depict":"草莓","active_name":"兴趣爱好测试","active_theme":"果汁","active_place":"冷饮店","maxnum":5,"user":{"userSex":"男","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userID":"14211160102"}}],"active_love":[{"active_id":18,"create_time":"2018-05-10 03:05:00","active_depict":"草莓","active_name":"兴趣爱好测试","active_theme":"果汁","active_place":"冷饮店","maxnum":5,"user":{"userSex":"男","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userID":"14211160102"}}],"extend_info":[{"extend_name":"广告1","icon_url":"/a.jpg","web_url":"/oper/1478169868app.html"},{"extend_name":"广告2","icon_url":"/b.jpg","web_url":"/oper/1478763176app.html"},{"extend_name":"广告4","icon_url":"/c.jpg","web_url":"/operation/img/1478169868/1478761370286.png"},{"extend_name":"广告5","icon_url":"/d.jpg","web_url":"/operation/img/1478169868/1478761370286.png"},{"extend_name":"广告6","icon_url":"/e.jpg","web_url":"/operation/img/1478169868/1478761370286.png"}],"active_info":[{"active_id":9,"create_time":"2018-05-06 01:05:03","active_depict":"能帮忙搬东西更好了^-^","active_name":"有去野餐的吗","active_theme":"野餐","active_place":"公园","maxnum":3,"user":{"userSex":"男","school":"丽水学院","userPwd":"005","userTel":"18757708103","userIcon":"/14211160105.png","userName":"何he","userID":"14211160105"},"finish_time":"2018-04-17 02:32:00"},{"active_id":11,"create_time":"2018-05-07 03:24:00","active_depict":"活动描述1","active_name":"这是标题","active_theme":"主题1","active_place":"某饭店","maxnum":5,"user":{"userSex":"女","school":"温州大学","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"},"finish_time":"2018-04-17 16:17:40"},{"active_id":15,"create_time":"2018-05-03 01:08:00","active_depict":"近的人来","active_name":"南门外","active_theme":"烧烤","active_place":"南门外","maxnum":5,"user":{"userSex":"女","userPwd":"001","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"}},{"active_id":17,"create_time":"2018-04-28 16:18:00","active_depict":"测试","active_name":"测试5","active_theme":"555","active_place":"测试","maxnum":5,"user":{"userSex":"男","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userID":"14211160102"}},{"active_id":18,"create_time":"2018-05-10 03:05:00","active_depict":"草莓","active_name":"兴趣爱好测试","active_theme":"果汁","active_place":"冷饮店","maxnum":5,"user":{"userSex":"男","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userID":"14211160102"}}],"active_friend":[{"commentlist":[],"active_id":9,"create_time":"2018-05-06 01:05:03","active_depict":"能帮忙搬东西更好了^-^","active_name":"有去野餐的吗","active_place":"公园","maxnum":3,"user":{"userSex":"男","school":"丽水学院","userPwd":"005","userTel":"18757708103","userIcon":"/14211160105.png","userName":"何he","userID":"14211160105"},"finish_time":"2018-04-17 02:32:00"}]}
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
        private List<ActiveSchoolBean> active_school;
        private List<ActiveLoveBean> active_love;
        private List<ExtendInfoBean> extend_info;
        private List<ActiveInfoBean> active_info;
        private List<ActiveFriendBean> active_friend;

        public List<ActiveSchoolBean> getActive_school() {
            return active_school;
        }

        public void setActive_school(List<ActiveSchoolBean> active_school) {
            this.active_school = active_school;
        }

        public List<ActiveLoveBean> getActive_love() {
            return active_love;
        }

        public void setActive_love(List<ActiveLoveBean> active_love) {
            this.active_love = active_love;
        }

        public List<ExtendInfoBean> getExtend_info() {
            return extend_info;
        }

        public void setExtend_info(List<ExtendInfoBean> extend_info) {
            this.extend_info = extend_info;
        }

        public List<ActiveInfoBean> getActive_info() {
            return active_info;
        }

        public void setActive_info(List<ActiveInfoBean> active_info) {
            this.active_info = active_info;
        }

        public List<ActiveFriendBean> getActive_friend() {
            return active_friend;
        }

        public void setActive_friend(List<ActiveFriendBean> active_friend) {
            this.active_friend = active_friend;
        }

        public static class ActiveSchoolBean {
            /**
             * active_id : 11
             * create_time : 2018-05-07 03:24:00
             * active_depict : 活动描述1
             * active_name : 这是标题
             * active_theme : 主题1
             * active_place : 某饭店
             * maxnum : 5
             * user : {"userSex":"女","school":"温州大学","userPwd":"004","userTel":"18757708103","userIcon":"/14211160104.png","userName":"赵梦","userID":"14211160104"}
             * finish_time : 2018-04-17 16:17:40
             */

            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private String active_theme;
            private String active_place;
            private int maxnum;
            private UserBean user;
            private String finish_time;
            private String active_icon;

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
                 * userSex : 女
                 * school : 温州大学
                 * userPwd : 004
                 * userTel : 18757708103
                 * userIcon : /14211160104.png
                 * userName : 赵梦
                 * userID : 14211160104
                 */

                private String userSex;
                private String school;
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

        public static class ActiveLoveBean {
            /**
             * active_id : 18
             * create_time : 2018-05-10 03:05:00
             * active_depict : 草莓
             * active_name : 兴趣爱好测试
             * active_theme : 果汁
             * active_place : 冷饮店
             * maxnum : 5
             * user : {"userSex":"男","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userID":"14211160102"}
             */

            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private String active_theme;
            private String active_place;
            private int maxnum;
            private UserBeanX user;
            private String active_icon;

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

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public static class UserBeanX {
                /**
                 * userSex : 男
                 * school : 温州大学
                 * userPwd : 002
                 * userTel : 18757708103
                 * userIcon : /20180427_014706.jpg
                 * userName : 井底之蛙
                 * userID : 14211160102
                 */

                private String userSex;
                private String school;
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

        public static class ExtendInfoBean {
            /**
             * extend_name : 广告1
             * icon_url : /a.jpg
             * web_url : /oper/1478169868app.html
             */

            private String extend_name;
            private String icon_url;
            private String web_url;

            public String getExtend_name() {
                return extend_name;
            }

            public void setExtend_name(String extend_name) {
                this.extend_name = extend_name;
            }

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }
        }

        public static class ActiveInfoBean {
            /**
             * active_id : 9
             * create_time : 2018-05-06 01:05:03
             * active_depict : 能帮忙搬东西更好了^-^
             * active_name : 有去野餐的吗
             * active_theme : 野餐
             * active_place : 公园
             * maxnum : 3
             * user : {"userSex":"男","school":"丽水学院","userPwd":"005","userTel":"18757708103","userIcon":"/14211160105.png","userName":"何he","userID":"14211160105"}
             * finish_time : 2018-04-17 02:32:00
             */
            private String active_icon;
            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private String active_theme;
            private String active_place;
            private int maxnum;
            private UserBeanXX user;
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

            public UserBeanXX getUser() {
                return user;
            }

            public void setUser(UserBeanXX user) {
                this.user = user;
            }

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
            }

            public static class UserBeanXX {
                /**
                 * userSex : 男
                 * school : 丽水学院
                 * userPwd : 005
                 * userTel : 18757708103
                 * userIcon : /14211160105.png
                 * userName : 何he
                 * userID : 14211160105
                 */

                private String userSex;
                private String school;
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

        public static class ActiveFriendBean {
            /**
             * commentlist : []
             * active_id : 9
             * create_time : 2018-05-06 01:05:03
             * active_depict : 能帮忙搬东西更好了^-^
             * active_name : 有去野餐的吗
             * active_place : 公园
             * maxnum : 3
             * user : {"userSex":"男","school":"丽水学院","userPwd":"005","userTel":"18757708103","userIcon":"/14211160105.png","userName":"何he","userID":"14211160105"}
             * finish_time : 2018-04-17 02:32:00
             */

            private int active_id;
            private String create_time;
            private String active_depict;
            private String active_name;
            private String active_place;
            private int maxnum;
            private UserBeanXXX user;
            private String finish_time;
            private List<?> commentlist;
            private String active_icon;
            private String active_theme;

            public String getActive_theme() {
                return active_theme;
            }

            public void setActive_theme(String active_theme) {
                this.active_theme = active_theme;
            }
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

            public UserBeanXXX getUser() {
                return user;
            }

            public void setUser(UserBeanXXX user) {
                this.user = user;
            }

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
            }

            public List<?> getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(List<?> commentlist) {
                this.commentlist = commentlist;
            }

            public static class UserBeanXXX {
                /**
                 * userSex : 男
                 * school : 丽水学院
                 * userPwd : 005
                 * userTel : 18757708103
                 * userIcon : /14211160105.png
                 * userName : 何he
                 * userID : 14211160105
                 */

                private String userSex;
                private String school;
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
