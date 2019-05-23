package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;


/**
 * Executes whe user clicks "Add Coupon" button on, adjust from Coupon Add.
 * Both the Image and Expiration are moved to the next page so the user can see Available
 * coupons and make a list.
 * @param
 * @author Stacey Ashbrook
 */


public class AddCouponActivity extends AppCompatActivity {

    /**
     * Member variables
     */
    private Button couponButtonCancel;
    private DBHelper db;
    private List<Coupons> couponsList;
    private ListView couponsListView;
    private ImageView CouponImageView;
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    public static final String EXTRA_DATE = "com.example.application.example.EXTRA_DATE";
    private ArrayAdapter couponsListAdapter;
    private static final String TAG = AddCouponActivity.class.getSimpleName();
    public static final int RESULT_LOAD_IMAGE = 200;
    private Animation customAnim;
    private EditText ExpirationDateEditText;


    /**
     * Create in the application, THIS WILL make the Button work for the Expiration Date Add to Coupons Activity,
     * This is finished.
     * (To add the Image of the Coupons, Gallery and picture to come)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        CouponImageView = findViewById(R.id.CouponImageView);
        CouponImageView.setImageURI(getUriToResource(this, R.drawable.images));
        db = new DBHelper(this);
        couponsList = db.getAllCouponsList();

        Button addCoupon = (Button) findViewById(R.id.button_addcoupon);
        addCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                openActivity2();
                Toast.makeText(AddCouponActivity.this, "Saving Money", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void toggleCustomAnim(View v) {
        customAnim = AnimationUtils.loadAnimation(this, R.anim.custom_sa_anim);
        CouponImageView.startAnimation(customAnim);
    }

    /**
     * Important for moving the Expiration Date,
     * basic number entry - no slashes or spaces required.
     *
     * >>may be adjusted to accomodate slashes?
     *      * number easiest entry.
     */
    public void openActivity2() {
        EditText couponEditText = (EditText) findViewById(R.id.ExpirationDateEditText);
       // int number = Integer.parseInt(couponEditText.getText().toString());
        long date = Integer.parseInt(couponEditText.getText().toString());

        Intent intent = new Intent(this, CouponActivity.class);
       // intent.putExtra(EXTRA_NUMBER, number);
        intent.putExtra(EXTRA_DATE, date);
        startActivity(intent);

    }
    //---/TO HERE /--/Exp Date/
    /**
     * ADD, DELETE COUPON
     */

    public void addCoupons (View view) {

        ExpirationDateEditText = findViewById(R.id.ExpirationDateEditText);
        if(view== findViewById(R.id.button_addcoupon)) {
            Toast.makeText(this, " Add Coupons ", Toast.LENGTH_SHORT).show();
        }

        String imageURI = (String) CouponImageView.getTag();
        String expirationDate = ExpirationDateEditText.getText().toString();
        Coupons newCoupons = new Coupons(-1, imageURI, expirationDate, null, null);

        // Add the new item to the database to ensure it is persisted.
        db.addCoupons(newCoupons);
        Intent forwardToCouponActivityIntent = new Intent();
        forwardToCouponActivityIntent.putExtra("Coupon", newCoupons);
        setResult(Activity.RESULT_OK, forwardToCouponActivityIntent);
        CouponImageView.setImageURI(getUriToResource(this, R.drawable.images));

        Toast.makeText(this, R.string.sample_text, Toast.LENGTH_SHORT).show();
        finish();


    }

    public void cancelAll( View view) {

        couponButtonCancel = (Button) findViewById(R.id.button_cancelCoupon);
        couponsListView = (ListView) findViewById(R.id.Coupon_Image_ListView);
        //show = (ListView) findViewById(R.id.Coupon_View);
        couponButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Toast.makeText(AddCouponActivity.this,"Moving",  Toast.LENGTH_SHORT).show();
                openActivity2();
            }
        });
        db.close();
    }

    /**
     * Ends AddItemActivity, thus reverting to the previous activity that called this one
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen(View v)
    {
        AddCouponActivity.this.finish();
    }
    public void returnToMainMenu(View v)
    {
        this.finish();
    }


    /**
     * TO ADD IMAGE TO GALLERY
     */
    public void selectCouponImageView ( View v ) {
        /** code for selectPetImage (View view) which uses a
         // CameraIntent to open the image gallery on the device and allows
         // a user to select an existing image or take one using their built-in
         // camera. We will complete this method during an in-class assignment.*/
        // 1) Make a list(empty) of permissions
        // 2) user grants, add each permission to the list
        List<String> permsList = new ArrayList<>();
        int permReqCode = 100;
        int hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //check to see if camera permission denied, if denied, add it to List of permissions requested.
        if (hasCameraPerm == PackageManager.PERMISSION_DENIED) {
            permsList.add(Manifest.permission.CAMERA);
        }

        int hasReadExternalPerm =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasReadExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        int hasWriteExternalPerm =
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteExternalPerm == PackageManager.PERMISSION_DENIED) {
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        //now that we've built the list, let's ask user
        /**
         * Notification listed for permissions to show and ask the user
         */
        if (permsList.size() > 0) {
            // convert the list into an array
            String[] perms = new String[permsList.size()];
            permsList.toArray(perms);

            //make the request to the user(backwards compatible)
            ActivityCompat.requestPermissions(this, perms, permReqCode);

        }
        /**
         * Permission Allowances to enter a new screen on the device,
         * which will access photos including the gallery.
         */
        if (hasCameraPerm == PackageManager.PERMISSION_GRANTED &&
                hasReadExternalPerm == PackageManager.PERMISSION_GRANTED &&
                hasWriteExternalPerm == PackageManager.PERMISSION_GRANTED) {
            // Open the Gallery!
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE); //permReqCode);


        } else {
            /**
             * Toast Widget Added, for Button and for Application,
             * a default message stating App Name when button clicked with
             * a notification to the user to enable permissions.
             */
            CouponImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick ( View v ) {
                    Toast.makeText(AddCouponActivity.this, "Coupon Selection!", Toast.LENGTH_SHORT).show();
                }
            });

            //MakeToast, toast informing user need permissions
            Toast toast = Toast.makeText(getApplicationContext(), "User Permission Needed",
                    Toast.LENGTH_LONG);
            toast.setMargin(150, 150);
            toast.show();
        }
        customAnim = AnimationUtils.loadAnimation(this, R.anim.custom_sa_anim);
        CouponImageView.startAnimation(customAnim);
    }

    /**
     * To add an image into view, loaded PetProtector image, none.png set through drawable.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    //override onActivityResult to find out user picked
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE) {
            Uri uri = data.getData();
            CouponImageView.setImageURI(uri);
            CouponImageView.setTag(uri.toString()); //For use in add to extract
        }
    }

    /**
     * To convert an android resource into a URI.
     *
     * @param context
     * @param resId
     * @return
     * @throws Resources.NotFoundException
     */
    private static Uri getUriToResource ( @NonNull Context context, @AnyRes int resId ) throws Resources.NotFoundException {
        Resources res = context.getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + "/" + res.getResourceTypeName(resId)
                + "/" + res.getResourceEntryName(resId);
        //android.resource://"
        return Uri.parse(uri);

    }
}

