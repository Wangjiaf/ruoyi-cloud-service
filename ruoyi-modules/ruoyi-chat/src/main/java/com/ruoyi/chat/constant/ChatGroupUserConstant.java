package com.ruoyi.chat.constant;

public class ChatGroupUserConstant {

    public enum GroupUserRole {
        OWNER("0", "群主"),
        MANAGER("1", "管理员"),
        COMMON("2", "普通成员");
        private String id;
        private String text;

        GroupUserRole(String id, String text) {
            this.id = id;
            this.text = text;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
