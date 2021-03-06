package com.example.spinner4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MentorForm extends Activity {

    String mOperation;
    String mProdTaskOne;
    String mProdTaskTwo;
    String mProdTaskThree;
    String mOperationSelected;
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
   //fist set of counter buttons
    private TextView counterTxt;
    private Button minusBtn;
    private Button plusBtn;
    private Button resetBtn;
    private int counter;
   //second set of counter buttons
    private TextView counterTxt1;
    private Button minusBtn1;
    private Button plusBtn1;
    private Button resetBtn1;
    private int counter1;
    //third set of counter buttons
    private TextView counterTxt2;
    private Button minusBtn2;
    private Button plusBtn2;
    private Button resetBtn2;
    private int counter2;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mentor_form);

        //read the data again

        readData();
        //create an intent that receives the data that was passed
        //from the main activity

        Intent fromMainActivity =getIntent();

        //this line gets the extra info that was passed with
        //.putExtra in main activity
         mOperationSelected = fromMainActivity.getExtras().getString("Operation Selected");

        //printing out the selected operation
        System.out.println(mOperationSelected);


        //calling method that assigns production tasks based on what
        //operation was selected
        assignProdTasks();

        //set the references for the textViews in the layout
        TextView mProdTaskOne =findViewById(R.id.prodTask1);
        TextView mProdTaskTwo =findViewById(R.id.prodTask2);
        TextView mProdTaskThree =findViewById(R.id.prodTask3);
        TextView mOperation = findViewById(R.id.operation);

        //creating reference for chronometer
         chronometer=(Chronometer)findViewById(R.id.chronometer);

         //creating references for counter elements and
        //using .setOnClickListener to get the value of the
        //button that was clicked in the clickListener

        //fist set of counter buttons
        counterTxt=(TextView)findViewById(R.id.counterTxt);
        minusBtn=(Button)findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(clickListener);
        plusBtn=(Button)findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(clickListener);
        resetBtn=(Button)findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(clickListener);
        //second set of counter buttons
        counterTxt1=(TextView)findViewById(R.id.counterTxt1);
        minusBtn1=(Button)findViewById(R.id.minusBtn1);
        minusBtn1.setOnClickListener(clickListener);
        plusBtn1=(Button)findViewById(R.id.plusBtn1);
        plusBtn1.setOnClickListener(clickListener);
        resetBtn1=(Button)findViewById(R.id.resetBtn1);
        resetBtn1.setOnClickListener(clickListener);
        //third set of counter buttons
        counterTxt2=(TextView)findViewById(R.id.counterTxt2);
        minusBtn2=(Button)findViewById(R.id.minusBtn2);
        minusBtn2.setOnClickListener(clickListener);
        plusBtn2=(Button)findViewById(R.id.plusBtn2);
        plusBtn2.setOnClickListener(clickListener);
        resetBtn2=(Button)findViewById(R.id.resetBtn2);
        resetBtn2.setOnClickListener(clickListener);



        //call methods
        setProdTasks(mProdTaskOne,mProdTaskTwo,mProdTaskThree,mOperation);
    }

    //click listener for button clicks
    //depending on what button was clicked, it will call the corresponding
    //method
    private View.OnClickListener clickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //v.getId will return the id of the clicked button
            switch (v.getId()){
                //case that first minus is clicked
                case R.id.minusBtn:
                    minusCounter();
                    break;
                //case that second minus is clicked
                case R.id.minusBtn1:
                    minusCounter1();
                    break;
                case R.id.minusBtn2:
                    minusCounter2();
                    break;
                case R.id.plusBtn:
                    plusCounter();
                    break;
                case R.id.plusBtn1:
                    plusCounter1();
                    break;
                case R.id.plusBtn2:
                    plusCounter2();
                    break;
                case R.id.resetBtn:
                    initCounter();
                    break;
                case R.id.resetBtn1:
                    initCounter1();
                    break;
                case R.id.resetBtn2:
                    initCounter2();
                    break;
            }
        }
    };

    //3 methods for FIRST set of counter buttons
    private void initCounter(){
        counter=0;
        counterTxt.setText(counter+"");
    }

    private void plusCounter(){
        counter++;
        counterTxt.setText(counter+"");
        System.out.println("this method was called too");
    }

    private void minusCounter(){
        counter--;
        counterTxt.setText(counter+"");
    }
    //3 methods for SECOND set of counter buttons
    private void initCounter1(){
        counter1=0;
        counterTxt1.setText(counter1+"");
    }

    private void plusCounter1(){
        counter1++;
        counterTxt1.setText(counter1+"");
    }

    private void minusCounter1(){
        counter1--;
        counterTxt1.setText(counter1+"");
    }
    //3 methods for THIRD set of counter buttons
    private void initCounter2(){
        counter2=0;
        counterTxt2.setText(counter2+"");
    }

    private void plusCounter2(){
        counter2++;
        counterTxt2.setText(counter2+"");
    }

    private void minusCounter2(){
        counter2--;
        counterTxt2.setText(counter2+"");
    }

    //3 methods used for starting, pausing, and resetting the chronometer https://www.youtube.com/watch?v=RLnb4vVkftc
    public void startChronometer(View view){
        //check if chronometer is running or not
        if(!running){
            //tell chronometer from which time we want to count onwards
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            chronometer.start();
            running=true;
        }
    }
    public void pauseChronometer(View view){
        if(running){
            //.stop() only stops updating the text, doesn't stop the
            //chronometer
            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;
        }

    }
    public void resetChronometer(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
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

        String line = "";

        //we have to surround with a try catch bc the if statement
        //could cause an I/O exception
        try {
            //while loop to iterate through each line of the csv file
            while ((line = reader.readLine()) != null) {
                //creating an array of strings called tokens every time the
                //while loop iterates. Each array will hold an individual line
                //from the csv file and each of its elements
                String[] tokens = line.split(",");
                //read the data
                sampleData sample = new sampleData();
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
                System.out.println("My activity just created: " + sample.toString());

            }

        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }

    }

    //this method uses TextView refernces from the oncreate method and assigns them the value of
    //the production tasks determined in the assignProdTasks() method with the .setText function.
    public void setProdTasks(TextView prodTaskOne, TextView prodTaskTwo, TextView prodTaskThree, TextView operation){
        prodTaskOne.setText(mProdTaskOne);
        prodTaskTwo.setText(mProdTaskTwo);
        prodTaskThree.setText(mProdTaskThree);
        operation.setText(mOperation);


    }

    //This method will use if else logic to determine which operation was selected
    //and based on the identified selection it will populate the production task member variables

    public void assignProdTasks(){
        System.out.println(mOperationSelected);
        System.out.println(sampleDataList.get(1).getOperation());

        if(mOperationSelected.equals(sampleDataList.get(1).getOperation())){
            //if the first operation was selected, then populate
            //the production tasks for that operation
            mOperation=sampleDataList.get(1).getOperation();
            mProdTaskOne=sampleDataList.get(1).getProd1();
            mProdTaskTwo=sampleDataList.get(1).getProd2();
            mProdTaskThree=sampleDataList.get(1).getProd3();
        }else if (mOperationSelected.equals(sampleDataList.get(2).getOperation())){
            mOperation=sampleDataList.get(2).getOperation();
            mProdTaskOne=sampleDataList.get(2).getProd1();
            mProdTaskTwo=sampleDataList.get(2).getProd2();
            mProdTaskThree=sampleDataList.get(2).getProd3();
        }else if(mOperationSelected.equals(sampleDataList.get(3).getOperation())){
            mOperation=sampleDataList.get(3).getOperation();
            mProdTaskOne=sampleDataList.get(3).getProd1();
            mProdTaskTwo=sampleDataList.get(3).getProd2();
            mProdTaskThree=sampleDataList.get(3).getProd3();
        }
    }


}




