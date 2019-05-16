package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {

    /**
     * The <code>Location</code> class represents a place where one can get a caffeine fix, including
     * its name, address, phone number and latitude/longitude location.
     */

    private long id;
    private String mName;
    private String mLocation;
    private double mLatitude;
    private double mLongitude;

    public Store()
    {
        id = -1;
        mName = "";
        mLocation = "";
        mLatitude = 0.0;
        mLongitude = 0.0;
    }

    public Store(long mID, String name, String location, double latitude, double longitude) {
        id = mID;
        mName = name;
        mLocation = location;
        mLatitude = latitude;
        mLongitude = longitude;
    }

//    public Store(String name, String location) {
//        this(name, location);
//    }

    public Store ( Parcel in ) {
        mName = in.readString();
        mLocation = in.readString();
    }

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



    public String getFullAddress()
    {
        return mName + "\n" + mLocation + ", ";
    }


    @Override
    public String toString() {
        return "Store{" +
                "Name=" + mName +
                ", Location='" + mLocation + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return -1;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mLocation);
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

