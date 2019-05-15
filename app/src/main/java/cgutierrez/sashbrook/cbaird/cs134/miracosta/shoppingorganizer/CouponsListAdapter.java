package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;

public class CouponsListAdapter extends ArrayAdapter<Coupons> {
//working on adapter 9:03pm 04/13/2019

//    //INSTANCE VARIABLES----------------------------------------------------------------------------
//    private long mId;
//    private String mImageURI;
//    private String mExpirationDate;
//    private String mIsFavorite;
//    private String mAdditionalNotes;
//    public ArrayList<Coupons> mCoupons;
//    //private String mImageURI;

    private Context mContext;
    private List<Coupons> mallCouponsList;
    private int mResourceId;

    //called from Main Activity
    /**
     * Creates a new <code>GameListAdapter</code> given a mContext, resource id and list of games.
     *
     * @param context The mContext for which the adapter is being used (typically an activity)
     * @param resourceId The resource id (typically the layout file name)
     * @param  objects The list of games to display
     */
    public CouponsListAdapter ( @NonNull Context context, int resourceId, @NonNull List<Coupons> objects) {
        super(context, resourceId, objects);
        mContext = context;
        mResourceId = resourceId;
        mallCouponsList = objects;
    }

//    INTEGER PRIMARY KEY, "
//            + FIELD_COUPON_IMAGE + " BLOB, "
//            + FIELD_EXPIRATION_DATE + "TEXT, "
//            + FIELD_IS_FAVORITE + " REAL, "
//            + FIELD_ADDITIONAL_NOTES + " TEXT"

    /**
     * Gets the view associated with the layout.
     * @param position The position of the Game selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView( int position, View convertView, @NonNull ViewGroup parent)
    {
        final Coupons selectedCoupons = mallCouponsList.get(position);
        //ImageView selectedImageView = (ImageView) selectedPet.getSelectedView();
        //Pet petDetails;

        //Done: CODE FOR GETVIEW, TO INFLATE THE VIEW(PET_LIST_ITEM) WITH INFORMATION
        //ABOUT THE PET. THEIMAGEVIEW SHOULD DISPLAY THE CORRECT IMAGE USING THE SETIMAGEURI()
        /** Manually inflate the custom layout */
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        /** Tell the inflater to inflate pet_list_item.xml */
        View view = inflater.inflate(mResourceId, null);
        //DONE:// SHOULD DISPLAY THE CORRECT IMAGE USING THE SETIMAGEURI() METHOD(NOT ASSETMANAGER). THE TWO
        //DONE:// TEXTVIEW (FOR NAME AND DETAILS) SHOULD BE INFLATED WITH THE CORRECT INFORMATION ABOUT THE PET.
        //use ALOHA MUSIC IS NEEDED for Uri & Buttons

        String uri ="android.resource://edu.miracostacollege.cs134.capstonelayouts/drawable/none";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mContext.startActivity(intent);

        LinearLayout CouponsLinearLayout =
                (LinearLayout) view.findViewById(R.id.CouponsLinearLayout);
        ImageView CouponImageView =
                (ImageView) view.findViewById(R.id.CouponImageView);
        TextView CouponExpirationTextView =
                (TextView) view.findViewById(R.id.CouponExpirationTextView);
        //TextView petListDescriptionTextView =
        //(TextView) view.findViewById(R.id.petListDescriptionTextView);
        //TextView petListPhoneTextView =
        //TextView) view.findViewById(R.id.petListPhoneTextView);
        /** put information into textviews and image view //Set Tag */
        //DONE: ENSURE THE TAG PROPERTY OF THE PETLISTLINEARLAYOUT(MAIN LAYOUT FOR PET_LIST_ITEM) IS SET TO BE THE SELECTED PET,
        //DONE: THIS WILL FACILITATE THE VIEWPETDETAILS (pub void ViewPetDetails) METHOD OF PETLISTACTIVITY.JAVA(MAINACTIVITY)
        //
        CouponsLinearLayout.setTag(selectedCoupons);
        CouponExpirationTextView.setText(selectedCoupons.getExpirationDate());
        //petListDescriptionTextView.setText(selectedPet.getDescription());
        // petListPhoneTextView.setText(selectedPet.getPhone());


        //refer to Aloha Music for AssetManager replacement.

        /** return view inflated with all information */
        return view;


    }

    public int getItemCount() {
        return mallCouponsList.size();
    }

}