package angela.example.parkingmacedonia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;


public class Database extends SQLiteOpenHelper {

    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String USERNAME = "USERNAME";
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String AGE = "AGE";
    public static final String GENDER = "GENDER";
    public static final String CITY_NAME = "CITY_NAME";
    public static final String PARKING_NAME = "PARKING_NAME";
    public static final String DATE = "DATE";
    public static final String TIMESLOT = "TIMESLOT";
    public static final String RESERVATION_TABLE = "RESERVATION_TABLE";

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 2;
    public static final String DB_PATH = "/data/user/0/angela.example.parkingmacedonia/databases/";
    SQLiteDatabase myDatabase;
    private final Context mContext;



    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    private boolean checkDatabase () {
        try{
            final String mPath = DB_PATH + DB_NAME;
            final File file = new File (mPath);
            if (file.exists())
                return true;
            else
                return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private void copyDatabase() throws IOException{
        try{
            InputStream mInputStream = mContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream mOutputStream = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = mInputStream.read(buffer))> 0){
                mOutputStream.write(buffer, 0, length);
            }
            mOutputStream.flush();
            mOutputStream.close();
            mInputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void createDatabase () throws IOException{
        boolean mDatabaseExists = checkDatabase();
        if (!mDatabaseExists)
        {
            this.getReadableDatabase();
            this.close();
            try{
                copyDatabase();
            }catch (IOException mIOException){
                mIOException.printStackTrace();
                throw new Error("Error copying database");
            } finally {
                this.close();
            }
        }
    }

    @Override
    public synchronized void close(){
        if(myDatabase != null)
            myDatabase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    /*public String loadHandler (){
        try{
            createDatabase();
        }catch (IOException e){
            e.printStackTrace();
        }
        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select * from USER_TABLE", null);

        while (c.moveToNext()){
            String
            R.drawable.sk
        }
    }*/


    public boolean addNewUser (CustomerModel customerModel){

        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, customerModel.getName());
        cv.put(SURNAME, customerModel.getSurname());
        cv.put(USERNAME, customerModel.getUsername());
        cv.put(PASSWORD, customerModel.getPassword());
        cv.put(EMAIL, customerModel.getEmail());
        cv.put(GENDER, customerModel.getGender());
        cv.put(AGE, customerModel.getAge());

        long insert = db.insert(USERS_TABLE, null, cv);
        if (insert == -1)
            return false;
        else
            return true;

    }






    public boolean checkUsername (String username)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();

        Cursor cursor = mydb.rawQuery("Select * from CUSTOMER_TABLE where CUSTOMER_USERNAME = ?", new String [] {username});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }




    public boolean isLogInValid (String username, String password){

        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();

        Cursor cursor = mydb.rawQuery("Select * from USERS_TABLE where USERNAME = ? and PASSWORD = ?", new String [] {username, password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }


    public String[] getCities() {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from CITIES_TABLE", null);

        int count = cursor.getCount();

        String []cities = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            cities[i] = cursor.getString(1);
            i++;
        }

        cursor.close();
        mydb.close();
        return cities;


    }

    public String[] getShortcuts() {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from CITIES_TABLE", null);

        int count = cursor.getCount();

        String []shortcuts = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            shortcuts[i] = cursor.getString(4);
            i++;
        }

        cursor.close();
        mydb.close();
        return shortcuts;
    }

    public int[] getNumParkingLots() {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from CITIES_TABLE", null);

        int count = cursor.getCount();

        int parking_lots[]= new int[count];

        int i=0;
        while (cursor.moveToNext()){
            parking_lots[i] = cursor.getInt(2);
            i++;
        }

        cursor.close();
        mydb.close();
        return parking_lots;
    }

    public int[] getCityImages() {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from CITIES_TABLE", null);

        int count = cursor.getCount();

        int images[]= new int[count];

        int i=0;
        while (cursor.moveToNext()){
            images[i] = cursor.getInt(3);
            i++;
        }

        cursor.close();
        mydb.close();
        return images;
    }

    public String[] getParkingLotsNames(String cityName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from PARKINGS_TABLE where CITY_NAME =?", new String [] {cityName});

        int count = cursor.getCount();

        String [] parking_lot_names = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            parking_lot_names[i] = cursor.getString(1);
            i++;
        }

        cursor.close();
        mydb.close();
        return parking_lot_names;
    }

