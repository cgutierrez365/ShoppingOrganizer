package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;

public class CouponDetailsActivity extends AppCompatActivity {

    /**
     * Member Variables for inputting
     * @Stacey
     */
   // private Button addToCouponButton;
    private String mId = "";
    private String id = "";
    private String mImageURI = "";
    private String mExpirationDate = "";
    private String mIsFavorite = "";
    private String mAdditionalNotes = "";
    private static final String TAG = CouponDetailsActivity.class.getSimpleName();
    private ImageView couponDetailsImageView;
    private TextView couponExpireTextView;
    private TextView couponIsFavoriteTextView;
    private TextView couponAdditionalNotesTextView;

    /**
     * Direct Information to Coupon, Information and the Details about the "coupon"
     * @param savedInstanceState
     */


    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);

        couponDetailsImageView = findViewById(R.id.couponDetailsImageView);
        couponExpireTextView = findViewById(R.id.couponExpireTextView);
        couponIsFavoriteTextView = findViewById(R.id.couponIsFavoriteTextView);
        couponAdditionalNotesTextView = findViewById(R.id.couponAdditionalNotesTextView);

        Intent detailsIntent = getIntent();

        mId = getIntent().getStringExtra("id");
        String id = detailsIntent.getStringExtra("id");
        mImageURI = getIntent().getStringExtra("imageURI");
        String imageURI = detailsIntent.getStringExtra("imageURI");
        mExpirationDate = getIntent().getStringExtra("expirationDate");
        String expirationDate = detailsIntent.getStringExtra("expirationDate");
        mIsFavorite = getIntent().getStringExtra("isFavorite");
        String isFavorite = detailsIntent.getStringExtra("isFavorite");
        mAdditionalNotes = getIntent().getStringExtra("additionalNotes");
        String additionalNotes = detailsIntent.getStringExtra("additionalNotes");
        couponExpireTextView.setText("Lists are great!" + mExpirationDate);
        couponIsFavoriteTextView.setText("Lots of Coupons to Enjoy " + mIsFavorite);
        couponAdditionalNotesTextView.setText("Write Everything Down " + mAdditionalNotes);

        //RETRIEVE SENT OBJECT
        Coupons coupons = detailsIntent.getParcelableExtra("Select Coupons");

    }


    /**
     * A Go Back Feature
     * Ends AddCouponActivity, thus reverting to the previous activity that called this one
     *
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen( View v) {
        CouponDetailsActivity.this.finish();
    }

}
