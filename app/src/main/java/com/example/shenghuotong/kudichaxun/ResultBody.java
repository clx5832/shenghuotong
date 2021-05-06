package com.example.shenghuotong.kudichaxun;

import java.util.List;

public class ResultBody {

    /**
     * status : 0
     * msg : ok
     * result : {"number":"","type":"ZTO","list":[{"time":"2020-09-29 19:27:38","status":"快件已在 【贺州钟山县】 签收, 签收人: 门卫, 如有疑问请电联:（18677480305）, 投诉电话:（0774-3207778）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2020-09-29 12:14:05","status":"【贺州钟山县】 的前台18177481252（18677480305） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）"},{"time":"2020-09-29 12:14:04","status":"快件已经到达 【贺州钟山县】"},{"time":"2020-09-28 16:24:20","status":"快件离开 【柳州转运中心】 已发往 【贺州钟山县】"},{"time":"2020-09-28 12:35:58","status":"快件已经到达 【柳州转运中心】"},{"time":"2020-09-27 06:49:31","status":"快件离开 【金华中转部】 已发往 【柳州转运中心】"},{"time":"2020-09-27 03:15:19","status":"快件已经到达 【金华中转部】"},{"time":"2020-09-27 01:28:54","status":"快件离开 【义乌中转部】 已发往 【金华中转部】"},{"time":"2020-09-27 01:25:46","status":"快件已经到达 【义乌中转部】"},{"time":"2020-09-26 22:57:53","status":"快件离开 【义乌城西】 已发往 【柳州转运中心】"},{"time":"2020-09-26 17:23:49","status":"【义乌城西】（0579-85317356、0579-85370105、0579-85322329） 的 操作部（18157968960） 已揽收"}],"deliverystatus":"3","issign":"1","expName":"中通快递","expSite":"www.zto.com ","expPhone":"95311","logo":"https://img3.fegine.com/express/zto.jpg","courier":"","courierPhone":"18677480305","updateTime":"2020-09-29 19:27:38","takeTime":"3天2小时3分"}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public static class ResultBean {
        /**
         * number : 75391346891005
         * type : ZTO
         * list : [{"time":"2020-09-29 19:27:38","status":"快件已在 【贺州钟山县】 签收, 签收人: 门卫, 如有疑问请电联:（18677480305）, 投诉电话:（0774-3207778）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2020-09-29 12:14:05","status":"【贺州钟山县】 的前台18177481252（18677480305） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）"},{"time":"2020-09-29 12:14:04","status":"快件已经到达 【贺州钟山县】"},{"time":"2020-09-28 16:24:20","status":"快件离开 【柳州转运中心】 已发往 【贺州钟山县】"},{"time":"2020-09-28 12:35:58","status":"快件已经到达 【柳州转运中心】"},{"time":"2020-09-27 06:49:31","status":"快件离开 【金华中转部】 已发往 【柳州转运中心】"},{"time":"2020-09-27 03:15:19","status":"快件已经到达 【金华中转部】"},{"time":"2020-09-27 01:28:54","status":"快件离开 【义乌中转部】 已发往 【金华中转部】"},{"time":"2020-09-27 01:25:46","status":"快件已经到达 【义乌中转部】"},{"time":"2020-09-26 22:57:53","status":"快件离开 【义乌城西】 已发往 【柳州转运中心】"},{"time":"2020-09-26 17:23:49","status":"【义乌城西】（0579-85317356、0579-85370105、0579-85322329） 的 操作部（18157968960） 已揽收"}]
         * deliverystatus : 3
         * issign : 1
         * expName : 中通快递
         * expSite : www.zto.com
         * expPhone : 95311
         * logo : https://img3.fegine.com/express/zto.jpg
         * courier :
         * courierPhone : 18677480305
         * updateTime : 2020-09-29 19:27:38
         * takeTime : 3天2小时3分
         */

        private String number;
        private String type;
        private String deliverystatus;
        private String issign;
        private String expName;
        private String expSite;
        private String expPhone;
        private String logo;
        private String courier;
        private String courierPhone;
        private String updateTime;
        private String takeTime;
        private List<ListBean> list;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeliverystatus() {
            return deliverystatus;
        }

        public void setDeliverystatus(String deliverystatus) {
            this.deliverystatus = deliverystatus;
        }

        public String getIssign() {
            return issign;
        }

        public void setIssign(String issign) {
            this.issign = issign;
        }

        public String getExpName() {
            return expName;
        }

        public void setExpName(String expName) {
            this.expName = expName;
        }

        public String getExpSite() {
            return expSite;
        }

        public void setExpSite(String expSite) {
            this.expSite = expSite;
        }

        public String getExpPhone() {
            return expPhone;
        }

        public void setExpPhone(String expPhone) {
            this.expPhone = expPhone;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCourier() {
            return courier;
        }

        public void setCourier(String courier) {
            this.courier = courier;
        }

        public String getCourierPhone() {
            return courierPhone;
        }

        public void setCourierPhone(String courierPhone) {
            this.courierPhone = courierPhone;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(String takeTime) {
            this.takeTime = takeTime;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * time : 2020-09-29 19:27:38
             * status : 快件已在 【贺州钟山县】 签收, 签收人: 门卫, 如有疑问请电联:（18677480305）, 投诉电话:（0774-3207778）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】
             */

            private String time;
            private String status;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
