package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Notes;

/**
 * Notes List Adapter inflates the Listview in activity_main_menu.xml with the note_list_item.xml
 * @Author Clarissa Gutierrez
 */
public class NotesListAdapter extends ArrayAdapter<Notes> {


    private Context mContext;
    private List<Notes> mNotesList;
    private int mResourceId;

    /**
     * Constructor creates an adapter with the given context, id, and List of Notes objects
     * @param c Context
     * @param rId id
     * @param notes notes
     */
    public NotesListAdapter(Context c, int rId, List<Notes> notes) {

        super(c, rId, notes);

        mContext = c;
        mResourceId = rId;
        mNotesList = notes;

    }

    /**
     * Gets the view to be inflated
     * @param pos position
     * @param convertView converted view
     * @param parent parent
     * @return inflated View
     */
    public View getView(int pos, View convertView, ViewGroup parent) {

        final Notes selectedNote = mNotesList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //Connect XML
        ConstraintLayout noteListConstraintLayout = view.findViewById(R.id.noteListConstraintLayout);
        TextView noteTitleTextView = view.findViewById(R.id.noteTitleTextView);
        TextView notePreviewTextView = view.findViewById(R.id.notePreviewTextView);

        noteListConstraintLayout.setTag(selectedNote);

        noteTitleTextView.setText(selectedNote.getNoteTitle());
        notePreviewTextView.setText(selectedNote.getNoteContents());

        return view;

    }
}