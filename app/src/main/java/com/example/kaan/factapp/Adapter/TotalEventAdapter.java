package com.example.kaan.factapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaan.factapp.Model.ReportModel;
import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;

import java.util.List;

public class TotalEventAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<TotalEventModel> totalEventModels;

    public TotalEventAdapter(Context context, List<TotalEventModel> totalEventModels) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.totalEventModels = totalEventModels;
    }

    @Override
    public int getCount() {
        return totalEventModels.size();
    }

    @Override
    public Object getItem(int position) {
        return totalEventModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null)
            row = layoutInflater.inflate(R.layout.prototype_report_row, null);

        Boolean condition = null;

        TextView tvIncidentName = row.findViewById(R.id.tvIncidentName);
        TextView tvIncidentLocation = row.findViewById(R.id.tvIncidentLocation);
        TextView tvIncidentInformer = row.findViewById(R.id.tvIncidentInformer);
        TextView tvIncidentDate = row.findViewById(R.id.tvIncidentDate);
        TextView ivOkey = row.findViewById(R.id.ivOkey);
        TextView ivClose = row.findViewById(R.id.ivClose);


        tvIncidentName.setText(totalEventModels.get(position).getIncidentName());
        tvIncidentLocation.setText(totalEventModels.get(position).getIncidentLocation());
        tvIncidentInformer.setText(totalEventModels.get(position).getIncidentInformer());
        tvIncidentDate.setText(totalEventModels.get(position).getIncidentDate());
        condition.equals(totalEventModels.get(position).getIncidentCondition());
        if (!condition.equals(null)) {
            if (condition == true) {
                ivOkey.setVisibility(View.VISIBLE);
                ivClose.setVisibility(View.GONE);
            } else if (condition == false) {
                ivClose.setVisibility(View.VISIBLE);
                ivOkey.setVisibility(View.GONE);
            }
        }


        return row;
    }
}
