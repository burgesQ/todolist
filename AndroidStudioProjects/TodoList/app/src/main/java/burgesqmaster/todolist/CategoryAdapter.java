package burgesqmaster.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by master on 1/18/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<Category> _categorys;
    private int _position;
    private Context _mcon;

    public CategoryAdapter(ArrayList<Category> category, Context mcon) {
        _categorys = category;
        _mcon = mcon;
    }

    public int getPosition() {
        return _position;
    }

    public void setPosition(int position) {
        _position = position;
    }

    @Override
    public int getItemCount() {
        return _categorys.size();
    }

    public void update(ArrayList<Category> categorys) {
        _categorys = categorys;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_categorys, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {

        Category category = _categorys.get(position);
        holder.display(category);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener {

        TextView _title;
        TextView _nbTask;
        Category _oneCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            _title = ((TextView) itemView.findViewById(R.id.category));
            _nbTask = ((TextView) itemView.findViewById(R.id.nbTasks));

            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("DEBUG", "Let jump to a category");

                    Intent newIntent = new Intent(_mcon, TasksActivity.class);

                    newIntent.putExtra("TITLE", _oneCategory.getTitle());

                    ((Activity) _mcon).startActivityForResult(newIntent, 1);
                }
            });
        }

        public void display(Category category) {
            _oneCategory = category;
            _title.setText(category.getTitle());

            int done = 0;
            for (Task task : category.getTasks())
                if (task.getDone() == true)
                    ++done;

            _nbTask.setText(category.getTasks().size() + " Task(s), " + done + " done");

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, R.id.menuTitle,
                    Menu.NONE, "Edit Category Title");
            menu.add(Menu.NONE, R.id.menuDelete,
                    Menu.NONE, "Delete Category");
        }


    }

}
