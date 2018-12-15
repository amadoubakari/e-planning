package com.flys.eplanning.activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.core.AbstractActivity;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.dao.service.IDao;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @@since 06/04/2018
 * @todo Elle permet de gérer tout ce qui va concerner le planning
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 674316936 / 690660199
 */

@EActivity
@OptionsMenu(R.menu.menu_main)
public class TaskActivity extends AbstractActivity {

    @Override
    public boolean deconnexion() {
        return false;
    }

    @Override
    public boolean connexion() {
        return false;
    }

    @Override
    protected void onCreateActivity() {

    }

    @Override
    protected IDao getDao() {
        return null;
    }

    @Override
    protected AbstractFragment[] getFragments() {
        return new AbstractFragment[0];
    }

    @Override
    protected CharSequence getFragmentTitle(int position) {
        return null;
    }

    @Override
    protected void navigateOnTabSelected(int position) {

    }

    @Override
    protected int getFirstView() {
        return 0;
    }

    @Override
    protected boolean needCollapse() {
        return false;
    }
}
