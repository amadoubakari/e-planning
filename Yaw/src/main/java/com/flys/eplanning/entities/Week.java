package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle représente la semaine du mois
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 674316936 / 690660199
 * @since 13/07/2018
 */

@DatabaseTable(tableName = "week")
public class Week extends BaseEntity {

    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Month month;

    @ForeignCollectionField
    private Collection<Day> days;

    public Week() {

    }

    public Week(String name) {
        this.name = name;
    }

    public Collection<Day> getDays() {
        return days;
    }

    public void setDays(Collection<Day> days) {
        this.days = days;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Week{" +
                "month=" + month +
                ", days=" + days +
                '}';
    }
}
