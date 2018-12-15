package com.flys.eplanning.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.flys.generictools.views.RecyclerViewItemClickListener;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.entities.Task;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet d'adapter chaque tâche à la vue.
 * @email amadou_bakari@yahoo.fr
 * @tel (+237) 674316936 / 690660199
 * @since 07/04/2018
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Holderview> {

    private List<Task> listModels;
    private Context context;
    private int resourceId;

    private Calendar calendar;

    private RecyclerViewItemClickListener listener;

    public TaskAdapter(List<Task> listModels, Context context, RecyclerViewItemClickListener listener) {
        this.listModels = listModels;
        this.context = context;
        this.resourceId = resourceId;
        this.listener = listener;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        final Holderview pvh = new Holderview(layout);
       /* layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, pvh.getLayoutPosition());
            }
        });*/
        return pvh;
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar mycal = new GregorianCalendar(year, month, day);

// Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28


        String weekday = new DateFormatSymbols().getShortWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)];


        //holder.date.setText();
        holder.title.setText(listModels.get(position).getTitle());

        // holder.description.setText(listModels.get(position).getDescription());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       
        //final Holderview pvh = new Holderview(layout);
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                PopupMenu popupMenu = new PopupMenu(context, holder.menu);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.option_menu_archive:
                                Toast.makeText(context, "Save", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.option_menu_delete:
                                listModels.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        //Action ou click sur un élément de la liste


    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }


    public void setFilter(List<Task> listModelsTasks) {
        listModels = new ArrayList<>();
        listModels.addAll(listModelsTasks);
        notifyDataSetChanged();

    }


    class Holderview extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        ImageView menu;
        LinearLayout details;
        //TextView description;

        public Holderview(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            menu = itemView.findViewById(R.id.task_menu);
            details = itemView.findViewById(R.id.details);

        }
    }
}
