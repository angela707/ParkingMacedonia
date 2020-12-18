package angela.example.parkingmacedonia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_USERNAME = "CUSTOMER_USERNAME";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_SURNAME = "CUSTOMER_SURNAME";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_CUSTOMER_GENDER = "CUSTOMER_GENDER";
    static String name = "database";
    static int version = 1;


    public Database(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_CUSTOMER_USERNAME + " TEXT PRIMARY KEY, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_SURNAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_CUSTOMER_GENDER + " TEXT )";

        db.execSQL(createTableStatement);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addOne (CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_SURNAME, customerModel.getSurname());
        cv.put(COLUMN_CUSTOMER_USERNAME, customerModel.getUsername());
        cv.put(COLUMN_CUSTOMER_PASSWORD, customerModel.getPassword());
        cv.put(COLUMN_CUSTOMER_EMAIL, customerModel.getEmail());
        cv.put(COLUMN_CUSTOMER_GENDER, customerModel.getGender());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
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

        SQLiteDatabase mydb = this.getWritableDatabase();

        Cursor cursor = mydb.rawQuery("Select * from CUSTOMER_TABLE where CUSTOMER_USERNAME = ? and CUSTOMER_PASSWORD = ?", new String [] {username, password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
