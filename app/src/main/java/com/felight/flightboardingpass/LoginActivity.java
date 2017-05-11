package com.felight.flightboardingpass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Activity for the User to Login
public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserPassword;
    private String DEFAULT = "N/A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserPassword = (EditText) findViewById(R.id.etUserPassword);

    }

    //Method to validate the username and password
    public void signIn(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("Registration Data", Context.MODE_PRIVATE);
        String mailId = sharedPreferences.getString("MailId",DEFAULT);
        String contactNumber = sharedPreferences.getString("ContactNumber",DEFAULT);
        String password = sharedPreferences.getString("Passowrd",DEFAULT);
        String enteredName = etUserName.getText().toString();
        String enteredPassword = etUserPassword.getText().toString();

        if(mailId.equals(DEFAULT)||contactNumber.equals(DEFAULT)||password.equals(DEFAULT)){
            Toast.makeText(this,"Please Sign Up to get access",Toast.LENGTH_SHORT).show();
        }
        else if((enteredName.equals(mailId)||enteredName.equals(contactNumber))&&enteredPassword.equals(password)){
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,BoardingPass.class);
            startActivity(intent); //Opens the Boarding Pass Activity
        }
        else
            Toast.makeText(this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
    }

    //Method takes the user to Registeration Page
    public void signUp(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("Registration Data", Context.MODE_PRIVATE);
        String mailId = sharedPreferences.getString("MailId",DEFAULT);
        String contactNumber = sharedPreferences.getString("ContactNumber",DEFAULT);
        String enteredName = etUserName.getText().toString();
        if (enteredName.equals(null)){
            Toast.makeText(this,"Enter the username or number to change the password",Toast.LENGTH_SHORT).show();
        }
        else{
            if(enteredName.equals(mailId)||enteredName.equals(contactNumber)){
                Intent intent = new Intent(this,ForgotPassword.class);
                startActivity(intent); //Takes the user to forgot password activity
            }
            else
                Toast.makeText(this,"Enter your registered EmailId or Contact Number",Toast.LENGTH_SHORT).show();
        }
    }
}
