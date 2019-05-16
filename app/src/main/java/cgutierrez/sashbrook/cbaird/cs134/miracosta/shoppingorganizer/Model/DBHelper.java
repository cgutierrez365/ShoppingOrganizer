package cgutierrez.sashbrook.cbaird.cs134.miracosta.shoppingorganizer.Model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //INSTANCE VARIABLES----------------------------------------------------------------------------
    private Context mContext;

    //database specs
    public static final String DATABASE_NAME = "ShoppingOrganizerDB";
    public static final int DATABASE_VERSION = 1;

    //Item table fields ; Chloe
    public static final String ITEMS_TABLE = "Items";
    private static final String ITEMS_KEY_FIELD_ID = "_id";
    private static final String FIELD_ITEM_NAME = "itemName";
    private static final String FIELD_STORE_NAME = "storeName";
    private static final String FIELD_STORE_LOCATION = "storeLocation";
    private static final String FIELD_ITEM_QUANTITY = "itemQuantity";
    private static final String FIELD_ITEM_URI = "image_uri";

    //Linking table for Items and Coupons fields : Clarissa
    private static final String LINK_ITEM_COUPONS_TABLE = "Link_Items_Coupons";
    private static final String FIELD_COURSE_ID = "item_id";
    private static final String FIELD_INSTRUCTOR_ID = "coupons_id";

    //Notes table fields : Clarissa
    public static final String NOTES_TABLE = "Notes";
    private static final String NOTES_KEY_FIELD_ID = "_id";
    private static final String FIELD_NOTE_TITLE = "noteTitle";
    private static final String FIELD_NOTE_CONTENTS = "noteContents";

    //Coupons Table : Stacey
    public static final String COUPONS_TABLES = "Coupons";
    public static final String COUPONS_KEY_FIELD_ID = "_id";
    private static final String FIELD_COUPON_IMAGE = "couponPicture";
    private static final String FIELD_EXPIRATION_DATE = "expirationDate";
    private static final String FIELD_IS_FAVORITE = "couponFavorite";
    private static final String FIELD_ADDITIONAL_NOTES = "additionalNotes";

    //Stores Table : Stacey
    private static final String STORES_TABLE = "Locations";
    private static final String STORES_KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_ZIP_CODE = "zip_code";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_LONGITUDE = "longitude";


    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //IMPLEMENT METHODS-----------------------------------------------------------------------------

    /**
     * Creates the database with all the tables if it doesn't already exist
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = "CREATE TABLE IF NOT EXISTS " + ITEMS_TABLE + "("
                + ITEMS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_ITEM_NAME + " TEXT, "
                + FIELD_STORE_NAME + " TEXT, "
                + FIELD_STORE_LOCATION + " REAL, "
                + FIELD_ITEM_QUANTITY + " TEXT,"
                + FIELD_ITEM_URI + " TEXT" + ")";

        db.execSQL(table);

        //Create Linking table
        table = "CREATE TABLE " + LINK_ITEM_COUPONS_TABLE + "("
                + FIELD_COURSE_ID + " INTEGER, "
                + FIELD_INSTRUCTOR_ID + " INTEGER, "
                + "FOREIGN KEY(" + FIELD_COURSE_ID + ") REFERENCES "
                + ITEMS_TABLE + "(" + ITEMS_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_INSTRUCTOR_ID + ") REFERENCES "
                + COUPONS_TABLES + "(" + COUPONS_KEY_FIELD_ID + "))";
        db.execSQL(table);

        //Create Notes Table
        table = "CREATE TABLE IF NOT EXISTS " + NOTES_TABLE + "("
                + NOTES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NOTE_TITLE + " TEXT, "
                + FIELD_NOTE_CONTENTS + " TEXT"
                + ")";

        db.execSQL(table);

        table = "CREATE TABLE IF NOT EXISTS " + COUPONS_TABLES + "("
                + COUPONS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_COUPON_IMAGE + " BLOB, "
                + FIELD_EXPIRATION_DATE + "TEXT, "
                + FIELD_IS_FAVORITE + " REAL, "
                + FIELD_ADDITIONAL_NOTES + " TEXT" + ")";

        db.execSQL(table);

        //Create Stores Table
        String createQuery = "CREATE TABLE " + STORES_TABLE + "("
                + STORES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_ADDRESS + " TEXT, "
                + FIELD_CITY + " TEXT,"
                + FIELD_STATE + " TEXT,"
                + FIELD_ZIP_CODE + " TEXT,"
                + FIELD_PHONE + " TEXT,"
                + FIELD_LATITUDE + " REAL,"
                + FIELD_LONGITUDE + " REAL"
                + ")";
        db.execSQL(createQuery);

    }
    /********** DATABASE OPERATIONS:  ADD, GET ALL, GET 1, DELETE*/
    /** Chloe:
     * Adds the Item to the Database with all table fields
     * @param item the new item to be added to the database
     */
    public void addItem(Items item)
    {
        //create new database connection
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_ITEM_NAME, item.getItemName());
        values.put(FIELD_STORE_NAME, item.getStoreName());
        values.put(FIELD_STORE_LOCATION, item.getStoreLocation());
        values.put(FIELD_ITEM_QUANTITY, item.getItemQuantity());
        values.put(FIELD_ITEM_URI, item.getImageURI());

        //insert the row in the table
        long id = db.insert(ITEMS_TABLE, null, values);

        //set the Item with the newly assigned id from the database
        item.setId(id);

        db.close();
    }

    /**
     * Adds Coupons to database : Stacey
     * @param coupons coupon
     */
    public void addCoupons(Coupons coupons)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_COUPON_IMAGE, coupons.getImageURI());
        values.put(FIELD_EXPIRATION_DATE, coupons.getExpirationDate());
        values.put(FIELD_IS_FAVORITE, coupons.getIsFavorite());
        values.put(FIELD_ADDITIONAL_NOTES, coupons.getAdditionalNotes());

        long id = db.insert(COUPONS_TABLES, null, values);  //DATABASE NAME, listed as table.
        coupons.setId(id);
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public void addStore(Store store) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, store.getName());
        values.put(FIELD_ADDRESS, store.getAddress());
        values.put(FIELD_CITY, store.getCity());
        values.put(FIELD_STATE, store.getState());
        values.put(FIELD_ZIP_CODE, store.getZipCode());
        values.put(FIELD_PHONE, store.getPhone());
        values.put(FIELD_LATITUDE, store.getLatitude());
        values.put(FIELD_LONGITUDE, store.getLongitude());

        long id = db.insert(STORES_TABLE, null, values);
        store.setId(id);
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    /**
     * Stacey:
     * Imports sample coupons from a comma separated value file
     * @param csvFileName csvFileName
     * @return
     */
    public boolean importCouponsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 9) {
                    Log.d("Coupons", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                long id = Long.parseLong(fields[0].trim());
                String imageURI = fields[1].trim();
                String expirationDate = fields[2].trim();
                String isFavorite = fields[3].trim();
                String additionalNotes = fields[4].trim();
                String addArray = fields[5].trim();
                addCoupons(new Coupons(id, imageURI, expirationDate, isFavorite, additionalNotes, addArray));
                //CORRECTION UPON ADDITION OF THE ADDCOUPON(s) TABLE IN FROM DBHelper, to add to database, etc.
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Imports Stores from CSV
     * @param csvFileName csvFileName
     * @return boolean
     */
    public boolean importStoresFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 9) {
                    Log.d("Stores", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                long id = Long.parseLong(fields[0].trim());
                String name = fields[1].trim();
                String address = fields[2].trim();
                String city = fields[3].trim();
                String state = fields[4].trim();
                String zipCode = fields[5].trim();
                String phone = fields[6].trim();
                double latitude = Double.parseDouble(fields[7].trim());
                double longitude = Double.parseDouble(fields[8].trim());
                addStore(new Store(id, name, address, city, state, zipCode, phone, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Updates the database when an addition or subtraction is made from it
     * @param db the database
     * @param oldVersion  oldVersion
     * @param newVersion newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COUPONS_TABLES);
        db.execSQL("DROP TABLE IF EXISTS " + LINK_ITEM_COUPONS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STORES_TABLE);
        onCreate(db);

    }

    /**
     * Returns all the notes in the Notes Table in a List
     * @return List of Notes
     */
    public List<Notes> getAllNotes()
    {
        List<Notes> noteList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        // Instantiate a cursor to hold results of database query
        Cursor cursor = database.query(
                NOTES_TABLE,
                new String[]{NOTES_KEY_FIELD_ID, FIELD_NOTE_TITLE, FIELD_NOTE_CONTENTS},
                null,
                null,
                null, null, null, null);

        //collect each row in the table
        if (cursor.moveToFirst()){
            do {
                Notes note =
                        new Notes(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return noteList;
    }

    /**
     * Returns a List of all the stores in the database
     * @return
     */
    public List<Store> getallStoresList() {
        ArrayList<Store> allStoresList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                STORES_TABLE,
                new String[]{STORES_KEY_FIELD_ID, FIELD_NAME, FIELD_ADDRESS, FIELD_CITY, FIELD_STATE, FIELD_ZIP_CODE, FIELD_PHONE, FIELD_LATITUDE, FIELD_LONGITUDE},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                Store store =
                        new Store(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getDouble(7),
                                cursor.getDouble(8));
                allStoresList.add(store);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allStoresList;
    }

    /**
     * Gets a store object from the database
     * @param id id
     * @return Store object
     */
    public Store getStore(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                STORES_TABLE,
                new String[]{STORES_KEY_FIELD_ID, FIELD_NAME, FIELD_ADDRESS, FIELD_CITY, FIELD_STATE, FIELD_ZIP_CODE, FIELD_PHONE, FIELD_LATITUDE, FIELD_LONGITUDE},
                STORES_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Store store =
                new Store(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getDouble(7),
                        cursor.getDouble(8));
        cursor.close();
        db.close();
        return store;
    }

    /**
     * Deletes a store in the database
     * @param store
     */
    public void deleteStore(Store store) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(STORES_TABLE, STORES_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(store.getId())});
        db.close();
    }

    /**
     * Deletes all the stores in the database
     */
    public void deleteAllStores() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STORES_TABLE, null, null);
        db.close();
    }
    /**
     * HERE ADD LISTVIEW LIST into SYSTEM.
     *
     * @param
     */
    public List<Coupons> getAllCoupons()
    {
        ArrayList<Coupons> couponsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        // Instantiate a cursor to hold results of database query
        Cursor cursor = database.query(" Choices: " + COUPONS_TABLES,
                new String[] {COUPONS_KEY_FIELD_ID, FIELD_COUPON_IMAGE, FIELD_EXPIRATION_DATE, FIELD_IS_FAVORITE, FIELD_ADDITIONAL_NOTES},
                null,null,null, null, null, null);

        //collect each row in the table
        if (cursor.moveToFirst()){
            do{
                Coupons coupon =
                        new Coupons(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5));
                couponsList.add(coupon);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return couponsList;
    }

    public Coupons getCoupons(String imageURI)
    {
        Coupons coupons = new Coupons();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(COUPONS_TABLES,
                new String[] {COUPONS_KEY_FIELD_ID, FIELD_COUPON_IMAGE, FIELD_EXPIRATION_DATE, FIELD_IS_FAVORITE, FIELD_ADDITIONAL_NOTES},
                FIELD_COUPON_IMAGE + " = ?", new String[]{imageURI}, null, null, null);

        if(cursor.moveToFirst())
        {
            do{
                long mId = cursor.getLong(0); // index column
                String mImageURI = cursor.getString(1);
                String mExpirationDate = cursor.getString(2);
                String mIsFavorite = cursor.getString(3);
                String mAdditionalNotes = cursor.getString(4);
                String mCoupons = cursor.getString(5);

                coupons.setImageURI(mImageURI);
                coupons.setExpirationDate(mExpirationDate);
                coupons = new Coupons(mId, mImageURI, mExpirationDate, mIsFavorite, mAdditionalNotes, mCoupons);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return coupons;
    }

    public void deleteCoupons(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(COUPONS_TABLES, COUPONS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAllCoupons() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COUPONS_TABLES, null, null);
        db.close();
    }

    public Coupons getCoupons(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                COUPONS_TABLES,
                new String[]{COUPONS_KEY_FIELD_ID, FIELD_COUPON_IMAGE, FIELD_EXPIRATION_DATE, FIELD_IS_FAVORITE, FIELD_ADDITIONAL_NOTES},
                COUPONS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Coupons coupon =
                new Coupons(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
//                        cursor.getString(6),
//                        cursor.getDouble(7),
//                        cursor.getDouble(8));
        cursor.close();
        db.close();
        return coupon;
    }

}
