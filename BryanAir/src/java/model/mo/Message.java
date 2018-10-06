/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mo;

import java.sql.Timestamp;
import org.joda.time.DateTime;

/**
 *
 * @author Marcello
 */
public class Message {
    private long convid;
    private DateTime sendtime;
    private String text;
    private String sender;
    private Boolean deleted;

    public long getConvid() {
        return convid;
    }

    public void setConvid(long convid) {
        this.convid = convid;
    }

    public DateTime getSendtime() {
        return sendtime;
    }

    public void setSendtime(DateTime sendtime) {
        this.sendtime = sendtime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    public DateTime initDate(long time){
        return new DateTime(time);
    }
}
