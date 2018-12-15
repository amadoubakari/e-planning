package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle représente une tâche.
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 674316936 / 690660199
 * @since 07/04/2018
 */

public class Task extends BaseEntity implements Serializable{
    @DatabaseField
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField
    private String startDate;
    @DatabaseField
    private String endDate;
    @DatabaseField
    private boolean rememberMe;

    public Task() {
    }

    public Task(String title, String description, String startDate, String endDate, boolean rememberMe) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rememberMe = rememberMe;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isRememberMe() == task.isRememberMe() &&
                Objects.equals(getTitle(), task.getTitle()) &&
                Objects.equals(getDescription(), task.getDescription()) &&
                Objects.equals(getStartDate(), task.getStartDate()) &&
                Objects.equals(getEndDate(), task.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getStartDate(), getEndDate(), isRememberMe());
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }


}
