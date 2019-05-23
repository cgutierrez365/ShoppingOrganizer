package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;

/**
 * Store Details for information on individual Stores or General Information on stores
 * that carry items, coupons and located close.
 * @author Chloe Baird
 */
public class StoreDetailsActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private String mId = "";
    private String id = "";
    private String mImageName = "";
    private String mLocation = "";
    private String mName = "";
    ImageView storeImageView;
    TextView storeTextView1;
    TextView storeTextView2;
    TextView storeTextView3;



    //ONCREATE--------------------------------------------------------------------------------------
    /**
     * Associates code with the xml layout.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        /**
         * layout input identifiers
         */
        storeImageView = findViewById(R.id.storeImageView);
        storeTextView1 = findViewById(R.id.storeTextView1);
        storeTextView2 = findViewById(R.id.storeTextView2);
        storeTextView3 = findViewById(R.id.storeTextView3);
        /** To move in activity and make an action occur*/
        Intent detailsIntent = getIntent();

        mId = getIntent().getStringExtra("id");
        String id = detailsIntent.getStringExtra("id");
        mImageName = getIntent().getStringExtra("imageName");
        String imageName = detailsIntent.getStringExtra("imageName");
        mName = getIntent().getStringExtra("name");
        String name = detailsIntent.getStringExtra("name");
        mLocation = getIntent().getStringExtra("location");
        String location = detailsIntent.getStringExtra("location");
        //No Longitude
        //No Latitude
        /**
         * Write to Screen for display
         */
        storeTextView1.setText("All Brand Name Stores" + mName);
        storeTextView2.setText("Lots of Coupons and Items to Enjoy " + mLocation);
        storeTextView3.setText("Everything can be organized, even with a pic " + mImageName);

        Store store = detailsIntent.getParcelableExtra("Select Store");

    }


    /**
     * A Go Back Feature
     * Ends AddCouponActivity, thus reverting to the previous activity that called this one
     *
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen( View v) {
        StoreDetailsActivity.this.finish();
    }

}