package com.example.spinner4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.io.Serializable;


public class MentorForm extends FragmentActivity implements Serializable {
    ViewPager viewPager;
    String mOperationSelected;
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
        //passing data form activity to fragment_one
        Bundle bundle = new Bundle();
        bundle.putString("operation", op.operationName);
        //set fragment arguments
        fragment_one myFrag = new fragment_one();
        myFrag.setArguments(bundle);




    }
}




