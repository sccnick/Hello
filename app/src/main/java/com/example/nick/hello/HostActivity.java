package com.example.nick.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class HostActivity extends AppCompatActivity {

    private ListView listcontainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        initInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, HostFragment.newInstance())
                    .commit();
        }
    }

    private void initInstance() {

    }
}
