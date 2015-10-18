package agermenos.codepath.todolists;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.Date;

import agermenos.codepath.todolists.pojos.Todo;
import agermenos.codepath.todolists.pojos.TodoList;
import agermenos.codepath.todolists.sql.TodoListDbHelper;

/**
 * Created by Alejandro on 10/17/15.
 */
public class DatabaseTest extends InstrumentationTestCase {
    private TodoListDbHelper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getInstrumentation().getContext(), "test_");
        db = new TodoListDbHelper(context);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    @SmallTest
    public void testCreateDatabase(){
        TodoList todoList = new TodoList("Test");
        todoList.setId((int) db.createToDoList(todoList));

        Todo todo = new Todo(todoList.getId(), "Todo Something", new Date(), Todo.OPEN_STATUS, Todo.Priority.HIGH);
        long todoId = db.createToDo(todo);
    }

}
