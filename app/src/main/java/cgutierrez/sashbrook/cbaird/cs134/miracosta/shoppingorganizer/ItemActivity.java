package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Items;

/**
 * speothpo
 * @author Chloe Baird
 */

public class ItemActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private static final int UPDATED_LIST_CODE = 101;
    private static final String TAG = ItemActivity.class.getSimpleName();

    private DBHelper db;
    private List<Items> itemsList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;


    //ONCREATE--------------------------------------------------------------------------------------
    /**
     * Sets up the database and inflates the cutsom view for Items in the list.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        //CREATE DATABASE
        db = new DBHelper(this);
        db.importItemsFromCSV("items.csv");
        itemsList = db.getAllItems();

        //INFLATE CUSTOM LIST VIEW
        itemListAdapter = new ItemListAdapter(this, R.layout.custom_item, itemsList);
        itemListView = findViewById(R.id.itemsListView);
        itemListView.setAdapter(itemListAdapter);

        //CONNECT ACTIVITY TO XML
        itemListView = findViewById(R.id.itemsListView);

    }


    //OTHER METHODS---------------------------------------------------------------------------------
    /**
     * Sends the user to an activity to add an item to the database. Returns the user when finished.
     * @param v
     */
    public void addItem(View v)
    {
        //Intent addItemIntent = new Intent(Intent.)
        Intent addItemIntent = new Intent(this, AddItemActivity.class);
        startActivityForResult(addItemIntent, UPDATED_LIST_CODE);
    }


    /**
     * Updates the ListView after the user has made changes to the database.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UPDATED_LIST_CODE)
        {
            if(data == null) //If user canceled action (Pressing Cancel button, exit the method)
            {
                return;
            }

            //TODO: get parcelable intent
            Items newItem = data.getParcelableExtra("newItem");

//           //DONE(??): Get update listview from Chole
//            Uri itemData = data.getData();
//            Cursor cursor = getContentResolver().query( itemData, null, null, null, null );
//
//            if(cursor.moveToFirst())
//            {
//                long id = Long.parseLong( cursor.getString(0) );
//                String itemName = cursor.getString(1);
//                String storeName = cursor.getString(2);
//                String storeLocation = cursor.getString(3);
//                String quantity = cursor.getString(4);
//                String imageUri = cursor.getString(5);
//
//                newItem = new Items(id, itemName, storeName, storeLocation, quantity, imageUri);

                //ADD TO LIST
     //           db.addItem(newItem); //Already added in AddItemActivity
                itemListAdapter.add(newItem);
                itemListAdapter.notifyDataSetChanged();
         //   }

        }
    }

    /**
     * Sends user to an activity to view the details of an individual item.
     * @param v
     */
    public void viewItemDetails(View v)
    {
        Items selectedItem = (Items) v.getTag();
        Intent itemDetailsIntent = new Intent(this, ItemDetailsActivity.class);

        itemDetailsIntent.putExtra("SelectedItem", selectedItem);

        startActivity(itemDetailsIntent);

    }

}
