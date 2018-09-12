package com.flys.eplanning.fragments.behavior.agenda;

import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flys.generictools.dao.daoException.DaoException;
import com.flys.generictools.views.RecyclerViewItemClickListener;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.core.ISession;
import com.flys.eplanning.architecture.custom.CoreState;
import com.flys.eplanning.dao.db.DailyTaskDao;
import com.flys.eplanning.dao.db.DailyTaskDaoImpl;
import com.flys.eplanning.entities.DailyTask;
import com.flys.eplanning.fragments.adapters.DailyTasksAdapter;

/**
 * Created by User on 17/04/2018.
 */

@EFragment(R.layout.fragment_tasks_day_list)
@OptionsMenu(R.menu.menu_list_task)
public class DailyTasksList extends AbstractFragment {

    //Initialisation des champs de la vue fragment_tasks_day_list
    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    @ViewById(R.id.dailytasklist_nbre_taches)
    protected TextView tasks;

    //Liste des tâches
    private List<DailyTask> listModels;

    private DailyTasksAdapter mainActivityAdapter;


    @Bean(DailyTaskDaoImpl.class)
    protected DailyTaskDao<DailyTask, Long> dailyTaskLongDao;

    SearchView searchView;

    @OptionsMenuItem(R.id.search)
    protected MenuItem menuItem;

    private DividerItemDecoration dividerItemDecoration;

    //Les différents comportements ou actions sur la vue
    @Click(R.id.fab)
    protected void addNewDailyTask() {
        mainActivity.navigateToView(6, ISession.Action.NAVIGATION);
    }

    @OptionsItem(R.id.search)
    protected void doRechercher() {
        // on récupère le client choisi
        searchView = (SearchView) menuItem.getActionView();
        changeSearchTextColor(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (listModels != null) {
                    List<DailyTask> filterListModels = filter(listModels, newText);
                    mainActivityAdapter.setFilter(filterListModels);
                    tasks.setText(""+filterListModels.size());
                }

                return true;
            }
        });

    }

    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 4;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        listModels = new ArrayList<>();
        try {
            listModels.addAll(dailyTaskLongDao.getAll());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        session.setDailyTasks(listModels);

    }

    @Override
    protected void initView(CoreState previousState) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.getItemOffsets(new Rect(20, 0, 20, 0), null, recyclerView, null);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        tasks.setText(""+session.getDailyTasks().size());
        mainActivityAdapter = new DailyTasksAdapter(session.getDailyTasks(), activity, new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                session.setSelectedDailyTask(listModels.get(position));
                mainActivity.navigateToView(5, ISession.Action.NAVIGATION);
            }
        }, dailyTaskLongDao);
        recyclerView.setAdapter(mainActivityAdapter);
    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {

    }

    @Override
    protected void updateOnRestore(CoreState previousState) {
        listModels = new ArrayList<>();
        try {
            listModels.addAll(dailyTaskLongDao.getAll());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        session.setDailyTasks(listModels);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.getItemOffsets(new Rect(20, 0, 20, 0), null, recyclerView, null);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        tasks.setText(""+session.getDailyTasks().size());
        mainActivityAdapter = new DailyTasksAdapter(session.getDailyTasks(), activity, new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                session.setSelectedDailyTask(listModels.get(position));
                mainActivity.navigateToView(5, ISession.Action.NAVIGATION);
            }
        }, dailyTaskLongDao);
        recyclerView.setAdapter(mainActivityAdapter);
    }

    @Override
    protected void notifyEndOfUpdates() {

    }

    @Override
    protected void notifyEndOfTasks(boolean runningTasksHaveBeenCanceled) {

    }

    private List<DailyTask> filter(List<DailyTask> list, String query) {
        query = query.toLowerCase();
        final List<DailyTask> tasks = new ArrayList<>();

        for (DailyTask model : list) {
            final String text = model.getTitle().toLowerCase();
            if (text.startsWith(query) || text.contains(query)) {
                tasks.add(model);
            }
        }
        return tasks;
    }

    private void changeSearchTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.grey_600));
                ((TextView) view).setBackgroundColor(getResources().getColor(R.color.white));
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}
