package com.example.dynamicmentorapp;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[]operations=new String[0];
    String[]productionTasks;
    String[]goalTimes;
    String []prefMethods=new String[0];
    Button submitBtn;
    Spinner spinner;
    EditText associateName;
    EditText mentorName;
    EditText idNumber;
    String nameMentor;
    String idAssociate;
    ConstraintLayout myLayout;
    String operationSelected;
    Button load;
    Button load2;
    String inputHolder;
    File fileHolder;
    File fileHolder2;
    String name;
    TextView dateText;
    DatePickerDialog.OnDateSetListener dateSetListener;
    String yearString;
    String monthString;
    String dayString;
    String dateString;
    String dateDisplayString;
    int year;
    int month;
    int day;
    int j=0;

    int i=0;
    private static final int PERMISSION_REQUEST_STORAGE=1000;
    private static final int READ_REQUEST_CODE=42;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI element references
        submitBtn=findViewById(R.id.startBtn);
        spinner=findViewById(R.id.spinner);
        myLayout=findViewById(R.id.myLayout);
        associateName= findViewById(R.id.associateName);
        dateText=findViewById(R.id.dateText);
        mentorName= findViewById(R.id.mentorName);
        idNumber= findViewById(R.id.idNumber);


        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(j>=1){
                    j=0;
                }

                Calendar cal = Calendar.getInstance();
                 year =cal.get(Calendar.YEAR);
                 month =cal.get(Calendar.MONTH);
                  day =cal.get(Calendar.DAY_OF_MONTH);

                  dateString=month+1+"."+day+"."+year;

                  System.out.println("Date before listener: "+dateString);


                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Black,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        yearString = Integer.toString(year);
                        monthString=Integer.toString(month);
                        dayString=Integer.toString(dayOfMonth);
                        dateDisplayString=monthString+"/"+dayString+"/"+yearString;
                        dateString=monthString+"."+ dayString+"."+yearString;

                        System.out.println("the date is: "+dateString);

                    }

                };




            }

        });


        //request permission (new)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }


        //create listener for spinner
        spinner.setOnItemSelectedListener(this);
        //create clickListener for button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefferedMethodReader("Download/pref.txt");
                name = associateName.getText().toString();
                nameMentor = mentorName.getText().toString();
                idAssociate=idNumber.getText().toString();
                activityChanger();

                Toast.makeText(MainActivity.this, ""+name, Toast.LENGTH_SHORT).show();
            }
        });
        //read data from csv
        //readData();

        readTextGoalTimes("Download/prod.txt");

    }






    private void readData() {

        //get reference to raw csvfile
        InputStream is = getResources().openRawResource(R.raw.csv);
        //need a buffered reader to read the file line by line
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))

        );


        try{

            String line ="";

        //we have to surround with a try catch bc the if statement
        //could cause an I/O exception

            //while loop to iterate through each line of the csv file
            while((line=reader.readLine())!=null){
                //creating an array of strings called tokens every time the
                //while loop iterates. Each array will hold an individual line
                //from the csv file and each of its elements
                String [] tokens=line.split(",");
                int numOfProdTasks= tokens.length-1;

                System.out.println("The number of production tasks is: "+numOfProdTasks);

                //add each operation to the operations array
                String newOperation=tokens[0];

                int currentSize=operations.length;
                int newSize=currentSize+1;
                String[]tempArray=new String[newSize];
                for(int i=0;i<currentSize;i++){
                    tempArray[i]=operations[i];
                }
                tempArray[newSize-1]=newOperation;
                operations=tempArray;


            }

        } catch (IOException e){
            Log.wtf("MyActivity","Error reading data file on line ",e);
            e.printStackTrace();
        }

        //populate the spinner with the contents of the
        //operations array
        ConstraintLayout.LayoutParams myParams= new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, operations );
        spinner.setAdapter(spinnerArrayAdapter);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        productionTasks=new String[0];
        operations= new String[0];
       goalTimes =new String[0];
       prefMethods= new String[0];

        //extract the value contained in the spinner
        operationSelected = parent.getItemAtPosition(position).toString();

        //read data again
        //get reference to raw csvfile
        InputStream is = getResources().openRawResource(R.raw.csv);
        InputStream is2 = getResources().openRawResource(R.raw.pref_methods);
        //need a buffered reader to read the file line by line (first csv)
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        //buffered reader for 2nd cvs
        BufferedReader reader2 = new BufferedReader(
                new InputStreamReader(is2, Charset.forName("UTF-8"))
        );


        try {
            BufferedReader br = new BufferedReader(new FileReader(fileHolder));
            String line;
            while ((line = br.readLine()) != null) {

                //creating an array of strings called tokens every time the
                //while loop iterates. Each array will hold an individual line
                //from the csv file and each of its elements
                String[] tokens = line.split(",");
                //creating array for production tasks
                if (operationSelected.equals(tokens[0])) {
                    int tokensLength = tokens.length;
                    for (int i = 1; i < tokensLength; i += 2) {

                        int currentSize = productionTasks.length;
                        int newSize = currentSize + 1;
                        String[] tempArray = new String[newSize];
                        for (int j = 0; j < currentSize; j++) {
                            tempArray[j] = productionTasks[j];
                        }
                        tempArray[newSize - 1] = tokens[i];
                        productionTasks = tempArray;

                    }

                    System.out.println("This is the length of the prod task array:" + productionTasks.length);
                }

                //creating array for goal times
                if (operationSelected.equals(tokens[0])) {
                    int tokensLength = tokens.length;
                    for (int i = 2; i < tokensLength; i += 2) {

                        int currentSize = goalTimes.length;
                        int newSize = currentSize + 1;
                        String[] tempArray2 = new String[newSize];
                        for (int j = 0; j < currentSize; j++) {
                            tempArray2[j] = goalTimes[j];
                        }
                        tempArray2[newSize - 1] = tokens[i];
                        goalTimes = tempArray2;
                        System.out.println("This is the value of the first goal time in the line" + goalTimes[0]);

                    }
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void activityChanger(){
        //set up the intent to open the second activity
        Intent changeToSecondActivity = new Intent(this,SecondActivity.class);

        final int result = 1;

        changeToSecondActivity.putExtra("operation", operationSelected );
        changeToSecondActivity.putExtra("production tasks",productionTasks);
        changeToSecondActivity.putExtra("goaltimes",goalTimes);
        changeToSecondActivity.putExtra("pref methods",prefMethods);
        changeToSecondActivity.putExtra("associate",name);
        changeToSecondActivity.putExtra("date",dateString);
        changeToSecondActivity.putExtra("mentor name",nameMentor);
        changeToSecondActivity.putExtra("id",idAssociate);
        startActivityForResult(changeToSecondActivity,result);
    }
    //read contents of file
    private String readTextGoalTimes(String input){

        inputHolder= input;
        File file = new File(Environment.getExternalStorageDirectory(),input);
        fileHolder= file;
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file ));
            String line;
            while((line = br.readLine()) !=null){
                //creating an array of strings called tokens every time the
                //while loop iterates. Each array will hold an individual line
                //from the csv file and each of its elements
                String [] tokens=line.split(",");
                int numOfProdTasks= tokens.length-1;

                System.out.println("The number of production tasks is: "+numOfProdTasks);

                //add each operation to the operations array
                String newOperation=tokens[0];

                int currentSize=operations.length;
                int newSize=currentSize+1;
                String[]tempArray=new String[newSize];
                for(int i=0;i<currentSize;i++){
                    tempArray[i]=operations[i];
                }
                tempArray[newSize-1]=newOperation;
                operations=tempArray;



            }

            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        //populate the spinner with the contents of the
        //operations array
        ConstraintLayout.LayoutParams myParams= new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, operations );
        spinner.setAdapter(spinnerArrayAdapter);

        return text.toString();

    }

    private String  readTextPreferredMethods(String input) {

        inputHolder = input;
        File file = new File(Environment.getExternalStorageDirectory(), input);
        fileHolder2 = file;
        String whatever="whatever";
        return  whatever;
    }


    /*select file from storage
    private void performFileSearchGoalTimes(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }
    private void performFileSearchPreferredMethods(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (i == 1) {
                if (data != null) {
                    Uri uri1 = data.getData();
                    String path1 = uri1.getPath();
                    path1 = path1.substring(path1.indexOf(":") + 1);
                    Toast.makeText(this, "" + path1, Toast.LENGTH_SHORT).show();
                    readTextGoalTimes(path1);
                }
            } else if (i == 2) {
                if (data != null) {
                    Uri uri2 = data.getData();
                    String path2 = uri2.getPath();
                    path2 = path2.substring(path2.indexOf(":") + 1);
                    Toast.makeText(this, "" + path2, Toast.LENGTH_SHORT).show();
                    readTextPreferredMethods(path2);
                }
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_REQUEST_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void prefferedMethodReader(String input){
        //while loop to iterate through each line of the preferred methods csv file

        try{
        String line;
        File file = new File(Environment.getExternalStorageDirectory(),input);
        BufferedReader br2 = new BufferedReader(new FileReader(file));


        while((line = br2.readLine()) !=null){
            //creating an array of strings called tokens every time the
            //while loop iterates. Each array will hold an individual line
            //from the csv file and each of its elements
            String [] tokens=line.split(",");
            //creating array for production tasks
            if(operationSelected.equals(tokens[0])){
                int tokensLength = tokens.length;
                for(int i=1;i<tokensLength;i++){

                    int currentSize2=prefMethods.length;
                    int newSize=currentSize2+1;
                    String[]tempArray2=new String[newSize];
                    for(int j=0;j<currentSize2;j++){
                        tempArray2[j]=prefMethods[j];
                    }
                    tempArray2[newSize-1]=tokens[i];
                    System.out.println("token: "+tokens[i]);
                    prefMethods=tempArray2;



                }
            }


        }

    } catch (IOException e){
        Log.wtf("MyActivity","Error reading data file on line "+e);
        e.printStackTrace();
    }

}


}



