package com.flys.eplanning.activity;

import android.support.design.widget.TabLayout;
import android.util.Log;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

import com.flys.eplanning.R;
import com.flys.eplanning.alarm.AlarmHelper;
import com.flys.eplanning.architecture.core.AbstractActivity;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.core.ISession;
import com.flys.eplanning.architecture.custom.Session;
import com.flys.eplanning.dao.service.Dao;
import com.flys.eplanning.dao.service.IDao;
import com.flys.eplanning.fragments.behavior.AuthenticationFragment_;
import com.flys.eplanning.fragments.behavior.HomeFragment_;
import com.flys.eplanning.fragments.behavior.agenda.DailyTaskDetailsFragment_;
import com.flys.eplanning.fragments.behavior.agenda.DailyTasksList_;
import com.flys.eplanning.fragments.behavior.agenda.NewDailyTasks_;
import com.flys.eplanning.fragments.behavior.agenda.WeekFragment_;
import com.flys.eplanning.fragments.behavior.planning.ListTaskFragment_;
import com.flys.eplanning.fragments.behavior.planning.NewTaskFragment_;
import com.flys.eplanning.tools.View;

@EActivity
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AbstractActivity {

    // couche [DAO]
    @Bean(Dao.class)
    protected IDao dao;

    // session
    private Session session;

    // méthodes classe parent -----------------------
    @Override
    protected void onCreateActivity() {
        // log
        if (IS_DEBUG_ENABLED) {
            Log.d(className, "onCreateActivity");
        }
        // session
        this.session = (Session) super.session;
        // todo : on continue les initialisations commencées par la classe parent
        TabLayout.Tab month = tabLayout.newTab();
        TabLayout.Tab week = tabLayout.newTab();
        TabLayout.Tab day = tabLayout.newTab();
        month.setText("MONTH");
        week.setText("WEEK");
        day.setText("DAY");
        tabLayout.addTab(month);
        tabLayout.addTab(week);
        tabLayout.addTab(day);

        //Initialisation du système d'alarme
        AlarmHelper alarmHelper=AlarmHelper.getInstance();
        alarmHelper.init(getApplicationContext());
    }

    @Override
    protected IDao getDao() {
        return dao;
    }

    @Override
    protected AbstractFragment[] getFragments() {
        // todo : définir les fragments ici
        return new AbstractFragment[]{new ListTaskFragment_(), new NewTaskFragment_(), new AuthenticationFragment_(), new HomeFragment_(),new DailyTasksList_(),new DailyTaskDetailsFragment_(),new NewDailyTasks_(),new WeekFragment_()};
    }


    @Override
    protected CharSequence getFragmentTitle(int position) {
        CharSequence title;
        switch (position) {
            default:
                title = "HOME";
                break;
        }
        return title;
    }

    @Override
    protected void navigateOnTabSelected(int position) {
        switch (position) {
            case 0:
                navigateToView(0, ISession.Action.NAVIGATION);
                break;
            case 1:
                navigateToView(View.WEEKS_LIST, ISession.Action.NAVIGATION);
                break;
            case 2:
                navigateToView(4, ISession.Action.NAVIGATION);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getFirstView() {
        // todo : définir le n° de la première vue (fragment) à afficher
        return 0;
    }

    @Override
    protected boolean needCollapse() {
        return true;
    }

    @Override
    public boolean deconnexion() {
        return false;
    }

    @Override
    public boolean connexion() {
        navigateToView(2, ISession.Action.NAVIGATION);
        return true;
    }
}
