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

public class CategoryDb {

    private SQLiteDatabase _db;
    private CategoryDbHelper _categoryDb;
    private TaskDb _taskDb;

    public CategoryDb(Context context) {

        _taskDb = new TaskDb(context);
        _taskDb.open();

        _categoryDb = new CategoryDbHelper(context);
    }


    public void open() {
        _db = _categoryDb.getWritableDatabase();
    }

    public void close() {
        _db.close();
        _taskDb.close();
    }

    public SQLiteDatabase getDb() {
        return _db;
    }

    public long insertCategory(Category category) {
        ContentValues values = new ContentValues();

        values.put(CategoryContract.FeedEntry.COLUMN_NAME_TITLE, category.getTitle());
        values.put(CategoryContract.FeedEntry.COLUMN_NAME_CREATION_DATE, category.getCreationDate().getTime());

        return _db.insert(CategoryContract.FeedEntry.TABLE_NAME, null, values);
    }

    public int updateCategory(int id, Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoryContract.FeedEntry.COLUMN_NAME_TITLE, category.getTitle());

        for (Task oneTask : category.getTasks())
            _taskDb.updateTaskString((int) oneTask.getId(), TaskContract.FeedEntry.COLUMN_NAME_CATEGORY, category.getTitle());

        return _db.update(CategoryContract.FeedEntry.TABLE_NAME, values, CategoryContract.FeedEntry._ID + " = " + id, null);
    }

    public int removeCategoryWithID(Category category) {

        for (Task oneTask : category.getTasks())
            _taskDb.removeTaskWithID((int) oneTask.getId());

        return _db.delete(CategoryContract.FeedEntry.TABLE_NAME,
                CategoryContract.FeedEntry._ID + " = " + category.getId(), null);
    }

    public ArrayList<Category> getCategorys() {

        Cursor cursor = _db.rawQuery("select * from " + CategoryContract.FeedEntry.TABLE_NAME, null);

        return cursorToCategory(cursor);
    }

    private ArrayList<Category> cursorToCategory(Cursor cursor) {

        if (cursor.getCount() == 0)
            return new ArrayList<>();

        ArrayList<Category> categorys = new ArrayList<>();

        while (cursor.moveToNext()) {
            Category oneCat = new Category(cursor.getString(CategoryContract.FeedEntry.NUM_COLUMN_TITLE),
                    new ArrayList<Task>());
            oneCat.setCreationDate(new Date(
                    cursor.getLong(CategoryContract.FeedEntry.NUM_COLUMN_CREATION_DATE)));
            oneCat.setId(cursor.getLong(cursor.getColumnIndexOrThrow(
                    CategoryContract.FeedEntry._ID)));
            oneCat.setTask(_taskDb.getTaskByCategory(oneCat.getTitle()));
            categorys.add(oneCat);
        }
        cursor.close();

        return categorys;
    }

}
