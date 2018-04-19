package com.example.kaan.factapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;

import java.util.ArrayList;

public class TotalEventAdapter extends RecyclerView.Adapter<TotalEventAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<TotalEventModel> totalEventModels;
    private String locationEqusals;

    public TotalEventAdapter(Context context, ArrayList<TotalEventModel> totalEventModels) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.totalEventModels = totalEventModels;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prototype_all_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TotalEventModel totalEventModel = totalEventModels.get(position);
        holder.tvIncidentName.setText(totalEventModel.getName());
        holder.tvIncidentLocation.setText(totalEventModel.getLocation());
        holder.tvIncidentInformers.setText(totalEventModel.getUserName()+" "+totalEventModel.getUserLastName());
        holder.tvIncidentDate.setText(totalEventModel.getDateTimes());
        holder.condition = totalEventModel.getResultIncident().toString();
        Log.i("kaankaan", " " + holder.condition);
        if (holder.condition.equals("YES") && holder.condition != null) {
            holder.ivOkey.setVisibility(View.VISIBLE);
            holder.ivClose.setVisibility(View.GONE);
            holder.llBorder.setBackgroundResource(R.drawable.border_prototype_report);
        } else if (holder.condition.equals("NO") && holder.condition != null) {
            holder.ivClose.setVisibility(View.VISIBLE);
            holder.ivOkey.setVisibility(View.GONE);
            holder.llBorder.setBackgroundResource(R.drawable.border_prototype_red);
        }
    }


    @Override
    public int getItemCount() {
        return totalEventModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        String condition;

        TextView tvIncidentName = itemView.findViewById(R.id.tvIncidentName);
        TextView tvIncidentLocation = itemView.findViewById(R.id.tvIncidentLocation);
        TextView tvIncidentInformers = itemView.findViewById(R.id.tvIncidentInformers);
        TextView tvIncidentDate = itemView.findViewById(R.id.tvIncidentDate);
        ImageView ivOkey = itemView.findViewById(R.id.ivOkey);
        ImageView ivClose = itemView.findViewById(R.id.ivClose);
        LinearLayout llBorder = itemView.findViewById(R.id.llBorder);

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
