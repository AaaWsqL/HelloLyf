package com.chotabheem.android.hellolyf.Utilities.RestUtils;

import android.util.Log;

import com.chotabheem.android.hellolyf.DataModels.ServerResponse;
import com.chotabheem.android.hellolyf.DataModels.SingletonSignUpData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chota_bheem on 25/8/16.
 */
public class GetJSONResponse {
//    public GetJSONResponse() {
//    }

    public String connectServer(String mUrl, JSONObject jsonObject){
        StringBuilder sb = new StringBuilder();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(mUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Accept", "application/json" );
            connection.connect();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

            int HttpResult =connection.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                System.out.println(""+sb.toString());
            } else{
                System.out.println(connection.getResponseMessage());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = sb.toString();
        return responseString;

    }

    public ServerResponse getResponseLogin(String restUrl, String... args){

        String username = args[0];
        String password = args[1];
        JSONObject jsonParam = new JSONObject();
        JSONObject jsonData = new JSONObject();
        try{

            jsonParam.put("email", username );
            jsonParam.put("password", password);

            jsonData.put("data", jsonParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String serverResponseString = connectServer(restUrl, jsonData);

        Gson gson =new GsonBuilder().create();
        ServerResponse resp = gson.fromJson(serverResponseString, ServerResponse.class);
        Log.i(" RestUtil", resp.getMessage().toString());

        return resp;

    }

    public ServerResponse getResponseSignUp(String restUrl, String... args){

        String patientType = SingletonSignUpData.getInstance().getPatientType();
        String emailId = SingletonSignUpData.getInstance().getEmailId();
        String password =  SingletonSignUpData.getInstance().getPassword();
        String firstName =  SingletonSignUpData.getInstance().getFirstName();
        String middleName =  SingletonSignUpData.getInstance().getMiddleName();
        String lastName =  SingletonSignUpData.getInstance().getLastName();
        String dob =  SingletonSignUpData.getInstance().getDob();
        Integer age =  Integer.parseInt(SingletonSignUpData.getInstance().getAge());
        String gender =  SingletonSignUpData.getInstance().getGender();
        String mobileNo =  SingletonSignUpData.getInstance().getMobileNo();

        JSONObject data = new JSONObject();
        JSONObject params = new JSONObject();
        try {
            params.put("patientType", patientType);
            params.put("emailId", emailId);
            params.put("password", password);
            params.put("firstName", firstName);
            params.put("middleName", middleName);
            params.put("lastName", lastName);
            params.put("dob", dob);
            params.put("age", age);
            params.put("gender", gender);
            params.put("mobileNo", mobileNo);

            data.put("data", params);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("RespnseSignup", data.toString());

        String serverResponseString = connectServer(restUrl, data);

        Gson gson =new GsonBuilder().create();
        ServerResponse resp = gson.fromJson(serverResponseString, ServerResponse.class);
        Log.i(" RestUtil", resp.getMessage().toString());

        return resp;



    }




}
