package com.felight.flightboardingpass;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserInformation extends AppCompatActivity {
    private TextView tvName;
    private TextView tvDOB;
    private TextView tvNumber;
    private TextView tvMail;
    private String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDOB = (TextView) findViewById(R.id.tvDOB);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        tvMail = (TextView) findViewById(R.id.tvMail);

        SharedPreferences sharedPreferences = getSharedPreferences("Registration Data", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name",DEFAULT);
        String dob = savedInstanceState.getString("DOB",DEFAULT);
        String mailId = sharedPreferences.getString("MailId",DEFAULT);
        String contactNumber = sharedPreferences.getString("ContactNumber",DEFAULT);

        tvName.setText(name);
        tvDOB.setText(dob);
        tvNumber.setText(contactNumber);
        tvMail.setText(mailId);
    }
}
