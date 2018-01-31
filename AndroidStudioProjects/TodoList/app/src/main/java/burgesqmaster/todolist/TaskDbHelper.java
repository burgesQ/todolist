package burgesqmaster.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by master on 1/31/17.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Task.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskContract.FeedEntry.TABLE_NAME + " (" +
                    TaskContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskContract.FeedEntry.COLUMN_NAME_CATEGORY + " CATEGORY," +
                    TaskContract.FeedEntry.COLUMN_NAME_DONE + " DONE," +
                    TaskContract.FeedEntry.COLUMN_NAME_DETAILS + " DETAILS," +
                    TaskContract.FeedEntry.COLUMN_NAME_TASK + " TASK," +
                    TaskContract.FeedEntry.COLUMN_NAME_OWNER + " OWNER," +
                    TaskContract.FeedEntry.COLUMN_NAME_TASKER + " TASKER," +
                    TaskContract.FeedEntry.COLUMN_NAME_COLOR + " COLOR," +
                    TaskContract.FeedEntry.COLUMN_NAME_FLAG + " FLAG," +
                    TaskContract.FeedEntry.COLUMN_NAME_DEADLINE + " DEADLINE," +
                    TaskContract.FeedEntry.COLUMN_NAME_CREATION_DATE + " CREATION_DATE," +
                    TaskContract.FeedEntry.COLUMN_NAME_PRIORITY + " PRIORITY)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskContract.FeedEntry.TABLE_NAME;

    public TaskDbHelper(Context context) {
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
