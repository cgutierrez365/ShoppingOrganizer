package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
    private DBHelper db;
    private List<Store> allStoresList;
    private ListView storesListView;
    private StoreAdapter storeListAdapter;
    private Button addStore;
    private TextView StoreNameTextView;
    private ImageView StoreFrontView;
    private EditText editTextStore;



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
        setContentView(R.layout.activity_add_store);


        Button addStore = findViewById(R.id.addStore);
        TextView StoreNameTextView = findViewById(R.id.StoreNameTextView);
        ImageView StoreFrontView = findViewById(R.id.StoreimageView);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importStoresFromCSV("stores.csv");


//        allStoresList = db.getallStoresList();

        // DONE:  Connect the list adapter with the list
        storeListAdapter = new StoreAdapter(this, R.layout.custom_store, allStoresList);

        // DONE:  Set the list view to use the list adapter
        storesListView = findViewById(R.id.storesListView);
        storesListView.setAdapter(storeListAdapter);

    }

    /**
     * Code to run, alongside or in app.
     * Flow: Stores Activity --> Model Stores ---> Adapter <- BUTTON
     */

    public void viewStoreDetails ( View view ) {
        Store selectedStore = (Store) view.getTag();

        Intent detailsIntent = new Intent(this, StoresActivity.class);

        detailsIntent.putExtra("SelectedStore", selectedStore);

        startActivity(detailsIntent);
    }
    // TODO: Implement the code for when the user clicks on the addStoreButton
    public void addStore( View view ) {

        TextView storeTextView = findViewById(R.id.StoreNameTextView);
       // EditText editTextStoreEditText = findViewById(R.id.editTextStore);

        String name = storeTextView.getText().toString();
        //String name = editTextStoreEditText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Both name and description of the store must be provided.", Toast.LENGTH_LONG);
            return;
        }
       // Store newStore = new Store(name);

        // Add the new game to the database to ensure it is persisted.
       // allStoresList.add(newStore);
        storeListAdapter.notifyDataSetChanged();
        // Clear all the entries (reset them)
    }

    public void clearAllStore ( View view ) {
        allStoresList.clear();
        // Permanently delete games from the database, buh bye
        storeListAdapter.notifyDataSetChanged();
    }
}
