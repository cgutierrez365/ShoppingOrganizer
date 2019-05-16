package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Class not in use yet, so greyed out until associated...? Work to do, not finished 5/3/19 @ 3:35pm
 */
public class Coupons implements Parcelable
{

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private long mId;
    private String mImageURI;
    private String mExpirationDate;
    private String mIsFavorite;
    private String mAdditionalNotes;
    public ArrayList<Coupons> mCoupons;
    //private String mImageURI;


    //CONSTRUCTORS----------------------------------------------------------------------------------
    public Coupons()
    {
        mId = -1;
        mImageURI = "";
        mExpirationDate = "";
        mIsFavorite = "";
        mAdditionalNotes = "";
        //mImageURI = "";
    }

    public Coupons ( long id, String imageURI, String expirationDate, String isFavorite, String additionalNotes, String addArray ) {
        mId = -1;
        mImageURI = "";
        mExpirationDate = "";
        mIsFavorite = "";
        mAdditionalNotes = "";
        //mImageURI = "";
    }


    public Coupons ( long id, String imageURI, String expirationDate, String isFavorite, String additionalNotes, ArrayList<Coupons> coupons) {
        mId = id;
        mImageURI = imageURI;
        mExpirationDate = expirationDate;
        mIsFavorite = isFavorite;
        mAdditionalNotes = additionalNotes;
        mCoupons = coupons;
        // mImageURI = imageURI;
    }


    public static final Creator<Coupons> CREATOR = new Creator<Coupons>() {
        @Override
        public Coupons createFromParcel( Parcel in) {
            return new Coupons(in);
        }

        @Override
        public Coupons[] newArray(int size) {
            return new Coupons[size];
        }
    };

    //MUTATORS--------------------------------------------------------------------------------------
    public void setId(long id) { mId = id; }

    //  public void setImageURI(String imageURI)
    // {
    //     mImageURI = itemName;
    //}


    public void setExpirationDate(String expirationDate)
    {
        mExpirationDate = expirationDate;
    }


    public void setIsFavorite(String isFavorite)
    {
        mIsFavorite = isFavorite;
    }


    public void setAdditionalNotes(String additionalNotes)
    {
        mAdditionalNotes = additionalNotes;
    }


    public void setCoupons(ArrayList<Coupons> coupons)
    {
        mCoupons = coupons;
    }


    //ACCESSORS-------------------------------------------------------------------------------------
    public long getId() { return mId; }

    // public String getImageURI()
    // {
    //     return mImageURI;
    //}


    public String getExpirationDate()
    {
        return mExpirationDate;
    }


    public String getIsFavorite()
    {
        return mIsFavorite;
    }


    public String getAdditionalNotes()
    {
        return mAdditionalNotes;
    }


    public ArrayList<Coupons> getCoupons()
    {
        return mCoupons;
    }

    /**
     * Accesses the imageURI of the pet
     * @return the Uri
     */
    public String getImageURI() {
        return mImageURI;
    }

    /**
     * Assigns the imageURI of the pet
     * @param imageURI the Uri
     */
    public void setImageURI(String imageURI) {
        mImageURI = imageURI;
    }


    //HELPER METHODS--------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Coupons{" +
                "ID: " + mId + '\'' +
                "ImageURI='" + mImageURI + '\'' +
                ", Expiration Date='" + mExpirationDate + '\'' +
                ", Is Favorite='" + mIsFavorite + '\'' +
                ", Additional Notes='" + mAdditionalNotes + '\'' +
                ", Coupons=" + mCoupons +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupons coupons = (Coupons) o;
        return Objects.equals(getImageURI(), coupons.getExpirationDate()) &&
                Objects.equals(getIsFavorite(), coupons.getAdditionalNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImageURI(), getExpirationDate());
    }


    //IMPLEMENT METHODS-----------------------------------------------------------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(mId);
        dest.writeString(mImageURI);
        dest.writeString(mExpirationDate);
        dest.writeString(mIsFavorite);
        dest.writeString(mAdditionalNotes);
        dest.writeList(mCoupons);

    }


    //PARCEL CONTROLLER
    private Coupons(Parcel parcel) {
        mId = parcel.readLong();
        mImageURI = parcel.readString();
        mExpirationDate = parcel.readString();
        mIsFavorite = parcel.readString();
        mAdditionalNotes = parcel.readString();
        mCoupons = parcel.readArrayList(Coupons.class.getClassLoader());
        // mImageURI = parcel.readString();
    }
}