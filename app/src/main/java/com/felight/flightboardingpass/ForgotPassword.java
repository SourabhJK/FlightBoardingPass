package com.felight.flightboardingpass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Activity to change the password
public class ForgotPassword extends AppCompatActivity {

    private EditText etNewPassword;
    private EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
    }

    public void addPassword(View view){
        String newPassword = etNewPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (newPassword.equals(null) || confirmPassword.equals(null)) {
            Toast.makeText(this,"Please fill both the fields",Toast.LENGTH_SHORT).show();
        }
        else {
            if (newPassword.equals(confirmPassword)) {
                SharedPreferences sharedPreferences = getSharedPreferences("Registration Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Passowrd",etNewPassword.getText().toString());
                editor.commit();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(this,"Entered Password and Confirm Password does not match",Toast.LENGTH_SHORT).show();
        }
    }
}
