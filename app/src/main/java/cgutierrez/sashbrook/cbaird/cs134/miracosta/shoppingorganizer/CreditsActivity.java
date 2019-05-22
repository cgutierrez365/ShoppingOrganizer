package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Activity displays the authors of this app by fading in (alpha animation) from the background color
 * @Author Clarissa Gutierrez
 * @Version 5/22/19
 */
public class CreditsActivity extends AppCompatActivity {

    private Animation alphaAnim;

    /**
     * Connects to the activity_credits and applies the alpha animation to the constraint layout holding the image and text
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
     * Finishes the activity to return to the Main Menu Activity
     * @param v the return button
     */
    public void returnToMainMenu(View v)
    {
        this.finish();
    }
}
