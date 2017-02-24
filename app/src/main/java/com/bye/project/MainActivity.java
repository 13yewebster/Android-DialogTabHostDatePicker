package com.bye.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bye.project.helper.DateTimePicker;
import com.bye.project.helper.SimpleDateTimePicker;

import java.util.Date;

/**
 * Created by Bye Webster on 24/02/17.
 * Website : http://bye-webster.blogspot.com
 */
public class MainActivity extends AppCompatActivity implements DateTimePicker.OnDateFromSetListener, DateTimePicker.OnDateToSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_datepicker = (Button)findViewById(R.id.btn_datepicker);
        btn_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                        "Plan Your Trip",
                        new Date(),
                        MainActivity.this,
                        MainActivity.this,
                        getSupportFragmentManager()
                );
                // Show It baby !
                simpleDateTimePicker.show();
            }
        });

    }

    @Override
    public void DateFromSet(String date) {
        Log.d("MainActivity", "Date From : " + date);
    }

    @Override
    public void DateToSet(String date) {
        Log.d("MainActivity", "Date To : " + date);
    }

}
