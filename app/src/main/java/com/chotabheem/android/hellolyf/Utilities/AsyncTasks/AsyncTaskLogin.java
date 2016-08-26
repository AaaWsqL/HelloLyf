package com.chotabheem.android.hellolyf.Utilities.AsyncTasks;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;


import com.chotabheem.android.hellolyf.DataModels.DTO_User;
import com.chotabheem.android.hellolyf.DataModels.ServerResponse;
import com.chotabheem.android.hellolyf.HomeActivity;
import com.chotabheem.android.hellolyf.LoginActivity;
import com.chotabheem.android.hellolyf.Utilities.AppConstants;
import com.chotabheem.android.hellolyf.Utilities.RestUtils.GetJSONResponse;
import com.chotabheem.android.hellolyf.Utilities.SessionManager;

import org.json.JSONObject;


/**
 * Created by chota_bheem on 24/8/16.
 */
public class AsyncTaskLogin extends AsyncTask<String, Void, String> {
    private ServerResponse jsonResponse;
    private Activity context;
    private ProgressDialog progressDialog;



    public AsyncTaskLogin(Activity context) {
        super();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Connecting");
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result) {
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialog.dismiss();
            }
        };
        //if response was a success, store the response in User data model
        //else show error , try again
        if(progressDialog.isShowing()){
            if (result == "true"){
                progressDialog.setMessage("Login Successful");
                DTO_User user = (DTO_User) jsonResponse.getData();
                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 1000);
                SessionManager sessionManager= new SessionManager(context);
                sessionManager.createLoginSession(user.getFirstName() , user.getEmailId() , user.getPatient_ID());
                launchHomeScreen();
            }

            else{
                progressDialog.setMessage("Error.. please try Again");

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2000);
            }



        }

        super.onPostExecute(result);
    }

    @Override
    protected String doInBackground(String... args) {
        GetJSONResponse mGetJSONResponse = new GetJSONResponse();
        jsonResponse = mGetJSONResponse.getResponseLogin(AppConstants.REST_API_LOGIN, args);
        //the ServerResponse contains success message as getSuccess(), and DTO_User in getData()

        Log.i("AsyncTaskLogin",jsonResponse.getSuccess().toString() );

        return jsonResponse.getSuccess().toString();


    }

    private void launchHomeScreen() {
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
