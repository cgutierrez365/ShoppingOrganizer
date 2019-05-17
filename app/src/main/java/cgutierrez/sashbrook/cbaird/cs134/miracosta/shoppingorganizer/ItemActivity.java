package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

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
 *
 */

public class ItemActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    /**
     * A list of items extracted to and changed from the database
     */
    private static final int UPDATED_LIST_CODE = 101;
    private static final String TAG = ItemActivity.class.getSimpleName();

    private DBHelper db;
    private List<Items> itemsList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;
    private Items newItem;


    /**
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //ESTABLISH DATABASE
        db = new DBHelper(this);
        itemsList = db.getAllItems();

        //INFLATE CUSTOM VIEW
        itemListAdapter = new ItemListAdapter(this, R.layout.custom_item, itemsList);
        itemListView = findViewById(R.id.itemsListView);
        itemListView.setAdapter(itemListAdapter);

    }

    public void addItem(View v)
    {
        //Intent addItemIntent = new Intent(Intent.)
        Intent addItemIntent = new Intent(this, AddItemActivity.class);
        startActivityForResult(addItemIntent, UPDATED_LIST_CODE);
    }

    //Will update the listView after user added things to the database
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UPDATED_LIST_CODE)
        {
            //TODO: get parcelable intent -- Pet selectedPet = detailsIntent.getParcelableExtra("SelectedPet");

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
         //       db.addItem(newItem); (Already added to the database in AddItemActivity)
                itemListAdapter.add(newItem);
                itemListAdapter.notifyDataSetChanged();
         //   }

        }
    }

}
