package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ItemActivity extends AppCompatActivity {

    private static final int updatedListCode = 101;

    /**
     * Chloe is in charge of this activity.# # # # # # # # ##
     * #
     * #
     * #
     * #
     * #
     * #
     * #
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }

    public void addItem(View v)
    {
        Intent addItemIntent = new Intent(this, AddItemActivity.class);
        startActivityForResult(addItemIntent, updatedListCode);
    }

    //Will update the listView after user added things to the database
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == updatedListCode)
        {
           //TODO: Get update listview from Chole (should have included it in her onCreate()
        }
    }

}
