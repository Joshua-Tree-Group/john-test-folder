package com.example.spinner4;

import android.arch.lifecycle.Lifecycle;
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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.charset.Charset;
import java.util.ArrayList;


public class fragment_two extends Fragment {
    ArrayList<String> selection = new ArrayList<String>();

     MentorForm activity3;
      TextView pm0;
     TextView pm1;
     TextView pm2;
     TextView pm3;
     TextView pm4;
     TextView pm5;
     TextView pm6;
     TextView pm7;
     TextView pm8;
     TextView paceTxt1;
     TextView paceTxt2;
     TextView paceTxt3;
     TextView paceTxt4;
     TextView paceTxt5;
     TextView utilTxt1;
     TextView utilTxt2;
     TextView utilTxt3;
     TextView utilTxt4;
     TextView utilTxt5;
     TextView methodsTxt1;
     TextView methodsTxt2;
     TextView methodsTxt3;
     Button summary;
     private FragmentBListner listener;
     EditText improvements1;
     EditText improvements2;
     EditText improvements3;
     EditText didWell1;
     EditText didWell2;
     EditText didWell3;
     EditText feedback1;
     EditText feedback2;
     EditText feedback3;
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
     String mPace;
     String mUtilization;
     String mMethods;




     public interface FragmentBListner{
         void onInputBSent(CharSequence input, CharSequence input2, CharSequence input3, CharSequence input4, CharSequence input5,
         CharSequence input6,CharSequence input7, CharSequence input8, CharSequence input9, ArrayList input10, String input11, String input12,String input13);
     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView= inflater.inflate(R.layout.fragment_two,container,false);
        //create a variable that holds the reference to the
        //mentor activity so that we can access info from it.
        activity3 = (MentorForm) getActivity();
        //set references to textviews
        pm0 =(TextView)fragView.findViewById(R.id.pm0);;
        pm1 =(TextView)fragView.findViewById(R.id.pm1);
        pm2 =(TextView)fragView.findViewById(R.id.pm2);
        pm3 =(TextView)fragView.findViewById(R.id.pm3);
        pm4 =(TextView)fragView.findViewById(R.id.pm4);
        pm5 =(TextView)fragView.findViewById(R.id.pm5);
        pm6 =(TextView)fragView.findViewById(R.id.pm6);
        pm7 =(TextView)fragView.findViewById(R.id.pm7);
        pm8 =(TextView)fragView.findViewById(R.id.pm8);
        paceTxt1=fragView.findViewById(R.id.paceTxt1);
        paceTxt2=fragView.findViewById(R.id.paceTxt2);
        paceTxt3=fragView.findViewById(R.id.paceTxt3);
        paceTxt4=fragView.findViewById(R.id.paceTxt4);
        paceTxt5=fragView.findViewById(R.id.paceTxt5);
        utilTxt1=fragView.findViewById(R.id.utilTxt1);
        utilTxt2=fragView.findViewById(R.id.utilTxt2);
        utilTxt3=fragView.findViewById(R.id.utilTxt3);
        utilTxt4=fragView.findViewById(R.id.utilTxt4);
        utilTxt5=fragView.findViewById(R.id.utilTxt5);
        methodsTxt1=fragView.findViewById(R.id.methodsTxt1);
        methodsTxt2=fragView.findViewById(R.id.methodsTxt2);
        methodsTxt3=fragView.findViewById(R.id.methodsTxt3);



        //set references for checkboxes
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

        //assign clicklistener to all checkboxes
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

