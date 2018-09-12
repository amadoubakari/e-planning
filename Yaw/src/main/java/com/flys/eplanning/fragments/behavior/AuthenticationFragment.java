package com.flys.eplanning.fragments.behavior;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import com.flys.eplanning.R;
import com.flys.eplanning.activity.MainActivity_;
import com.flys.eplanning.architecture.core.AbstractFragment;
import com.flys.eplanning.architecture.custom.CoreState;
import com.flys.eplanning.dao.db.UserDao;
import com.flys.eplanning.dao.db.UserDaoImpl;
import com.flys.eplanning.entities.User;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet de gérér l'authentification
 * @since 04/04/2018
 */

@EFragment(R.layout.login_fragment)
@OptionsMenu(R.menu.menu_vide)
public class AuthenticationFragment extends AbstractFragment {

    @ViewById(R.id.username)
    protected EditText userName;

    @ViewById(R.id.password)
    protected EditText password;

    @Bean(UserDaoImpl.class)
    protected UserDao<User, Long> userDao;

    @Click(R.id.btn_login)
    protected void btnLogin() {
        if (userName.getText().toString().equals("tony") && password.getText().toString().equals("parker")) {

            //session.setConnectedUser(new User("Amadou", "Bakari", "amadou_bakari@yahoo.fr", "00237690660199", "", ""));
            startActivity(new Intent(activity, MainActivity_.class));
        } else {
            Toast.makeText(activity, "Votre login ou mot de passe est incorrect.", Toast.LENGTH_SHORT).show();
        }
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
        Toast.makeText(activity, "Je suis authentification", Toast.LENGTH_LONG).show();
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
