package burgesqmaster.todolist;

import android.provider.BaseColumns;

/**
 * Created by master on 1/31/17.
 */

public class CategoryContract {

    private CategoryContract() {
    }

    public class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "category_table";

        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CREATION_DATE = "creation_date";

        public static final int NUM_COLUMN_ID = 0;
        public static final int NUM_COLUMN_TITLE = 1;
        public static final int NUM_COLUMN_CREATION_DATE = 2;
    }
}
