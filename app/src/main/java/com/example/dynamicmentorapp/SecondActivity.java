package com.example.dynamicmentorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends FragmentActivity implements MentorFormFragment.mentorFormFragmentListener,PrefMethodsFragment.prefMethodsFragmentListener {
    ViewPager viewPager;
    String operation;
    String[] prodTasks=new String[1];
    String[] goalTimes=new String[1];
    String[]prefMethods=new String[1];
    String mPerformance;
    String mPace;
    String mUtilization;
    String mMethods;
    String mEarnedTime;
    String mOnStandardTime;
    ArrayList selections;
    CharSequence improvements1;
    CharSequence improvements2;
    CharSequence improvements3;
    CharSequence didWell1;
    CharSequence didWell2;
    CharSequence didWell3;
    CharSequence feedback1;
    CharSequence feedback2;
    CharSequence feedback3;
    String name;
    String date;
    String nameMentor;
    String idAssociate;
    String extraNotes;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the content view
        setContentView(R.layout.second_activity);

        //initialize viewPager
        viewPager = findViewById(R.id.pager);

        //create the object of the adapter class
        //the adapter returns what page (fragment) the user is in

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        //add object as an adapter for the viewPager
        viewPager.setAdapter(pagerAdapter);

        //create intent to receive data sent from MainAcrivity
        Intent fromMainActivity=getIntent();

        operation=fromMainActivity.getStringExtra("operation");
        prodTasks=fromMainActivity.getStringArrayExtra("production tasks");
        goalTimes=fromMainActivity.getStringArrayExtra("goaltimes");
        prefMethods=fromMainActivity.getStringArrayExtra("pref methods");
        name=fromMainActivity.getStringExtra("associate");
        date= fromMainActivity.getStringExtra("date");
        nameMentor=fromMainActivity.getStringExtra("mentor name");
        idAssociate=fromMainActivity.getStringExtra("id");




        System.out.println("made it here: "+date);







        //print it out to the console
        System.out.println("This is the operation passed: "+operation);



        //print it out to the console
        System.out.println("This is the first prod task passed "+prodTasks[0]);

        //passing data from second activity to mentor_form_fragment
        Bundle bundle = new Bundle();
        bundle.putString("operation",operation);
        bundle.putStringArray("production tasks", prodTasks);
        bundle.putStringArray("goaltimes",goalTimes);

    }


    @Override
    public void onInputSent(CharSequence input, CharSequence input2, CharSequence input3) {
        mPerformance=input.toString();
        mEarnedTime=input2.toString();
        mOnStandardTime=input3.toString();
    }

    @Override
    public void onInputSent(CharSequence input, CharSequence input2, CharSequence input3, CharSequence input4, CharSequence input5, CharSequence input6, CharSequence input7, CharSequence input8, CharSequence input9, ArrayList input10, String input11, String input12, String input13,String input14) {
        improvements1=input.toString();
        improvements2=input2.toString();
        improvements3=input3.toString();
        didWell1=input4.toString();
        didWell2=input5.toString();
        didWell3=input6.toString();
        feedback1=input7.toString();
        feedback2=input8.toString();
        feedback3=input9.toString();
        selections=input10;
        mPace=input11;
        mUtilization=input12;
        mMethods=input13;
        extraNotes = input14;

        RadioGroup radioGroup1;
        RadioGroup radioGroup2;
        RadioGroup radioGroup3;

        RadioButton radioButton1;
        RadioButton radioButton2;
        RadioButton radioButton3;

        int radioId1;
        int radioId2;
        int radioId3;

        //create strings combinind everything

        String totImprovements=improvements1+", "+improvements2+", "+improvements3;
        String totDidWell=didWell1+", "+didWell2+", "+didWell3;
        String totFeedback=feedback1+", "+feedback2+", "+feedback3;

        //create intent to send information to third activity (summary sheet)
        Intent intent = new Intent(this, SummaryPage.class);
        intent.putExtra("improvements", totImprovements.toString());
        intent.putExtra("didWell", totDidWell.toString());
        intent.putExtra("feedback", totFeedback.toString());
        intent.putExtra("performance",mPerformance);
        intent.putExtra("earned time", mEarnedTime);
        intent.putExtra("on standard time", mOnStandardTime);
        intent.putExtra("methods array",""+selections);
        intent.putExtra("pace",mPace);
        intent.putExtra("utilization",mUtilization);
        intent.putExtra("method",mMethods);
        intent.putExtra("associate", name);
        intent.putExtra("operation",operation);
        intent.putExtra("date",date);
        intent.putExtra("mentor name",nameMentor);
        intent.putExtra("id",idAssociate);
        intent.putExtra("extra notes",extraNotes);

        System.out.println("it made it to the second activity: "+name);

        startActivity(intent);
    }


}
