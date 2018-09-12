package com.flys.eplanning.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle décrit la tâche journalière.
 * @email amadou_bakari@yahoo.fr
 * @phone (+237) 674316936 / 690660199
 * @since 19/04/2018
 */

@DatabaseTable(tableName = "daily_task")
public class DailyTask extends Task {
    @DatabaseField
    private int numero;
    @DatabaseField
    private String heureDebut;
    @DatabaseField
    private String heureFin;
    @DatabaseField
    private String place;
    @DatabaseField
    private boolean finish;
    @DatabaseField
    private boolean notify;
    @DatabaseField
    private String categorie;
    @DatabaseField
    private String temps;
    @DatabaseField
    private boolean archived;
    @DatabaseField(defaultValue = "0")
    private int niveau;
    @DatabaseField
    private String name;


    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day day;



    //The constructors
    public DailyTask() {
    }

    public DailyTask(String heureDebut, String heureFin, String place) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.place = place;
    }

    public DailyTask(String title, String description, String startDate, String endDate, boolean rememberMe, String heureDebut, String heureFin, String place) {
        super(title, description, startDate, endDate, rememberMe);
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.place = place;
    }

    public DailyTask(String title, String description, String startDate, String endDate, boolean rememberMe, int numero, String heureDebut, String heureFin, String place, boolean finish, boolean notify, String categorie, String temps, boolean archived, int niveau) {
        super(title, description, startDate, endDate, rememberMe);
        this.numero = numero;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.place = place;
        this.finish = finish;
        this.notify = notify;
        this.categorie = categorie;
        this.temps = temps;
        this.archived = archived;
        this.niveau = niveau;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DailyTask dailyTask = (DailyTask) o;

        if (numero != dailyTask.numero) return false;
        if (finish != dailyTask.finish) return false;
        if (notify != dailyTask.notify) return false;
        if (archived != dailyTask.archived) return false;
        if (niveau != dailyTask.niveau) return false;
        if (heureDebut != null ? !heureDebut.equals(dailyTask.heureDebut) : dailyTask.heureDebut != null)
            return false;
        if (heureFin != null ? !heureFin.equals(dailyTask.heureFin) : dailyTask.heureFin != null)
            return false;
        if (place != null ? !place.equals(dailyTask.place) : dailyTask.place != null) return false;
        if (categorie != null ? !categorie.equals(dailyTask.categorie) : dailyTask.categorie != null)
            return false;
        return temps != null ? temps.equals(dailyTask.temps) : dailyTask.temps == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numero;
        result = 31 * result + (heureDebut != null ? heureDebut.hashCode() : 0);
        result = 31 * result + (heureFin != null ? heureFin.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (finish ? 1 : 0);
        result = 31 * result + (notify ? 1 : 0);
        result = 31 * result + (categorie != null ? categorie.hashCode() : 0);
        result = 31 * result + (temps != null ? temps.hashCode() : 0);
        result = 31 * result + (archived ? 1 : 0);
        result = 31 * result + niveau;
        return result;
    }

    @Override
    public String toString() {
        return "DailyTask{" +
                "numero=" + numero +
                ", heureDebut='" + heureDebut + '\'' +
                ", heureFin='" + heureFin + '\'' +
                ", place='" + place + '\'' +
                ", finish=" + finish +
                ", notify=" + notify +
                ", categorie='" + categorie + '\'' +
                ", temps='" + temps + '\'' +
                ", archived=" + archived +
                ", niveau=" + niveau +
                ", name='" + name + '\'' +
                '}';
    }
}
