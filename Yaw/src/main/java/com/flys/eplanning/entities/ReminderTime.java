package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle encapsule toutes les informations liées à la notification.
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 674316936 / 690660199
 * @since 21/05/2018
 */

@DatabaseTable(tableName = "reminder_name")
public class ReminderTime extends BaseEntity implements Serializable {
    @DatabaseField
    private int hour;
    @DatabaseField
    private int minute;
    @DatabaseField
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField
    private long timeStamp;
    @DatabaseField
    private long date;


    public ReminderTime() {
        this.timeStamp= new Date().getTime();
    }

    public ReminderTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public ReminderTime(int hour, int minute, String title, String description) {
        this.hour = hour;
        this.minute = minute;
        this.title = title;
        this.description = description;
    }

    public ReminderTime(int hour, int minute, String title, String description, long timeStamp) {
        this.hour = hour;
        this.minute = minute;
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public ReminderTime(int hour, int minute, String title, String description, long timeStamp, long date) {
        this.hour = hour;
        this.minute = minute;
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ReminderTime that = (ReminderTime) o;

        if (hour != that.hour) return false;
        if (minute != that.minute) return false;
        if (timeStamp != that.timeStamp) return false;
        if (date != that.date) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + hour;
        result = 31 * result + minute;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (timeStamp ^ (timeStamp >>> 32));
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ReminderTime{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timeStamp=" + timeStamp +
                ", date=" + date +
                '}';
    }
}
