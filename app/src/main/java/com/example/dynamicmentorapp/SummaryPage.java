package com.example.dynamicmentorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SummaryPage extends AppCompatActivity {
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
    Button saveSession;
    String name;
    String operation;
    String date;
    String nameMentor;
    String idAssociate;
    String extraNotes;
    Button newSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_page);

        //set references
        didWell=findViewById(R.id.didWellText);
        didNotdoWell=findViewById(R.id.didNotDoWellTxt);
        feedback=findViewById(R.id.feedbackTxt);
        performance=findViewById(R.id.performanceTxt);
        onStandardTime=findViewById(R.id.onStandardTxt);
        earnedTime=findViewById(R.id.earnedTimeTxt);
        pace=findViewById(R.id.paceTxt1);
        utilization=findViewById(R.id.utilTxt);
        methods=findViewById(R.id.mthdsTxt);
        usedMethods=findViewById(R.id.methodsArrayTxt);
        saveSession=findViewById(R.id.saveSession);
        newSession=findViewById(R.id.newSession);
        newSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger();
            }
        });



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
        pace.setText(intent.getCharSequenceExtra("pace"));
        utilization.setText(intent.getCharSequenceExtra("utilization"));
        methods.setText(intent.getCharSequenceExtra("method"));
        usedMethods.setText(intent.getCharSequenceExtra("methods array"));

        name = intent.getStringExtra("associate");
        operation= intent.getStringExtra("operation");
        date = intent.getStringExtra("date");
        nameMentor=intent.getStringExtra("mentor name");
        idAssociate=intent.getStringExtra("id");
        extraNotes=intent.getStringExtra("extra notes");



        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();

        //onclick for save session button
        saveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToInternalStorage();
            }
        });


    }

    public void writeToInternalStorage(){
        String summaryString= "Associate name: "+name+"\n\n"+
                "Associate ID: "+idAssociate+"\n\n"+
                "Mentored by: "+nameMentor+"\n\n"+
                "Date of session: "+date+"\n\n"+
                "Operation: "+operation+"\n\n"+
                "Earned time: "+earnedTime.getText().toString()+"\n\n"+
                "On standard time: "+onStandardTime.getText().toString()+"\n\n"+
                "Performance: "+performance.getText().toString()+"\n\n"+
                "Methods followed: "+usedMethods.getText().toString()+"\n\n"+
                "Pace: "+pace.getText().toString()+"\n\n"+
                "Utilization: "+utilization.getText().toString()+"\n\n"+
                "Methods: "+methods.getText().toString()+"\n\n"+
                "Opportunities to improve: "+didNotdoWell.getText().toString()+"\n\n"+
                "Things the associate did well: "+didWell.getText().toString()+"\n\n"+
                "Associate feedback: "+feedback.getText().toString()+"\n\n"+
                "Extra notes: "+extraNotes+"";

       /* File storage = new File(Environment.getExternalStorageDirectory(),"Mentor_Session_Storage.txt");
        try {
            BufferedWriter br= new BufferedWriter(new FileWriter(storage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream out = null;

        try{
            out= openFileOutput("Mentor_Session_Storage.txt", MODE_PRIVATE);
            out.write(summaryString.getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/


       // new way
        BufferedWriter writer = null;
        try {
            File file = new File (Environment.getExternalStorageDirectory()+"/Download",name+"-"+date+".txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            writer= new BufferedWriter(new OutputStreamWriter(fileOutputStream), MODE_APPEND);
            writer.write(summaryString);
            //writer.flush();
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error close writer");
                    e.printStackTrace();
                }
            }
        }

       //third attempt

       /* File file = new File (Environment.getExternalStorageDirectory(),"Mentor_Session_Storage.txt");

        FileOutputStream fos= null;
     try {
         fos = new FileOutputStream(file);
         fos.write(summaryString.getBytes());
         Toast.makeText(this, "Saved successfully to: "+Environment.getExternalStorageDirectory()+"/"+"Mentor_Session_Storage", Toast.LENGTH_LONG).show();
     }catch (FileNotFoundException e){
         e.printStackTrace();
     }catch (IOException e){
         e.printStackTrace();
     }finally {
         if (fos!=null){
             try {
                 fos.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }*/


    }

    public void activityChanger(){
        //set up the intent to open the second activity
        Intent changeToMainActivity = new Intent(this,MainActivity.class);

        final int result = 1;

        startActivityForResult(changeToMainActivity,result);
    }
}
