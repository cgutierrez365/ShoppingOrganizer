package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Step by Step, adding an Intent ==> to where? to switch to another activity and this is
        //without a delay so it is immediate. keep in mind for simplicity in onCreate.
        //moved: Intent musicIntent = new Intent(this, MusicActivity.class);
        // startActivity(musicIntent);
        //error on move, inside due to entirely different class error, the context is now.
        //must now be specific., this changed to SplashActivity.this


        //Delay the activity with a TimerTask
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent musicIntent = new Intent(SplashActivity.this, MainMenuActivity.class);
                startActivity(musicIntent);
                //Finish the current activity (finish SplashActivity)
                finish();
            }
        };
        //anonymous inner class, to specify the timer [itself]
        Timer timer = new Timer();
        timer.schedule(task, 3000);

        // Any loading code goes here (DBHelper gets instantiated, loading JSON)



    }
}