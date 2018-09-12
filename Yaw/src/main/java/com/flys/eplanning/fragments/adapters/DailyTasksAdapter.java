package com.flys.eplanning.fragments.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flys.generictools.dao.daoException.DaoException;
import com.flys.generictools.views.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.dao.db.DailyTaskDao;
import com.flys.eplanning.entities.DailyTask;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet d'afficher chaque tâche sur un modèle de vue.
 * @email amadou_bakari@yahoo.fr
 * @phone (+237) 674316936 / 690660199
 * @since 19/04/2018
 */

public class DailyTasksAdapter extends RecyclerView.Adapter<DailyTasksAdapter.Holderview> {

    private List<DailyTask> listModels;
    private Context context;
    private int resourceId;
    private DailyTaskDao<DailyTask, Long> dailyTaskDao;

    private RecyclerViewItemClickListener listener;

    public DailyTasksAdapter(List<DailyTask> listModels, Context context, RecyclerViewItemClickListener listener, DailyTaskDao<DailyTask, Long> dailyTaskDao) {
        this.listModels = listModels;
        this.context = context;
        this.resourceId = resourceId;
        this.listener = listener;
        this.dailyTaskDao = dailyTaskDao;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_task_item, parent, false);
        final Holderview pvh = new Holderview(layout);
        return pvh;
    }

    @Override
    public void onBindViewHolder(Holderview holder, int position) {
        DailyTask dailyTask = listModels.get(position);
        holder.title.setText(dailyTask.getTitle());
        holder.hourFrom.setText(dailyTask.getHeureDebut());
        holder.hourTo.setText(dailyTask.getHeureFin());
        holder.number.setText(String.valueOf(position + 1));

        if (dailyTask.isFinish()||dailyTask.getNiveau()==100) {
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.green_500)));
        }else {
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.blue_300)));
            holder.progressBar.setProgress(dailyTask.getNiveau());
        }
        if(dailyTask.isArchived()){
            holder.itemLayout.setBackgroundColor(context.getResources().getColor(R.color.grey_200));
        }
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
                Context wrapper=new ContextThemeWrapper(context,R.style.popupmenu);
                PopupMenu popupMenu = new PopupMenu(wrapper, holder.menu);
                popupMenu.setGravity(10);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        DailyTask task;
                        switch (menuItem.getItemId()) {
                            case R.id.option_menu_edit:
                                Toast.makeText(context, "Modifier", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.option_menu_evaluer:
                                Toast.makeText(context, "Evaluation", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.option_menu_archive:
                               DailyTask task1=listModels.get(position);
                                task1.setArchived(true);
                                dailyTaskDao.update(task1);
                                notifyDataSetChanged();
                                break;
                            case R.id.option_menu_delete:
                                try {
                                    dailyTaskDao.delete(listModels.get(position));
                                    listModels.remove(position);
                                    notifyDataSetChanged();

                                } catch (DaoException e) {
                                    e.printStackTrace();
                                }
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

    }

    @Override
    public int getItemCount() {
        int size=0;
        if(listModels!=null&&!listModels.isEmpty()){
            size= listModels.size();
        }
        return size;
    }


    public void setFilter(List<DailyTask> listModelsTasks) {
        listModels = new ArrayList<>();
        listModels.addAll(listModelsTasks);
        notifyDataSetChanged();

    }


    class Holderview extends RecyclerView.ViewHolder {

        TextView title;
        ImageView menu;
        LinearLayout details;
        TextView number;
        TextView hourFrom;
        TextView hourTo;
        ProgressBar progressBar;
        LinearLayout itemLayout;

        public Holderview(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            number = itemView.findViewById(R.id.task_number);
            menu = itemView.findViewById(R.id.task_menu);
            details = itemView.findViewById(R.id.details);
            hourFrom = itemView.findViewById(R.id.task_from);
            hourTo = itemView.findViewById(R.id.task_to);
            progressBar = itemView.findViewById(R.id.progressBar);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }


}
