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
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Items;

public class ItemListAdapter extends ArrayAdapter<Items> {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private Context mContext;
    private List<Items> mItemsList = new ArrayList<>();
    private int mResourceId;


    //CONSTRUCTORS----------------------------------------------------------------------------------
    public ItemListAdapter(Context c, int rId, List<Items> items) {

        super(c, rId, items);

        mContext = c;
        mResourceId = rId;
        mItemsList = items;

    }


    //GET VIEW ASSOCIATED WITH LAYOUT
    public View getView(int pos, View convertView, ViewGroup parent)
    {

        final Items selectedItem = mItemsList.get(pos);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout customItemLinearLayout = view.findViewById(R.id.customItemLinearLayout);
        ImageView itemImageView = view.findViewById(R.id.itemImageView);
        TextView itemNameTextView = view.findViewById(R.id.itemNameTextView);
        TextView qtyTextView = view.findViewById(R.id.qtyTextView);

        customItemLinearLayout.setTag(selectedItem);

        itemNameTextView.setText(selectedItem.getStoreName());
        qtyTextView.setText(selectedItem.getItemQuantity());

        try
        {
            itemImageView.setImageURI(Uri.parse(selectedItem.getImageURI()));
        }
        catch (Exception e)
        {
            Log.e("Shopping Organizer", "Error loading " + selectedItem.getImageURI(), e);
        }

        return view;

    }


}