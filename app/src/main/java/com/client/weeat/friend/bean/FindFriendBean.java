package com.client.weeat.friend.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des 由于服务器传回的user类型变为user而不是friend，重新编写bean
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FindFriendBean {

    /**
     * msg : success
     * result : {"user_info":[{"userSex":"男","userPwd":"005","userTel":"18757708103","userIcon":"/14211160105.png","userName":"何he","userID":"14211160105"},{"userSex":"男","userPwd":"006","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},{"userSex":"女","school":"温州大学","userPwd":"002","userTel":"18757708103","userIcon":"/14211160102.png","userName":"刘霞","userID":"14211160102"},{"userSex":"男","userPwd":"006","userTel":"18757708103","userIcon":"/14211160106.png","userName":"季鹏","userID":"14211160106"},{"userSex":"女","userPwd":"008","userTel":"18757708103","userIcon":"/14211160108.png","userName":"张芳","userID":"14211160108"},{"userSex":"男","userPwd":"009","userTel":"18757708103","userIcon":"/14211160109.png","userName":"文文","userID":"14211160109"},{"userSex":"男","userPwd":"010","userTel":"","userIcon":"","userName":"王强","userID":"14211160110"},{"userSex":"","userPwd":"112","userTel":"18757708103","userIcon":"","userName":"小鱼鱼","userID":"14211160115"},{"userPwd":"123","userTel":"18757708103","userName":"look","userID":"14211160152"},{"userSex":"女","userPwd":"007","userTel":"18757708103","userIcon":"/14211160107.png","userName":"李敏","userID":"14211160107"}]}
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
        private List<UserInfoBean> user_info;

        public List<UserInfoBean> getUser_info() {
            return user_info;
        }

        public void setUser_info(List<UserInfoBean> user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * userSex : 男
             * userPwd : 005
             * userTel : 18757708103
             * userIcon : /14211160105.png
             * userName : 何he
             * userID : 14211160105
             * school : 温州大学
             */

            private String userSex;
            private String userPwd;
            private String userTel;
            private String userIcon;
            private String userName;
            private String userID;
            private String school;

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

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }
        }
    }
}
