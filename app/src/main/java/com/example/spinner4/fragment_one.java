package com.example.spinner4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class fragment_one extends Fragment {
    DecimalFormat percentage = new DecimalFormat("###.#%");
    public String fragOneOperationSelected;
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
    //chronometer buttons
    private Button startBtn;
    private Button resetChronBtn;
    private Button pauseBtn;
    MentorForm activity2;
    long startTimeChron;
    //goal time text views
    TextView goalTime1;
    TextView goalTime2;
    TextView goalTime3;
    //calculate performance btn
    Button calculateBtn;
    //assign member variables to the goal times used in the mentor form
    Double mGoalTime1;
    Double mGoalTime2;
    Double mGoalTime3;
    //textview display for performance calculation
    TextView performanceCalculation;
    //member variable for interface
    //the listener is the activity that uses our fragment
    private FragmentAListener listener;

    //Interface to comunicate with activity
    public interface FragmentAListener{
        void onInputASent(CharSequence input, CharSequence input2, CharSequence input3);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("the oncreate methd was called");
        if (savedInstanceState==null){
            counter=0;
            counter1=0;
            counter2=0;
            startTimeChron=0;
        }else{
            counter=savedInstanceState.getInt("counter");
            counter1=savedInstanceState.getInt("counter1");
            counter2=savedInstanceState.getInt("counter2");
            //set the text on the chronometer
            startTimeChron=0;
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_one, container, false);
        //reading the csv file
        readData();
        //create a variable that holds the reference to the
        //mentor activity so that we can access info from it.
        activity2 = (MentorForm) getActivity();
        //passing the value of the operation selected from the mentorForm activity to the this fragment
        fragOneOperationSelected = activity2.op.operationName;
        System.out.println(fragOneOperationSelected + " from oncreateView");
        //calling method that assigns production tasks based on what
        //operation was selected
        assignProdTasks(fragOneOperationSelected);

        //set the references for the textViews in the layout
        TextView mProdTaskOne = fragView.findViewById(R.id.prodTask1);
        TextView mProdTaskTwo = fragView.findViewById(R.id.prodTask2);
        TextView mProdTaskThree = fragView.findViewById(R.id.prodTask3);
        TextView mOperation = fragView.findViewById(R.id.operation);
        //creating reference for chronometer
        chronometer = (Chronometer) fragView.findViewById(R.id.chronometer);
        //creating references for counter elements and
        //using .setOnClickListener to get the value of the
        //button that was clicked in the clickListener
        //fist set of counter buttons
        counterTxt = (TextView) fragView.findViewById(R.id.counterTxt);
        minusBtn = (Button) fragView.findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(clickListener);
        plusBtn = (Button) fragView.findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(clickListener);
       // resetBtn = (Button) fragView.findViewById(R.id.resetBtn);
       // resetBtn.setOnClickListener(clickListener);
        //second set of counter buttons
        counterTxt1 = (TextView) fragView.findViewById(R.id.counterTxt1);
        minusBtn1 = (Button) fragView.findViewById(R.id.minusBtn1);
        minusBtn1.setOnClickListener(clickListener);
        plusBtn1 = (Button) fragView.findViewById(R.id.plusBtn1);
        plusBtn1.setOnClickListener(clickListener);
       // resetBtn1 = (Button) fragView.findViewById(R.id.resetBtn1);
        //resetBtn1.setOnClickListener(clickListener);
        //third set of counter buttons
        counterTxt2 = (TextView) fragView.findViewById(R.id.counterTxt2);
        minusBtn2 = (Button) fragView.findViewById(R.id.minusBtn2);
        minusBtn2.setOnClickListener(clickListener);
        plusBtn2 = (Button) fragView.findViewById(R.id.plusBtn2);
        plusBtn2.setOnClickListener(clickListener);
        //resetBtn2 = (Button) fragView.findViewById(R.id.resetBtn2);
        //resetBtn2.setOnClickListener(clickListener);
        //setting references for chronometer buttons
        startBtn = (Button) fragView.findViewById(R.id.startBtn);
        pauseBtn = (Button) fragView.findViewById(R.id.pauseBtn);
        resetChronBtn = (Button) fragView.findViewById(R.id.resetBtnChron);
        //set references for goal time textViews
        goalTime1=(TextView)fragView.findViewById(R.id.goalTime1);
        goalTime2=(TextView)fragView.findViewById(R.id.goalTime2);
        goalTime3=(TextView)fragView.findViewById(R.id.goalTime3);
        //set reference for the calculation txtView
        performanceCalculation=fragView.findViewById(R.id.calculation);
        //initialize the counter buttons
        counterTxt.setText(""+counter);
        counterTxt1.setText(""+counter1);
        counterTxt2.setText(""+counter2);
        //set click listener for chronometer
        startBtn.setOnClickListener(chronClickListener);
        pauseBtn.setOnClickListener(chronClickListener);
        resetChronBtn.setOnClickListener(chronClickListener);
        //set click listener for calculate button
        calculateBtn=(Button)fragView.findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(calculateClickListener);
        //call methods
        setProdTasks(mProdTaskOne, mProdTaskTwo, mProdTaskThree, mOperation);
        setGoalTimes(fragOneOperationSelected);


        return fragView;


    }

    //this method will be called when our fragment is attached to our activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //context is our activity
        //we are checking if the activity implements our interface
        if (context instanceof  FragmentAListener){
            //if the activity does implement the interface then
            //we want to take our listener variable and assign it to
            //the context, which is our activity.
            listener=(FragmentAListener)context;

        }else{

            throw new RuntimeException(context.toString()
            + "must implement fragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        System.out.println("The onViewStateRestored method was called");
        if (savedInstanceState==null){
            counter=0;
            counter1=0;
            counter2=0;

        }else{
            counter=savedInstanceState.getInt("counter");
            counter1=savedInstanceState.getInt("counter1");
            counter2=savedInstanceState.getInt("counter2");
            //set the text on the chronometer
            chronometer.setBase(SystemClock.elapsedRealtime());
        }


    }

    //click listener for calculatePerformance Button
    private View.OnClickListener calculateClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CharSequence holderCharSequence = chronometer.getText();
            System.out.println("CHRONOMETER TIME  "+holderCharSequence.toString());
            String holderString = holderCharSequence.toString();
            String holderString2 = holderString.replace(":","");
            System.out.println("THIS IS THE STRING WITHOUT :"+holderString2);
            int stringLenght = holderString2.length();
            System.out.println("THIS IS THE STRING LENGTH "+stringLenght);
            //getting the first digit
            Character firstNum=holderString2.charAt(0);
            System.out.println("THIS IS THE FIRST DIGIT IN CHAR FORMAT"+firstNum);
            String firstNumString = firstNum.toString();
            int firstNumInt=Integer.parseInt(firstNumString);
            System.out.println("THIS IS THE NUMBER "+firstNumInt);
            //getting the second digit
            Character secondNum=holderString2.charAt(1);
            System.out.println("THIS IS THE SECOND DIGIT IN CHAR FORMAT"+secondNum);
            String secondNumString = secondNum.toString();
            int secondNumInt=Integer.parseInt(secondNumString);
            System.out.println("THIS IS THE NUMBER "+secondNumInt);
            //getting the third digit
            Character thirdNum=holderString2.charAt(2);
            System.out.println("THIS IS THE THIRD DIGIT IN CHAR FORMAT"+firstNum);
            String thirdNumString = thirdNum.toString();
            int thirdNumInt=Integer.parseInt(thirdNumString);
            System.out.println("THIS IS THE NUMBER "+thirdNumInt);
            //getting the fourth digit
            Character fourthNum=holderString2.charAt(3);
            System.out.println("THIS IS THE FOURTH DIGIT IN CHAR FORMAT"+firstNum);
            String fourthNumString = fourthNum.toString();
            int fourthNumInt=Integer.parseInt(fourthNumString);
            System.out.println("THIS IS THE NUMBER "+fourthNumInt);
            //concatenate last 2 digits
            String thirdAndFourthNumString= thirdNumString+fourthNumString;
            int thirdAndFourthNumint=Integer.parseInt(thirdAndFourthNumString);

            //manipulate digits to get total seconds
            int totSeconds= (firstNumInt*600)+(secondNumInt*60)+thirdAndFourthNumint;
            System.out.println("THIS IS THE TOTAL TIME IN CHRONOMETER :"+totSeconds+" SECONDS" );
            //extracting values from counterTxts to see how many production tasks were achieved
            //converting the charSequence values to ints so we can perform math with them
            //counter text 1
            CharSequence counterTxtHolder = counterTxt.getText();
            String counterTxtStringHolder = counterTxtHolder.toString();
            int counterTxtNumberHolder = Integer.parseInt(counterTxtStringHolder);
            System.out.println("PROD TASK 1: "+counterTxtNumberHolder);
            //counter text 2
            CharSequence counterTxtHolder1 = counterTxt1.getText();
            String counterTxtStringHolder1 = counterTxtHolder1.toString();
            int counterTxtNumberHolder1 = Integer.parseInt(counterTxtStringHolder1);
            //counter text 3
            CharSequence counterTxtHolder2 = counterTxt2.getText();
            String counterTxtStringHolder2 = counterTxtHolder2.toString();
            int counterTxtNumberHolder2 = Integer.parseInt(counterTxtStringHolder2);
            //using the int values for the prod task counts and multiplying them by the
            //goal time for each production task.
            //get the seconds value for each goal time
            Double goalTimeSeconds = mGoalTime1*60;
            Double goalTime1Seconds=mGoalTime2*60;
            Double goalTime2Seconds=mGoalTime3*60;
            //multiply the seconds and the prod tasks
            Double timeEarned= goalTimeSeconds*counterTxtNumberHolder;
            Double timeEarned1= goalTime1Seconds*counterTxtNumberHolder1;
            Double timeEarned2= goalTime2Seconds*counterTxtNumberHolder2;
            //add up all of the time earned
            Double totTimeEarned= timeEarned+timeEarned1+timeEarned2;
            System.out.println("TOTAL TIME EARNED IS : "+totTimeEarned);
            //calculate performance
            String performanceCalc =percentage.format((totTimeEarned/totSeconds));
            //assign the performanceCalc textview with the calculation value
            performanceCalculation.setText(performanceCalc);

            CharSequence sendCalculation = performanceCalculation.getText();
            //this information will be sent to whoever implements this interface
            listener.onInputASent(sendCalculation, totTimeEarned.toString(), String.valueOf(totSeconds));



        }
    };

    //click listener from chronometer
    private View.OnClickListener chronClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.startBtn:
                    startChron();
                    break;
                case R.id.pauseBtn:
                    pauseChron();
                    break;
                case R.id.resetBtnChron:
                    resetChron();
                    break;

            }
        }
    };

    //click listener for button clicks
    //depending on what button was clicked, it will call the corresponding
    //method
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //v.getId will return the id of the clicked button
            switch (v.getId()) {
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
               // case R.id.resetBtn:
               //     initCounter();
                //    break;
               // case R.id.resetBtn1:
               //     initCounter1();
               //     break;
              //  case R.id.resetBtn2:
               //     initCounter2();
               //     break;
            }
        }
    };

    //3 methods to update chronometer
    private void startChron(){
        if (!running) {
            //tell chronometer from which time we want to count onwards
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    private void pauseChron(){
        if (running) {
            //.stop() only stops updating the text, doesn't stop the
            //chronometer
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    private void resetChron(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        System.out.println("This method was called");
        }

    //3 methods for FIRST set of counter buttons
    private void initCounter() {
        counter = 0;
        counterTxt.setText(counter + "");
    }

    private void plusCounter() {
        counter++;
        counterTxt.setText(counter + "");
        System.out.println("this method was called too");
    }

    private void minusCounter() {
        counter--;
        counterTxt.setText(counter + "");
    }

    //3 methods for SECOND set of counter buttons
    private void initCounter1() {
        counter1 = 0;
        counterTxt1.setText(counter1 + "");
    }

    private void plusCounter1() {
        counter1++;
        counterTxt1.setText(counter1 + "");
    }

    private void minusCounter1() {
        counter1--;
        counterTxt1.setText(counter1 + "");
    }

    //3 methods for THIRD set of counter buttons
    private void initCounter2() {
        counter2 = 0;
        counterTxt2.setText(counter2 + "");
    }

    private void plusCounter2() {
        counter2++;
        counterTxt2.setText(counter2 + "");
    }

    private void minusCounter2() {
        counter2--;
        counterTxt2.setText(counter2 + "");
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
    public void setProdTasks(TextView prodTaskOne, TextView prodTaskTwo, TextView prodTaskThree, TextView operation) {
        prodTaskOne.setText(activity2.op.productionTaskOne);
        prodTaskTwo.setText(activity2.op.productionTaskTwo);
        prodTaskThree.setText(activity2.op.productionTaskThree);
        operation.setText(activity2.op.operationName);


    }

    //This method will use if else logic to determine which operation was selected
    //and based on the identified selection it will populate the production task member variables

    public void assignProdTasks(String fragOneOperationSelected) {

        System.out.println(fragOneOperationSelected + " mOperationSelected");
        System.out.println(sampleDataList.get(1).getOperation() + " sampleDataList");

        if (fragOneOperationSelected.equals(sampleDataList.get(1).getOperation())) {
            //if the first operation was selected, then populate
            //the production tasks for that operation
            activity2.op.productionTaskOne = sampleDataList.get(1).getProd1();
            activity2.op.productionTaskTwo = sampleDataList.get(1).getProd2();
            activity2.op.productionTaskThree = sampleDataList.get(1).getProd3();


        } else if (fragOneOperationSelected.equals(sampleDataList.get(2).getOperation())) {
            activity2.op.productionTaskOne = sampleDataList.get(2).getProd1();
            activity2.op.productionTaskTwo = sampleDataList.get(2).getProd2();
            activity2.op.productionTaskThree = sampleDataList.get(2).getProd3();
        } else if (fragOneOperationSelected.equals(sampleDataList.get(3).getOperation())) {
            activity2.op.productionTaskOne = sampleDataList.get(3).getProd1();
            activity2.op.productionTaskTwo = sampleDataList.get(3).getProd2();
            activity2.op.productionTaskThree = sampleDataList.get(3).getProd3();
        }else if (fragOneOperationSelected.equals(sampleDataList.get(4).getOperation())){
            activity2.op.productionTaskOne = sampleDataList.get(4).getProd1();
            activity2.op.productionTaskTwo = sampleDataList.get(4).getProd2();
            activity2.op.productionTaskThree = sampleDataList.get(4).getProd3();
        }
    }

    //saving the values for when the fragment is paused
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter1",counter);
        outState.putInt("counter2",counter1);
        outState.putInt("counter3",counter2);
        outState.putLong("chron",SystemClock.elapsedRealtime()-chronometer.getBase());
        outState.putLong("pauseOffset",pauseOffset);
    }



    @Override
    public void onPause() {
        super.onPause();
        if (!running) {
            //tell chronometer from which time we want to count onwards
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;

            System.out.println("The onpause method was called");

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!running) {
            //tell chronometer from which time we want to count onwards
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
            System.out.println("The onDestroy method was called");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("The onDestroyView method was called");

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("The onresume method was called");


    }

    public void setGoalTimes(String fragOneOperationSelected){
        if (fragOneOperationSelected.equals(sampleDataList.get(1).getOperation())) {

            //assigning values to the member variables so we can use them
            //in the calculateBtn onclick method
           mGoalTime1=activity2.op.goalTimes1[0];
           mGoalTime2=activity2.op.goalTimes1[1];
           mGoalTime3=activity2.op.goalTimes1[2];

           goalTime1.setText(""+mGoalTime1);
           goalTime2.setText(""+mGoalTime2);
           goalTime3.setText(""+mGoalTime3);

        } else if (fragOneOperationSelected.equals(sampleDataList.get(2).getOperation())) {
            mGoalTime1=activity2.op.goalTimes1[0];
            mGoalTime2=activity2.op.goalTimes1[1];
            mGoalTime3=activity2.op.goalTimes1[2];

            goalTime1.setText(""+mGoalTime1);
            goalTime2.setText(""+mGoalTime2);
            goalTime3.setText(""+mGoalTime3);

        } else if (fragOneOperationSelected.equals(sampleDataList.get(3).getOperation())) {
            mGoalTime1=activity2.op.goalTimes1[0];
            mGoalTime2=activity2.op.goalTimes1[1];
            mGoalTime3=activity2.op.goalTimes1[2];

            goalTime1.setText(""+mGoalTime1);
            goalTime2.setText(""+mGoalTime2);
            goalTime3.setText(""+mGoalTime3);

        }else if (fragOneOperationSelected.equals(sampleDataList.get(4).getOperation())){
            mGoalTime1=activity2.op.goalTimes1[0];
            mGoalTime2=activity2.op.goalTimes1[1];
            mGoalTime3=activity2.op.goalTimes1[2];

            goalTime1.setText(""+mGoalTime1);
            goalTime2.setText(""+mGoalTime2);
            goalTime3.setText(""+mGoalTime3);
        }
    }


}