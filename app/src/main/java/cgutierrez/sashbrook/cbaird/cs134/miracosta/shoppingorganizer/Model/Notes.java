package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Notes class holds custom note attributes of user entries
 * @Author Clarissa Gutierrez
 */
public class Notes implements Parcelable
{
    private int id;
    private String noteTitle;
    private String noteContents;

    /**
     * Default constructor initializes member variables to empty strings
     */
    public Notes()
    {
        id = -1;
        noteTitle = "";
        noteContents = "";
    }

    /**
     * Constructor initializes title and contents to arguments
     * @param noteTitle the title
     * @param noteContents the contents
     */
    public Notes(int id, String noteTitle, String noteContents) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContents = noteContents;
    }

    /**
     * Gets the id of the Note item
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the id of the note
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the Note Title
     * @return noteTitle
     */
    public String getNoteTitle() {
        return noteTitle;
    }

    /**
     * Sets the noteTitle
     * @param noteTitle new title
     */
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    /**
     * Gets the note contents
     * @return noteContents
     */
    public String getNoteContents() {
        return noteContents;
    }

    /**
     * Sets the note contents
     * @param noteContents new contents
     */
    public void setNoteContents(String noteContents) {
        this.noteContents = noteContents;
    }

    /**
     * Describes the contents (unused)
     * @return int
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes the Notes class fields of an object to a Parcel
     * @param dest what will go in the parcel
     * @param flags integer flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteTitle);
        dest.writeString(noteContents);
    }

    /**
     * Creates a Notes object and Array given a Parcel with Notes object data
     */
    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        /**
         * Creates the Notes object using a parcel
         * @param in the parcel
         * @return a new Notes object
         */
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        /**
         * Creates an array of notes given a size (unused)
         * @param size the size
         * @return a new Notes array
         */
        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    /**
     * Constructor creates Notes object from parcel object
     * @param parcel the parcel
     */
    //PARCEL CONTROLLER
    private Notes(Parcel parcel)
    {
        noteTitle = parcel.readString();
        noteContents = parcel.readString();
    }

}
