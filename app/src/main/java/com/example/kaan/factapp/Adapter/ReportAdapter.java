package com.example.kaan.factapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaan.factapp.Model.ReportModel;
import com.example.kaan.factapp.R;

import java.util.List;

public class ReportAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<ReportModel> reportModels;

    public ReportAdapter(Context context, List<ReportModel> reportModels) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.reportModels = reportModels;
    }

    @Override
    public int getCount() {
        return reportModels.size();
    }

    @Override
    public Object getItem(int position) {
        return reportModels.get(position);
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
        TextView ivOkey = row.findViewById(R.id.ivOkey);
        TextView ivClose = row.findViewById(R.id.ivClose);


        tvIncidentName.setText(reportModels.get(position).getIncidentName());
        tvIncidentLocation.setText(reportModels.get(position).getIncidentLocation());
        condition.equals(reportModels.get(position).getIncidentCondition());
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
