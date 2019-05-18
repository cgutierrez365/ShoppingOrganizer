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
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Items;

/**
 * This Activity handles the events launched by a user adding an Item to their list.
 * It Adds the item to the Items database.
 *
 * @Author Clarissa Gutierrez
 * @Version 4/30/19
 */
public class AddItemActivity extends AppCompatActivity {

    private DBHelper db;

    private EditText itemNameEditText;
    private EditText storeNameEditText;
    private EditText storeAddressEditText;
    private EditText quantityEditText;
    private ImageView mItemImageView;
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
        setContentView(R.layout.activity_add_item);

        //create a new database if one doesn't already exist
        db = new DBHelper(this);

        itemNameEditText = findViewById(R.id.itemNameEditText);
        storeNameEditText = findViewById(R.id.storeNameEditText);
        storeAddressEditText = findViewById(R.id.storeAddressEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        mItemImageView = findViewById(R.id.mItemImageView);

        //Initialize item image to that of the shopping cart
        imageUri = getUriToResource(this, R.drawable.ic_shopping_cart_24dp);

        mItemImageView.setImageURI(imageUri);
        mItemImageView.setTag(imageUri.toString()); //tag has to be a URI toString b/c that's how Items class data type was configured

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
            //TODO: Toast informing the user need permissions
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
            mItemImageView.setImageURI(uri);
            mItemImageView.setTag(uri.toString()); //For use in addPet() to extract the imageUri from the image view and add it to the database
        }
    }

    /**
     * Executes whe user clicks "Add Item" button on activity_add_item.xml.
     * Adds the item to the item database if all the fields are filled
     * @param v the button
     */
    public void addItem(View v)
    {
        String imageUriString = (String) mItemImageView.getTag();

        //Capture the fields added by the user
        String itemName = itemNameEditText.getText().toString();
        String storeName = storeNameEditText.getText().toString();
        String storeAddress = storeAddressEditText.getText().toString();
        String quantity = quantityEditText.getText().toString();


        if (TextUtils.isEmpty(itemName) || TextUtils.isEmpty(storeName) || TextUtils.isEmpty(storeAddress) || TextUtils.isEmpty(quantity))
        {
            Toast.makeText(this, "All fields (excluding image) must be provided to add an item to the list.", Toast.LENGTH_LONG).show();
            return;
        }

        //DONE: Need to figure out if should send location Address - saving address, not coordinates
    //TODO UNCOMMENT WHEN ADD COUPONSLIST BACK TO ITEMS CLASS---    Items newItem = new Items(-1, itemName, storeName, storeAddress, quantity, null, imageUriString);
        Items newItem = new Items(-1, itemName, storeName, storeAddress, quantity, imageUriString); //TODO: Erase this line when above TODO is DONE

        // Add the new item to the database to ensure it is persisted.
        db.addItem(newItem);



        //TODO: Check if lines below update the listView in ItemActivity (but doesn't immediately go to ItemActivity, until press done/cancel button
        //TODO: Will have to coordinate with Chloe so that she starts my  AddItemActivity using startActivityForResult()

        Intent backToItemActivityIntent = new Intent();
        backToItemActivityIntent.putExtra("newItem", newItem);
        setResult(Activity.RESULT_OK, backToItemActivityIntent); //Sets the Intent field in onActivityResult to this intent


//
//        Intent backToItemActivityIntent = new Intent(this, ItemActivity.class);
//    //    Intent backToItemActivityIntent = getIntent();
//        backToItemActivityIntent.putExtra("newItem", newItem); //OK b/c Items is Parcelable
//     //   setResult(RESULT_OK, backToItemActivityIntent); //will send

        // Reset all entries so user can add more if they want
        itemNameEditText.setText("");
        storeNameEditText.setText("");
        storeAddressEditText.setText("");
        quantityEditText.setText("");
        mItemImageView.setImageURI(getUriToResource(this, R.drawable.ic_shopping_cart_24dp));

        Toast.makeText(this, "Item Successfully Added", Toast.LENGTH_SHORT).show();

        finish();

        //set value of cancel button to say "Done" instead since a change was made to the database
//        Button doneButton = findViewById(R.id.cancelButton);
//        doneButton.setText(R.string.done);
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
