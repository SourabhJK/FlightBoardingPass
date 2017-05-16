package com.felight.flightboardingpass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//Activity to display the boarding pass
public class BoardingPass extends AppCompatActivity {

    private TextView tvPassengerName;
    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvFlightId;
    private TextView tvBoardingTime;
    private TextView tvBoardingIn;
    private TextView tvDeparture;
    private TextView tvArrival;
    private TextView tvTerminal;
    private TextView tvGate;
    private TextView tvSeat;

    private ImageView ivQrCode;

    private String url = "https://flightboardingpass-be2a7.firebaseio.com/.json";
    private String jsonStr;
    private String passengerName;
    private String from;
    private String to;
    private String flightId;
    private String boardingTime;
    private String boardingIn;
    private String departue;
    private String arrival;
    private String terminal;
    private String gate;
    private String seat;
    private String qrCode;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_pass);

        tvPassengerName = (TextView) findViewById(R.id.tvPassengerName);
        tvFrom = (TextView) findViewById(R.id.tvFrom);
        tvTo = (TextView) findViewById(R.id.tvTo);
        tvFlightId = (TextView) findViewById(R.id.tvFlightId);
        tvBoardingTime = (TextView) findViewById(R.id.tvBoardingTime);
        tvBoardingIn = (TextView) findViewById(R.id.tvBoardingIn);
        tvDeparture = (TextView) findViewById(R.id.tvDeparture);
        tvArrival = (TextView) findViewById(R.id.tvArrival);
        tvTerminal = (TextView) findViewById(R.id.tvTerminal);
        tvGate = (TextView) findViewById(R.id.tvGate);
        tvSeat = (TextView) findViewById(R.id.tvSeat);
        ivQrCode = (ImageView) findViewById(R.id.ivQRCode);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        new GetJSON().execute(url);
    }

    //AsyncTask to get the JSON data from the url
    private class GetJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urlReq) {
            HttpHandler sh = new HttpHandler();
            jsonStr = sh.makeServiceCall(urlReq[0]);
            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            if(jsonStr!=null){ //Checking if the jsonStr is empty
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    String trial = jsonObject.getString("boardingpass");
                    Log.i("Checking",trial);
                    JSONObject jsonBoardingPass = jsonObject.getJSONObject("boardingpass");

                    passengerName = jsonBoardingPass.getString("passengerName");
                    tvPassengerName.setText(passengerName);
                    Log.i("Checking",passengerName);

                    from = jsonBoardingPass.getString("from");
                    tvFrom.setText(from);
                    Log.i("Checking",from);

                    to = jsonBoardingPass.getString("to");
                    tvTo.setText(to);

                    flightId = jsonBoardingPass.getString("flightId");
                    tvFlightId.setText(flightId);

                    boardingTime = jsonBoardingPass.getString("boardingTime");
                    tvBoardingTime.setText(boardingTime);

                    boardingIn = jsonBoardingPass.getString("boardingIn");
                    tvBoardingIn.setText(boardingIn);

                    departue = jsonBoardingPass.getString("departure");
                    tvDeparture.setText(departue);

                    arrival = jsonBoardingPass.getString("arrival");
                    tvArrival.setText(arrival);

                    terminal = jsonBoardingPass.getString("terminal");
                    tvTerminal.setText(terminal);

                    gate = jsonBoardingPass.getString("gate");
                    tvGate.setText(gate);

                    seat = jsonBoardingPass.getString("seat");
                    tvSeat.setText(seat);

                    qrCode = jsonBoardingPass.getString("qrcode");

                    ImageLoadTask imageLoadTask = new ImageLoadTask(qrCode);
                    imageLoadTask.execute();

                }catch (final JSONException e){
                    Toast.makeText(BoardingPass.this,"Error:  "+e,Toast.LENGTH_LONG).show();
                }
            }else
                Toast.makeText(BoardingPass.this,"Please connect to internet",Toast.LENGTH_SHORT).show();


        }
    }

    //AsyncTask to load the image form the url
    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;

        public ImageLoadTask(String url) {
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            ivQrCode.setImageBitmap(result);
            progressDialog.dismiss();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // To create the pop up menu
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.boarding_pass_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.resetPassword: // Goes to the Activity to reset the password
                Intent intent = new Intent(this,ForgotPassword.class);
                startActivity(intent);
                break;

            case R.id.userInformation: // Goes to the Activity which displays the User Information
                Intent informationIntent = new Intent(this,UserInformation.class);
                startActivity(informationIntent);
                break;

            case R.id.userLocation:  // Goes to the Maps Activity and displays the current location
                Intent mapIntent = new Intent(this,MapsActivity.class);
                startActivity(mapIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
