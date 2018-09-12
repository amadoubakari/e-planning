package com.flys.eplanning.tools;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import com.flys.eplanning.entities.DailyTask;
import com.flys.eplanning.entities.Day;
import com.flys.eplanning.entities.Month;
import com.flys.eplanning.entities.ReminderTime;
import com.flys.eplanning.entities.User;
import com.flys.eplanning.entities.Week;

/**
 * @author AMADOU BAKARI
 * @since 07/05/2018
 * @todo Elle permet de configurer les entités de la base de données.
 * @version 1.0.0
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 690660199 / 674316936
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {
            DailyTask.class, User.class, ReminderTime.class, Week.class, Day.class, Month.class
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
