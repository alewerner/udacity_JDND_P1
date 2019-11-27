package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    private String username;
    private String msg;
    private int onlineCount;
    
    private MessageType type;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public enum MessageType {
        CHAT,
        ENTER,
        LEAVE
    }

    public Message(MessageType type){
        this.type = type;
    }

    public Message(String username, String msg, int onlineCount, MessageType type) {
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
        this.type = type;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }
    
}
