package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Items;

/**
 * Item Details for information on individual Items or General Information on items,
 * their coupons and where the items are located at in a Store.
 * @author Chloe Baird
 */

public class ItemDetailsActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private ImageView itemImageView;
    private TextView itemNameTextView;
    private TextView storeNameTextView;
    private TextView quantityTextView;
    private ListView couponListView;
    private DBHelper db;
    private Animation customAnim;

    private static final String TAG = ItemDetailsActivity.class.getSimpleName();


    //ON CREATE-------------------------------------------------------------------------------------
    /**
     * Retrieves the item which the user wishes to view, along with its data.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        //create a new database if one doesn't already exist
        db = new DBHelper(this);

        itemImageView = findViewById(R.id.itemImageView);
        itemNameTextView = findViewById(R.id.itemListItemNameTextView);
        storeNameTextView = findViewById(R.id.storeNameTextView);
        quantityTextView = findViewById(R.id.quantityTextView);

        Intent detailIntent = getIntent();

        //RETRIEVE SENT OBJECT
        Items item = detailIntent.getParcelableExtra("SelectedItem");

        itemImageView.setImageURI(Uri.parse(item.getImageURI()));
        itemNameTextView.setText(item.getItemName());
        storeNameTextView.setText(item.getStoreName());
        quantityTextView.setText(item.getItemQuantity());
        // TODO: ask how you would get the coupon database visible in the list??
//        db.getAllLinkedItemCoupons()

    }
    public void toggleCustomAnim(View v) {
        customAnim = AnimationUtils.loadAnimation(this, R.anim.chloe_shake_anim);
        itemImageView.startAnimation(customAnim);
    }



    //OTHER METHODS---------------------------------------------------------------------------------
    /**
     * Redirects the user to an activity to upload coupons relating to the particular item.
     * @param v
     */
    public void addCoupon(View v)
    {
        Intent addCouponIntent = new Intent(this, AddCouponActivity.class);

        startActivity(addCouponIntent);

    }

    /**
     * Gets a image resource from a given URI
     * @param context the context we wish to use the image resource in
     * @param id the id
     * @return a URI connecting to the image resource
     */
    protected static Uri getUriToResource(Context context, int id)
    {
        //String uri = "android.resource://";
        Resources res = context.getResources();

        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(id) + "/"
                + res.getResourceTypeName(id) + "/"
                + res.getResourceEntryName(id);
        return Uri.parse(uri);
    }


}
