package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;

/**
 * CouponListAdapter, ArrayAdapter to get information around from the Database
 * and to inflate the view of the ListView for Coupons.
 * Work with Model Coupons through DBHelper to run AddCoupon and CouponActivity, with
 * CouponDetailActivity as the last connection.
 * @author Stacey Ashbrook
 */
public class CouponsListAdapter extends ArrayAdapter<Coupons> {


//    //INSTANCE VARIABLES----------------------------------------------------------------------------
//    private long mId;
//    private String mImageURI;
//    private String mExpirationDate;
//    private String mIsFavorite;
//    private String mAdditionalNotes;
//    public ArrayList<Coupons> mCoupons;
//    //private String mImageURI;

    private Context mContext;
    private List<Coupons> mallCouponsList = new ArrayList<>();
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

         /** Manually inflate the custom layout */
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        /** Tell the inflater to inflate pet_list_item.xml */
        View view = inflater.inflate(mResourceId, null);

         /** ADD COUPON
         */
        LinearLayout CouponsLinearLayout =
              (LinearLayout) view.findViewById(R.id.CouponsLinearLayout);
        ImageView CouponImageView =
                (ImageView) view.findViewById(R.id.CouponImageView);
        TextView CouponExpirationTextView =
                (TextView) view.findViewById(R.id.CouponExpirationTextView);


        /** COUPON ACTIVITY TO DETAILS */

        LinearLayout allCouponListLinearLayout = (LinearLayout) view.findViewById(R.id.allCouponListLinearLayout);
        TextView CouponIsFavoriteTextView =
                (TextView) view.findViewById(R.id.IsFavorite);
        TextView CouponExpirationDateTextView =
                (TextView) view.findViewById(R.id.ExpireDate);
        TextView CouponAdditionalNotesTextView =
                (TextView) view.findViewById(R.id.AddNotes);
        ImageView COUPONImageCoupon = view.findViewById(R.id.Image_Coupon);

        allCouponListLinearLayout.setTag(selectedCoupons);
        COUPONImageCoupon.setImageURI(Uri.parse("images.jpg"));

        CouponIsFavoriteTextView.setText(selectedCoupons.getIsFavorite());
        CouponExpirationDateTextView.setText(selectedCoupons.getExpirationDate());
        CouponAdditionalNotesTextView.setText(selectedCoupons.getAdditionalNotes());

        AssetManager am = mContext.getAssets();
        try {
            COUPONImageCoupon.setImageURI(Uri.parse(selectedCoupons.getImageURI()));
            InputStream stream = am.open(selectedCoupons.getImageURI());
            Drawable event = Drawable.createFromStream(stream, selectedCoupons.getExpirationDate());
            COUPONImageCoupon.setImageDrawable(event);
        }
        catch (IOException ex)
        {
            Log.e("Coupons Select", "Error loading " + selectedCoupons.getImageURI(), ex);
        }


        /** put information into textviews and image view //Set Tag */

        /** return view inflated with all information */
        return view;


    }

    public int getItemCount() {
        return mallCouponsList.size();
    }

}
