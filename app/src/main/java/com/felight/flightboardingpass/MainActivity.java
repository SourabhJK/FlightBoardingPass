package com.felight.flightboardingpass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Activity to store the User Information
public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etDOB;
    private EditText etContactNumber;
    private EditText etMailId;
    private EditText etPassword;
    private EditText etConfirmPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etContactNumber = (EditText) findViewById(R.id.etContactNumber);
        etMailId =(EditText) findViewById(R.id.etMailId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
    }

    //Method to save the user information using Shared Preferences
    public void register(View view){
        String name = etName.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("Registration Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String contactNumber = etContactNumber.getText().toString();
        String dob = etDOB.getText().toString();
        String mailId = etMailId.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        //Checking if any field is left empty
        if(name.equals("")||dob.equals("")||contactNumber.equals("")||mailId.equals("")||password.equals("")||confirmPassword.equals("")){
            Toast.makeText(this,"Please fill all the field and then click on the register",Toast.LENGTH_SHORT).show();
        }
        else {
            if(password.equals(confirmPassword)){
                editor.putString("Name",etName.getText().toString());
                editor.putString("DOB",etDOB.getText().toString());
                editor.putString("ContactNumber",etContactNumber.getText().toString());
                editor.putString("MailId",etMailId.getText().toString());
                editor.putString("Passowrd",etPassword.getText().toString());
                editor.commit();
                Toast.makeText(this,"Registeration Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,LoginActivity.class); //To open Login Page
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Confirm password does not match with the password",Toast.LENGTH_SHORT).show();
                etPassword.requestFocus();
            }
        }
    }
}
