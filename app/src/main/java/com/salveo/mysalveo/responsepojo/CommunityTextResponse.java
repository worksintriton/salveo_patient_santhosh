package com.salveo.mysalveo.responsepojo;

public class CommunityTextResponse {

    /**
     * Status : Success
     * Message : community_text
     * Data : Admit it – social media is a part of your daily life. Currently there are over 1.5 billion monthly Facebook users. Yes, you read that correctly. Billion. With social media platforms being such a big part of the average person’s life, it only makes sense that there are social media sites for pets! Over 75% of the US population considers their pets to be family. Combine this with social media user statistics, and you have a lot of people who care about their pets and making it known online! The perfect medium for pet loving social media users? The Pet Community! The Pet Community is a social network for pet owners. It’s a place where pet parents can share photos and videos of our pets with like minded individuals…without flooding our Facebook feed (although I have no shame in this)!
     * Code : 200
     */

    private String Status;
    private String Message;
    private String Data;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}