        //assign values to textviews
        pm0.setText(activity3.op.prefferedMethods[0]);
        pm1.setText(activity3.op.prefferedMethods[1]);
        pm2.setText(activity3.op.prefferedMethods[2]);
        pm3.setText(activity3.op.prefferedMethods[3]);
        pm4.setText(activity3.op.prefferedMethods[4]);
        pm5.setText(activity3.op.prefferedMethods[5]);
        pm6.setText(activity3.op.prefferedMethods[6]);
        pm7.setText(activity3.op.prefferedMethods[7]);
        pm8.setText(activity3.op.prefferedMethods[8]);
        //assign value to button
        summary =fragView.findViewById(R.id.summary);
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


        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input1=improvements1.getText();
                CharSequence input2= improvements2.getText();
                CharSequence input3= improvements3.getText();
                CharSequence input4= didWell1.getText();
                CharSequence input5= didWell2.getText();
                CharSequence input6= didWell3.getText();
                CharSequence input7= feedback1.getText();
                CharSequence input8= feedback2.getText();
                CharSequence input9= feedback3.getText();
                listener.onInputBSent(input1,input2,input3,input4,input5,input6,input7,input8,input9,selection,mPace,mUtilization,mMethods);
            }
        });


        return fragView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentBListner){
            listener =(FragmentBListner) context;
        }else{
            throw new RuntimeException(context.toString()
            +" must implement fragmentBlistener");
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

           boolean checked = ((CheckBox) v).isChecked();

           //find out which checkboxes where selected by the user
           switch (v.getId()){
               case R.id.topPref1:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm0.getText());
                   }else{

                       selection.remove(""+pm0.getText());
                       System.out.println("THIS IS THE TEXT FOR THE FIRST PROD TASK: " +pm0.getText());
                   }
                   break;
               case R.id.topPref2:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm1.getText());
                   }else{

                       selection.remove(""+pm1.getText());
                   }
                   break;
               case R.id.topPref3:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm2.getText());
                   }else{

                       selection.remove(""+pm2.getText());
                   }
                   break;
               case R.id.pref1:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm3.getText());
                   }else{

                       selection.remove(""+pm3.getText());
                   }
                   break;
               case R.id.pref2:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm4.getText());
                   }else{

                       selection.remove(""+pm4.getText());
                   }
                   break;
               case R.id.pref3:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm5.getText());
                   }else{

                       selection.remove(""+pm5.getText());
                   }
                   break;
               case R.id.adv1:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm6.getText());
                   }else{

                       selection.remove(""+pm6.getText());
                   }
                   break;
               case R.id.adv2:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm7.getText());
                   }else{

                       selection.remove(""+pm7.getText());
                   }
                   break;
               case R.id.adv3:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       selection.add("" + pm8.getText());
                   }else{

                       selection.remove(""+pm8.getText());
                   }
                   break;
               case R.id.pace1:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mPace= paceTxt1.getText().toString();
                       System.out.println("PACE 1 FROM fragment_two"+mPace);
                   }else{

                   }
                   break;
               case R.id.pace2:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mPace= paceTxt2.getText().toString();
                   }else{

                   }
                   break;
               case R.id.pace3:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mPace= paceTxt3.getText().toString();
                   }else{

                   }
                   break;
               case R.id.pace4:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mPace= paceTxt4.getText().toString();
                   }else{

                   }
                   break;
               case R.id.pace5:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mPace= paceTxt5.getText().toString();
                   }else{

                   }
                   break;
               case R.id.util1:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mUtilization= utilTxt1.getText().toString();
                   }else{

                   }
                   break;
               case R.id.util2:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mUtilization= utilTxt2.getText().toString();
                   }else{

                   }
                   break;
               case R.id.util3:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mUtilization= utilTxt3.getText().toString();
                   }else{

                   }
                   break;
               case R.id.util4:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mUtilization= utilTxt4.getText().toString();
                   }else{

                   }
                   break;
               case R.id.util5:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mUtilization=utilTxt5.getText().toString();
                   }else{

                   }
                   break;
               case R.id.methods1:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mMethods= methodsTxt1.getText().toString();
                   }else{

                   }
                   break;
               case R.id.methods2:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mMethods= methodsTxt2.getText().toString();;
                   }else{

                   }
                   break;
               case R.id.methods3:
                   //if the user checks it we add it to the list
                   //if user unchecks it we remove it from list

                   if(checked) {
                       mMethods= methodsTxt3.getText().toString();
                   }else{

                   }
                   break;



           }
           System.out.println("THIS IS THE ARRAY: " +selection);
           Toast.makeText(getContext(), "array is "+ selection.toString(),Toast.LENGTH_LONG);
           System.out.println("THIS IS THE TEXT FOR THE FIRST PROD TASK: " +pm0.getText());

       }
   };


}
