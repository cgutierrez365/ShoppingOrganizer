package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;

/**
 * Helper class to provide custom adapter for the <code>Store</code> list and to
 * inflate the ListView between StoresActivity, StoreDetailsActivity and AddStoreActivity.
 * @author Stacey Ashbrook
 */
public class StoreAdapter extends ArrayAdapter<Store> {

    private Context mContext;
    private List<Store> mallStoresList = new ArrayList<>();
    private int mResourceId;
    private ListView storeListView;



    /**
     * Creates a new <code>LocationsListAdapter</code> given a mContext, resource id and list of locations.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param locations The list of locations to display
     */
    public StoreAdapter(Context c, int rId, List<Store> locations) {
        super(c, rId, locations);
        mContext = c;
        mResourceId = rId;
        mallStoresList = locations;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Location selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final Store selectedLocation = mallStoresList.get(pos);


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout allStoresListLinearLayout = view.findViewById(R.id.allStoresListLinearLayout);

        ImageView allStoresListImageView = view.findViewById(R.id.allStoresListImageView);

        ListView storeListView = view.findViewById(R.id.storesListView);

        TextView allStoresListNameTextView =
                view.findViewById(R.id.NameStorestextView);
        TextView allStoresListLocationTextView =
                view.findViewById(R.id.LocationTextView);

        allStoresListLinearLayout.setTag(selectedLocation);
        allStoresListImageView.setImageURI(Uri.parse("images.jpg"));
        allStoresListNameTextView.setText(selectedLocation.getFullAddress());
        allStoresListLocationTextView.setText(selectedLocation.getLocation());
        allStoresListNameTextView.setText(selectedLocation.getName());


        AssetManager am = mContext.getAssets();
        try {
              allStoresListImageView.setImageURI(Uri.parse(selectedLocation.getImageName()));
             // InputStream stream = am.open(selectedLocation.getImageName(), mResourceId);
             // Drawable event = Drawable.createFromStream(stream, selectedLocation.getName());
             // allStoresListImageView.setImageDrawable(event);

        }
        catch (Exception e)
        {
            Log.e("Stores Select", "Error loading " + selectedLocation.getImageName(), e);
        }

        return view;
    }



}