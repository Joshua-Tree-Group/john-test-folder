package com.example.dynamicmentorapp;

import android.content.Context;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

public class MentorFormFragment extends Fragment {
    DecimalFormat percentage = new DecimalFormat("###.#%");
    DecimalFormat minutes = new DecimalFormat("####.##");

    Long pauseOffset= Long.valueOf(0);
    Boolean running=false;
    Chronometer chronometer;
    LinearLayout myLayout;
    ScrollView scrollView;
    SecondActivity secondActivity;
    String operation;
    String[] prodTasks = new String[1];
    String[] goalTimes = new String[1];
    TextView operationTxt;
    Button startBtn;
    Button pauseBtn;
    Button resetBtn;
    Button calculateBtn;
    Double totalSecondsEarned;
    Double totSecondsChronometer;
    Double performancePercentge;
    String performancePercentageString;
    TextView performanceTxt;
    private mentorFormFragmentListener listener;
    String totTimeEarnedMinutesCharSequence;
    String totTimeChronometerCharSequence;



    int[] counts;
    String[] counter = new String[1];
    String[] counterTxtHolder;
    int i;

    //Interface to communicate with activity
    public interface mentorFormFragmentListener{
        void onInputSent(CharSequence input, CharSequence input2, CharSequence input3);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.activity_mentor_form, container, false);

        //create a variable that holds the reference to the  second
        //activity
        secondActivity = (SecondActivity) getActivity();
        operation = secondActivity.operation;
        prodTasks = secondActivity.prodTasks;
        goalTimes=secondActivity.goalTimes;


        counts= new int[prodTasks.length];

        //create references for UI elements
        operationTxt = fragView.findViewById(R.id.operationText);
        startBtn= fragView.findViewById(R.id.startBtn);
        pauseBtn= fragView.findViewById(R.id.pauseBtn);
        resetBtn= fragView.findViewById(R.id.restartBtn);
        chronometer=fragView.findViewById(R.id.chronometer);
        calculateBtn=fragView.findViewById(R.id.calculateBtn);
        performanceTxt=fragView.findViewById(R.id.performanceTxt);


