package com.example.kaan.factapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kaan.factapp.Adapter.ReportAdapter;
import com.example.kaan.factapp.Adapter.TotalEventAdapter;
import com.example.kaan.factapp.Model.ReportModel;
import com.example.kaan.factapp.Model.TotalEventModel;
import com.example.kaan.factapp.R;

import java.util.ArrayList;
import java.util.List;

public class TotalEventActivity extends AppCompatActivity {

    private ListView lvTotalEventsList;
    private List<TotalEventModel> totalEventModels = new ArrayList<>();
    private TotalEventAdapter totalEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_event);
        init();
    }

    private void init() {
        lvTotalEventsList = (ListView) findViewById(R.id.lvTotalEventList);
    }
}
