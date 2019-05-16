package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

//TODO: Add an Alpha animation to the image view
/**
 * Displays the authors of this app
 */
public class CreditsActivity extends AppCompatActivity {

    private Animation alphaAnim;

    /**
     * connects to the activity_credits
     * @param savedInstanceState the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //Connect to xml
        ConstraintLayout creditsConstraintLayout = findViewById(R.id.creditsConstraintLayout);

        alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        creditsConstraintLayout.startAnimation(alphaAnim);
    }

    /**
     * finishes the activity to return to the main menu
     * @param v the return button
     */
    public void returnToMainMenu(View v)
    {
        this.finish();
    }
}
