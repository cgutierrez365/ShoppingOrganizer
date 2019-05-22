package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Items;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;

/**
 * This Activity handles the events launched by a user adding an Store to their list.
 *
 * @Author Clarissa Gutierrez
 * @Version 5/21/19
 */
public class AddStoreActivity extends AppCompatActivity {

    private DBHelper db;

    private EditText storeNameEditText;
    private EditText storeAddressEditText;
    private EditText storeLatitudeEditText;
    private EditText storeLongitudeEditText;
    private ImageView storeImageView;
    private Uri imageUri;

    public static final int RESULT_LOAD_ITEM_IMAGE = 101;

    /**
     * This method executes when AddItemActivity is first launched and does not yet contain user input.
     * It associates the contents of activity_add_item.xml with this Activity (the controller)
     * @param savedInstanceState the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);

//        db = db.SQLiteOpenHelper(this ,null, null, 1); //TODO: figure out how to get existing database??
        db = new DBHelper(this);

        storeNameEditText = findViewById(R.id.storeNameEditText);
        storeAddressEditText = findViewById(R.id.storeAddressEditText);
        storeLatitudeEditText = findViewById(R.id.storeLatitudeEditText);
        storeLongitudeEditText = findViewById(R.id.storeLongitudeEditText);
        storeImageView = findViewById(R.id.storeImageView);

        //Initialize item image to that of the shopping cart
        imageUri = getUriToResource(this, R.drawable.images);

        storeImageView.setImageURI(imageUri);
        storeImageView.setTag(imageUri.toString()); //tag has to be a URI toString b/c that's how Items class data type was configured

    }

    /**
     * Launches events to access a item image from the user's camera roll, including asking for permissions from the user,
     * when the user clicks the mPetImageView
     * @param v the mPetImageView
     */
    public void setItemImage(View v)
    {
        // 1) Make a list (empty) of permissions
        // 2) As the user grants them, add each permission to the list
        List<String> permsList = new ArrayList<>();
        int permRequestCode = 100;
        int hasCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        // Check to see if we have camera enabled
        // If denied, add it to the list of permissions we will ask the user for
        if(hasCameraPermission == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.CAMERA);
        }

        int hasReadExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(hasReadExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        int hasWriteExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(hasWriteExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        //Ask the user for permissions
        if(permsList.size() > 0)
        {
            //convert list into an array
            String [] perms = new String[permsList.size()];
            permsList.toArray(perms);
            //Make request to the user (backwards compatible
            ActivityCompat.requestPermissions(this, perms, permRequestCode);
        }

        // After requesting permissions, find out which ones user granted
        // Check to see if ALL permissions were granted
        if(hasCameraPermission == PackageManager.PERMISSION_GRANTED &&
                hasReadExternalPerm == PackageManager.PERMISSION_GRANTED &&
                hasWriteExternalPerm == PackageManager.PERMISSION_GRANTED)
        {
            //Open the gallery!
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_ITEM_IMAGE);
        }
        else
        {
            //DONE: Toast informing the user need permissions
            Toast.makeText(this, R.string.permissions_message,Toast.LENGTH_LONG).show();
        }
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

    /**
     * Sets the item image view to the chosen image from the user's camera roll
     * @param requestCode the request code of the chosen image
     * @param resultCode the result code of the chosen image
     * @param data an intent holding the data of the image file
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
        {
            return;
        }

        if(requestCode == RESULT_LOAD_ITEM_IMAGE)
        {
            Uri uri = data.getData();
            storeImageView.setImageURI(uri);
            storeImageView.setTag(uri.toString()); //extract the imageUri from the image view and add it to the database
        }
    }

    /**
     * Executes whe user clicks "Add Item" button on activity_add_item.xml.
     * Adds the item to the item database if all the fields are filled
     * @param v the button
     */
    public void addStore(View v)
    {
        String imageUriString = (String) storeImageView.getTag();

        //Capture the fields added by the user
        String storeName = storeNameEditText.getText().toString();
        String storeAddress = storeAddressEditText.getText().toString();
        String storeLatitudeString = (storeLatitudeEditText.getText().toString());
        String storeLongitudeString = (storeLongitudeEditText.getText().toString());


        if (TextUtils.isEmpty(storeName) || TextUtils.isEmpty(storeAddress) || TextUtils.isEmpty(storeLatitudeString) || TextUtils.isEmpty(storeLongitudeString))
        {
            Toast.makeText(this, R.string.add_store_toast, Toast.LENGTH_LONG).show();
            return;
        }

        double storeLatitude = Double.parseDouble(storeLatitudeString);
        double storeLongitude = Double.parseDouble(storeLongitudeString);

        //Create new Store with data from fields
        Store newStore = new Store(-1, storeName, storeAddress, storeLatitude, storeLongitude, imageUriString);

        //DONE: Set the result (the newStore) so StoreActivity has access to the new data
        Intent backToItemActivityIntent = new Intent();
        backToItemActivityIntent.putExtra("newStore", newStore);

        db.addStore(newStore); //Add Store to the database

        setResult(Activity.RESULT_OK, backToItemActivityIntent); //Sets the Intent field in onActivityResult to this intent

        // Reset all entries so user can add more if they want
        storeNameEditText.setText("");
        storeAddressEditText.setText("");
        storeLatitudeEditText.setText("");
        storeLongitudeEditText.setText("");
        storeImageView.setImageURI(getUriToResource(this, R.drawable.images));

        Toast.makeText(this, R.string.store_successful_added, Toast.LENGTH_SHORT).show();

        finish();
    }

    /**
     * Ends AddItemActivity, thus reverting to the previous activity that called this one
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen(View v)
    {
        this.finish();
    }
}
