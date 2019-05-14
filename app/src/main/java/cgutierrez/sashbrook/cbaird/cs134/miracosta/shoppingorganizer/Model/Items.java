package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Pet objects capture information pertaining to one pet
 * @author Chole
 */
public class Items implements Parcelable
{
    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private long mId;
    private String mItemName;
    private String mStoreName;
    private String mStoreLocation;
    private String mItemQuantity;
    private ArrayList<Coupons> mCoupons;
    private String mImageURI;


    //CONSTRUCTORS----------------------------------------------------------------------------------
    public Items() {
        mId = -1;
        mItemName = "";
        mStoreName = "";
        mStoreLocation = "";
        mItemQuantity = "";
        mImageURI = "";
    }


    public Items(long id, String itemName, String storeName, String storeLocation, String itemQuantity, ArrayList<Coupons> coupons, String imageURI) {
        mId = id;
        mItemName = itemName;
        mStoreName = storeName;
        mStoreLocation = storeLocation;
        mItemQuantity = itemQuantity;
        mCoupons = coupons;
        mImageURI = imageURI;
    }


    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    //MUTATORS--------------------------------------------------------------------------------------
    public void setId(long id) { mId = id; }

    public void setItemName(String itemName)
    {
        mItemName = itemName;
    }


    public void setStoreName(String storeName)
    {
        mStoreName = storeName;
    }


    public void setStoreLocation(String storeLocation)
    {
        mStoreLocation = storeLocation;
    }


    public void setItemQuantity(String itemQuantity)
    {
        mItemQuantity = itemQuantity;
    }


    public void setCoupons(ArrayList<Coupons> coupons)
    {
        mCoupons = coupons;
    }


    //ACCESSORS-------------------------------------------------------------------------------------
    public long getId() { return mId; }

    public String getItemName()
    {
        return mItemName;
    }


    public String getStoreName()
    {
        return mStoreName;
    }


    public String getStoreLocation()
    {
        return mStoreLocation;
    }


    public String getItemQuantity()
    {
        return mItemQuantity;
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
        return "Items{" +
                "ID: " + mId + '\'' +
                "Item Name='" + mItemName + '\'' +
                ", Store Name='" + mStoreName + '\'' +
                ", Store Location='" + mStoreLocation + '\'' +
                ", Item Quantity='" + mItemQuantity + '\'' +
                ", Coupons=" + mCoupons +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(getItemName(), items.getItemName()) &&
                Objects.equals(getStoreName(), items.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemName(), getStoreName());
    }


    //IMPLEMENT METHODS-----------------------------------------------------------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(mId);
        dest.writeString(mItemName);
        dest.writeString(mStoreName);
        dest.writeString(mStoreLocation);
        dest.writeString(mItemQuantity);
        dest.writeList(mCoupons);
        dest.writeString(mImageURI);

    }


    //PARCEL CONTROLLER
    private Items(Parcel parcel) {
        mId = parcel.readLong();
        mItemName = parcel.readString();
        mStoreName = parcel.readString();
        mStoreLocation = parcel.readString();
        mItemQuantity = parcel.readString();
        mCoupons = parcel.readArrayList(Items.class.getClassLoader());
        mImageURI = parcel.readString();
    }
}