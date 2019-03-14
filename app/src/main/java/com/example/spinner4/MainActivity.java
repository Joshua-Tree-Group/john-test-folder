package com.example.spinner4;

import android.content.Intent;
import android.os.Parcelable;
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
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

     String mOperationSelected;
     operation op;
     String[] receivingMethods=new String[9];
     String[] equipmentMethods=new String[9];
     String[] shippingMethods=new String[9];
     String[] osMethods=new String[9];

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

        changeToMentorForm.putExtra("Operation object", op);


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

            assignPreferredMethods(mOperationSelected);

        }else if (mOperationSelected.equals(sampleDataList.get(2).getOperation())){
            mOperationSelected=sampleDataList.get(2).getOperation();
            assignSelectedOperation(mOperationSelected);

            assignPreferredMethods(mOperationSelected);

        }else if(mOperationSelected.equals(sampleDataList.get(3).getOperation())){
            mOperationSelected=sampleDataList.get(3).getOperation();
            assignSelectedOperation(mOperationSelected);

            assignPreferredMethods(mOperationSelected);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //this method receives selection from spinner and assigns the
    //operationName attribute to the op object.
    public void assignSelectedOperation(String mOperation){

        mOperationSelected=mOperation;
        op = new operation();
        op.setOperationName(mOperationSelected);
    }

    public void assignPreferredMethods(String value){


        receivingMethods[0] = "Make sure dock is clear before receiving trailer";
        receivingMethods[1] = "Wear safety googles when operating power equipment";
        receivingMethods[2] = "Double stack as many pallets as possible";
        receivingMethods[3] = "Use knife to cut stack";
        receivingMethods[4] = "Place only one dot sticker per pallet";
        receivingMethods[5] = "Separate SKUS when placing in lane";
        receivingMethods[6] = "Have 2 pallet stacks outide the dock door";
        receivingMethods[7] = "Breakdown load as you unload it";
        receivingMethods[8] = "";

        equipmentMethods[0]="In putaways, scan the pallet label before placing forks in pallet.";
        equipmentMethods[1]="Exit pallet with forks pointing towards the next putaway pallet";
        equipmentMethods[2]="Do not corner to fast";
        equipmentMethods[3]="Clean as you go";
        equipmentMethods[4]="Check aisles for remaining putaways after completing replenishments";
        equipmentMethods[5]="Clean work area 15 min prior to end of day";
        equipmentMethods[6]="Drive back to startup area so next shift can find machines easily";
        equipmentMethods[7]="Sign out of system";
        equipmentMethods[8]="";

        shippingMethods[0]="Contact manager if you are constantly waiting for work";
        shippingMethods[1]="Do not make multiple attempts to load a case in the same spot";
        shippingMethods[2]="Place heavy items on the floor below waist";
        shippingMethods[3]="Do not have more than 2 people per trailer";
        shippingMethods[4]="Do not pause conveyor";
        shippingMethods[5]="Keep your area clean and organized as you load the trailer";
        shippingMethods[6]="Use load sheet to review high and tight";
        shippingMethods[7]="Stack cases all the way across, avoid column stacking";
        shippingMethods[8]="Use a step stool to stack freight on top level";

        osMethods[0]="Tie a trash bag to your pouch";
        osMethods[1]="Make accurate throws to the belt to eliminate double handling";
        osMethods[2]="Unwrap entire pallet";
        osMethods[3]="Always layer pick cases";
        osMethods[4]="Use 2-3 wraps when closing up open cases";
        osMethods[5]="Use time productively during line stops";
        osMethods[6]="Place label bricks inside of pouch";
        osMethods[7]="Label multiple cases at once and move as many as you can pick up";
        osMethods[8]="Avoid team picks at the same location";

        if (value.equals(sampleDataList.get(1).getOperation())){
            op.setPrefferedMethods(receivingMethods);
            System.out.println("it worked "+op.prefferedMethods[1]);
        }else if (value.equals(sampleDataList.get(2).getOperation())) {
            op.setPrefferedMethods(osMethods);

        }else if (value.equals(sampleDataList.get(3).getOperation())){
            op.setPrefferedMethods(equipmentMethods);
        }else if (value.equals(sampleDataList.get(4).getOperation())){
            op.setPrefferedMethods(shippingMethods);
        }else{
            System.out.println("it did not work");
        }

    }
}



