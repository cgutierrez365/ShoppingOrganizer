package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash Screen Start Activity
 * to welcome the user to the application
 * @author: Stacey Ashbrook
 */


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Delay the activity with a TimerTask
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent ShoppingIntent = new Intent(SplashActivity.this, StoresActivity.class); //MainMenuActivity
                startActivity(ShoppingIntent);
                //Finish the current activity (finish SplashActivity)
                finish();
            }
        };
        //anonymous inner class, to specify the timer [itself]
        Timer timer = new Timer();
        timer.schedule(task, 5000);
    }
}