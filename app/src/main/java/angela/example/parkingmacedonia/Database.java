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

    public int[] getParkingLots() {
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
}
