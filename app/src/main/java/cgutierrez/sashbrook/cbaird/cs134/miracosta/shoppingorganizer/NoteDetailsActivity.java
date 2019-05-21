package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Notes;

/**
 * Displays a specific note to saved by the user
 */
public class NoteDetailsActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private TextView titleTextView;
    private TextView contentsTextView;


    //ON CREATE-------------------------------------------------------------------------------------
    /**
     * Get database information in order to select the given note. Link the variables to the
     * View to those in the Controller.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        //LINK VIEW AND CONTROLLER
        titleTextView = findViewById(R.id.titleTextView);
        contentsTextView = findViewById(R.id.contentsTextView);

        //RETRIEVE SENT ITEM
        Intent detailsIntent = getIntent();
        Notes note = detailsIntent.getParcelableExtra("SelectedNote");

        titleTextView.setText(note.getNoteTitle());
        contentsTextView.setText(note.getNoteContents());
    }

    public void deleteNote(View v)
    {

    }

    public void finishActivity(View v)
    {
        this.finish();

    }
}
