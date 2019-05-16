package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;


/**
 * Helper class to provide custom adapter for the <code>Location</code> list.
 */
public class StoreAdapter extends ArrayAdapter<Store> {

    private Context mContext;
    private List<Store> mallStoresList = new ArrayList<>();
    private int mResourceId;



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

        ConstraintLayout allStoresListConstraintLayout =
                view.findViewById(R.id.storesListView);  //use view. when adding layout.t

        ImageView allStoresListImageView
                = view.findViewById(R.id.imageView2);

        TextView allStoresListNameTextView =
                view.findViewById(R.id.textView);

        allStoresListConstraintLayout.setTag(selectedLocation);

        allStoresListNameTextView.setText(selectedLocation.getName());

        return view;
    }
}