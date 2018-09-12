package com.flys.eplanning.fragments.behavior.agenda;

import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.custom.CoreState;
import com.flys.eplanning.entities.Week;
import com.flys.eplanning.fragments.adapters.WeekAdapter;
import com.flys.eplanning.tools.View;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle affiche les tâches hebdomadaires.
 * @since 25/08/2018
 */

@EFragment(R.layout.fragment_list_weeks)
@OptionsMenu(R.menu.menu_list_task)
public class WeekFragment extends AbstractFragment {

    @ViewById(R.id.recyclerview)
    protected RecyclerView recyclerView;

    private WeekAdapter weekAdapter;
    private List<Week> weeks;
    private DividerItemDecoration dividerItemDecoration;

    @OptionsMenuItem(R.id.search)
    protected MenuItem menuItem;

    SearchView searchView;

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
                if (weeks != null) {
                    List<Week> filterListModels = filter(weeks, newText);
                    weekAdapter.setFilter(filterListModels);
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
        return View.WEEKS_LIST;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        weeks = new ArrayList<>();
        weeks.add(new Week("Semaine du 01 au 07"));
        weekAdapter = new WeekAdapter(activity,weeks);
    }

    @Override
    protected void initView(CoreState previousState) {
        //Toast.makeText(activity,"Weeks list",Toast.LENGTH_SHORT).show();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.getItemOffsets(new Rect(20, 0, 20, 0), null, recyclerView, null);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        weekAdapter = new WeekAdapter(activity,weeks);
        recyclerView.setAdapter(weekAdapter);
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

    private List<Week> filter(List<Week> list, String query) {
        query = query.toLowerCase();
        final List<Week> tasks = new ArrayList<>();

        for (Week model : list) {
            final String text = model.getName().toLowerCase();
            if (text.startsWith(query) || text.contains(query)) {
                tasks.add(model);
            }
        }
        return tasks;
    }

    private void changeSearchTextColor(android.view.View view) {
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
