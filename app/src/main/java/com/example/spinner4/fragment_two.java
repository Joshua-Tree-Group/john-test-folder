package com.example.spinner4;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


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


        return fragView;
    }
}
