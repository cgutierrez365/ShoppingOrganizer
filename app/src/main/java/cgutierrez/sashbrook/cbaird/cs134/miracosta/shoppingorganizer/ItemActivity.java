package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
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

        //itemsList = db.

    }

    public void addItem(View v)
    {
        Intent addItemIntent = new Intent(this, AddItemActivity.class);
        startActivityForResult(addItemIntent, UPDATED_LIST_CODE);
    }

    //Will update the listView after user added things to the database
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UPDATED_LIST_CODE)
        {
           //TODO: Get update listview from Chole (should have included it in her onCreate()
        }
    }

}
