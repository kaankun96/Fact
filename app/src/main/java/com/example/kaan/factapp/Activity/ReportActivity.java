package com.example.kaan.factapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kaan.factapp.Adapter.ReportAdapter;
import com.example.kaan.factapp.Model.ReportModel;
import com.example.kaan.factapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private ListView lvReportList;
    private List<ReportModel> reportModels = new ArrayList<>();
    private ReportAdapter reportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        init();
    }

    private void init() {
        lvReportList = (ListView) findViewById(R.id.lvReportList);
    }
}
