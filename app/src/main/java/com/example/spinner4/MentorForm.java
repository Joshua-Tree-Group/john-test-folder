package com.example.spinner4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class MentorForm extends FragmentActivity implements Serializable, fragment_one.FragmentAListener, com.example.spinner4.fragment_two.FragmentBListner {
    ViewPager viewPager;
    private fragment_one fragment_one;
    private fragment_two fragment_two;
    CharSequence improvements1;
    CharSequence improvements2;
    CharSequence improvements3;
    CharSequence didWell1;
    CharSequence didWell2;
    CharSequence didWell3;
    CharSequence feedback1;
    CharSequence feedback2;
    CharSequence feedback3;
    String mPerformance;
    String mEarnedTime;
    String mOnStandardTime;
    ArrayList selection;
    String mPace;
    String mUtilization;
    String mMethods;

    operation op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the mentor layout so that we can se the viewPager
        setContentView(R.layout.mentor_form);
        //initialize the viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        //create object of the adapter class
        //the adapter returns what page the user is in
        PagerAdapter pAdapter = new pagerAdapter(getSupportFragmentManager());
        //add object as an adapter for the viewPager
        viewPager.setAdapter(pAdapter);
        //read the data again

        //readData();
        //create an intent that receives the data that was passed
        //from the main activity

        Intent fromMainActivity = getIntent();
         op = fromMainActivity.getParcelableExtra("Operation object");


        //printing out the selected operation
        System.out.println(op.operationName + " from mentorForm activity");
        System.out.println(op.prefferedMethods[1] + " array from mentorForm activity");
        //passing data form activity to fragment_one
        Bundle bundle = new Bundle();
        bundle.putString("operation", op.operationName);

        //set fragment arguments for fragment one
        fragment_one = new fragment_one();
        fragment_one.setArguments(bundle);

        //passing data from activity to fragment_two
        Bundle bundle2 =new Bundle();
        bundle2.putStringArray("preferred methods",op.prefferedMethods);

        //set fragment arguments fro fragment 2
        fragment_two =new fragment_two();
        fragment_two.setArguments(bundle2);



    }

    @Override
    public void onInputASent(CharSequence input, CharSequence input2, CharSequence input3) {
        Toast.makeText(getApplication(),"THIS IS WHAT WAS PASSED: "+input,Toast.LENGTH_LONG).show();

        mPerformance= input.toString();
        mEarnedTime= input2.toString();
        mOnStandardTime=input3.toString();
    }

    @Override
    public void onInputBSent(CharSequence input, CharSequence input2, CharSequence input3, CharSequence input4, CharSequence input5,
                             CharSequence input6, CharSequence input7, CharSequence input8, CharSequence input9, ArrayList input10,String input11,
                             String input12, String input13) {
        Toast.makeText(getApplication(),"THIS IS WHAT WAS PASSED: "+input+""+input2,Toast.LENGTH_LONG).show();

        improvements1=input;
        improvements2=input2;
        improvements3=input3;
        didWell1=input4;
        didWell2=input5;
        didWell3=input6;
        feedback1=input7;
        feedback2=input8;
        feedback3=input9;
        selection=input10;
        mPace=input11;
        mUtilization=input12;
        mMethods=input13;

        String totImprovements=improvements1+", "+improvements2+", "+improvements3;
        String totDidWell=didWell1+", "+didWell2+", "+didWell3;
        String totFeedback=feedback1+", "+feedback2+", "+feedback3;

        Intent intent = new Intent(this, summaryPage.class);
        intent.putExtra("improvements", totImprovements.toString());
        intent.putExtra("didWell", totDidWell.toString());
        intent.putExtra("feedback", totFeedback.toString());
        intent.putExtra("performance",mPerformance);
        intent.putExtra("earned time", mEarnedTime);
        intent.putExtra("on standard time", mOnStandardTime);
        intent.putExtra("methods array",""+selection);
        intent.putExtra("pace",mPace);
        intent.putExtra("utilization",mUtilization);
        intent.putExtra("method",mMethods);



        startActivity(intent);

       ;





    }


}




