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
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.charset.Charset;


public class fragment_two extends Fragment {
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


     public interface FragmentBListner{
         void onInputBSent(CharSequence input, CharSequence input2, CharSequence input3, CharSequence input4, CharSequence input5,
         CharSequence input6,CharSequence input7, CharSequence input8, CharSequence input9);
     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView= inflater.inflate(R.layout.fragment_two,container,false);
        //create a variable that holds the reference to the
        //mentor activity so that we can access info from it.
        activity3 = (MentorForm) getActivity();
        //set references to textviews
        pm0 =(TextView)fragView.findViewById(R.id.pm0);
        pm1 =(TextView)fragView.findViewById(R.id.pm1);
        pm2 =(TextView)fragView.findViewById(R.id.pm2);
        pm3 =(TextView)fragView.findViewById(R.id.pm3);
        pm4 =(TextView)fragView.findViewById(R.id.pm4);
        pm5 =(TextView)fragView.findViewById(R.id.pm5);
        pm6 =(TextView)fragView.findViewById(R.id.pm6);
        pm7 =(TextView)fragView.findViewById(R.id.pm7);
        pm8 =(TextView)fragView.findViewById(R.id.pm8);
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
                listener.onInputBSent(input1,input2,input3,input4,input5,input6,input7,input8,input9);
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
}
