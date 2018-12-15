package com.flys.eplanning.fragments.behavior.agenda;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.flys.eplanning.dao.db.DayDao;
import com.flys.eplanning.dao.db.DayDaoImpl;
import com.flys.eplanning.entities.Day;
import com.flys.generictools.dao.daoException.DaoException;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.alarm.AlarmHelper;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.core.ISession;
import com.flys.eplanning.architecture.custom.CoreState;
import com.flys.eplanning.dao.db.DailyTaskDao;
import com.flys.eplanning.dao.db.DailyTaskDaoImpl;
import com.flys.eplanning.dao.db.ReminderTimeDao;
import com.flys.eplanning.dao.db.ReminderTimeDaoImpl;
import com.flys.eplanning.entities.DailyTask;
import com.flys.eplanning.entities.ReminderTime;
import com.flys.eplanning.tools.DatabaseHelper;
import com.flys.eplanning.tools.Utility;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet d'ajouter une nouvelle tâche journalière.
 * @email amadou_bakari@yahoo.fr
 * @phone (+237) 674316936/ 690660199
 * @since 20/04/2018
 */

@EFragment(R.layout.fragment_new_day_task)
@OptionsMenu(R.menu.menu_vide)
public class NewDailyTasks extends AbstractFragment {

    //Initialisation des champs de la vue fragment_new_day_task
    @ViewById(R.id.title)
    protected EditText title;

    @ViewById(R.id.description)
    protected EditText description;

    @ViewById(R.id.date)
    protected EditText date;

    @ViewById(R.id.task_start_hour)
    protected EditText heureDebut;

    @ViewById(R.id.task_end_hour)
    protected EditText heureFin;

    @ViewById(R.id.task_place)
    protected EditText lieu;


    @ViewById(R.id.categorie_tache)
    protected MaterialSpinner categorie;

    @ViewById(R.id.task_remember_me)
    protected Switch rememberMe;

    @ViewById(R.id.date_to_notify)
    protected EditText dateToNotificeBefore;

    @ViewById(R.id.time_to_notify)
    protected EditText timeToNotificeBefore;

    @ViewById(R.id.remember_me_layout)
    protected LinearLayout rememberMeLayout;


    //Déclaration et/ou initialisation des autres champs
    Calendar calendar;

    //
    DatabaseHelper databaseHelper;

    //La tâche courante
    private DailyTask dailyTask;

    //Reminder for this task
    private ReminderTime reminderTime;

    //
    final Calendar calRemember = Calendar.getInstance();

    //Injection de la DAO

    @Bean(ReminderTimeDaoImpl.class)
    protected ReminderTimeDao<ReminderTime,Long> reminderTimeDao;

    @Bean(DailyTaskDaoImpl.class)
    protected DailyTaskDao<DailyTask, Long> dailyTaskLongDao;

    @Bean(DayDaoImpl.class)
    protected DayDao<Day, Long> dayDao;

    //Les différents comportements ou actions sur la vue
    @Click(R.id.date)
    protected void date() {
        Utility.setDate(activity, calendar, date);
       // Log.e(getClass().getSimpleName(),""+calRemember.getTime());
    }

    @Click(R.id.task_start_hour)
    protected void heureDebut() {

        Utility.setHour(activity, calendar, heureDebut);
    }

    @Click(R.id.task_end_hour)
    protected void heureFin() {

        Utility.setHour(activity, calendar, heureFin);
    }

    @Click(R.id.time_to_notify)
    protected void heureRappel() {
        TimePickerDialog tpd = new TimePickerDialog(activity, (view1, hourOfDay, minute) -> {
            calRemember.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calRemember.set(Calendar.MINUTE, minute);
            calRemember.set(Calendar.SECOND, 0);
            timeToNotificeBefore.setText(Utility.getTime(calRemember.getTimeInMillis()));
        }, calRemember.get(Calendar.HOUR_OF_DAY), calRemember.get(Calendar.MINUTE), DateFormat.is24HourFormat(activity));
        tpd.show();
    }


    @Click(R.id.date_to_notify)
    protected void dateRappel() {
        DatePickerDialog dpd = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calRemember.set(Calendar.YEAR, year);
                        calRemember.set(Calendar.MONTH, month);
                        calRemember.set(Calendar.DAY_OF_MONTH, day);
                        dateToNotificeBefore.setText(Utility.getDate(calRemember.getTimeInMillis()));
                    }

                }
                , calRemember.get(Calendar.YEAR), calRemember.get(Calendar.MONTH), calRemember.get(Calendar.DATE));
        dpd.setTitle("Tony Parker Freeman V");
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.setIcon(R.drawable.tony);
        dpd.show();
    }

    @Click(R.id.task_remember_me)
    protected void rememberMe() {
        if (rememberMe.isChecked()) {
            rememberMeLayout.setVisibility(View.VISIBLE);
        } else {
            rememberMeLayout.setVisibility(View.GONE);
        }
    }

    @Click(R.id.btn_add_new_daily_task)
    protected void addNewTask() {
        dailyTask.setTitle(title.getText().toString());
        dailyTask.setDescription(description.getText().toString());
        dailyTask.setStartDate(date.getText().toString());
        dailyTask.setEndDate(date.getText().toString());
        dailyTask.setHeureFin(heureFin.getText().toString());
        dailyTask.setHeureDebut(heureDebut.getText().toString());
        dailyTask.setPlace(lieu.getText().toString());
        dailyTask.setRememberMe(rememberMe.isChecked());
        Log.e(getClass().getSimpleName(), timeToNotificeBefore.getText().toString());
        dailyTask.setTemps(timeToNotificeBefore.getText().toString());
        Log.e("get", dailyTask.getTemps());

        try {
            String notificationTime=timeToNotificeBefore.getText().toString();

            if (dateToNotificeBefore.length() != 0 || timeToNotificeBefore.length() != 0) {
                reminderTime=new ReminderTime(Integer.parseInt(notificationTime.substring(0,notificationTime.indexOf(":"))),Integer.parseInt(notificationTime.substring(notificationTime.indexOf(":")+1,notificationTime.length())),dailyTask.getTitle(),dailyTask.getDescription());
                //reminderTime.setTimeStamp(calendar.getTimeInMillis());
                Log.e(getClass().getSimpleName(),"------------------ New Task Fulldate:"+ Utility.getFullDate(calRemember.getTimeInMillis())+" ----------------------");
                reminderTime.setDate(calRemember.getTimeInMillis());
                reminderTimeDao.save(reminderTime);


                //Tester la liste de toutes les taches planifiées
                AlarmHelper alarmHelper = AlarmHelper.getInstance();
                alarmHelper.setAlarm(reminderTime);
                Day

                    day=new Day("Dimanche",null);
                    //int i= dayDao.save(day);

                dailyTask.setDay(dayDao.save(day));
                dailyTaskLongDao.save(dailyTask);
                mainActivity.navigateToView(4, ISession.Action.NAVIGATION);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 6;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        //

        //Initialisation des champs
        dailyTask = new DailyTask();

        calendar = Calendar.getInstance();


    }

    @Override
    protected void initView(CoreState previousState) {

        categorie.setItems("Sélectionne la catégorie", "SANTE", "PROFESSIONNEL", "DEVELOPPEMENT PERSONNEL", "LOISIR");
        categorie.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                dailyTask.setCategorie(item);
            }
        });

    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {

    }

    @Override
    protected void updateOnRestore(CoreState previousState) {


    }

    @Override
    protected void notifyEndOfUpdates() {

    }

    @Override
    protected void notifyEndOfTasks(boolean runningTasksHaveBeenCanceled) {

    }
}
