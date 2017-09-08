package android.paperwrrk.www.krezr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    private Boolean firstTime = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                if (isFirstTime()){
/*
                    Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(intent);
*/
                }
                else {
                    Intent intent = new Intent(SplashActivity.this,LocationActivity.class);
                    startActivity(intent);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }

    private boolean isFirstTime(){
        if (firstTime == null){
            SharedPreferences sharedPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = sharedPreferences.getBoolean("firstTime",true);
            if(firstTime){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("firstTime",false);
                editor.commit();
            }
        }
        return firstTime;
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
