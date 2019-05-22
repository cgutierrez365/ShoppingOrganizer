package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Notes;


/**
 * Main Menu displays the main page for the user to navigate to items, coupons, stores, or notes
 * @Author Clarissa Gutierrez
 * @Version 5/22/19
 */
public class MainMenuActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Notes> mNotesList;
    private NotesListAdapter mNotesListAdapter;
    //xml attributes
    private ListView mNotesListView;

    private static final int UPDATED_LIST_CODE = 101;

    /**
     * Creates the database if it doesn't already exist and connects to the activity_main_menu
     * @param savedInstanceState the saved bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //create a new database if one doesn't already exist (using DBHelper constructor)
        db = new DBHelper(this);
        db.importNotesFromCSV("notes.csv");
        mNotesList = db.getAllNotes(); //DONE: write getAllNotes() method as well as a Notes database

        // Inflate the custom list view with any notes existing in the database from a previous session
        mNotesListAdapter = new NotesListAdapter(this, R.layout.note_list_item, mNotesList);
        mNotesListView = findViewById(R.id.notesListView);
        mNotesListView.setAdapter(mNotesListAdapter);


        //Done: Connect activity to the xml list view
        mNotesListView = findViewById(R.id.itemsListView);

    }

    /**
     * links to ItemActivity
     * @param v the Items button
     */
    public void viewItems(View v)
    {
        Intent itemsIntent = new Intent(this, ItemActivity.class);
        startActivity(itemsIntent);
    }

    //TODO: Uncomment the button onClicks when the other classes are added to the project

    /**
     * links to StoreActivity
     * @param v the Stores button
     */
    public void viewStores(View v)
    {
        Intent storeIntent = new Intent(this, StoresActivity.class);
        startActivity(storeIntent);
    }

    /**
     * links to CouponsActivity
     * @param v the coupons button
     */
    public void viewCoupons(View v)
    {
        Intent couponIntent = new Intent(this, CouponActivity.class);
        startActivity(couponIntent);
    }

    /**
     * links to the MapActivity
     * @param v the map button
     */
    public void viewMap(View v)
    {
        Intent mapIntent = new Intent(this, MapActivity.class);
        startActivity(mapIntent);
    }

    /**
     * links to Credits Activity
     * @param v the coupons button
     */
    public void viewCredits(View v)
    {
        Intent creditsIntent = new Intent(this, CreditsActivity.class);
        startActivity(creditsIntent);
    }

    /**
     * links to the NotesActivity
     * @param v the Notes button
     */
    public void addNote(View v)
    {
        Intent noteIntent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(noteIntent, UPDATED_LIST_CODE);

        startActivity(noteIntent);
    }

    /**
     * Updates the Notes List View after a note is added in AddNoteActivity
     * @param requestCode tells onActivityResult which requestCode if block to execute. (In this case, the only option is requestCode == UPDATED_LIST_CODE)
     * @param resultCode the result code
     * @param data The intent (Containing a Note object) returned by the calling activity (AddNotesActivity)
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UPDATED_LIST_CODE)
        {
            if(data == null)
            {
                return;
            }

            Notes newNote = data.getParcelableExtra("");
            db.addNote(newNote); //Already added in AddItemActivity
            mNotesListAdapter.add(newNote);
            mNotesListAdapter.notifyDataSetChanged();
        }


    }


    //NOTE LIST ONCLICK -- Chole did this part
    /**
     * Launches NoteDetailsActivity when user clicks a note item
     * @param v the listView item of a note
     */
    public void viewNoteDetails(View v)
    {
        Notes selectedNote = (Notes) v.getTag();
        Intent notesDetailsIntent = new Intent(this, NoteDetailsActivity.class);

        notesDetailsIntent.putExtra("SelectedNote", selectedNote);

        startActivity(notesDetailsIntent);
    }

}
