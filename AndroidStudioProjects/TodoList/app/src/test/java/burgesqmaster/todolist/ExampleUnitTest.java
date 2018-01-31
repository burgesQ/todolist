package burgesqmaster.todolist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void creationTask()
    {
        Task task = new Task("Urgent", new Date());

//        final String match = "[" + task.getTask() + "]\n" + task.getDetails()
//                + "\nCreated by " + task.getOwner() + "\nAttribut to " + task.getTasker()
//                + "\nThe " + task.getCreationDate().toString() + "\nFor the "
//                + task.getDeadLine().toString();
//        assertEquals(task.toString(), match);

        task.setOwner("Turlututu");
        assertEquals(task.getOwner(), "Turlututu");
        task.setColor("red");
        assertEquals(task.getColor(), "red");
        task.setPriority(10);
        assertEquals(task.getPriority(), 10);
        assertEquals(task.getTask(), "Urgent");


    }

    @Test
    public void creationCategory()
    {
        Category cat = new Category("Turlu", new ArrayList<Task>());

        assertEquals(cat.getTitle(), "Turlu");

        cat.addTask(new Task("Tutu", new Date()));

        assertEquals(cat.size(), 1);

        cat.remove(0);

        assertEquals(cat.size(), 0);

    }


}