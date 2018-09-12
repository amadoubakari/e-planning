package com.flys.eplanning.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.flys.generictools.dao.daoException.DaoException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.flys.eplanning.R;
import com.flys.eplanning.architecture.custom.Session;
import com.flys.eplanning.dao.db.UserDao;
import com.flys.eplanning.dao.db.UserDaoImpl;
import com.flys.eplanning.entities.User;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet de gérer la connexion.
 * @since 07/07/2018
 */
//@EActivity
//@OptionsMenu(R.menu.menu_main)
@EActivity(R.layout.login_fragment)
public class LoginActivity extends AppCompatActivity {

    @ViewById(R.id.username)
    protected EditText userName;

    @ViewById(R.id.password)
    protected EditText password;

    @Bean(UserDaoImpl.class)
    protected UserDao<User, Long> userDao;

    private Session session;

    @Click(R.id.btn_login)
    protected void login() {
        startActivity(new Intent(this, MainActivity_.class));
        User user;
        try {
            user = userDao.findUser(userName.getText().toString().trim(), password.getText().toString().trim());
            if (user != null) {
                if (user.getPassword().equals("bakari")) {
                    session.setConnectedUser(user);
                    startActivity(new Intent(this, MainActivity_.class));
                }
            } else {
                Toast.makeText(this, R.string.msg_login_or_password_incorrect, Toast.LENGTH_SHORT).show();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @AfterViews
    protected void afterView() {
        session=new Session();

    }

    void initView() {

    }
}
