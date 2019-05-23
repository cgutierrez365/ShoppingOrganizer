package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;

import static cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.AddCouponActivity.EXTRA_NUMBER;

/**
 * Coupon Activity, interact for the user and a way to bring together the
 * image along with the Notes and access to the Details.
 * @author Stacey Ashbrook
 */


public class CouponActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * Coupons Activity Button
     */
    private Button Collect; //Coupons ONLY
    private CouponsListAdapter couponsListAdapter;
    private static final String TAG = CouponActivity.class.getSimpleName();
    public static final int RESULT_LOAD_IMAGE = 200;
    private static final int REQUEST_GALLERY = 101;

    /**
     * Needed Variables for USer Input
     */
    private ArrayList<String> additionalNotes = new ArrayList<String>();
    private EditText txt;
    private ListView show;
    private List<Coupons> couponsList;
    private ListView couponsListView;
    private static final int UPDATED_LIST_CODE = 131;
    private Coupons newCoupons;
    private DBHelper db;
    private Coupons id;

    /**
     * Here is to put onto the Layout for said "Coupons"
     */
    private Button cancelButton;


    /**
     * Working on DB Helper, DBHELPER COUPON, switched to a similar one like Chloe's for Coupons, Store.
     * NEed to figure out adapter
     *
     * @param savedInstanceState
     */
    //DONE: GOING TO NEED TO ADD A COUPON LIST VIEW FOR A LIST OF COUPONS THAT WILL BE SELECTED
    //DONE: OR INPUT BY USER TO PREVIEW ON A LIST ONCE FINISHED. MASS SCROLL. 4:18PM 5/3/19
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);


        /**
         * This is for getting TEXT from ADDCouponActivity to the COUPON Activity, THIS IS DONE.
         * DOES AS INTENDED, FOR COUPON LIST WITH LIST VIEW, ADD tOO LIST and VIEW Coupons Added to next activity.**
         */
        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importCouponsFromCSV("coupons.csv");

        couponsList = db.getAllCouponsList();

        couponsListAdapter = new CouponsListAdapter(this, R.layout.custom_coupon, couponsList);
        couponsListView = findViewById(R.id.Coupon_Image_ListView);
        couponsListView.setAdapter(couponsListAdapter);

        Intent intent = getIntent();
        int number = intent.getIntExtra(EXTRA_NUMBER, 0);
        /** TextView Details */
        TextView textView1 = (TextView) findViewById(R.id.textview1);

        textView1.setText("" + number);
        /**
         * Get User Input, to allow interaction between application and user.
         */
        txt = (EditText) findViewById(R.id.editTextCoupon); //Needed for User Input, options
        show = (ListView) findViewById(R.id.Coupon_View);
        Collect = (Button) findViewById(R.id.Button_CollectAll);
        Collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = txt.getText().toString();

                if (additionalNotes.contains(getInput)) {
                    Toast.makeText(getBaseContext(), "Coupon Added", Toast.LENGTH_LONG).show();
                } else if (getInput == null || getInput.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "InputField if Empty", Toast.LENGTH_LONG).show();

                } else {
                    additionalNotes.add(getInput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CouponActivity.this, android.R.layout.simple_list_item_1, additionalNotes);
                    show.setAdapter(adapter);
                    ((EditText) findViewById(R.id.editTextCoupon)).setText("");
                }
            }
        });

    }
    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    public void onClick( View view) {

        cancelButton = (Button) findViewById(R.id.cancelCoupons);
        couponsListView = (ListView) findViewById(R.id.Coupon_Image_ListView);
        //show = (ListView) findViewById(R.id.Coupon_View);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Toast.makeText(CouponActivity.this,"Coupons Viewed!",  Toast.LENGTH_SHORT).show();
                couponsListView.deferNotifyDataSetChanged();
                openActivityDetails();
                Toast.makeText(CouponActivity.this, "Coupons the Details", Toast.LENGTH_LONG).show();
            }
        });
        db.close();
    }

    public void viewCouponDetails(View view ) {

        Coupons selectCoupons;
        int position = (int) view.getTag();

        selectCoupons = couponsList.get(position);

        Intent detailsIntent = new Intent(this, StoresActivity.class);
        // commented out & convert to:

        //startActivity(detailsIntent);

        detailsIntent.putExtra("imageURI", selectCoupons.getImageURI());
        detailsIntent.putExtra("expirationDate", selectCoupons.getExpirationDate());
        detailsIntent.putExtra("isFavorite", selectCoupons.getIsFavorite());
        detailsIntent.putExtra("additionalNotes", selectCoupons.getAdditionalNotes());
        detailsIntent.putExtra("Selected Coupon", selectCoupons);


        startActivityForResult(detailsIntent, UPDATED_LIST_CODE);
    }

    public void deleteCoupons(View view) {          //another addition to the app view
       // Grab the contract from the view
        Coupons c = (Coupons) view.getTag();
        db.deleteCoupons(c.getId());

        couponsListAdapter.remove(c);
        // Update the list view
        couponsListAdapter.notifyDataSetChanged();

    }

    /**UPDATE the DATABASE after User Input*/
  //  Will update the listView after user added things to the database
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATED_LIST_CODE) {
            Coupons coupons = new Coupons();
            /// (should have included it in her onCreate()
            Uri couponData = data.getData();
            Cursor cursor = getContentResolver().query(couponData, null, null, null, null);

            if (cursor.moveToFirst()) {
                long _id = cursor.getLong(0); // index column
                String imageURI = cursor.getString(1);
                String expirationDate = cursor.getString(2);
                String isFavorite = cursor.getString(3);
                String additionalNotes = cursor.getString(4);

                coupons.setId(_id);
                coupons.setImageURI(imageURI);
                coupons.setExpirationDate(expirationDate);
                coupons.setIsFavorite(isFavorite);
                coupons.setAdditionalNotes(additionalNotes);
                newCoupons = new Coupons(_id, imageURI, expirationDate, isFavorite, additionalNotes);

                //ADD TO LIST AND DATABASE
                db.addCoupons(newCoupons);
                couponsListAdapter.add(newCoupons);
                couponsListAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * A Go Back Feature
     * Ends AddCouponActivity, thus reverting to the previous activity that called this one
     *
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen(View v) {
        this.finish();
    }




    public void openActivityDetails() {
        Intent intent = new Intent(this, CouponDetailsActivity.class);
        startActivity(intent);
    }
}