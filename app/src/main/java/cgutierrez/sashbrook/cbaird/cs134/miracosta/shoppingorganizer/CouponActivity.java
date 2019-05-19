package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.database.Cursor;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;

import static cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.AddCouponActivity.EXTRA_NUMBER;

//import edu.miracosta.cs134.capstonelayouts.Model.DBHelperCoupon;

public class CouponActivity extends AppCompatActivity {


    /**
     * Coupons Activity Button
     */
    private Button Collect; //Coupons ONLY
    private TextView CouponExpiration; //AddCouponActivity
    private ImageView Coupon; //AddCouponActivity
    private EditText editText1; //AddCouponActivity
    private ImageView ThemePicture; //AddCouponActivity
    private int COUPON_KEY_FIELD_ID; //CouponID
    private Blob FIELD_COUPON_IMAGE; //DBHelper CouponID
    private Text FIELD_EXPIRATION_DATE;//DB Helper CouponID
    private UCharacter.NumericType FIELD_IS_FAVORITE; //DBHElper Coupons ID
    private Text FIELD_ADDITIONAL_NOTES; //DBHelper Coupons ID

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
    /**
     * Here is to put onto the Layout for said "Coupons"
     */
   // private DBHelperCoupon db;
   // private ArrayAdapter<Coupons> allCouponsList;
    ; // Need a list view for AddCouponActivity.
    // private CouponsListAdapter couponsListAdapter; //careful for coupon and coupons
    private Button addCoupon;  //AddCouponActivity


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
        //String text = intent.getStringExtra(AddCouponActivity.EXTRA_TEXT);
        int number = intent.getIntExtra(EXTRA_NUMBER, 0);
        //String text = intent.getStringExtra(EXTRA_TEXT);

        TextView textView1 = (TextView) findViewById(R.id.textview1);
        // TextView textView2 = (TextView) findViewById(R.id.textview2);

        textView1.setText("" + number);
        //textView1.setText("" + text);

        /**
         * USER INPUT for COUPON ACTIVITY, This is DONE
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
//    // DONE: Implement the view store details using an Intent
//    public void viewCouponsDetails ( View view ) {
//
//        Coupons selectedCoupon = (Coupons) view.getTag();
//
//        Intent couponsIntent = new Intent(this, CouponDetailsActivity.class);
//        //detailsIntent.putExtra("Name", selectedGame.getName());
//        //detailsIntent.putExtra("Description", selectedGame.getDescription());
//        // detailsIntent.putExtra("Rating", selectedGame.getRating());
//        // detailsIntent.putExtra("ImageName", selectedGame.getImageName());
//        // commented out & convert to:
//        couponsIntent.putExtra("SelectedCoupon", selectedCoupon);
//
//        startActivity(couponsIntent);
//    }


//    public void addCoupons(View view) {            //to add an activity into view in the app
//
//        // Implicit intent (normally we do explicit)
//        Button addCoupons = (Button) findViewById(R.id.addCoupons);
//        couponsListView = (ListView) findViewById(R.id.storesListView);
//        //show = (ListView) findViewById(R.id.Coupon_View);
//        addCoupons.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick ( View v ) {
//                Toast.makeText(CouponActivity.this,"Adding",  Toast.LENGTH_SHORT).show();
//                couponsListView.deferNotifyDataSetChanged();
//                openActivityDetails();
//                Toast.makeText(CouponActivity.this, "Coupons the Details", Toast.LENGTH_LONG).show();
//            }
//        });
////        Intent couponsIntent = new Intent(Intent.ACTION_PICK,
////                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
////        // Works for any kind of intent
////        startActivityForResult(couponsIntent,101);
////        Intent addItemIntent = new Intent(this, CouponActivity.class);
////        startActivityForResult(addItemIntent, UPDATED_LIST_CODE);
//
//    }

    public void deleteCoupons(View view) {          //another addition to the app view
       // Grab the contract from the view
        Coupons c = (Coupons) view.getTag();
        db.deleteCoupons(c.getId());
        couponsListAdapter.remove(c);
        // Update the list view
        couponsListAdapter.notifyDataSetChanged();

    }
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
                //*******REFERENCES

                //                    public Coupons ( long id, String imageURI, String expirationDate, String isFavorite, String additionalNotes, ArrayList<Coupons> coupons) {
//                    mId = id;
//                    mImageURI = imageURI;
//                    mExpirationDate = expirationDate;
//                    mIsFavorite = isFavorite;
//                    mAdditionalNotes = additionalNotes;
//                    mCoupons = coupons;
//                    // mImageURI = imageURI;
//                }

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



    private void setListAdapter(CouponsListAdapter couponsListAdapter) {
        couponsListView.getTag();
    }

    /**
     * A Go Back Feature
     * Ends AddCouponActivity, thus reverting to the previous activity that called this one
     *
     * @param v The cancel / done button
     */
    public void revertToPreviousScreen(View v) {
        CouponActivity.this.finish();
    }

    public void openActivityDetails() {
        //ListView  = findViewById(
        //ListView = findViewById(
        //Gallery Image = findViewById(

        //TextView textViewDetails
        Intent detailsIntent = new Intent(this, CouponDetailsActivity.class);
        //intent.putExtra("");
        //intent.putExtra("ListViewDetails");
        //intent.putExtra(mCoupons);
        startActivity(detailsIntent);
    }
}


//    public void openActivity3() {
//        //EditText editText1 = (EditText) findViewById(R.id.edittext1);
//        //int number = Integer.parseInt(editText1.getText().toString());
//
//        Intent intent = new Intent(this, StoresActivity.class);
//       // intent.putExtra(EXTRA_NUMBER, number);
//        startActivity(intent);
//    }
//    //---/TO HERE /--/Exp Date/
//}
