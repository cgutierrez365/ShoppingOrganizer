package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {

    /**
     * The <code>Location</code> class represents a place where one can get a caffeine fix, including
     * its name, address, phone number and latitude/longitude location.
     */

    private String mName;
    private String mLocation;


    public Store(String name, String location) {
        mName = name;
        mLocation = location;

    }

//    public Store(String name, String location) {
//        this(name, location);
//    }

    public Store ( Parcel in ) {
        mName = in.readString();
        mLocation = in.readString();
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

