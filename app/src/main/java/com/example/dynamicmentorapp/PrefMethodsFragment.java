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
import android.widget.TextView;

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
    TextView paceTxt2;
    TextView paceTxt3;
    TextView paceTxt4;
    TextView paceTxt5;
    //utilization txtViews
    TextView utilTxt1;
    TextView utilTxt2;
    TextView utilTxt3;
    TextView utilTxt4;
    TextView utilTxt5;
    //methods txtViews
    TextView methodsTxt1;
    TextView methodsTxt2;
    TextView methodsTxt3;
    TextView methodsTxt4;
    TextView methodsTxt5;
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

    //fragment listener
    private prefMethodsFragmentListener listener;



    public interface prefMethodsFragmentListener{
        void onInputSent(CharSequence input, CharSequence input2, CharSequence input3, CharSequence input4,
                         CharSequence input5, CharSequence input6, CharSequence input7, CharSequence input8, CharSequence input9,
                         ArrayList input10, String input11, String input12, String input13);
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

        //set pace texts
        paceTxt1= fragView.findViewById(R.id.paceTxt1);
        paceTxt2= fragView.findViewById(R.id.paceTxt2);
        paceTxt3= fragView.findViewById(R.id.paceTxt3);
        paceTxt4= fragView.findViewById(R.id.paceTxt4);
        paceTxt5= fragView.findViewById(R.id.paceTxt5);
        //set util texts
        utilTxt1=fragView.findViewById(R.id.utilTxt1);
        utilTxt2=fragView.findViewById(R.id.utilTxt2);
        utilTxt3=fragView.findViewById(R.id.utilTxt3);
        utilTxt4=fragView.findViewById(R.id.utilTxt4);
        utilTxt5=fragView.findViewById(R.id.utilTxt5);
        //set methods texts
        methodsTxt1=fragView.findViewById(R.id.methodsTxt1);
        methodsTxt2=fragView.findViewById(R.id.methodsTxt2);
        methodsTxt3=fragView.findViewById(R.id.methodsTxt3);
        methodsTxt4=fragView.findViewById(R.id.methodsTxt4);
        methodsTxt5=fragView.findViewById(R.id.methodsTxt5);

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

        pace1=fragView.findViewById(R.id.pace1);
        pace2=fragView.findViewById(R.id.pace2);
        pace3=fragView.findViewById(R.id.pace3);
        pace4=fragView.findViewById(R.id.pace4);
        pace5=fragView.findViewById(R.id.pace5);

        util1=fragView.findViewById(R.id.util1);
        util2=fragView.findViewById(R.id.util2);
        util3=fragView.findViewById(R.id.util3);
        util4=fragView.findViewById(R.id.util4);
        util5=fragView.findViewById(R.id.util5);

        methods1=fragView.findViewById(R.id.methods1);
        methods2=fragView.findViewById(R.id.methods2);
        methods3=fragView.findViewById(R.id.methods3);

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
                listener.onInputSent(input1, input2,input3,input4,input5,input6,input7,input8
                ,input9,selections,mPace,mUtilization,mMethods);

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

        pace1.setOnClickListener(selectedItem);
        pace2.setOnClickListener(selectedItem);
        pace3.setOnClickListener(selectedItem);
        pace4.setOnClickListener(selectedItem);
        pace5.setOnClickListener(selectedItem);

        util1.setOnClickListener(selectedItem);
        util2.setOnClickListener(selectedItem);
        util3.setOnClickListener(selectedItem);
        util4.setOnClickListener(selectedItem);
        util5.setOnClickListener(selectedItem);

        methods1.setOnClickListener(selectedItem);
        methods2.setOnClickListener(selectedItem);
        methods3.setOnClickListener(selectedItem);







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
                case R.id.pace1:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mPace=paceTxt1.getText().toString();
                    }else{

                    }
                    break;
                case R.id.pace2:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mPace=paceTxt2.getText().toString();
                    }else{

                    }
                    break;
                case R.id.pace3:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mPace=paceTxt3.getText().toString();
                    }else{

                    }
                    break;
                case R.id.pace4:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mPace=paceTxt4.getText().toString();
                    }else{

                    }
                    break;
                case R.id.pace5:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mPace=paceTxt5.getText().toString();
                    }else{

                    }
                    break;
                case R.id.util1:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mUtilization= utilTxt1.getText().toString();
                    }else{

                    }
                    break;
                case R.id.util2:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mUtilization= utilTxt2.getText().toString();
                    }else{

                    }
                    break;
                case R.id.util3:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mUtilization= utilTxt3.getText().toString();
                    }else{

                    }
                    break;
                case R.id.util4:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mUtilization= utilTxt4.getText().toString();
                    }else{

                    }
                    break;
                case R.id.util5:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mUtilization= utilTxt5.getText().toString();
                    }else{

                    }
                    break;
                case R.id.methods1:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mMethods=methodsTxt1.getText().toString();
                    }else{

                    }
                    break;
                case R.id.methods2:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mMethods=methodsTxt2.getText().toString();
                    }else{

                    }
                    break;
                case R.id.methods3:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mMethods=methodsTxt3.getText().toString();
                    }else{

                    }
                    break;
                case R.id.methods4:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mMethods=methodsTxt4.getText().toString();
                    }else{

                    }
                    break;
                case R.id.methods5:
                    //if the user checks it, we add the string that
                    //corresponds to the checkbox to the list
                    if(checked){
                        mMethods=methodsTxt5.getText().toString();
                    }else{

                    }
                    break;

            }



        }
    };
}
