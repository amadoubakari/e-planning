package com.flys.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "daily_task")
public class DailyTask implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(columnName = "nom",useGetSet = true)
    private String nom;

    public DailyTask() {
    }

    public DailyTask(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyTask)) return false;

        DailyTask dailyTask = (DailyTask) o;

        if (!getId().equals(dailyTask.getId())) return false;
        return getNom().equals(dailyTask.getNom());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNom().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DailyTask{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
