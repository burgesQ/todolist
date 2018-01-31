package burgesqmaster.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class TasksActivity extends AppCompatActivity {

    private Category _tasks;
    private String _categoryTitle;
    private TasksAdapter _tasksAdapter;
    private RecyclerView _tasksView;
    private TaskDb _taskDb;

    // TODO reorder Tasks
    // TODO make views|methodes [choice group of task]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        _tasksView = (RecyclerView) findViewById(R.id.tasks);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        _categoryTitle = getIntent().getExtras().getString("TITLE");
        setTitle(_categoryTitle);

        _tasks = new Category(_categoryTitle, new ArrayList<Task>());

        _taskDb = new TaskDb(this);
        _taskDb.open();

        _tasks.setTask(_taskDb.getTaskByCategory(_categoryTitle));

        makeText(this,
                "Their is " + _tasks.size() + " tasks !",
                LENGTH_SHORT).show();

        _tasksAdapter = new TasksAdapter(_tasks);
        _tasksView.setLayoutManager(new LinearLayoutManager(this));
        _tasksView.setItemAnimator(new DefaultItemAnimator());
        _tasksView.setAdapter(_tasksAdapter);
        registerForContextMenu(_tasksView);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


        _taskDb.close();

        Log.i("DEBUG_ACTIVITY", "Task onDestroy");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        Log.i("DEBUG_ACTIVITY", "Task onPause");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

        Log.i("DEBUG_ACTIVITY", "Task onRestart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Log.i("DEBUG_ACTIVITY", "Task onResume");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Log.i("DEBUG_ACTIVITY", "Task onStart");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        Log.i("DEBUG_ACTIVITY", "Task onStop");
    }

    public void onCheckboxClicked(View view) {

        int position = -1;
        try {
            position = _tasksAdapter.getPosition();
        } catch (Exception e) {
            Toast.makeText(this, "Error", LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Task : " + position + " has been checked", LENGTH_SHORT).show();
        editDone(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuSync:
                Toast.makeText(this,
                        "Soooooon ;)",
                        Toast.LENGTH_SHORT);
                break;
            case android.R.id.home:
//                writeItems();
                this.finish();
                break;
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = _tasksAdapter.getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menuTask:
                editTask(position);
                break;
            case R.id.menuDetails:
                editDetails(position);
                break;
            case R.id.menuDeadLine:
                editDeadLine(position);
                break;
            case R.id.menuTime:
                editTime(position);
                break;
            case R.id.menuShare:
                shareTask(position);
                break;
            case R.id.menuDelete:
                deleteTask(position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    final public boolean editTask(int id) {

        final Task item = _tasks.get(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Value of The Task");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setTask(input.getText().toString());
                _tasksAdapter.update(_tasks);
                _tasksAdapter.notifyDataSetChanged();

                _taskDb.updateTaskString((int) item.getId(), TaskContract.FeedEntry.COLUMN_NAME_TASK, item.getTask());

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

    final public boolean editDone(int id) {

        final Task item = _tasks.get(id);
        Boolean state = item.getDone();

        item.setDone(!state);
        _tasksAdapter.update(_tasks);
        _tasksAdapter.notifyDataSetChanged();

        _taskDb.updateTaskBool((int) item.getId(), TaskContract.FeedEntry.COLUMN_NAME_DONE, item.getDone());

        return true;
    }

    final public boolean editDetails(int id) {

        final Task item = _tasks.get(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Details for the task ");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setDetails(input.getText().toString());
                _tasksAdapter.update(_tasks);

                _tasksAdapter.notifyDataSetChanged();
                _taskDb.updateTaskString((int) item.getId(), TaskContract.FeedEntry.COLUMN_NAME_DETAILS, item.getDetails());
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

    public boolean editTime(int id) {

        final Task item = _tasks.get(id);
        final TimePicker input = new TimePicker(this);

        Log.i("DEBUG", "In time picker");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Value of The time for DeadLine");
        builder.setView(input);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Date date = item.getDeadLine();

                date.setHours(input.getHour());
                date.setMinutes(input.getMinute());
                date.setSeconds(0);

                item.setDeadLine(date);

                _tasksAdapter.update(_tasks);
                _tasksAdapter.notifyDataSetChanged();
                _taskDb.updateTaskLong((int) item.getId(), TaskContract.FeedEntry.COLUMN_NAME_DEADLINE, item.getDeadLine().getTime());
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

    public boolean editDeadLine(int id) {

        final Task item = _tasks.get(id);
        final DatePicker input = new DatePicker(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Value of The DeadLine");
        builder.setView(input);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Log.i("DEBUG_DATE", String.valueOf(input.getDayOfMonth()));
                Log.i("DEBUG_DATE", String.valueOf(input.getMonth()));
                Log.i("DEBUG_DATE", String.valueOf(input.getYear()));

                Date date = item.getDeadLine();

                date.setDate(input.getDayOfMonth());
                date.setMonth(input.getMonth());
                date.setYear((input.getYear() - 1900));

                item.setDeadLine(date);

//                Log.i("DEBUG_DATE_OUT", String.valueOf(date.getYear()));
//                Log.i("DEBUG_DATE_OUT", item.toString());

                _tasksAdapter.update(_tasks);
                _tasksAdapter.notifyDataSetChanged();
                _taskDb.updateTaskLong((int) item.getId(), TaskContract.FeedEntry.COLUMN_NAME_DEADLINE, item.getDeadLine().getTime());

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

    // use User ?
    final public boolean editTasker(int id) {

        final Task item = _tasks.get(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Value of The Tasker");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setTasker(input.getText().toString());
                _tasksAdapter.update(_tasks);
                _tasksAdapter.notifyDataSetChanged();
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

    public boolean editColor(int id) {
        makeText(this,
                "Let's edit the color of the task : " + id + "!",
                LENGTH_SHORT).show();

        final Task task = _tasks.get(id);

        final CharSequence _colors[] = new CharSequence[]
                {"red", "blue", "yellow", "default"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a color");
        builder.setItems(_colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Log.i("DEBUG_COLORS", (String) _colors[i]);

                task.setColor((String) _colors[i]);

                _tasksAdapter.update(_tasks);
                _tasksAdapter.notifyDataSetChanged();
            }
        });
        builder.show();

        return true;
    }

    public boolean shareTask(int id) {
        makeText(this,
                "Sharing Task : " + id + "!",
                LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, _tasks.get(id).toString());
        startActivity(Intent.createChooser(intent, "Share!"));

        return true;
    }

    public boolean deleteTask(int id) {

        makeText(this,

                "Delete Task : " + id + "!",
                LENGTH_SHORT).show();

        Task toDelete = _tasks.get(id);
        _tasks.remove(id);

        _tasksAdapter.update(_tasks);
        _tasksAdapter.notifyDataSetChanged();

        _taskDb.removeTaskWithID((int) toDelete.getId());

        return true;
    }

    public void addTask(View v) {

        Task newTask = new Task("", new Date());

        newTask.setId(_taskDb.insertTask(newTask, _categoryTitle));

        _tasks.addTask(newTask);
        _tasksAdapter.update(_tasks);

        editTask(_tasks.size() - 1);
        editDeadLine(_tasks.size() - 1);
//        editColor
//        editPrioryti

        makeText(this,
                "New Task : " + newTask.getTask() + " created !",
                LENGTH_SHORT).show();
    }

}
