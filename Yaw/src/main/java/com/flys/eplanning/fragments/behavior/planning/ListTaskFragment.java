package com.flys.eplanning.fragments.behavior.planning;

import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flys.generictools.views.RecyclerViewItemClickListener;

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
import com.flys.eplanning.entities.Month;
import com.flys.eplanning.entities.Week;
import com.flys.eplanning.fragments.adapters.MonthsAdapter;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Affiche la liste de tâches programmées.
 * @email amadou_bakari@yahoo.fr
 * @tel: (+237) 674316936 / 690660199
 * @since 07/04/2018
 */

@EFragment(R.layout.fragment_list_task)
@OptionsMenu(R.menu.menu_list_task)
public class ListTaskFragment extends AbstractFragment {

    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<Month> listModels;
    private MonthsAdapter monthsAdapter;
    SearchView searchView;

    @OptionsMenuItem(R.id.search)
    protected MenuItem menuItem;

    private DividerItemDecoration dividerItemDecoration;

    @Click(R.id.fab)
    protected void fab() {

        mainActivity.navigateToView(1, ISession.Action.NAVIGATION);
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
                    List<Month> filterListModels = filter(listModels, newText);
                    monthsAdapter.setFilter(filterListModels);
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
        return 3;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        listModels = new ArrayList<>();

        List<Week> weeks = new ArrayList<>();
        weeks.add(new Week("Semaine du 01 au 07"));
        weeks.add(new Week("Semaine du 07 au 14"));
        weeks.add(new Week("Semaine du 14 au 21"));
        weeks.add(new Week("Semaine du 21 au 28"));
        weeks.add(new Week("Semaine du 28 au 31"));
        listModels.add(new Month("January", weeks));
        listModels.add(new Month("February", null));
        listModels.add(new Month("March", null));
        listModels.add(new Month("April", null));
        listModels.add(new Month("May", null));
        listModels.add(new Month("Jun", null));
        listModels.add(new Month("Jully", null));
        listModels.add(new Month("August", null));
        listModels.add(new Month("September", null));
        listModels.add(new Month("October", null));
        listModels.add(new Month("November", null));
        listModels.add(new Month("December", null));
    }

    @Override
    protected void initView(CoreState previousState) {

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.getItemOffsets(new Rect(20, 0, 20, 0), null, recyclerView, null);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        monthsAdapter = new MonthsAdapter(listModels, activity, new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(activity, "Item " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(monthsAdapter);
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

    private List<Month> filter(List<Month> list, String query) {
        query = query.toLowerCase();
        final List<Month> tasks = new ArrayList<>();

        for (Month model : list) {
            final String text = model.getName().toLowerCase();
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
