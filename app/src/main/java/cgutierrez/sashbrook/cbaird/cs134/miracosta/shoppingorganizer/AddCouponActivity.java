package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.Manifest;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;

/**6. This activity is to aide and help in gathering coupons
 *  in various forms. Whether displayed within the application
 *  under drawable for user access, or from the gallery after a
 *  user has taken a picture. The activity will show the coupon as
 *  an image and allow for an expiration date to be viewed under
 *  the coupon image. At the bottom, an add coupon button will
 *  allow the user to add the coupon to an organizer list to
 *  aide in shopping discounts. The organizer portions of the
 *  application to bring together how coupon information will
 *  be display will be here.
 *
 */

public class AddCouponActivity extends AppCompatActivity {

    /**
     * Last DONE: Added in EditText for entry of Coupons date, switched on layout activity_add_coupon.xml -
     * the text and (wrench text) to display Expiration date correctly. <exactly a switch></exactly>
     * Added Button clickable for adding coupon, (will add image later)
     * Added textviews on second activity
     * Added Switching to next activity and displaying text in a textview! woo!
     *

    //public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";

    /**
     * Reminders
     */
//    private int COUPON_KEY_FIELD_ID;
//    private Text FIELD_COUPON_IMAGE;
//    private Text FIELD_EXPIRATION_DATE;
//    private Text FIELD_IS_FAVORITE;
//    private Text FIELD_ADDITIONAL_NOTES;

    /**
     * Member variables
     */
    private Button addCoupon;
    private TextView CouponExpiration;


    private EditText ExpireDateEditText;
    private EditText IsFavoriteEditText;
    private EditText AddNotesEditText;
    private EditText CouponsEditText;
    private String imageUriString;
    private ImageView ThemePicture;
    private DBHelper db;
    private List<Coupons> couponsList;
    private ListView CouponListView;
    private ImageView CouponImageView;
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_TEXT";
    private ArrayAdapter couponsListAdapter;
    private static final String TAG = AddCouponActivity.class.getSimpleName();
    public static final int RESULT_LOAD_IMAGE = 200;



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

        Button addCoupon = (Button) findViewById(R.id.button_addcoupon);
        addCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                    openActivity2();
                    Toast.makeText(AddCouponActivity.this, "Saving Money", Toast.LENGTH_SHORT).show();
                }
        });
    }
    public void openActivity2() {
        EditText editText1 = (EditText) findViewById(R.id.edittext1);
        //String text = String.format(editText1.getText().toString());
        int number = Integer.parseInt(editText1.getText().toString());

        Intent intent = new Intent(this, CouponActivity.class);
        intent.putExtra(EXTRA_NUMBER, number);
        //intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }
    //---/TO HERE /--/Exp Date/
    /**
     * ADD, DELETE COUPON
     */

//    /**
//     * Executes whe user clicks "Add Item" button on activity_add_item.xml.
//     * Adds the item to the item database if all the fields are filled
//     * @param view the button
//     */
//
//    public void addCoupons (View view) {
//
//        String imageUriString = (String) CouponImageView.getTag();
//
//        //Capture the fields added by the user
//        String expirationDate = ExpireDateEditText.getText().toString();
//        String isFavorite = IsFavoriteEditText.getText().toString();
//        String additionalNotes = AddNotesEditText.getText().toString();
//        String coupons = CouponsEditText.getText().toString();
//
//
//        if (TextUtils.isEmpty(expirationDate) || !isEmpty(isFavorite) || !isEmpty(additionalNotes) || !isEmpty(coupons))
//        {
//            Toast.makeText(this, "Including what you have!", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        //DONE: Need to figure out if should send location Address - saving address, not coordinates
//        Coupons newCoupon = new Coupons(-1, imageUriString, expirationDate, isFavorite, additionalNotes, coupons);
//
//        // Add the new item to the database to ensure it is persisted.
//        db.addCoupons(newCoupon); //DONE: write addItem method in DBHelper
//
//
//
//        // Check if lines below update the listView in ItemActivity (but doesn't immediately go to ItemActivity, until press done/cancel button
//        // Will have to coordinate with Chloe so that she starts my  AddItemActivity using startActivityForResult()
//        Intent backToItemActivityIntent = new Intent(this, CouponActivity.class);
//        backToItemActivityIntent.putExtra("newCoupon", newCoupon); //OK b/c Items is Parcelable
//        setResult(RESULT_OK, backToItemActivityIntent); //will send
//
//        // Reset all entries so user can add more if they want
//        ExpireDateEditText.setText("");
//        IsFavoriteEditText.setText("");
//        AddNotesEditText.setText("");
//        CouponsEditText.setText("");
//        CouponImageView.setImageURI(getUriToResource(this, R.drawable.images));
//
//        Toast.makeText(this, "Item Successfully Added", Toast.LENGTH_SHORT).show();
//
//        //set value of cancel button to say "Done" instead since a change was made to the database
//        Button doneButton = findViewById(R.id.button_addcoupon);
//        doneButton.setText(R.string.store_coupon);
//    }

    /**
     * Ends AddItemActivity, thus reverting to the previous activity that called this one
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen(View v)
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

        // after requesting permissions, find out which ones user granted
        //check to see if ALL permissions were granted
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
