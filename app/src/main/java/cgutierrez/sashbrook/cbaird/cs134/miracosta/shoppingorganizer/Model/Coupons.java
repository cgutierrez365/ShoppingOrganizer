package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Coupons Model connected to CouponListAdapter, CouponActivity and
 * AddCouponActivity. To be added to: CouponDetailsActivity.
 * The <code>Coupons</code> class maintains information about coupons,
 * * including the_id assigned to it, image of the coupon, the expiration date,
 * * favorite of coupons and any additional notes.
 * * @author: Stacey Ashbrook
 * */

public class Coupons implements Parcelable
{
    /**FOR INFORMATION ON/TO GO TO DATABASE*/

    /**INSTANCE VARIABLES------------------------------------------------------------------------*/
    private long id;
    private String mImageURI;
    private String mExpirationDate;
    private String mIsFavorite;
    private String mAdditionalNotes;


    /**
     * CONSTRUCTORS
     */
    public Coupons ()
    {
        id = -1;
        mImageURI = "";
        mExpirationDate = "";
        mIsFavorite = "";
        mAdditionalNotes = "";
    }

    public Coupons ( long mID, String imageURI, String expirationDate, String isFavorite, String additionalNotes) {
        id = mID;
        mImageURI = imageURI;
        mExpirationDate = expirationDate;
        mIsFavorite = isFavorite;
        mAdditionalNotes = additionalNotes;
    }

    /**PARCEL CONTROLLER*/
    public Coupons ( Parcel in ) {
        id = in.readLong();
        mImageURI = in.readString();
        mExpirationDate = in.readString();
        mIsFavorite = in.readString();
        mAdditionalNotes = in.readString();
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

    /**MUTATORS----------------------------------------------------------------------------------*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


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


    /**
     * Accesses the imageURI of the coupon
     * @return the Uri
     */
    public String getImageURI() {
        return mImageURI;
    }

    /**
     * Assigns the imageURI of the coupon
     * @param imageURI the Uri
     */
    public void setImageURI(String imageURI) {
        mImageURI = imageURI;
    }


    /**HELPER METHODS---------------------------------------------------------------------------**/
    @Override
    public String toString() {
        return "Coupons{" +
                "ID: " + id + '\'' +
                "ImageURI='" + mImageURI + '\'' +
                ", Expiration Date='" + mExpirationDate + '\'' +
                ", Is Favorite='" + mIsFavorite + '\'' +
                ", Additional Notes='" + mAdditionalNotes + '\'' +
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


    /**IMPLEMENT METHODS-----------------------------------------------------------------------**/
    @Override
    public int describeContents() {
        return -1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(mImageURI);
        dest.writeString(mExpirationDate);
        dest.writeString(mIsFavorite);
        dest.writeString(mAdditionalNotes);

    }
}
