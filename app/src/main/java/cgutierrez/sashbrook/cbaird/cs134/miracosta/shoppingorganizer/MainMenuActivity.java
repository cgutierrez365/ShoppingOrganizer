package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Notes;

public class MainMenuActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Notes> mNotesList;
    private NotesListAdapter mNotesListAdapter;
    //xml attributes
    private ListView mNotesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //create a new database if one doesn't already exist (using DBHelper constructor)
        db = new DBHelper(this);
        //TODO: write getAllNotes() method as well as a Notes database
        mNotesList = db.getAllNotes();

        // Inflate the custom list view with any notes existing in the database from a previous session
        mNotesListAdapter = new NotesListAdapter(this, R.layout.note_list_item, mNotesList);
        mNotesListView = findViewById(R.id.notesListView);
        mNotesListView.setAdapter(mNotesListAdapter);


        //Done: Connect activity to the xml list view
        mNotesListView = findViewById(R.id.itemsListView);


    }



}
