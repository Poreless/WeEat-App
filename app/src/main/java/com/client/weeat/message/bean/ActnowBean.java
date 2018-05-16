package com.client.weeat.message.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}  MessageFragment显示的bean
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActnowBean {

    /**
     * msg : success
     * result : [{"act":{"active_depict":"能帮忙复习一下大学物理吗^-^","active_icon":"/1.jpg","active_id":5,"active_name":"有杭电的校友吗","commentlist":[],"create_time":"2018-03-16 10:22:00","maxnum":3,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}},"num":0},{"act":{"active_depict":"描述啊","active_icon":"/1.jpg","active_id":12,"active_name":"标题啊","active_place":"地点啊","commentlist":[],"create_time":"2018-04-16 07:20:00","maxnum":5,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}},"num":0},{"act":{"active_depict":"测试结束活动","active_icon":"/1.jpg","active_id":13,"active_name":"测试结束活动","active_place":"测试","commentlist":[],"create_time":"2018-04-17 01:24:00","maxnum":5,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}},"num":0},{"act":{"active_depict":"还是结束活动功能","active_icon":"/1.jpg","active_id":14,"active_name":"测试2","active_place":"测试","commentlist":[],"create_time":"2018-04-17 02:15:00","maxnum":5,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}},"num":0},{"act":{"active_depict":"没结束","active_icon":"/1.jpg","active_id":16,"active_name":"测试3","active_place":"地点3","commentlist":[],"create_time":"2018-04-23 03:58:00","maxnum":5,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}},"num":0},{"act":{"active_depict":"草莓","active_icon":"/1.jpg","active_id":18,"active_name":"兴趣爱好测试","active_place":"冷饮店","commentlist":[],"create_time":"2018-05-10 03:05:00","maxnum":5,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}},"num":0}]
     * code : 200
     */

    private String msg;
    private int code;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * act : {"active_depict":"能帮忙复习一下大学物理吗^-^","active_icon":"/1.jpg","active_id":5,"active_name":"有杭电的校友吗","commentlist":[],"create_time":"2018-03-16 10:22:00","maxnum":3,"user":{"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}}
         * num : 0
         */

        private ActBean act;
        private int num;

        public ActBean getAct() {
            return act;
        }

        public void setAct(ActBean act) {
            this.act = act;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public static class ActBean {
            /**
             * active_depict : 能帮忙复习一下大学物理吗^-^
             * active_icon : /1.jpg
             * active_id : 5
             * active_name : 有杭电的校友吗
             * commentlist : []
             * create_time : 2018-03-16 10:22:00
             * maxnum : 3
             * user : {"school":"温州大学","userID":"14211160102","userIcon":"/20180427_014706.jpg","userName":"井底之蛙","userPwd":"002","userSex":"男","userTel":"18757708103"}
             */

            private String active_depict;
            private String active_icon;
            private int active_id;
            private String active_name;
            private String create_time;
            private int maxnum;
            private UserBean user;
            private List<?> commentlist;

            public String getActive_depict() {
                return active_depict;
            }

            public void setActive_depict(String active_depict) {
                this.active_depict = active_depict;
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

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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

            public List<?> getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(List<?> commentlist) {
                this.commentlist = commentlist;
            }

            public static class UserBean {
                /**
                 * school : 温州大学
                 * userID : 14211160102
                 * userIcon : /20180427_014706.jpg
                 * userName : 井底之蛙
                 * userPwd : 002
                 * userSex : 男
                 * userTel : 18757708103
                 */

                private String school;
                private String userID;
                private String userIcon;
                private String userName;
                private String userPwd;
                private String userSex;
                private String userTel;

                public String getSchool() {
                    return school;
                }

                public void setSchool(String school) {
                    this.school = school;
                }

                public String getUserID() {
                    return userID;
                }

                public void setUserID(String userID) {
                    this.userID = userID;
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
            }
        }
    }
}