         //set clickListeners
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChron();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChron();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChron();
            }
        });

        //set text
        operationTxt.setText(operation);


        //create production task Layouts
        myLayout = fragView.findViewById(R.id.linearLayout);
        scrollView= fragView.findViewById(R.id.scrollview);

        for (i=0; i < prodTasks.length; i++) {
            //set Parameters for the new layout
            LinearLayout.LayoutParams myParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //set parameters for the goal time textView
            LinearLayout.LayoutParams myParams2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            //set parameters for the minus button
            LinearLayout.LayoutParams myParams3 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            //set parameters for the counter text
            //set parameters for the minus button
            LinearLayout.LayoutParams myParams4 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            //set margin parameters so there is space between elements
            myParams.setMargins(0, 0, 0, 0);
            myParams2.setMargins(50, 0, 0, 0);
            myParams3.setMargins(40, 0, 0, 0);
            myParams4.setMargins(10, 0, 10, 0);
            //define new layout
            LinearLayout newLayout = new LinearLayout(getContext());
            newLayout.setLayoutParams(myParams);
            //define prod task textView to be placed inside layout
            TextView newText = new TextView(getContext());
            newText.setText("" + prodTasks[i]);
            newText.setTextSize(20);
            newText.setWidth(300);
            //define goal time text view
            TextView newText2 = new TextView(getContext());
            newText2.setText(""+goalTimes[i]);
            newText2.setTextSize(20);
            newText2.setLayoutParams(myParams2);
            newText2.setWidth(100);
            //define minus button Array
            Button[] buttonsMinus = new Button[prodTasks.length+1];
            buttonsMinus[i] = new Button(getContext());
            buttonsMinus[i].setText("-");
            buttonsMinus[i].setTextSize(15);
            buttonsMinus[i].setWidth(5);
            buttonsMinus[i].setHeight(2);
            buttonsMinus[i].setLayoutParams(myParams3);
            buttonsMinus[i].setId(i);

            //define counter text and holder
            counterTxtHolder = new String[prodTasks.length];
           final TextView[] counterTxt= new TextView[prodTasks.length];
            counterTxt[i] = new TextView(getContext());
            counterTxt[i].setText("0");
            counterTxt[i].setTextSize(20);
            counterTxt[i].setLayoutParams(myParams4);
            counterTxt[i].setId(i);
            System.out.println("This is the text: "+counterTxt[i].getText());

            buttonsMinus[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id= v.getId();

                    for(int j =0; j<prodTasks.length;j++) {
                        if (id == j) {
                            //the index of the clicked prod task is holder
                            //get the current value of the counter in the prod task
                            String currentValue = counterTxt[j].getText().toString();
                            //make it an integer
                            int currentIntValue = Integer.parseInt(currentValue);
                            //add one to int
                            currentIntValue--;
                            //turn it back into a string
                            String newStringValue = Integer.toString(currentIntValue);
                            //update the text
                            counterTxt[j].setText(newStringValue);
                            //set equal to the counts

                            counts[j]=currentIntValue;

                        }
                    }
                }
            });

            //define plus button
            Button[] buttonsPlus=new Button[prodTasks.length];
            buttonsPlus[i] = new Button(getContext());
            buttonsPlus[i].setText("+");
            buttonsPlus[i].setTextSize(15);
            buttonsPlus[i].setWidth(5);
            buttonsPlus[i].setHeight(5);
            buttonsPlus[i].setId(i);

            buttonsPlus[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id= v.getId();

                    for(int j =0; j<prodTasks.length;j++) {
                        if (id == j) {
                            //the index of the clicked prod task is holder
                            //get the current value of the counter in the prod task
                            String currentValue = counterTxt[j].getText().toString();
                            //make it an integer
                            int currentIntValue = Integer.parseInt(currentValue);
                            //add one to int
                            currentIntValue++;

                            //turn it back into a string
                            String newStringValue = Integer.toString(currentIntValue);
                            //update the text
                            counterTxt[j].setText(newStringValue);

                            counts[j]=Integer.parseInt(newStringValue);
                            //print
                            System.out.println("This is the value: "+newStringValue);

                        }
                    }

                }
            });



            //add elements to the layout
            newLayout.addView(newText);
            newLayout.addView(newText2);
            newLayout.addView(buttonsMinus[i]);
            newLayout.addView(counterTxt[i]);
            newLayout.addView(buttonsPlus[i]);
            newLayout.setOrientation(LinearLayout.HORIZONTAL);
            //add the layout to the main layout
            myLayout.addView(newLayout, myParams);

            System.out.println(i);

            System.out.println("This is the counter text array INSIDE : "+counterTxt[i].getText().toString());

            calculateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    totalSecondsEarned=0.0;

                    for (int j = 0; j < prodTasks.length; j++) {

                        //transform goalTime string into number
                        Double goalTimeDouble=Double.valueOf(goalTimes[j]);
                        //convert int value of count to Double
                        Double countDouble=Double.valueOf(counts[j]);
                        //multiply those two numbers
                        Double totalProdTaskEarnedTime=(goalTimeDouble*countDouble)*60;
                        //add them to the total time
                        totalSecondsEarned=totalSecondsEarned+totalProdTaskEarnedTime;


                        //now extract the time from the chronometer
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
                        //change total chronometer seconds to double
                        totSecondsChronometer=Double.valueOf(totSeconds);
                        Double totMinutesChronometer=totSecondsChronometer/60;
                        totTimeChronometerCharSequence=minutes.format(totMinutesChronometer);
                        //divide the total earned time by the total time in the chronometer
                        //and multiply by 100 to get the performance percentage
                        performancePercentge=(totalSecondsEarned/totSecondsChronometer);
                        //change to string and format
                       Double totTimeEarnedMinutes= totalSecondsEarned/60;
                       totTimeEarnedMinutesCharSequence = minutes.format(totTimeEarnedMinutes);

                        performancePercentageString = percentage.format(performancePercentge);
                    }

                    System.out.println("This is the performance "+performancePercentageString);

                    //set the text for the performance
                    performanceTxt.setText(performancePercentageString);

                    //call the listener to send information to the mainactivity
                    //this information will be sent wherever the interface ins implemented
                    listener.onInputSent(performancePercentageString,totTimeEarnedMinutesCharSequence, totTimeChronometerCharSequence);


                }
            });
        }

        System.out.println("These are the total seconds earned so far: "+totalSecondsEarned);


        return fragView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //context is our activity
        //we are checking if the activity implements our interface
        if (context instanceof  mentorFormFragmentListener){
            //if the activity does implement the interface then
            //we want to take our listener variable and assign it to
            //the context, which is our activity.
            listener=(mentorFormFragmentListener) context;

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
        pauseOffset = Long.valueOf(0);
        System.out.println("This method was called");
    }






}
