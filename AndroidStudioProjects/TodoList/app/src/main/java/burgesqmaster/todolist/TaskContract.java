package burgesqmaster.todolist;

import android.provider.BaseColumns;

/**
 * Created by master on 1/31/17.
 */

public class TaskContract {

    private TaskContract() {
    }

    public class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "task_table";

        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_DONE = "done";
        public static final String COLUMN_NAME_DETAILS = "details";
        public static final String COLUMN_NAME_TASK = "task";
        public static final String COLUMN_NAME_OWNER = "owner";
        public static final String COLUMN_NAME_TASKER = "tasker";
        public static final String COLUMN_NAME_COLOR = "color";
        public static final String COLUMN_NAME_FLAG = "flag";
        public static final String COLUMN_NAME_DEADLINE = "deadline";
        public static final String COLUMN_NAME_CREATION_DATE = "creation_date";
        public static final String COLUMN_NAME_PRIORITY = "priority";

        public static final int NUM_COLUMN_ID = 0;
        public static final int NUM_COLUMN_CATEGORY = 1;
        public static final int NUM_COLUMN_DONE = 2;
        public static final int NUM_COLUMN_DETAILS = 3;
        public static final int NUM_COLUMN_TASK = 4;
        public static final int NUM_COLUMN_OWNER = 5;
        public static final int NUM_COLUMN_TASKER = 6;
        public static final int NUM_COLUMN_COLOR = 7;
        public static final int NUM_COLUMN_FLAG = 8;
        public static final int NUM_COLUMN_DEADLINE = 9;
        public static final int NUM_COLUMN_CREATION_DATE = 10;
        public static final int NUM_COLUMN_PRIORITY = 11;

    }
}
