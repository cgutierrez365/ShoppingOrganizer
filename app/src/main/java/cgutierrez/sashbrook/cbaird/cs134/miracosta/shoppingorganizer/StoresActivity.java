package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import edu.miracosta.cs134.capstonelayouts.Model.DBHelper;
import edu.miracosta.cs134.capstonelayouts.Model.Store;

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

        // Adjusted the ListView in for RecyclerView, cannot use Recyclerview without LinearLayout
        // and thus needs a layout manager to go with.


    /**
     * Member variables
     */

    private Button addStore;
    //private View RecyclerView;
    private TextView StoreNameTextView;
    private ImageView StoreFrontView;
    private int STORES_KEY_FIELD_ID;
    private Text FIELD_STORE_NAME;
    private int FIELD_NUMBER_OF_ITEMS;
    private int FIELD_NUMBER_OF_COUPONS;
    private Text FIELD_LOCATION;

    private DBHelper db;
    private List<Store> allStoresList;
    private ListView storesListView;
    private StoreAdapter StoreAdapter;
   // private RecyclerView recyclerView;


    /**
     * Constructor class
     */
    public StoresActivity () {
    }

    /**
     * Created in application
     * @param savedInstanceState
     */

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importStoresFromCSV("coupons.csv");

        //android.support.v7.widget.RecyclerView Custom_Store = (RecyclerView) findViewById(R.id.parent_layout);
        //CardView StoreFace = (CardView) findViewById(R.id.parent_layout);

        allStoresList = db.getallStoresList();
        storesListView = findViewById(R.id.storesListView);
        StoreAdapter = new StoreAdapter(this, R.layout.custom_store, allStoresList);
        storesListView.setAdapter(StoreAdapter);





    }

    /**
     * Code to run, alongside or in app.
     */
//    public void viewStoreDetails(View view) {
//        Store selectedStore = (Store) view.getTag();
//
//        Intent detailsIntent = new Intent(this, MainActivity.class);
//        //detailsIntent.putExtra("Name", selectedGame.getName());
//        //detailsIntent.putExtra("Description", selectedGame.getDescription());
//        // detailsIntent.putExtra("Rating", selectedGame.getRating());
//        // detailsIntent.putExtra("ImageName", selectedGame.getImageName());
//        // commented out & convert to:
//        detailsIntent.putExtra("SelectedStore", selectedStore);
//
//        startActivity(detailsIntent);
//    }
//    public void addStore(View view) {
//
//        EditText nameEditText = findViewById(R.id.nameEditText);
//        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
//
//        String name = nameEditText.getText().toString();
//        String description = descriptionEditText.getText().toString();
//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description)) {
//            Toast.makeText(this, "Both name and description of the store must be provided.", Toast.LENGTH_LONG);
//            return;
//        }
//        Store newStore = new Store(name, description);
//
//        // Add the new game to the database to ensure it is persisted.
//        db.addStore(newStore);
//        StoreAdapter.add(newStore);
//        // Clear all the entries (reset them)
//        nameEditText.setText("");
//        descriptionEditText.setText("");
//    }
//
//    public void clearAllStore(View view)
//    {
//        allStoresList.clear();
//        // Permanently delete games from the database, buh bye
//        db.deleteAllStores();
//        StoreAdapter.notifyDataSetChanged();
//    }

}



//RecyclerView recyclerView = findViewById(R.id.recyclerView);
//StoreAdapter adapter = new StoreAdapter(this, R.layout.custom_store, allStoresList);
// Custom_Store.setAdapter(StoreAdapter);


//ex: locationListAdapter = new LocationListAdapter(this, R.layout.location_list_item, allLocationsList);
//locationsListView.setAdapter(locationListAdapter);

// String[] stores = ["Home Depot", "Costco", "Grocery Outlet"];
//StoreAdapter adapter = new StoreAdapter(new StoreAdapter(stores));
//StoreAdapter adapter = new StoreAdapter(new StoreAdapter(stores));
//Custom_Store.setAdapter(adapter);
//Custom_Store.setLayoutManager(new LinearLayoutManager(this));
// TODO:  Connect the list adapter with the list
//StoreAdapter = new StoreAdapter(this, R.layout.activity_add_store, allStoresList);  //OR selectable list on layout?

// TODO:  Set the list view to use the list adapter
//RecyclerView = findViewById(R.id.recyclerView);
//recyclerView.setAdapter(StoreAdapter);
