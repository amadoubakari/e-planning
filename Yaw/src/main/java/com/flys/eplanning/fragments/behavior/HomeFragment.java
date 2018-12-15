package com.flys.eplanning.fragments.behavior;

import android.support.design.widget.Snackbar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.custom.CoreState;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo La page d'accueil.
 * @since 03/04/2018
 */

@EFragment(R.layout.fragment_home)
@OptionsMenu(R.menu.menu_vide)
public class HomeFragment extends AbstractFragment {

    @Click(R.id.fab)
    protected void fab() {
        Snackbar.make(getView(), "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 1;
    }

    @Override
    protected void initFragment(CoreState previousState) {

    }

    @Override
    protected void initView(CoreState previousState) {

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
