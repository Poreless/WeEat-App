package com.client.weeat.home.bean;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActListBean {

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
        private String active_id;
        private String create_time;
        private String active_depict;
        private String active_name;
        private String active_theme;
        private String active_place;
        private String  maxnum;
        private UserBean user;
        private String finish_time;

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

        public String getMaxnum() {
            return maxnum;
        }

        public void setMaxnum(String maxnum) {
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

        public static class UserBean{
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
