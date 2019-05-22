package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Notes;

/**
 * Add Note Activity enables user to add a general note reminder
 * @Author Chloe Baird
 */
public class AddNoteActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private DBHelper db;

    private EditText titleEditText;
    private EditText contentsEditText;


    //ONCREATE--------------------------------------------------------------------------------------
    /**
     * On Create associates AddNoteActivity.java with associated file activity_add_note.xml and inflates the
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.titleEditText);
        contentsEditText = findViewById(R.id.contentsEditText);

        //create a new database if one doesn't already exist (using DBHelper constructor)
        db = new DBHelper(this);

        //Connect activity to the xml list view
        titleEditText = findViewById(R.id.titleEditText);
        contentsEditText = findViewById(R.id.contentsEditText);
    }


    //OTHER METHODS---------------------------------------------------------------------------------
    /**
     * Obtains title and note entered by the user, then adds it to the database.
     * @param v
     */
    public void addNote(View v)
    {
        //CAPTURE FIELDS ADDED BY USER
        String title = titleEditText.getText().toString();
        String contents = contentsEditText.getText().toString();

        //GIVE MESSAGE IF FORM IS NOT COMPLETE
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(contents))
        {
            Toast.makeText(this, "All fields must be provided!", Toast.LENGTH_LONG).show();
            return;
        }

        //ADD NOTE TO DATABASE
        Notes newNote = new Notes(-1, title, contents);
        db.addNote(newNote);

        //RESET DATA FIELDS
        titleEditText.setText("");
        contentsEditText.setText("");

        Toast.makeText(this, "Note successfully added!", Toast.LENGTH_LONG).show();

        //TODO: Send Intent back to MainMenuActivity
        Intent backToMenuActivityIntent = new Intent();
        backToMenuActivityIntent.putExtra("newNote", newNote);
        setResult(Activity.RESULT_OK, backToMenuActivityIntent); //Sets the Intent field in onActivityResult to this intent

        finish();

    }

    public void revertToPreviousScreen(View v)
    {
        this.finish();
    }

}
