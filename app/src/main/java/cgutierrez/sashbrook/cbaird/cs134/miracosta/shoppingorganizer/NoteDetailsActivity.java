package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Notes;

/**
 * Displays a specific note to saved by the user
 */
public class NoteDetailsActivity extends AppCompatActivity {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private DBHelper db;

    private TextView titleEditText;
    private TextView contentsEditText;


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

        //CREATE DATABASE



    }
}
