package burgesqmaster.todolist;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import static android.support.v7.widget.helper.ItemTouchHelper.Callback.makeMovementFlags;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private Category _tasks;
    private int _position;

    public TasksAdapter(Category tasks) {
        _tasks = tasks;
    }

    public int getPosition() {
        return _position;
    }

    public void setPosition(int position) {
        _position = position;
    }

    @Override
    public int getItemCount() {
        return _tasks.size();
    }

    public void update(Category tasks) {
        _tasks = tasks;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_tasks, parent, false);

        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TasksViewHolder holder, int position) {
        Task task = _tasks.get(position);
        holder.display(task);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });

        holder._done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setPosition(holder.getPosition());
            }
        });


    }

    public class TasksViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener {

        private final TextView _title;
        private final CheckBox _done;
        private Task _task;

        public TasksViewHolder(final View itemView) {
            super(itemView);

            _title = ((TextView) itemView.findViewById(R.id.title));
            _done = ((CheckBox) itemView.findViewById(R.id.done));

            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(_task.getTask())
                            .setMessage(_task.toString())
                            .show();
                }
            });
        }

        public void display(Task oneTask) {
            _task = oneTask;
            _title.setText(oneTask.getTask());
            _done.setChecked(oneTask.getDone());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, R.id.menuTask,
                    Menu.NONE, R.string.editTask);
            menu.add(Menu.NONE, R.id.menuDetails,
                    Menu.NONE, R.string.editDetails);
            menu.add(Menu.NONE, R.id.menuDeadLine,
                    Menu.NONE, R.string.editDeadLine);
            menu.add(Menu.NONE, R.id.menuTime,
                    Menu.NONE, R.string.editTime);
            menu.add(Menu.NONE, R.id.menuShare,
                    Menu.NONE, R.string.share);
            menu.add(Menu.NONE, R.id.menuDelete,
                    Menu.NONE, R.string.deleteTask);
        }

    }
}