    public int[] getNumParkingSpots(String cityName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from PARKINGS_TABLE where CITY_NAME =?", new String [] {cityName});

        int count = cursor.getCount();

        int num_parking_spots[]= new int[count];

        int i=0;
        while (cursor.moveToNext()){
            num_parking_spots[i] = cursor.getInt(3);
            i++;
        }

        cursor.close();
        mydb.close();
        return num_parking_spots;
    }


    public int[] getReservedSpots(String name, String date, String time_slot, String [] parking_lots) {

        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }

        SQLiteDatabase mydb = this.getWritableDatabase();

        int takenSpots[] = new int[parking_lots.length];

        int i=0;

        String parking_name;

        for (i=0; i<parking_lots.length; i++){

            parking_name = parking_lots[i];

            Cursor cursor = mydb.rawQuery("Select * from RESERVATION_TABLE where CITY_NAME =? and PARKING_NAME=? and DATE=? and TIMESLOT=? ", new String [] {name, parking_name, date, time_slot});

            takenSpots[i] = cursor.getCount();

            cursor.close();
        }

        mydb.close();
        return takenSpots;
    }

    public boolean addReservation(String userName, String cityName, String parkingLotName, String date, String time_slot) {

        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME, userName);
        cv.put(CITY_NAME, cityName);
        cv.put(PARKING_NAME, parkingLotName);
        cv.put(DATE, date);
        cv.put(TIMESLOT, time_slot);

        long insert = db.insert(RESERVATION_TABLE, null, cv);
        if (insert == -1)
            return false;
        else
            return true;
    }


    public String [] getLatitude(String parkingName) {

        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from PARKINGS_TABLE where PARKING_NAME =?", new String [] {parkingName});

        int count = cursor.getCount();

        String [] latitude = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            latitude[i] = cursor.getString(4);
            i++;
        }


        cursor.close();
        mydb.close();
        return latitude;
    }

    public String [] getLongitude(String parkingName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from PARKINGS_TABLE where PARKING_NAME =?", new String [] {parkingName});

        int count = cursor.getCount();

        String [] longitude = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            longitude[i] = cursor.getString(5);
            i++;
        }


        cursor.close();
        mydb.close();
        return longitude;
    }

    public String[] getParkingLotsNamesForUser(String userName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from RESERVATION_TABLE where USERNAME =?", new String [] {userName});

        int count = cursor.getCount();

        String [] parking_lot_names = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            parking_lot_names[i] = cursor.getString(3);
            i++;
        }

        cursor.close();
        mydb.close();
        return parking_lot_names;
    }

    public String[] getReservationDatesForUser(String userName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from RESERVATION_TABLE where USERNAME =?", new String [] {userName});

        int count = cursor.getCount();

        String [] reservation_dates = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            reservation_dates[i] = cursor.getString(4);
            i++;
        }

        cursor.close();
        mydb.close();
        return reservation_dates;
    }

    public String[] getReservationTimeSlotsForUser(String userName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from RESERVATION_TABLE where USERNAME =?", new String [] {userName});

        int count = cursor.getCount();

        String [] reservation_time_slots = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            reservation_time_slots[i] = cursor.getString(5);
            i++;
        }

        cursor.close();
        mydb.close();
        return reservation_time_slots;
    }


    public String[] getCitiesForUser(String userName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from RESERVATION_TABLE where USERNAME =?", new String [] {userName});

        int count = cursor.getCount();

        String [] cities_for_user = new String[count];

        int i=0;
        while (cursor.moveToNext()){
            cities_for_user[i] = cursor.getString(2);
            i++;
        }

        cursor.close();
        mydb.close();
        return cities_for_user;
    }

    public void removeReservation(String user, String city, String parking, String current_date, String current_time_slot) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }


        SQLiteDatabase mydb = this.getWritableDatabase();

        String query = "DELETE FROM RESERVATION_TABLE WHERE USERNAME = "+user+" AND CITY_NAME = "+ city +" AND PARKING_NAME = "+parking + " AND DATE = "+current_date+" AND TIMESLOT= "+ current_time_slot;

        mydb.execSQL(query);
        mydb.close();

    }

    public int getNumUserReservations(String userName) {
        try{
            createDatabase();
        } catch (IOException e){
            e.printStackTrace();
        }
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from RESERVATION_TABLE where USERNAME =?", new String [] {userName});

        int numRes = cursor.getCount();

        cursor.close();
        mydb.close();

        return numRes;
    }
}
