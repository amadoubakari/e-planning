package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by User on 23/08/2018.
 */

@DatabaseTable(tableName = "month")
public class Month extends BaseEntity {

    @DatabaseField
    private String name;

    @ForeignCollectionField
    private Collection<Week> weeks;

    public Month() {
    }

    public Month(String name, Collection<Week> weeks) {
        this.name = name;
        this.weeks = weeks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(Collection<Week> weeks) {
        this.weeks = weeks;
    }
}
