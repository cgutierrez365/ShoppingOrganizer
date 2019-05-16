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

import edu.miracosta.cs134.capstonelayouts.Model.Store;

//Did finish StoreAdapter, need to figure out how it is going to be connected to StoresActivity.
//Videos on Youtube.
//8:44PM 5/3/2019
//URL: https://www.youtube.com/watch?v=Nw9JF55LDzE
//URL: https://www.youtube.com/watch?v=IGGT_jfZQrA
//URL: https://www.youtube.com/watch?v=ZXoGG2XTjzU

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
                view.findViewById(R.id.storesListView);  //use view. when adding layout.
        // Works for all on LinearLayout

        ImageView allStoresListImageView
                = view.findViewById(R.id.imageView2);

        TextView allStoresListNameTextView =
                view.findViewById(R.id.textView);
        //TextView locationListAddressTextView =
                //view.findViewById(R.id.);
        //TextView locationListPhoneTextView =
               // view.findViewById(R.id.locationListPhoneTextView);

        allStoresListConstraintLayout.setTag(selectedLocation);

        allStoresListNameTextView.setText(selectedLocation.getName());

        //locationListAddressTextView.setText(selectedLocation.getFullAddress());
        //locationListPhoneTextView.setText(selectedLocation.getPhone());


//        AssetManager am = mContext.getAssets();
//        try {
//            InputStream stream = am.open(selectedLocation.getImageName());
//            Drawable event = Drawable.createFromStream(stream, selectedLocation.getName());
//            allStoresListImageView.setImageDrawable(event);
//        }
//        catch (IOException ex)
//        {
//            Log.e("Stores Select", "Error loading " + selectedLocation.getImageName(), ex);
//        }

        return view;
    }
}


//
//public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>{
//
//    private String[] data;
//
//    public StoreAdapter(String[] data)
//    {
//        this.data = data;
//
//    }
//
//    @NonNull
//    @Override
//    public StoreAdapter.StoreViewHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup, int i ) {
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
//        View view = inflater.inflate(R.layout.custom_store, viewGroup, false);
//        return new StoreViewHolder(view);
//        //return null;
//    }
//
//    @Override
//    public void onBindViewHolder ( @NonNull StoreAdapter.StoreViewHolder storeViewHolder, int i ) {
//        String title = data[i];
//        storeViewHolder.txtView.setText(title);
//
//    }
//
//    @Override
//    public int getItemCount () {
//        return data.length;
//    }
//
//    public class StoreViewHolder extends RecyclerView.ViewHolder {
//       ImageView imgView;
//       TextView txtView;
//
//        public StoreViewHolder ( View itemView ) {
//            super(itemView);
//            imgView = (ImageView) itemView.findViewById(R.id.imageView2);
//            txtView = (TextView) itemView.findViewById(R.id.textView);
//        }
//    }
//
//}