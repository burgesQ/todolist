package burgesqmaster.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by master on 1/31/17.
 */

public class TaskDb {

    private SQLiteDatabase db;

    private TaskDbHelper taskDb;

    public TaskDb(Context context) {
        taskDb = new TaskDbHelper(context);
    }

    public void open() {
        db = taskDb.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public long insertTask(Task task, String categoryTitle) {
        ContentValues values = new ContentValues();

        values.put(TaskContract.FeedEntry.COLUMN_NAME_CATEGORY, categoryTitle);
        values.put(TaskContract.FeedEntry.COLUMN_NAME_DONE, task.getDone());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_DETAILS, task.getDetails());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_TASK, task.getTask());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_OWNER, task.getOwner());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_TASKER, task.getTasker());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_TASKER, task.getTasker());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_COLOR, task.getColor());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_FLAG, task.getFlag());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_DEADLINE, task.getDeadLine().getTime());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_CREATION_DATE, task.getCreationDate().getTime());
        values.put(TaskContract.FeedEntry.COLUMN_NAME_PRIORITY, task.getPriority());

        return db.insert(TaskContract.FeedEntry.TABLE_NAME, null, values);
    }

    public int updateTaskString(int id, String columnName, String val) {
        ContentValues values = new ContentValues();
        values.put(columnName, val);
        return db.update(TaskContract.FeedEntry.TABLE_NAME, values, TaskContract.FeedEntry._ID + " = " + id, null);
    }

    public int updateTaskBool(int id, String columnName, Boolean val) {
        ContentValues values = new ContentValues();
        values.put(columnName, val);
        return db.update(TaskContract.FeedEntry.TABLE_NAME, values, TaskContract.FeedEntry._ID + " = " + id, null);
    }

    public int updateTaskLong(int id, String columnName, long val) {
        ContentValues values = new ContentValues();
        values.put(columnName, val);
        return db.update(TaskContract.FeedEntry.TABLE_NAME, values, TaskContract.FeedEntry._ID + " = " + id, null);
    }

    public int removeTaskWithID(int id) {
        return db.delete(TaskContract.FeedEntry.TABLE_NAME, TaskContract.FeedEntry._ID + " = " + id, null);
    }

    public ArrayList<Task> getTaskByCategory(String categoryTitle) {

        String[] projection = {
                TaskContract.FeedEntry._ID,
                TaskContract.FeedEntry.COLUMN_NAME_CATEGORY,
                TaskContract.FeedEntry.COLUMN_NAME_DONE,
                TaskContract.FeedEntry.COLUMN_NAME_DETAILS,
                TaskContract.FeedEntry.COLUMN_NAME_TASK,
                TaskContract.FeedEntry.COLUMN_NAME_OWNER,
                TaskContract.FeedEntry.COLUMN_NAME_TASKER,
                TaskContract.FeedEntry.COLUMN_NAME_COLOR,
                TaskContract.FeedEntry.COLUMN_NAME_FLAG,
                TaskContract.FeedEntry.COLUMN_NAME_DEADLINE,
                TaskContract.FeedEntry.COLUMN_NAME_CREATION_DATE,
                TaskContract.FeedEntry.COLUMN_NAME_FLAG
        };

        String selection = TaskContract.FeedEntry.COLUMN_NAME_CATEGORY + " = ?";
        String[] selectionArgs = {categoryTitle};

        String sortOrder =
                TaskContract.FeedEntry.COLUMN_NAME_CREATION_DATE + " ASC";

        Cursor cursor = db.query(
                TaskContract.FeedEntry.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return cursorToTasks(cursor);
    }

    private ArrayList<Task> cursorToTasks(Cursor cursor) {

        if (cursor.getCount() == 0)
            return new ArrayList<>();

        ArrayList<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            Task oneTask = new Task(
                    cursor.getString(TaskContract.FeedEntry.NUM_COLUMN_TASK),
                    new Date(cursor.getLong(TaskContract.FeedEntry.NUM_COLUMN_CREATION_DATE)));

            oneTask.setDone((cursor.getInt(TaskContract.FeedEntry.NUM_COLUMN_DONE) != 0));
            oneTask.setDetails(cursor.getString(TaskContract.FeedEntry.NUM_COLUMN_DETAILS));
            oneTask.setOwner(cursor.getString(TaskContract.FeedEntry.NUM_COLUMN_OWNER));
            oneTask.setTasker(cursor.getString(TaskContract.FeedEntry.NUM_COLUMN_TASKER));
            oneTask.setColor(cursor.getString(TaskContract.FeedEntry.NUM_COLUMN_COLOR));
            oneTask.setFlag(cursor.getString(TaskContract.FeedEntry.NUM_COLUMN_FLAG));
            oneTask.setDeadLine(new Date(cursor.getLong(TaskContract.FeedEntry.NUM_COLUMN_DEADLINE)));
            oneTask.setPriority(cursor.getInt(TaskContract.FeedEntry.NUM_COLUMN_PRIORITY));

            oneTask.setId(cursor.getLong(
                    cursor.getColumnIndexOrThrow(TaskContract.FeedEntry._ID)));

            tasks.add(oneTask);
        }
        cursor.close();

        return tasks;
    }
}
