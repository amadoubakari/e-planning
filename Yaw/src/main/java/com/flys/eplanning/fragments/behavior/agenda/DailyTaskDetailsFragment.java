package com.flys.eplanning.fragments.behavior.agenda;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.custom.CoreState;
import com.flys.eplanning.dao.db.DailyTaskDao;
import com.flys.eplanning.dao.db.DailyTaskDaoImpl;
import com.flys.eplanning.entities.DailyTask;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle affiche le détails d'une tâche journalière.
 * @email amadou_bakari@yahoo.fr
 * @phone (+237) 674316936/ 690660199
 * @since 19/04/2018
 */

@EFragment(R.layout.fragment_day_details_item)
@OptionsMenu(R.menu.menu_vide)
public class DailyTaskDetailsFragment extends AbstractFragment {

    //La tâche qui a été sélectionnée de la liste
    private DailyTask dailyTask;

    //Les champs de la vue fragment_day_details_item
    @ViewById(R.id.task_title)
    protected TextView title;

    @ViewById(R.id.task_day)
    protected TextView date;

    @ViewById(R.id.task_start_time)
    protected TextView heureDebut;

    @ViewById(R.id.task_end_time)
    protected TextView heureFin;

    @ViewById(R.id.task_place)
    protected TextView lieu;

    @ViewById(R.id.task_time_reminder)
    protected TextView heureRappel;

    @ViewById(R.id.task_if_repeat)
    protected Switch repeat;

    @ViewById(R.id.task_is_finish)
    protected CheckBox finish;

    @ViewById(R.id.task_description)
    protected TextView description;

    @ViewById(R.id.task_evaluate)
    protected CheckBox evaluate;

    @ViewById(R.id.evaluate_bloc_layout)
    protected LinearLayout blocEvaluation;

    @ViewById(R.id.step1)
    protected TextView step1;

    @ViewById(R.id.step2)
    protected TextView step2;

    @ViewById(R.id.step3)
    protected TextView step3;

    @ViewById(R.id.step4)
    protected TextView step4;

    @ViewById(R.id.step5)
    protected TextView step5;

    @ViewById(R.id.categorie)
    protected TextView categorie;

    @ViewById(R.id.testt)
    protected TextView testt;


    @Click(R.id.task_evaluate)
    protected void evaluate() {
        if (evaluate.isChecked()) {
            blocEvaluation.setVisibility(View.VISIBLE);
        } else {
            blocEvaluation.setVisibility(View.GONE);
        }
    }

    @Click(R.id.task_is_finish)
    protected void finished() {
        dailyTask.setFinish(finish.isChecked());
        dailyTaskLongDao.update(dailyTask);
    }

    @Click(R.id.step1)
    protected void step1() {
        step1.setBackgroundColor(activity.getResources().getColor(R.color.blue_200));
        step1.setTextColor(getResources().getColor(R.color.white));
        dailyTask.setNiveau(20);
        dailyTaskLongDao.update(dailyTask);

    }

    @Click(R.id.step2)
    protected void step2() {
        step2.setBackgroundColor(activity.getResources().getColor(R.color.blue_200));
        step2.setTextColor(getResources().getColor(R.color.white));
        dailyTask.setNiveau(40);
        dailyTaskLongDao.update(dailyTask);
    }

    @Click(R.id.step3)
    protected void step3() {
        step3.setBackgroundColor(activity.getResources().getColor(R.color.blue_200));
        step3.setTextColor(getResources().getColor(R.color.white));
        dailyTask.setNiveau(60);
        dailyTaskLongDao.update(dailyTask);
    }

    @Click(R.id.step4)
    protected void step4() {
        step4.setBackgroundColor(activity.getResources().getColor(R.color.blue_200));
        step4.setTextColor(getResources().getColor(R.color.white));
        dailyTask.setNiveau(80);
        dailyTaskLongDao.update(dailyTask);
    }

    @Click(R.id.step5)
    protected void step5() {
        step5.setBackgroundColor(activity.getResources().getColor(R.color.blue_200));
        step5.setTextColor(getResources().getColor(R.color.white));
        dailyTask.setNiveau(100);
        dailyTaskLongDao.update(dailyTask);
    }

    @Bean(DailyTaskDaoImpl.class)
    protected DailyTaskDao<DailyTask, Long> dailyTaskLongDao;

    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 5;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        //Quand on arrive pour la première fois sur la vue détails d'une list
        if (session != null && previousState == null) {
            dailyTask = session.getSelectedDailyTask();
        }
    }

    @Override
    protected void initView(CoreState previousState) {
        //Quand ce n'est pas la première fois qu'on arrive sur cette vue.
        if (previousState != null) {
            dailyTask = session.getSelectedDailyTask();
            Log.e(getClass().getCanonicalName(), dailyTask.toString());

        }
        fillFields();
    }

    //Renseigner les champs de la page
    private void fillFields() {
        title.setText(dailyTask.getTitle());
        date.setText(dailyTask.getStartDate());
        heureDebut.setText(dailyTask.getHeureDebut());
        heureFin.setText(dailyTask.getHeureFin());
        lieu.setText(dailyTask.getPlace());
        repeat.setChecked(dailyTask.isNotify());
        finish.setChecked(dailyTask.isFinish());
        heureRappel.setText(dailyTask.getTemps());
        description.setText(dailyTask.getDescription());
        testt.setText(dailyTask.getDay().getName());
        categorie.setText(dailyTask.getCategorie());

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
