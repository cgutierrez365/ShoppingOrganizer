package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;


/**7. Bringing together a layout called activity_add_store.xml.
 * An Activity that will allow the user to view numerous stores listed together.
 * Able to select a store from the list (currently listed as item), once
 * selected a click of the button, “+ Add Store” moves to another activity where
 * 'Store' is added to an organizer list for the user to visit and achieve needed
 * goods. This activity will manage a list of Stores, imageView(s) of the stores,
 * the names of all stores and possibly local information such as an address.
 * The organizer portions of the application to bring together how store information
 * will be display will be here.
 *
 */

public class StoresActivity extends AppCompatActivity {


    /**
     * Member variables
     */
    private static final int UPDATED_LIST_CODE = 121;
    private List<Store> allStoresList;
    private ListView storesListView;
    private StoreAdapter storeListAdapter;
    private DBHelper db;
    private Store newStore;



    /**
     * Constructor class
     */
    public StoresActivity () {
    }

    /**
     * Created in application
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);


        Button addStore = findViewById(R.id.addStore);
        //TextView StoreNameTextView = findViewById(R.id.StoreNameTextView);
        //ImageView StoreFrontView = findViewById(R.id.StoreimageView);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importStoresFromCSV("stores.csv");

        //Establish database db = new DBHelper(this);
        allStoresList = db.getallStoresList();


        // DONE:  Connect the list adapter with the list
        storeListAdapter = new StoreAdapter(this, R.layout.custom_store, allStoresList);

        // DONE:  Set the list view to use the list adapter
        storesListView = findViewById(R.id.storesListView);
        storesListView.setAdapter(storeListAdapter);


    }


    /**
     * Code to run, alongside or in app.
     * Flow: Stores Activity --> Model Stores ---> Adapter <- BUTTON
     * Implement the view Store Details using a view and an intent
     */

    // DONE: Implement the view store details using an Intent
    //
    public void viewStoreDetails(View view ) {
        Store selectedStore = (Store) view.getTag();

        Intent detailsIntent = new Intent(this, StoresActivity.class);
        // commented out & convert to:
        detailsIntent.putExtra("Selected Store", selectedStore);
        startActivity(detailsIntent);
    }

    // DONE: Implement the code for when the user clicks on the addStoreButton
    public void addStore(View view ) {

        Store stores = newStore;
        Button addStore = (Button) findViewById(R.id.addStore);
        storesListView = (ListView) findViewById(R.id.storesListView);
        //show = (ListView) findViewById(R.id.Coupon_View);
        addStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Toast.makeText(StoresActivity.this, "Store Added!", Toast.LENGTH_SHORT).show();
                storesListView.deferNotifyDataSetChanged();
            }
        });
        allStoresList.add(stores);
        Intent addItemIntent = new Intent(this, AddStoreActivity.class); //Change to store
        startActivityForResult(addItemIntent, UPDATED_LIST_CODE);
    }

    public void deleteAllStores ( View view ) {
        //if (view== findViewById(R.id.cancelStore))
        // {
        allStoresList.clear();
        // Permanently delete games from the database, buh bye
        db.deleteAllStores();
        storeListAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Store Deleted", Toast.LENGTH_SHORT).show();
        finish();
        //}
    }

    //Will update the listView after user added things to the database
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATED_LIST_CODE) {
            Store stores = new Store();
            /// (should have included it in her onCreate()
            Uri storeData = data.getData();
            Cursor cursor = getContentResolver().query(storeData, null, null, null, null);

            if (cursor.moveToFirst()) {
                long id = cursor.getLong(0); // index column
                String mName = cursor.getString(1);
                String mLocation = cursor.getString(2);
                String mLatitude = cursor.getString(3);
                String mLongitude = cursor.getString(4);
                String mImageName = cursor.getString(5);

                stores.setId(id);
                stores.setName(mName);
                stores.setLocation(mLocation);
                stores.setImageName(mImageName);
                //stores.setLatitude(mLatitude);
                //stores.setLongitude(mLongitude);
                newStore = new Store(id, mName, mLocation, Double.parseDouble(mLatitude), Double.parseDouble(mLongitude), mImageName);

                //ADD TO LIST AND DATABASE
                db.addStore(newStore);
                storeListAdapter.add(newStore);
                storeListAdapter.notifyDataSetChanged();
            }
        }
    }

    public void revertToPreviousScreen ( View v ) {
        this.finish();
    }
}