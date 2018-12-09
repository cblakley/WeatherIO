package colin.weatherio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter{
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_SERIAL_NUMBER = "serial_number";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "MyDB.db";
    static final String DATABASE_TABLE = "sensors";

    static final int DATABASE_VERSION = 2;

    static final String DATABASE_CREATE =
            "create table sensors (_id integer primary key autoincrement, "
                    + "name text not null, serial_number text not null);";



    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

        // Add a table into the DB

    }

    public static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS sensor");
            onCreate(db);
        }
    }


    //---opens the database---
    public DBAdapter open(String path) throws SQLException
    {

        db = DBHelper.getWritableDatabase();
        db.openDatabase(path, null, 0);
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public boolean insertSensor(String name, String serial_number)
    {
        db = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SERIAL_NUMBER, serial_number);
        db.insert(DATABASE_TABLE, null, initialValues);
        return true;
    }

    //---deletes a particular contact---
    public boolean deleteSensor(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllSensors()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_SERIAL_NUMBER}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getSensor(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_NAME, KEY_SERIAL_NUMBER}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateContact(long rowId, String name, String serial_number)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_SERIAL_NUMBER, serial_number);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


}
