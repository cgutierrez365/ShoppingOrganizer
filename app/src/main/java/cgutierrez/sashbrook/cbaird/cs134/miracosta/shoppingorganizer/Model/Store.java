package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {

    /**
     * Author@: Stacey Ashbrook 5/18/19
     * The <code>Store</code> class represents a place where one can get coupon exchanged, including
     * its name, address, latitude/longitude location.
     */

    private long id;
    private String mName;
    private String mLocation;
    private double mLatitude;
    private double mLongitude;
    private String mImageName;


    /**
     * CONSTRUCTORS
     */
    public Store()
    {
        id = -1;
        mName = "";
        mLocation = "";
        mLatitude = 0.0;
        mLongitude = 0.0;
        mImageName = "";
    }

    public Store(long mID, String name, String location, double latitude, double longitude, String imageName) {
        id = mID;
        mName = name;
        mLocation = location;
        mLatitude = latitude;
        mLongitude = longitude;
        mImageName = imageName;
    }

//    public Store(String name, String location, double latitude, double longitude) {
//        this(name, location, latitude, longitude);
//    }

    public Store ( Parcel in ) {
        mName = in.readString();
        mLocation = in.readString();
        mImageName = in.readString();
    }
    /**
     * GETTERS AND SETTERS FOR STORE
     * @return
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public static Creator<Store> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getImageName () {
        return mImageName;
    }

    public void setImageName(String imageName) {
        mImageName = imageName;
    }


    public String getFullAddress()
    {
        return mName + "\n" + mLocation + ", ";
    }


    @Override
    public String toString() {
        return "Store{" +
                "Id=" + id +
                ", Name='" + mName + '\'' +
                ", Location='" + mLocation + '\'' +
                ", ImageName='" + mImageName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return -1;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(mName);
        parcel.writeString(mLocation);
        parcel.writeString(mImageName);
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
}



