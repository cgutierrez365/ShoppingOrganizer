package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
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

import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.Coupons;
import cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model.DBHelper;

import static cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.AddCouponActivity.EXTRA_NUMBER;

//import edu.miracosta.cs134.capstonelayouts.Model.DBHelperCoupon;

public class CouponActivity extends AppCompatActivity {

    /**
     * 3:43PM, after TODOS on AddCouponActivity.java, 4/28/2019
     * Adding in database and listview
     */
    private DBHelper db;
    private ArrayAdapter<Coupons> allCouponsList;
    private ListView couponsListView; // Need a list view for AddCouponActivity.
    // private CouponsListAdapter couponsListAdapter; //careful for coupon and coupons
    private Button addCoupon;  //AddCouponActivity
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

    /**
     * Needed Variables for USer Input
     */
    private ArrayList<String> addArray = new ArrayList<String>();
    private EditText txt;
    private ListView show;


    /**
     * Working on DB Helper, DBHELPER COUPON, switched to a similar one like Chloe's for Coupons, Store.
     * NEed to figure out adapter
     *
     * @param savedInstanceState
     */
    //DONE: GOING TO NEED TO ADD A COUPON LIST VIEW FOR A LIST OF COUPONS THAT WILL BE SELECTED
    //DONE: OR INPUT BY USER TO PREVIEW ON A LIST ONCE FINISHED. MASS SCROLL. 4:18PM 5/3/19
    //TODO: ADD IMAGES TO COUPON OPTIONS, adjust the input for numbers only to include slash.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        /**
         * List View = DBHELPER is not finished, this is not complete work. Just additions, incase... easier find.
         */
        //     deleteDatabase(DBHelperCoupon.DATABASE_NAME);   //Database for Coupons
        //    db = new DBHelperCoupon(this);                //Importing list of coupons
        //  db.importCouponsFromCSV("coupons.csv");  //direct input

        //setListAdapter(new CouponsListAdapter(this, R.layout.custom_store, allCouponsList));
        //couponsListAdapter = new CouponsListAdapter(this, R.layout.custom_store, allCouponsList);

        // allCouponsList = db.getallCouponsList();  // in for list of specific name coupons and where from.
        //couponsListView = findViewById(R.id.couponsListView); //identify
        // CouponsListAdapter = new CouponsListAdapter(this, R.layout.custom_coupon, allCouponsList); //linking list to layout
        // couponsListView.setAdapter(CouponsListAdapter); //list can pull up on screen because of adapter

        /** end of list view
         */

        /**
         * This is for getting TEXT from ADDCouponActivity to the COUPON Activity, THIS IS DONE.
         * DOES AS INTENDED, FOR COUPON LIST WITH LIST VIEW, ADD tOO LIST and VIEW Coupons Added to next activity.**
         */

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

                if (addArray.contains(getInput)) {
                    Toast.makeText(getBaseContext(), "Coupon Added", Toast.LENGTH_LONG).show();
                } else if (getInput == null || getInput.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "InputField if Empty", Toast.LENGTH_LONG).show();

                } else {
                    addArray.add(getInput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CouponActivity.this, android.R.layout.simple_list_item_1, addArray);
                    show.setAdapter(adapter);
                    ((EditText) findViewById(R.id.editTextCoupon)).setText("");
                }
            }
        });

    }

    private void setListAdapter(CouponsListAdapter couponsListAdapter) {
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
