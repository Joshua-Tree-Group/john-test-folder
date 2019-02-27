package com.example.spinner4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

     String mOperationSelected;
     String mOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //here we will include everything that has goes on in the main layout
        setContentView(R.layout.activity_main);

        //call method to read csv file https://www.youtube.com/watch?v=i-TqNzUryn8
        readData();

        //spinner reference
        Spinner spinner =findViewById(R.id.spinner);

        //creating the listener for the spinner
        spinner.setOnItemSelectedListener(this);

        //button reference
        Button button=findViewById(R.id.submit_button);

        //creating the listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls method that creates intent to change activity
                //was getting error for instantiating the intent inside the onclick method
                activityChanger();
            }
        });


    }

    //method called from within the onClick method inside the oncreate method
    //used to change activity.
    public void activityChanger (){

        //this line sets up the intent to open the second activity
        Intent changeToMentorForm = new Intent(this,
                MentorForm.class);

        final int result = 1;

        changeToMentorForm.putExtra("Operation Selected", mOperationSelected);

        //this line calls the intent and executes it, if we want to pass
        //values back from the second activity, we have to use
        //startActivityForResult() instead of startActivity

        startActivityForResult(changeToMentorForm, result);

    }

    //creating method to read data*** https://www.youtube.com/watch?v=i-TqNzUryn8
    private List<sampleData> sampleDataList = new ArrayList<>();

    private void readData() {
        //get reference to raw csvfile
        InputStream is = getResources().openRawResource(R.raw.csv);
        //need a buffered reader to read the file line by line
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line ="";

        //we have to surround with a try catch bc the if statement
        //could cause an I/O exception
        try{
         //while loop to iterate through each line of the csv file
         while((line=reader.readLine())!=null){
             //creating an array of strings called tokens every time the
             //while loop iterates. Each array will hold an individual line
             //from the csv file and each of its elements
             String [] tokens=line.split(",");
             //read the data
             sampleData sample=new sampleData();
             //in the first iteration, tokens[0] will represent
             //the name of the first operation in the first line
             //of the csv file
             sample.setOperation(tokens[0]);
             sample.setProd1(tokens[1]);
             sample.setProd2(tokens[2]);
             sample.setProd3(tokens[3]);
             //now we add the created sample to the sampleDataList
             //which is the list of arrays
             sampleDataList.add(sample);
             //printing each sample array created
             System.out.println("My activity just created: "+sample);

         }

        } catch (IOException e){
            Log.wtf("MyActivity","Error reading data file on line "+line,e);
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //extract the value contained in the spinner
        String mOperationSelected = parent.getItemAtPosition(position).toString();

        //if else statement that figures out which operation was selected

        if(mOperationSelected.equals(sampleDataList.get(1).getOperation())){
            mOperationSelected=sampleDataList.get(1).getOperation();
            assignSelectedOperation(mOperationSelected);
        }else if (mOperationSelected.equals(sampleDataList.get(2).getOperation())){
            mOperationSelected=sampleDataList.get(2).getOperation();
            assignSelectedOperation(mOperationSelected);
        }else if(mOperationSelected.equals(sampleDataList.get(3).getOperation())){
            mOperationSelected=sampleDataList.get(3).getOperation();
            assignSelectedOperation(mOperationSelected);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //this method receives selection from spinner and assigns the
    //member variable the correct operation selected
    public void assignSelectedOperation(String mOperation){

        mOperationSelected=mOperation;
    }
}



