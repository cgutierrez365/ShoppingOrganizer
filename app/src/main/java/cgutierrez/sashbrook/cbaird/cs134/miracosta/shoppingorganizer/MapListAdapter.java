package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
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

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Store;

public class MapListAdapter extends ArrayAdapter<Store>
{
    private Context mContext;
    private List<Store> mMapList;
    private int mResourceId;



    /**
     * Creates a new <code>MapListAdapter</code> given a mContext, resource id and list of locations.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param mapList The list of locations to display
     */
    public MapListAdapter(Context c, int rId, List<Store> mapList) {
        super(c, rId, mapList);
        mContext = c;
        mResourceId = rId;
        mMapList = mapList;
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
        final Store selectedLocation = mMapList.get(pos);


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout locationListLinearLayout =
                view.findViewById(R.id.mapListLinearLayout);

        TextView mapListNameTextView =
                view.findViewById(R.id.mapListNameTextView);
        TextView mapListAddressTextView =
                view.findViewById(R.id.mapListAddressTextView);

        locationListLinearLayout.setTag(selectedLocation);

        mapListNameTextView.setText(selectedLocation.getName());
        mapListAddressTextView.setText(selectedLocation.getLocation());

        return view;
    }
}
