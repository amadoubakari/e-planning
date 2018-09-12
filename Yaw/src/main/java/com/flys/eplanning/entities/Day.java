package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle encapsule les les tâches journalières.
 * @email amadou_bakari@yahoo.fr
 * @tel (+2367) 674316936/ 690660199
 * @since 23/08/2018
 */
@DatabaseTable(tableName = "day")
public class Day extends BaseEntity {

    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Week week;

    @ForeignCollectionField
    private Collection<DailyTask> dailyTasks;

    public Day() {
    }

    public Day(String name, Collection<DailyTask> dailyTasks) {
        this.name = name;
        this.dailyTasks = dailyTasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<DailyTask> getDailyTasks() {
        return dailyTasks;
    }

    public void setDailyTasks(Collection<DailyTask> dailyTasks) {
        this.dailyTasks = dailyTasks;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;
        if (!super.equals(o)) return false;

        Day day = (Day) o;

        if (getName() != null ? !getName().equals(day.getName()) : day.getName() != null)
            return false;
        return getDailyTasks() != null ? getDailyTasks().equals(day.getDailyTasks()) : day.getDailyTasks() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDailyTasks() != null ? getDailyTasks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "name='" + name + '\'' +
                ", dailyTasks=" + dailyTasks +
                '}';
    }
}
