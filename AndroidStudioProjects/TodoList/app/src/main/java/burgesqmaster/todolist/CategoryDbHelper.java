package burgesqmaster.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by master on 1/31/17.
 */

public class CategoryDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Category.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CategoryContract.FeedEntry.TABLE_NAME + " (" +
                    CategoryContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    CategoryContract.FeedEntry.COLUMN_NAME_TITLE + " TITLE," +
                    CategoryContract.FeedEntry.COLUMN_NAME_CREATION_DATE + " CREATION_DATE)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CategoryContract.FeedEntry.TABLE_NAME;

    public CategoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
