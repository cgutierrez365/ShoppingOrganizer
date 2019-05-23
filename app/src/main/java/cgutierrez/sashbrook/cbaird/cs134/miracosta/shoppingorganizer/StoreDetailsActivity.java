package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * oiseht
 * @author Chloe Baird
 */
public class StoreDetailsActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    ImageView storeImageView;



    //ONCREATE--------------------------------------------------------------------------------------
    /**
     * Associates code with the xml layout.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
    }
}
