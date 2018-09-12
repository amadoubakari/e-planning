package com.flys.eplanning.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flys.generictools.adapter.AbstractAdapter;

import java.util.ArrayList;
import java.util.List;

import com.flys.eplanning.R;
import com.flys.eplanning.entities.Week;

/**
 * Created by AMADOU BAKARI on 25/08/2018.
 */

public class WeekAdapter extends AbstractAdapter<WeekAdapter.HolderView> {

    private List<Week> weeks;

    public WeekAdapter(Context context, List<Week> weeks) {
        super(context);
        this.weeks = weeks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_item, parent, false);

        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Week week=models.get(position);

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (weeks != null) {
            size = weeks.size();
        }
        return size;
    }

    class HolderView extends RecyclerView.ViewHolder {

        TextView weekName;

        public HolderView(View itemView) {
            super(itemView);
            weekName = itemView.findViewById(R.id.date_name1);
        }
    }

    /**
     *
     * @param listModelsTasks
     */
    public void setFilter(List<Week> listModelsTasks) {
        weeks = new ArrayList<>();
        weeks.addAll(listModelsTasks);
        notifyDataSetChanged();

    }
    public void setDataSource(List<Week> weeks) {
        //this.models=weeks;
    }
}
