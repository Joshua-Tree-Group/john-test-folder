package com.example.spinner4;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Array;

//https://www.youtube.com/watch?v=vdCejJobMp4

public class operation implements Parcelable {
    String operationName;
    String productionTaskOne;
    String productionTaskTwo;
    String productionTaskThree;
    String prefferedMethods[];

    //constructor method
    public operation() {

    }

    public String[] getPrefferedMethods() {
        return prefferedMethods;
    }

    public void setPrefferedMethods(String[] prefferedMethods) {
        this.prefferedMethods = prefferedMethods;
        System.out.println("this is it "+prefferedMethods[1]);
    }

    protected operation(Parcel in) {
        operationName = in.readString();
        productionTaskOne= in.readString();
        productionTaskTwo=in.readString();
        productionTaskThree=in.readString();
        prefferedMethods=in.createStringArray();
    }

    public static final Creator<operation> CREATOR = new Creator<operation>() {
        @Override
        public operation createFromParcel(Parcel in) {
            return new operation(in);
        }

        @Override
        public operation[] newArray(int size) {
            return new operation[size];
        }
    };

    public String getProductionTaskTwo() {
        return productionTaskTwo;
    }

    public void setProductionTaskTwo(String productionTaskTwo) {
        this.productionTaskTwo = productionTaskTwo;
    }

    public String getProductionTaskThree() {
        return productionTaskThree;
    }

    public void setProductionTaskThree(String productionTaskThree) {
        this.productionTaskThree = productionTaskThree;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getProductionTaskOne() {
        return productionTaskOne;
    }

    public void setProductionTaskOne(String productionTaskOne) {
        this.productionTaskOne = productionTaskOne;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operationName);
        dest.writeString(productionTaskOne);
        dest.writeString(productionTaskTwo);
        dest.writeString(productionTaskThree);
        dest.writeStringArray(prefferedMethods);
    }
}
