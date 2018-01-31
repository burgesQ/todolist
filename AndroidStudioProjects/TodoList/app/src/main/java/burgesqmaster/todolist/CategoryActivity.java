package burgesqmaster.todolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by master on 1/18/17.
 */

/**
 * Add google Calendar API
 * Add google Acount/User gestion
 * Slide to delete
 * LongPres N drag to reorder
 */

public class CategoryActivity extends AppCompatActivity {

    private ArrayList<Category> _categorys;
    private CategoryAdapter _categorysAdapter;
    private RecyclerView _categorysView;
    private CategoryDb _categoryDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        _categorysView = (RecyclerView) findViewById(R.id.categorys);

        _categoryDb = new CategoryDb(this);
        _categoryDb.open();
        _categorys = _categoryDb.getCategorys();

        _categorysAdapter = new CategoryAdapter(_categorys, CategoryActivity.this);
        _categorysView.setLayoutManager(new LinearLayoutManager(this));
        _categorysView.setAdapter(_categorysAdapter);

        registerForContextMenu(_categorysView);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = _categorysAdapter.getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menuTitle:
                Toast.makeText(CategoryActivity.this,
                        "Title",
                        LENGTH_SHORT).show();
                editCategoryTitle(position);
                break;
            case R.id.menuDelete:
                deleteCategory(position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        _categoryDb.close();
        Log.i("DEBUG_ACTIVITY", "Category onDestroy");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

        _categorys = _categoryDb.getCategorys();
        _categorysAdapter.update(_categorys);
        _categorysAdapter.notifyDataSetChanged();

        Log.i("DEBUG_ACTIVITY", "Category onRestart");
    }

    public void addCategory(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name of the new category");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String title = input.getText().toString();

                Log.i("DEBUG_TITLE", title);
                for (Category cat : _categorys) {
                    Log.i("DEBUG_TITLE", cat.getTitle());

                    if (title.equals(cat.getTitle())) {
                        Toast.makeText(CategoryActivity.this,
                                "Category " + title + " already exist !",
                                LENGTH_SHORT).show();

                        return;
                    }
                }

                final Category newCategory = new Category(title, new ArrayList<Task>());
                newCategory.setId(_categoryDb.insertCategory(newCategory));
                _categorys.add(newCategory);

                _categorysAdapter.update(_categorys);
                _categorysAdapter.notifyDataSetChanged();

                Log.i("DEBUG", "New category " + newCategory.getTitle());
                Toast.makeText(CategoryActivity.this,
                        "New Category created : " + newCategory.getTitle(),
                        LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public boolean deleteCategory(int id) {

        Toast.makeText(this,
                "Delete Task : " + id + "!",
                LENGTH_SHORT).show();

        Category onDelete = _categorys.get(id);
        _categorys.remove(id);
        _categoryDb.removeCategoryWithID(onDelete);

        _categorysAdapter.update(_categorys);
        _categorysAdapter.notifyDataSetChanged();

        return true;
    }

    final public boolean editCategoryTitle(final int id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Value of The Title");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Category oneCategory = _categorys.get(id);
                String title = input.getText().toString();

                for (Category cat : _categorys)
                    if (title.equals(cat.getTitle())) {
                        Toast.makeText(CategoryActivity.this,
                                "Category " + title + " already exist !",
                                LENGTH_SHORT).show();
                        return;
                    }


                oneCategory.setTitle(title);

                _categoryDb.updateCategory((int) oneCategory.getId(), oneCategory);

                _categorysAdapter.update(_categorys);
                _categorysAdapter.notifyDataSetChanged();

                Log.i("DEBUG", "Change category to " + oneCategory.getTitle());
                Toast.makeText(CategoryActivity.this,
                        "Changed Category Name to : " + oneCategory.getTitle(),
                        LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        return true;
    }

}
