package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;

/**
 * Add Note Activity enables user to add a general note reminder
 */
public class AddNoteActivity extends AppCompatActivity {

    private DBHelper db;

    private EditText titleEditText;
    private EditText contentsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //create a new database if one doesn't already exist (using DBHelper constructor)
        db = new DBHelper(this);
        //DONE: write getAllNotes() method as well as a Notes database
//        mNotesList = db.getAllNotes();
//
//        // Inflate the custom list view with any notes existing in the database from a previous session
//        mNotesListAdapter = new NotesListAdapter(this, R.layout.note_list_item, mNotesList);
//        mNotesListView = findViewById(R.id.notesListView);
//        mNotesListView.setAdapter(mNotesListAdapter);
//
//
//        //Done: Connect activity to the xml list view
//        mNotesListView = findViewById(R.id.itemsListView);

    }


}
