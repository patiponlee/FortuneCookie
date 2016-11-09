package a13265.egco428.com.fortunecookie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USER on 6/11/2559.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{
    public static final String TABLE_RESULTS = "results"; //table name is used
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "result";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_DATE = "time";
    private static final String DATABASE_NAME = "resultFortune.db"; //database name file SQLite
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RESULTS + "(" + COLUMN_ID + " integer primary key autoincrement, "+
            COLUMN_IMAGE + " text not null,"+ COLUMN_COMMENT + " text not null,"
            + COLUMN_DATE + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE); //execute SQL
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //if update program not always onCreate
        Log.w(MySQLiteHelper.class.getName(),                                  //delete old one add new
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        onCreate(db);
    }
}
