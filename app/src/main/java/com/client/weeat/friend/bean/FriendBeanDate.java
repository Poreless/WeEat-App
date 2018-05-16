package com.client.weeat.friend.bean;

import java.util.List;

/**
 * @author Admin  好友列表的bean
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FriendBeanDate {

    /**
     * msg : success
     * result : {"friend_info":[{"fid":"14211160105","friendname":"何叔","imageUrl":"/14211160105.png"},{"fid":"14211160107","friendname":"李敏","imageUrl":"/14211160107.png"},{"fid":"14211160108","friendname":"张芳","imageUrl":"/14211160108.png"},{"fid":"14211160109","friendname":"吴起","imageUrl":"/14211160109.png"}]}
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
        private List<FriendInfoBean> friend_info;

        public List<FriendInfoBean> getFriend_info() {
            return friend_info;
        }

        public void setFriend_info(List<FriendInfoBean> friend_info) {
            this.friend_info = friend_info;
        }

        public static class FriendInfoBean {
            /**
             * fid : 14211160105
             * friendname : 何叔
             * imageUrl : /14211160105.png
             */

            private String fid;
            private String friendname;
            private String imageUrl;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getFriendname() {
                return friendname;
            }

            public void setFriendname(String friendname) {
                this.friendname = friendname;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
