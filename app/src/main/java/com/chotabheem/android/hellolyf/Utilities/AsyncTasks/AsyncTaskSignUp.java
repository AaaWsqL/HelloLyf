package com.chotabheem.android.hellolyf.Utilities.AsyncTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.chotabheem.android.hellolyf.DataModels.ServerResponse;
import com.chotabheem.android.hellolyf.DataModels.SingletonSignUpData;
import com.chotabheem.android.hellolyf.HomeActivity;
import com.chotabheem.android.hellolyf.Utilities.AppConstants;
import com.chotabheem.android.hellolyf.Utilities.RestUtils.GetJSONResponse;
import com.chotabheem.android.hellolyf.Utilities.SessionManager;

/**
 * Created by chota_bheem on 26/8/16.
 */
public class AsyncTaskSignUp extends AsyncTask<String, Void, String> {
    ServerResponse jsonResponse;
    private Activity context;
    private ProgressDialog progressDialog;



    public AsyncTaskSignUp(Activity context) {
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
                progressDialog.setMessage("Registration Successful");
                String patientID = (String) jsonResponse.getData();
                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 1000);
                SessionManager sessionManager= new SessionManager(context);
                sessionManager.createLoginSession(SingletonSignUpData.getInstance().getFirstName(), SingletonSignUpData.getInstance().getEmailId(), patientID);
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
        jsonResponse = mGetJSONResponse.getResponseSignUp(AppConstants.REST_API_SIGNUP, args);

        Log.i("AsyncTaskSignUp",jsonResponse.getSuccess().toString() );

        return jsonResponse.getSuccess().toString();


    }

    private void launchHomeScreen() {
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
