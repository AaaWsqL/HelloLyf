package com.chotabheem.android.hellolyf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.chotabheem.android.hellolyf.Utilities.SessionManager;

public class SplashActivity extends Activity {
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session= new SessionManager(getApplicationContext());
        // a handler is used to wait for specific time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                if(session.isLoggedIn()){
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }


                // close this activity
                finish();
            }
        }, 3000);





    }
}
