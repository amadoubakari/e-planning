package com.flys.eplanning.fragments.behavior.planning;

import android.support.design.widget.Snackbar;
import android.widget.EditText;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.custom.CoreState;
import com.flys.eplanning.tools.Utility;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Créer ou ajouter une nouvelle tache
 * @email amadou_bakari@yahoo.fr
 * @tel: (+237) 674316936 / 690660199
 * @since 6/04/2018
 */

@EFragment(R.layout.fragment_new_day_task)
@OptionsMenu(R.menu.menu_vide)
public class NewTaskFragment extends AbstractFragment {

    //Initialisation des champs de la vue
    @ViewById(R.id.spinner)
    protected MaterialSpinner spinner;

    @ViewById(R.id.task_start_date)
    protected EditText startDate;

    @ViewById(R.id.task_end_date)
    protected EditText endDate;

    //Initialisation des autres champs qui ne sont pas de la vue
    private Calendar calendar;

    @Click(R.id.task_start_date)
    protected void dateDebut() {
        Utility.setDate(activity,calendar,startDate);
    }

    @Click(R.id.task_end_date)
    protected void dateFin() {
        Utility.setDate(activity,calendar,endDate);
    }
    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 0;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        calendar = Calendar.getInstance();
    }

    @Override
    protected void initView(CoreState previousState) {

        spinner.setItems("Sélectionne la catégorie", "SANTE", "PROFESSIONNEL", "DEVELOPPEMENT PERSONNEL", "LOISIR");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
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
