package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Items;


public class ItemDetailsActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    ImageView itemImageView;
    TextView itemNameTextView;
    TextView storeNameTextView;
    TextView locationTextView;
    TextView quantityTextView;

    ListView couponListView;
    Button addCouponButton;

    private static final String TAG = ItemDetailsActivity.class.getSimpleName();

    //ON CREATE-------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        itemImageView = findViewById(R.id.itemImageView);
        itemNameTextView = findViewById(R.id.itemListItemNameTextView);
        storeNameTextView = findViewById(R.id.storeNameTextView);
        locationTextView = findViewById(R.id.locationTextView);
        quantityTextView = findViewById(R.id.quantityTextView);

        Intent addItemIntent = getIntent();

        //RETRIEVE SENT OBJECT
        Items item = addItemIntent.getParcelableExtra("SelectedItem");


        //IMPORT IMAGE
        /*
        AssetManager am = getAssets();

        try
        {
        // TODO: add getImageName() to Items.java
            InputStream stream = am.open( item.getImageName() );
            Drawable image = Drawable.createFromStream(stream, item.getItemName() );
            itemImageView.setImageDrawable(image);
        }
        catch(IOException e)
        {
            Log.e(TAG, "Error loading: " + item.getItemName(), e);
        }

        */

        itemNameTextView.setText(item.getItemName());
        storeNameTextView.setText(item.getStoreName());
        locationTextView.setText(item.getStoreLocation());
        quantityTextView.setText(item.getItemQuantity());
        // TODO: ask how you would get the coupon database visible in the list??


    }


    //ON CLICK--------------------------------------------------------------------------------------
    public void addCoupon(View v)
    {


    }




}
