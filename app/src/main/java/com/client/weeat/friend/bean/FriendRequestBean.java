package com.client.weeat.friend.bean;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}好友请求列表的bean
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FriendRequestBean {

    /**
     * msg : success
     * result : [{"fid":"14211160101","num":1,"name":"杨莉","icon":"/14211160101.png"},{"fid":"14211160104","num":1,"name":"赵梦","icon":"/14211160104.png"},{"fid":"14211160102","num":0,"name":"刘霞","icon":"/14211160102.png","time":"2018-04-23 00:42:04"}]
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
         * fid : 14211160101
         * num : 1
         * name : 杨莉
         * icon : /14211160101.png
         * time : 2018-04-23 00:42:04
         */

        private String fid;
        private int num;
        private String name;
        private String icon;
        private String time;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
