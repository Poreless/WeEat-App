package com.client.weeat.user.bean;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UserBeanDate {

    /**
     * msg : success
     * result : {"user_info":{"userPwd":"001","userSex":"男","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"}}
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
        /**
         * user_info : {"userPwd":"001","userSex":"男","userTel":"18757708103","userIcon":"/14211160101.png","userName":"杨莉","userID":"14211160101"}
         */

        private UserInfoBean user_info;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * userPwd : 001
             * userSex : 男
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
}
