package com.example.spinner4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.nio.charset.Charset;

public class summaryPage extends AppCompatActivity {
    TextView earnedTime;
    TextView onStandardTime;
    TextView performance;
    TextView usedMethods;
    TextView pace;
    TextView utilization;
    TextView methods;
    TextView didNotdoWell;
    TextView didWell;
    TextView feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the summaryPage layout
        setContentView(R.layout.summarypage);
        //set references
        didWell=findViewById(R.id.didWellText);
        didNotdoWell=findViewById(R.id.didNotDoWellTxt);
        feedback=findViewById(R.id.feedbackTxt);
        performance=findViewById(R.id.performanceTxt);
        onStandardTime=findViewById(R.id.onStandardTxt);
        earnedTime=findViewById(R.id.earnedTimeTxt);


        //get intent
        Intent intent = getIntent();
        //CharSequence didNotDoWellChar= intent.getStringExtra("improvements");
       // CharSequence didWellChar= intent.getStringExtra("didWell");
        //System.out.println("THIS IS WHAT WAS PASSED: "+didNotDoWellChar);

        didWell.setText(intent.getCharSequenceExtra("didWell"));
        didNotdoWell.setText(intent.getCharSequenceExtra("improvements"));
        feedback.setText(intent.getCharSequenceExtra("feedback"));
        performance.setText(intent.getCharSequenceExtra("performance"));
        onStandardTime.setText(intent.getCharSequenceExtra("on standard time"));
        earnedTime.setText(intent.getCharSequenceExtra("earned time"));




    }

}
