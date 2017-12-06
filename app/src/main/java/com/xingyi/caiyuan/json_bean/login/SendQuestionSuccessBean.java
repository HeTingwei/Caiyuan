package com.xingyi.caiyuan.json_bean.login;

/**
 * Created by HeTingwei on 2017/7/27.
 */

public class SendQuestionSuccessBean {


    /**
     * status : {"code":201,"msg":"发布问题成功"}
     * data : {"question":{"id":1,"userId":1,"userName":"helly","topicId":1,"topicName":"话题1","title":"问题标题","content":"问题内容","isPublish":1,"scanCount":0,"answerCount":0,"gmtCreate":"2017-07-23 12:18:20","gmtModified":"2017-07-23 12:18:20"}}
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
         * msg : 发布问题成功
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
         * question : {"id":1,"userId":1,"userName":"helly","topicId":1,"topicName":
         * "话题1","title":"问题标题","content":"问题内容","isPublish":1,"scanCount":0,
         * "answerCount":0,"gmtCreate":"2017-07-23 12:18:20","gmtModified":"2017-07-23 12:18:20"}
         */

        private QuestionBean question;

        public QuestionBean getQuestion() {
            return question;
        }

        public void setQuestion(QuestionBean question) {
            this.question = question;
        }

        public static class QuestionBean {
            /**
             * id : 1
             * userId : 1
             * userName : helly
             * topicId : 1
             * topicName : 话题1
             * title : 问题标题
             * content : 问题内容
             * isPublish : 1
             * scanCount : 0
             * answerCount : 0
             * gmtCreate : 2017-07-23 12:18:20
             * gmtModified : 2017-07-23 12:18:20
             */

            private int id;
            private int userId;
            private String userName;
            private int topicId;
            private String topicName;
            private String title;
            private String content;
            private int isPublish;
            private int scanCount;
            private int answerCount;
            private String gmtCreate;
            private String gmtModified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getTopicId() {
                return topicId;
            }

            public void setTopicId(int topicId) {
                this.topicId = topicId;
            }

            public String getTopicName() {
                return topicName;
            }

            public void setTopicName(String topicName) {
                this.topicName = topicName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIsPublish() {
                return isPublish;
            }

            public void setIsPublish(int isPublish) {
                this.isPublish = isPublish;
            }

            public int getScanCount() {
                return scanCount;
            }

            public void setScanCount(int scanCount) {
                this.scanCount = scanCount;
            }

            public int getAnswerCount() {
                return answerCount;
            }

            public void setAnswerCount(int answerCount) {
                this.answerCount = answerCount;
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
