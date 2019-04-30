package com.example.dynamicmentorapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class PrefMethodsFragment extends Fragment {

    SecondActivity secondActivity;
    ArrayList<String>selections= new ArrayList<>();
    String[]prefMethods = new String[1];
    //preferred methods txtViews
    TextView tpm1;
    TextView tpm2;
    TextView tpm3;

    TextView pm1;
    TextView pm2;
    TextView pm3;

    TextView am1;
    TextView am2;
    TextView am3;

    //Pace txtViews
    TextView paceTxt1;

    //Summary button
    Button summary;

    //Edit texts
    EditText improvements1;
    EditText improvements2;
    EditText improvements3;

    EditText didWell1;
    EditText didWell2;
    EditText didWell3;

    EditText feedback1;
    EditText feedback2;
    EditText feedback3;

    EditText extraNotes;

    String mPace;
    String mUtilization;
    String mMethods;

    //checkboxes
    CheckBox topPref1;
    CheckBox topPre2;
    CheckBox topPref3;
    CheckBox pref1;
    CheckBox pref2;
    CheckBox pref3;
    CheckBox adv1;
    CheckBox adv2;
    CheckBox adv3;
    CheckBox pace1;
    CheckBox pace2;
    CheckBox pace3;
    CheckBox pace4;
    CheckBox pace5;
    CheckBox util1;
    CheckBox util2;
    CheckBox util3;
    CheckBox util4;
    CheckBox util5;
    CheckBox methods1;
    CheckBox methods2;
    CheckBox methods3;

    String notes;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    int radioId1;
    int radioId2;
    int radioId3;

    RadioButton radioButonOne;
    RadioButton radioButonTwo;
    RadioButton radioButonThree;
    RadioButton radioButonFour;
    RadioButton radioButonFive;
    RadioButton radioButonSix;
    RadioButton radioButonSeven;
    RadioButton radioButonEight;
    RadioButton radioButonNine;
    RadioButton radioButonTen;
    RadioButton radioButoneleven;
    RadioButton radioButonTwelve;
    RadioButton radioButonThirteen;
    RadioButton radioButonFourteen;
    RadioButton radioButonFifteen;



    //fragment listener
    private prefMethodsFragmentListener listener;



    public interface prefMethodsFragmentListener{
        void onInputSent(CharSequence input, CharSequence input2, CharSequence input3, CharSequence input4,
                         CharSequence input5, CharSequence input6, CharSequence input7, CharSequence input8, CharSequence input9,
                         ArrayList input10, String input11, String input12, String input13,String input14);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.activity_pref_methods, container, false);

        secondActivity = (SecondActivity) getActivity();
        prefMethods=secondActivity.prefMethods;

        tpm1=fragView.findViewById(R.id.tpm1);
        tpm2=fragView.findViewById(R.id.tpm2);
        tpm3=fragView.findViewById(R.id.tpm3);

        pm1=fragView.findViewById(R.id.pm1);
        pm2=fragView.findViewById(R.id.pm2);
        pm3=fragView.findViewById(R.id.pm3);

        am1=fragView.findViewById(R.id.am1);
        am2=fragView.findViewById(R.id.am2);
        am3=fragView.findViewById(R.id.am3);

        extraNotes=fragView.findViewById(R.id.notes);

        //set pace texts
        paceTxt1= fragView.findViewById(R.id.paceTxt1);

        //set util texts


        //set texts
        tpm1.setText(prefMethods[0]);
        tpm2.setText(prefMethods[1]);
        tpm3.setText(prefMethods[2]);

        pm1.setText(prefMethods[3]);
        pm2.setText(prefMethods[4]);
        pm3.setText(prefMethods[5]);

        am1.setText(prefMethods[6]);
        am2.setText(prefMethods[7]);
        am3.setText(prefMethods[8]);

        //edit texts
        improvements1=fragView.findViewById(R.id.improvements1);
        improvements2=fragView.findViewById(R.id.improvements2);
        improvements3=fragView.findViewById(R.id.improvements3);

        didWell1=fragView.findViewById(R.id.didWell1);
        didWell2=fragView.findViewById(R.id.didWell2);
        didWell3=fragView.findViewById(R.id.didWell3);

        feedback1=fragView.findViewById(R.id.feedback1);
        feedback2=fragView.findViewById(R.id.feedback2);
        feedback3=fragView.findViewById(R.id.feedback3);

        //checkboxes
        topPref1=fragView.findViewById(R.id.topPref1);
        topPre2=fragView.findViewById(R.id.topPref2);
        topPref3=fragView.findViewById(R.id.topPref3);

        pref1=fragView.findViewById(R.id.pref1);
        pref2=fragView.findViewById(R.id.pref2);
        pref3=fragView.findViewById(R.id.pref3);

        adv1=fragView.findViewById(R.id.adv1);
        adv2=fragView.findViewById(R.id.adv2);
        adv3=fragView.findViewById(R.id.adv3);



        //RadioGroups
        radioGroup1=fragView.findViewById(R.id.radioGroup);
        radioGroup2=fragView.findViewById(R.id.radioGroup2);
        radioGroup3=fragView.findViewById(R.id.radioGroup3);

        //RadioButtons (I have to set the onclicks manually, onclick through xml takes me to second activity, can't be done
        radioButonOne=fragView.findViewById(R.id.pace_one);
        radioButonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPace= radioButonOne.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonOne.getText(), Toast.LENGTH_SHORT).show();

            }
        });
        radioButonTwo=fragView.findViewById(R.id.pace_two);
        radioButonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPace= radioButonTwo.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonTwo.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonThree=fragView.findViewById(R.id.pace_three);
        radioButonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPace= radioButonThree.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonThree.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonFour=fragView.findViewById(R.id.pace_four);
        radioButonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPace= radioButonFour.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonFour.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonFive=fragView.findViewById(R.id.pace_five);
        radioButonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPace= radioButonFive.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonFive.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonSix=fragView.findViewById(R.id.util_one);
        radioButonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtilization=radioButonSix.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonSix.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonSeven=fragView.findViewById(R.id.util_two);
        radioButonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtilization= radioButonSeven.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonSeven.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonEight=fragView.findViewById(R.id.util_three);
        radioButonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtilization= radioButonEight.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonEight.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonNine=fragView.findViewById(R.id.util_four);
        radioButonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtilization= radioButonNine.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonNine.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonTen=fragView.findViewById(R.id.util_five);
        radioButonTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtilization= radioButonTen.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButonTen.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButoneleven=fragView.findViewById(R.id.methods_one);
        radioButoneleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMethods= radioButoneleven.getText().toString();
                Toast.makeText(secondActivity, "Selected: "+radioButoneleven.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonTwelve=fragView.findViewById(R.id.methods_two);
        radioButonTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMethods= radioButonTwelve.getText().toString();

                Toast.makeText(secondActivity, "Selected: "+radioButonTwelve.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonThirteen=fragView.findViewById(R.id.methods_three);
        radioButonThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMethods= radioButonThirteen.getText().toString();

                Toast.makeText(secondActivity, "Selected: "+radioButonThirteen.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonFourteen=fragView.findViewById(R.id.methods_four);
        radioButonFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMethods= radioButonFourteen.getText().toString();

                Toast.makeText(secondActivity, "Selected: "+radioButonThirteen.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        radioButonFifteen=fragView.findViewById(R.id.methods_five);
        radioButonFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMethods= radioButonFifteen.getText().toString();

                Toast.makeText(secondActivity, "Selected: "+radioButonFifteen.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        //clickListener for radio buttons


        //Button and clicklistener
        summary=fragView.findViewById(R.id.summary);
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input1=improvements1.getText();
                CharSequence input2=improvements2.getText();
                CharSequence input3=improvements3.getText();
                CharSequence input4=didWell1.getText();
                CharSequence input5=didWell2.getText();
                CharSequence input6=didWell3.getText();
                CharSequence input7=feedback1.getText();
                CharSequence input8=feedback2.getText();
                CharSequence input9=feedback3.getText();
                notes = extraNotes.getText().toString();
                listener.onInputSent(input1, input2,input3,input4,input5,input6,input7,input8
                ,input9,selections,mPace,mUtilization,mMethods,notes);

                System.out.println("feedback text: "+feedback1.getText().toString()+","+feedback2.getText().toString()+","+feedback3.getText().toString());

            }
        });

        //set click listener for checkboxes
        topPref1.setOnClickListener(selectedItem);
        topPre2.setOnClickListener(selectedItem);
        topPref3.setOnClickListener(selectedItem);

        pref1.setOnClickListener(selectedItem);
        pref2.setOnClickListener(selectedItem);
        pref3.setOnClickListener(selectedItem);

        adv1.setOnClickListener(selectedItem);
        adv2.setOnClickListener(selectedItem);
        adv3.setOnClickListener(selectedItem);








        return fragView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //context is our activity
        //we are checking if the activity implements our interface
        if (context instanceof MentorFormFragment.mentorFormFragmentListener){
            //if the activity does implement the interface then
            //we want to take our listener variable and assign it to
            //the context, which is our activity.
            listener=(prefMethodsFragmentListener) context;

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

    public View.OnClickListener selectedItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //find out which checkboxes were selected by the user
            //create boolean that represents if boxes are checked or not
            boolean checked =((CheckBox) v).isChecked();
            switch (v.getId()){
                case R.id.topPref1:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+tpm1.getText());
                    }else{
                        selections.remove(""+tpm1.getText());
                    }
                    break;
                case R.id.topPref2:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+tpm2.getText());
                    }else{
                        selections.remove(""+tpm2.getText());
                    }
                    break;
                case R.id.topPref3:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+tpm3.getText());
                    }else{
                        selections.remove(""+tpm3.getText());
                    }
                    break;
                case R.id.pref1:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+pm1.getText());
                    }else{
                        selections.remove(""+pm1.getText());
                    }
                    break;
                case R.id.pref2:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+pm2.getText());
                    }else{
                        selections.remove(""+pm2.getText());
                    }
                    break;
                case R.id.pref3:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+pm3.getText());
                    }else{
                        selections.remove(""+pm3.getText());
                    }
                    break;
                case R.id.adv1:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+am1.getText());
                    }else{
                        selections.remove(""+am1.getText());
                    }
                    break;
                case R.id.adv2:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+am2.getText());
                    }else{
                        selections.remove(""+am2.getText());
                    }
                    break;
                case R.id.adv3:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        selections.add(""+am3.getText());
                    }else{
                        selections.remove(""+am3.getText());
                    }
                    break;


            }



        }
    };

    public void checkedButton1(View v){
         radioId1=radioGroup1.getCheckedRadioButtonId();

        radioButton1=getView().findViewById(radioId1);

        Toast.makeText(secondActivity, "Selected: "+radioButton1.getText(), Toast.LENGTH_SHORT).show();

    }
    public void checkedButton2(View v){
        radioId2=radioGroup2.getCheckedRadioButtonId();

        radioButton2=getView().findViewById(radioId2);

        Toast.makeText(secondActivity, "Selected: "+radioButton2.getText(), Toast.LENGTH_SHORT).show();


    }
    public void checkedButton3(View v){
        radioId3=radioGroup3.getCheckedRadioButtonId();

        radioButton2=getView().findViewById(radioId3);

        Toast.makeText(secondActivity, "Selected: "+radioButton3.getText(), Toast.LENGTH_SHORT).show();


    }
}
