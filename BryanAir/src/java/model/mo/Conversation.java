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
public class Conversation {
    private long idconv;
    private String title;
    private User user;
    private Admin admin;
    private DateTime startdate;
    private DateTime enddate;
    private boolean deleted;

    public long getIdconv() {
        return idconv;
    }

    public void setIdconv(long idconv) {
        this.idconv = idconv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public DateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(DateTime startdate) {
        this.startdate = startdate;
    }

    public DateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(DateTime enddate) {
        this.enddate = enddate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DateTime initDate(long time){
        return new DateTime(time);
    }
}
