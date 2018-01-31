package burgesqmaster.todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by master on 1/18/17.
 */

public class Category implements Serializable {

    private String _title;
    private ArrayList<Task> _tasks;
    private Date _creationDate;
    private long _id;

    public Category(String title, ArrayList<Task> tasks) {
        _title = title;
        _tasks = tasks;
        _creationDate = new Date();
    }

    public void setTask(ArrayList<Task> tasks) {
        _tasks = tasks;
    }

    public void addTask(Task task) {
        _tasks.add(task);
    }

    public void remove(int id) {
        _tasks.remove(id);
    }

    public final int size() {
        return _tasks.size();
    }

    public final Task get(int id) {
        return _tasks.get(id);
    }

    public final String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public final ArrayList<Task> getTasks() {
        return _tasks;
    }

    public final Date getCreationDate() {
        return _creationDate;
    }

    public void setCreationDate(Date date) {
        _creationDate = date;
    }

    public final long getId() {
        return _id;
    }

    public void setId(final long id) {
        _id = id;
    }
}
