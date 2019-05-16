package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class CouponDetailsActivity extends AppCompatActivity {

    /**
     * Member Variables for inputting
     * @Stacey
     */
   // private Button addToCouponButton;
    private String mId = "";
    private String mExpirationDate = "";
    private String mIsFavorite = "";
    private String mAdditionalNotes = "";

    /**
     * Direct Information to Coupon, Information and the Details about the "coupon"
     * @param savedInstanceState
     */


    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_coupon);

        //Button addToCouponButton = findViewById(R.id.Button_CollectAll);
        ImageView COUPON_IMAGE = findViewById(R.id.Image_Coupon);
        TextView ExpireDate = findViewById(R.id.ExpireDate);
        TextView IsFavorite = findViewById(R.id.IsFavorite);
        TextView AddNotes = findViewById(R.id.AddNotes);

        mId = getIntent().getStringExtra("id");
        String imageURI = getIntent().getStringExtra("imageURI");
        mExpirationDate = getIntent().getStringExtra("expirationDate");
        mIsFavorite = getIntent().getStringExtra("isFavorite");
        mAdditionalNotes = getIntent().getStringExtra("additionalNotes");

        //addToCouponButton = (Button) findViewById(R.id.Button_CollectAll);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(imageURI);
            Drawable event = Drawable.createFromStream(stream, mExpirationDate);
            COUPON_IMAGE.setImageDrawable(event);
        }
        catch (IOException ex)
        {
            Log.e("Coupon", "Error loading " + imageURI, ex);
        }

        ExpireDate.setText(mExpirationDate);
        IsFavorite.setText("Lots of Coupons to Enjoy " + toString().format(mIsFavorite));
        AddNotes.setText("Write Everything Down " + (mAdditionalNotes));
    }
}
