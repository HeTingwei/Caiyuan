package com.xingyi.caiyuan.json_bean.login;

/**
 * Created by Htw on 2017/7/18.
 * 登录成功后返回的json数据
 */

public class LoginSuccessBean {


    /**
     * status : {"code":201,"msg":"登录成功"}
     * data : {"user":{"id":1,"name":"helly","password":null,"avatarUrl":"default.png","gender":"男",
     * "birthday":"2017-07-02","phone":"18483661669","email":"hellyuestc@gmail.com","address":"未填写","job":"未填写",
     * "introduction":"Ta很懒，什么也没有留下~~","followingCount":0,"followerCount":0,"status":0,"activationCode":null,
     * "isExpert":0,"gmtCreate":"2017-07-02 13:36:01","gmtModified":null}}
     */

    private StatusBean status;
    private DataBean data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * code : 201
         * msg : 登录成功
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class DataBean {
        /**
         * user : {"id":1,"name":"helly","password":null,"avatarUrl":"default.png","gender":"男",
         * "birthday":"2017-07-02","phone":"18483661669","email":"hellyuestc@gmail.com","address":"未填写","job":"未填写",
         * "introduction":"Ta很懒，什么也没有留下~~","followingCount":0,"followerCount":0,"status":0,"activationCode":null,
         * "isExpert":0,"gmtCreate":"2017-07-02 13:36:01","gmtModified":null}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 1
             * name : helly
             * password : null
             * avatarUrl : default.png
             * gender : 男
             * birthday : 2017-07-02
             * phone : 18483661669
             * email : hellyuestc@gmail.com
             * address : 未填写
             * job : 未填写
             * introduction : Ta很懒，什么也没有留下~~
             * followingCount : 0
             * followerCount : 0
             * status : 0
             * activationCode : null
             * isExpert : 0
             * gmtCreate : 2017-07-02 13:36:01
             * gmtModified : null
             */

            private int id;
            private String name;
            private String password;
            private String avatarUrl;
            private String gender;
            private String birthday;
            private String phone;
            private String email;
            private String address;
            private String job;
            private String introduction;
            private int followingCount;
            private int followerCount;
            private int status;
            private String activationCode;
            private int isExpert;
            private String gmtCreate;
            private String gmtModified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowingCount() {
                return followingCount;
            }

            public void setFollowingCount(int followingCount) {
                this.followingCount = followingCount;
            }

            public int getFollowerCount() {
                return followerCount;
            }

            public void setFollowerCount(int followerCount) {
                this.followerCount = followerCount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getActivationCode() {
                return activationCode;
            }

            public void setActivationCode(String activationCode) {
                this.activationCode = activationCode;
            }

            public int getIsExpert() {
                return isExpert;
            }

            public void setIsExpert(int isExpert) {
                this.isExpert = isExpert;
            }

            public String getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(String gmtCreate) {
                this.gmtCreate = gmtCreate;
            }

            public String getGmtModified() {
                return gmtModified;
            }

            public void setGmtModified(String gmtModified) {
                this.gmtModified = gmtModified;
            }
        }
    }
}
