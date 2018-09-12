package com.flys.eplanning.architecture.custom;

import java.util.List;

import com.flys.eplanning.architecture.core.AbstractSession;
import com.flys.eplanning.entities.DailyTask;
import com.flys.eplanning.entities.Task;
import com.flys.eplanning.entities.User;

public class Session extends AbstractSession {
    // données à partager entre fragments eux-mêmes et entre fragments et activit' <>é
    // les éléments qui ne peuvent être sérialisés en jSON doivent avoir l'annotation @JsonIgnore
    // ne pas oublier les getters et setters nécessaires pour la sérialisation / désérialisation jSON

    private List<Task> taskList;

    //Elément sélectionné pour faire passer d'un vue à une autre
    private Task selectedTask;

    //Liste des tâches journalières
    private List<DailyTask> dailyTasks;

    //Tâche sélectionnée
    private DailyTask selectedDailyTask;

    //Connected user
    private User connectedUser;


    //Les getters and setterss
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public List<DailyTask> getDailyTasks() {
        return dailyTasks;
    }

    public void setDailyTasks(List<DailyTask> dailyTasks) {
        this.dailyTasks = dailyTasks;
    }

    public DailyTask getSelectedDailyTask() {
        return selectedDailyTask;
    }

    public void setSelectedDailyTask(DailyTask selectedDailyTask) {
        this.selectedDailyTask = selectedDailyTask;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }
}
