package com.flys.eplanning.tools;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;

import com.flys.generictools.reminder.LocalData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.flys.eplanning.R;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle sauvergarde toutes les fonctions génériques propre au module.
 * @email amadou_bakari@yahoo.fr
 * @tel: (+237) 674316936 / 690660199
 * @since 07/04/2018
 */

public class Utility {

    /**
     * @param context
     * @param calendar
     * @param editText : la vue sur laquelle la date sélectionnée sera transmise.
     */
    public static Calendar setDate(Context context, Calendar calendar, EditText editText) {
        DatePickerDialog dpd = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        editText.setText(String.format("%02d", day) + "/" + String.format("%02d", month + 1) + "/" + String.format("%d", year));

                    }

                }
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        dpd.setTitle("Tony Parker Freemanù$0 V");
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.setIcon(R.drawable.tony);
        dpd.show();
        return calendar;
    }

    public static void setHour(Context context, Calendar calendar, EditText editText) {
        TimePickerDialog tpd = new TimePickerDialog(context, (view1, hourOfDay, minute) -> {
            editText.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(context));
        tpd.show();
    }

    public static void setReminderTime(Context context, Calendar calendar, EditText editText) {

        TimePickerDialog tpd = new TimePickerDialog(context, (view1, hourOfDay, minute) -> {
            LocalData localData = new LocalData(context);
            //localData.set_hour(hourOfDay);
            //localData.set_min(minute);
            //localData.setTitle(dailyTask.getTitle());
            //localData.setDescription(dailyTask.getDescription());
            editText.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
           // Log.e("Notification", "titre: " + localData.getTitle() + " description :" + localData.getDescription());
            //NotificationScheduler.setReminder(context, AlarmReceiver.class, dailyTask.getTitle(), dailyTask.getDescription(), localData.get_hour(), localData.get_min());
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(context));
        tpd.show();
    }

    public static String getDate(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(date);
    }

    public static String getTime(long time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(time);
    }
    //returns full date
    public static String getFullDate(long date) {
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
        return fullDateFormat.format(date);
    }
}
