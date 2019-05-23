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
//    private static final String FIELD_STORE_LOCATION = "storeLocation";
    private static final String FIELD_ITEM_QUANTITY = "itemQuantity";
    private static final String FIELD_ITEM_URI = "image_uri";

    //Linking table for Items and Coupons fields : Clarissa
    private static final String LINK_ITEM_COUPON_TABLE = "Link_Items_Coupons";
    private static final String FIELD_COURSE_ID = "item_id";
    private static final String FIELD_INSTRUCTOR_ID = "coupons_id";

    //Notes table fields : Clarissa
    public static final String NOTES_TABLE = "Notes";
    private static final String NOTES_KEY_FIELD_ID = "_id";
    private static final String FIELD_NOTE_TITLE = "noteTitle";
    private static final String FIELD_NOTE_CONTENTS = "noteContents";

    //Coupons Table : Stacey
    public static final String COUPON_TABLE = "Coupons";
    public static final String COUPON_KEY_FIELD_ID = "_id";
    private static final String FIELD_COUPON_IMAGE = "imageURI";
    private static final String FIELD_EXPIRATION_DATE = "expirationDate";
    private static final String FIELD_IS_FAVORITE = "isFavorite";
    private static final String FIELD_ADDITIONAL_NOTES = "additionalNotes";

    //Stores Table : Stacey
    private static final String STORES_TABLE = "Locations";
    private static final String STORES_KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_LONGITUDE = "longitude";
    private static final String FIELD_STORE_IMAGE = "imageName";

    /**
     * mContext identifier, fixed mContext to be available for identifier
     * @param context
     */
    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
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
//                + FIELD_STORE_LOCATION + " REAL, "
                + FIELD_ITEM_QUANTITY + " TEXT,"
                + FIELD_ITEM_URI + " TEXT" + ")";

        db.execSQL(table);

        //Create Linking table Clarissa
        table = "CREATE TABLE " + LINK_ITEM_COUPON_TABLE + "("
                + FIELD_COURSE_ID + " INTEGER, "
                + FIELD_INSTRUCTOR_ID + " INTEGER, "
                + "FOREIGN KEY(" + FIELD_COURSE_ID + ") REFERENCES "
                + ITEMS_TABLE + "(" + ITEMS_KEY_FIELD_ID + "), "
                + "FOREIGN KEY(" + FIELD_INSTRUCTOR_ID + ") REFERENCES "
                + COUPON_TABLE + "(" + COUPON_KEY_FIELD_ID + "))";
        db.execSQL(table);

        //Create Notes Table Chloe
        table = "CREATE TABLE IF NOT EXISTS " + NOTES_TABLE + "("
                + NOTES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NOTE_TITLE + " TEXT, "
                + FIELD_NOTE_CONTENTS + " TEXT"
                + ")";

        db.execSQL(table);
        //Create Coupon Table Stacey
        table = "CREATE TABLE IF NOT EXISTS " + COUPON_TABLE + "("
                + COUPON_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_COUPON_IMAGE + " String, "
                + FIELD_EXPIRATION_DATE + " TEXT, " //Correction 5/19/2019 .." T..from "T
                + FIELD_IS_FAVORITE + " TEXT, "     //Corrected REAL and plural Coupon(s) and Table, not Tables
                + FIELD_ADDITIONAL_NOTES + " TEXT" + ")";

        db.execSQL(table);

        /**
         * Stores Database, Added Store Image
         */
        //Create Stores Table Stacey
        String createQuery = "CREATE TABLE " + STORES_TABLE + "("
                + STORES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_LOCATION + " TEXT, "
                + FIELD_LATITUDE + " REAL,"
                + FIELD_LONGITUDE + " REAL,"
                + FIELD_STORE_IMAGE + " TEXT"
                + ")";
        db.execSQL(createQuery);

    }
    /********** DATABASE OPERATIONS:  ADD, GET ALL, GET 1, DELETE*/
    /**
     * Clarissa:
     *
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
//        values.put(FIELD_STORE_LOCATION, item.getStoreLocation());
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
     * @param coupons coupon        checked 5/19
     */
    public void addCoupons(Coupons coupons)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_COUPON_IMAGE, coupons.getImageURI());
        values.put(FIELD_EXPIRATION_DATE, coupons.getExpirationDate());
        values.put(FIELD_IS_FAVORITE, coupons.getIsFavorite());
        values.put(FIELD_ADDITIONAL_NOTES, coupons.getAdditionalNotes());

        long id = db.insert(COUPON_TABLE, null, values);  //DATABASE NAME, listed as table.
        coupons.setId(id);
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public void addStore(Store store) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, store.getName());
        values.put(FIELD_LOCATION, store.getLocation());
        values.put(FIELD_LATITUDE, store.getLatitude());
        values.put(FIELD_LONGITUDE, store.getLongitude());
        values.put(FIELD_STORE_IMAGE, store.getImageName());

        long id = db.insert(STORES_TABLE, null, values);
        store.setId(id);
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }


    public void addNote(Notes note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NOTE_TITLE, note.getNoteTitle());
        values.put(FIELD_NOTE_CONTENTS, note.getNoteContents());

        long id = db.insert(NOTES_TABLE, null, values);
        note.setId(id);
        // CLOSE THE DATABASE CONNECTION
        db.close();
    }


    /**
     * Stacey: Coupons Database/ CouponListAdapter/ CouponDetails
     *   Stacey: Coupons/AddCouponsActivity & CouponActivity
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
                if (fields.length != 5) {
                    Log.d("Coupons", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                long id = Long.parseLong(fields[0].trim());
                String imageURI = fields[1].trim();
                String expirationDate = fields[2].trim();
                String isFavorite = fields[3].trim();
                String additionalNotes = fields[4].trim();
                //TODO: If error shows up for importCouponsFromCSV, might be because we shouldn't import the coupon id from the csv file
                addCoupons(new Coupons(id, imageURI, expirationDate, isFavorite, additionalNotes));
                //CORRECTION UPON ADDITION OF THE ADDCOUPON(s) TABLE IN FROM DBHelper, to add to database, etc.
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Clarissa:
     *
     * Imports Notes from a csv file
     * @param csvFileName commma separated values of notes
     * @return boolean value indicating whether was able to successfully add items to the database or not
     */
    public boolean importNotesFromCSV(String csvFileName) {
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
                if (fields.length != 2) {
                    Log.d("Shopping Organizer", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                String noteTitle = fields[0].trim();
                String noteContents = fields[1].trim();
                addNote(new Notes(-1, noteTitle, noteContents));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Stacey: Stores/StoresActivity/Stores Database Partial/StoreAdapter
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
                if (fields.length != 6) {
                    Log.d("Stores", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                long id = Long.parseLong(fields[0].trim());
                String name = fields[1].trim();
                String location = fields[2].trim();
                double latitude = Double.parseDouble(fields[3].trim());
                double longitude = Double.parseDouble(fields[4].trim());
                String imageName = fields[5].trim();
                addStore(new Store(id, name, location, latitude, longitude, imageName));
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
        db.execSQL("DROP TABLE IF EXISTS " + COUPON_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LINK_ITEM_COUPON_TABLE);
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
                        new Notes(cursor.getLong(0),
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
     * Stacey: Stores/StoresActivity/Stores Database Partial/StoreAdapter
     * Returns a List of all the stores in the database
     * @return allStoresList, db.getallStoresList()
     * @add ListView
     */
    public List<Store> getallStoresList() {
        ArrayList<Store> allStoresList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                STORES_TABLE,
                new String[]{STORES_KEY_FIELD_ID, FIELD_NAME, FIELD_LOCATION, FIELD_LATITUDE, FIELD_LONGITUDE, FIELD_STORE_IMAGE},
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
                                cursor.getDouble(3),
                                cursor.getDouble(4),
                                cursor.getString(5));
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
//    public Store getStore(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(
//                STORES_TABLE,
//                new String[]{STORES_KEY_FIELD_ID, FIELD_NAME, FIELD_LOCATION, FIELD_CITY, FIELD_STATE, FIELD_ZIP_CODE, FIELD_PHONE, FIELD_LATITUDE, FIELD_LONGITUDE},
//                STORES_KEY_FIELD_ID + "=?",
//                new String[]{String.valueOf(id)},
//                null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Store store =
//                new Store(cursor.getLong(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getString(5),
//                        cursor.getString(6),
//                        cursor.getDouble(7),
//                        cursor.getDouble(8));
//        cursor.close();
//        db.close();
//        return store;
//    }

    /**
     *
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

        //Corrected: ListView Up!
    // **Up To Date** 5/19/2019
//-------------------/-----------------------------------------------/
    /**
     * Stacey: Coupons Database/ CouponListAdapter/ CouponDetails
     *      Stacey: Coupons/AddCouponsActivity & CouponActivity
    /**
     * HERE ADD LISTVIEW LIST into SYSTEM.
     *
     * @param
     */
    public List<Coupons> getAllCouponsList() {
        ArrayList<Coupons> couponsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                COUPON_TABLE,
                new String[]{COUPON_KEY_FIELD_ID, FIELD_COUPON_IMAGE, FIELD_EXPIRATION_DATE, FIELD_IS_FAVORITE, FIELD_ADDITIONAL_NOTES},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()) {
            do {
                Coupons coupons =
                        new Coupons(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4));
                couponsList.add(coupons);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return couponsList;
    }

    public Coupons getCoupons(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                COUPON_TABLE,
                new String[]{COUPON_KEY_FIELD_ID, FIELD_COUPON_IMAGE, FIELD_EXPIRATION_DATE, FIELD_IS_FAVORITE, FIELD_ADDITIONAL_NOTES},
                COUPON_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Coupons coupon =
                new Coupons(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
        cursor.close();
        db.close();
        return coupon;
    }

    public Coupons getCoupons(String imageURI)
    {
        Coupons coupons = new Coupons();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(COUPON_TABLE,
                new String[] {COUPON_KEY_FIELD_ID, FIELD_COUPON_IMAGE, FIELD_EXPIRATION_DATE, FIELD_IS_FAVORITE, FIELD_ADDITIONAL_NOTES},
                FIELD_COUPON_IMAGE + " = ?", new String[]{imageURI}, null, null, null);

        if(cursor.moveToFirst())
        {
            do{
                long mId = cursor.getLong(0); // index column
                String mImageURI = cursor.getString(1);
                String mExpirationDate = cursor.getString(2);
                String mIsFavorite = cursor.getString(3);
                String mAdditionalNotes = cursor.getString(4);

                coupons.setId(mId);
                coupons.setImageURI(mImageURI);
                coupons.setExpirationDate(mExpirationDate);
                coupons.setIsFavorite(mIsFavorite);
                coupons.setAdditionalNotes(mAdditionalNotes);
                coupons = new Coupons(mId, mImageURI, mExpirationDate, mIsFavorite, mAdditionalNotes);
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
        db.delete(COUPON_TABLE, COUPON_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAllCoupons() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COUPON_TABLE, null, null);
        db.close();
    }
        //Corrected: ListView Up!
    // **Up To Date** 5/19/2019
//-------------------/-----------------------------------------------/


    /**
     *  Imports sample items from a comma separated value file
     * @param csvFileName
     * @return
     */
    public boolean importItemsFromCSV(String csvFileName)
    {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;

        try
        {
            InputStream inputStream = inStream = manager.open(csvFileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;

        try
        {
            while ( (line = buffer.readLine()) != null )
            {
                String[] fields = line.split(",");
                if(fields.length != 6)
                {
                    Log.d("Items", "Skipping bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }

                long id = Long.parseLong(fields[0].trim());
                String itemName = fields[1].trim();
                String storeName = fields[2].trim();
                String storeLocation = fields[3].trim();
                String quantity = fields[4].trim();
                String imageUri = fields[5].trim();

                addItem(new Items(id, itemName, storeName,/* storeLocation, */quantity, imageUri));

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }


        return true;
    }


    /**
     * Returns a List of all the items in the items table
     * @return list of Items
     */
    public List<Items> getAllItems()
    {
        List<Items> itemsList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        //CREATE A CURSOR FOR THE DATABASE
        Cursor cursor = database.query(
                ITEMS_TABLE,
                new String[]{ITEMS_KEY_FIELD_ID, FIELD_ITEM_NAME, FIELD_STORE_NAME,/* FIELD_STORE_LOCATION,*/ FIELD_ITEM_QUANTITY, FIELD_ITEM_URI},
                null,
                null,
                null, null, null, null);

        //COLLECT EACH ROW IN TABLE
        if( cursor.moveToFirst() )
        {
            do
            {
                Items item = new Items(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
//                        cursor.getString(3),
                        cursor.getString(3),
                        cursor.getString(4));
            } while ( cursor.moveToNext() );

        }

        cursor.close();
        database.close();

        return itemsList;

    }

    /**
     * Gets a  singular item from the database
     * @param id id
     * @return Items object
     */
    public Items getItem(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ITEMS_TABLE,
                new String[]{ITEMS_KEY_FIELD_ID, FIELD_ITEM_NAME, FIELD_STORE_NAME, /*FIELD_STORE_LOCATION, */FIELD_ITEM_QUANTITY, FIELD_ITEM_URI},
                ITEMS_KEY_FIELD_ID + " =?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        Items item = new Items(cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
//                cursor.getString(3),
                cursor.getString(3),
                cursor.getString(4)
        );

        cursor.close();
        db.close();

        return item;

    }

    /**
     * Deletes an item from the database
     * @param item
     */
    public void deleteItem(Items item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //DELETE THE TABLE ROW
        db.delete(ITEMS_TABLE, ITEMS_KEY_FIELD_ID + " = ?",
                new String[]{ String.valueOf(item.getId()) } );

        db.close();
    }

    /**
     * Deletes all the items from the database
     */
    public void deleteAllItems()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEMS_TABLE, null, null);

        db.close();
    }

    public void deleteNote(Notes note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES_TABLE, NOTES_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(note.getId())});

        db.close();
    }

}
