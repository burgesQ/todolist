package burgesqmaster.todolist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by master on 1/11/17.
 */

public class Task implements Serializable {

    private Boolean _done;
    private String _details;
    private String _task;
    private String _owner;
    private String _tasker;
    private String _color;
    private String _flag;
    private Date _deadLine;
    private Date _creationDate;
    private int _priority;
    private long _id;

    public Task(String Title, Date DeadLine) {

        _done = false;
        _task = Title;
        _details = "";
        _owner = "You";
        _tasker = "Yourself";
        _color = "default";
        _flag = "";
        _creationDate = new Date();
        _deadLine = DeadLine;

    }

    @Override
    public String toString() {
        String ret = "";

        ret += "[" + _task + "]";

        if (!_details.equals(""))
            ret += "\n" + _details;

        ret += "\nCreated by " + _owner + "\nThe "
                + _creationDate.toString();

        ret += "\nFor " + _tasker + "\nFor the "
                + _deadLine.toString();

        return ret;
    }

    public final String getTask() {
        return _task;
    }

    public void setTask(final String task) {
        _task = task;
    }

    public final String getDetails() {
        return _details;
    }

    public void setDetails(final String details) {
        _details = details;
    }

    public final String getTasker() {
        return _tasker;
    }

    public void setTasker(final String Tasker) {
        _tasker = Tasker;
    }

    public final Date getDeadLine() {
        return _deadLine;
    }

    public void setDeadLine(final Date DeadLine) {
        _deadLine = DeadLine;
    }

    public final String getColor() {
        return _color;
    }

    public void setColor(final String color) {
        _color = color;
    }

    public final Boolean getDone() {
        return _done;
    }

    public void setDone(final Boolean Done) {
        _done = Done;
    }

    public final String getOwner() {
        return _owner;
    }

    public void setOwner(final String owner) {
        _owner = owner;
    }

    public final Date getCreationDate() {
        return _creationDate;
    }

    public final int getPriority() {
        return _priority;
    }

    public void setPriority(final int priority) {
        _priority = priority;
    }

    public final String getFlag() {
        return _flag;
    }

    public void setFlag(final String flag) {
        _flag = flag;
    }

    public final long getId() {
        return _id;
    }

    public void setId(final long id) {
        _id = id;
    }
}
