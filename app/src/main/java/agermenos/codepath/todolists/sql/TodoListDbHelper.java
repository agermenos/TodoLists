package agermenos.codepath.todolists.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import agermenos.codepath.todolists.pojos.Todo;

/**
 * Created by Alejandro on 10/13/15.
 */
public class TodoListDbHelper extends SQLiteOpenHelper{
    public final static String DATABASE_NAME="TodoList";
    public final static int DATABASE_VERSION=1;
    private static final String LOG = "TodoTableInterface";

    public TodoListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(createTableScript(new TodoListTableInterface()));
        db.execSQL(createTableScript(new TodoTableInterface()));
    }

    private String createTableScript(TableInterface tableInterface){
        StringBuffer createScript = new StringBuffer ("CREATE TABLE " + tableInterface.NAME);
        createScript.append(" (");
        for (String row[]:tableInterface.COLUMNS) {
            createScript.append(row[0]).append(" ").append(row[1]).append((row[3]!=null?" "+row[3]:"")).append(",");
        }
        createScript.replace(createScript.length() - 2, createScript.length() - 1, ")");
        return createScript.toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoListTableInterface.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TodoTableInterface.NAME);
        // create new tables
        onCreate(db);
    }
    /**
    * Creating a todo
    */
    public long createToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoTableInterface.COLUMNS[0][0], todo.getId());
        values.put(TodoTableInterface.COLUMNS[0][1], todo.getListId());
        values.put(TodoTableInterface.COLUMNS[0][2], todo.getText());
        values.put(TodoTableInterface.COLUMNS[0][3], String.valueOf(todo.getCreationDate()));
        values.put(TodoTableInterface.COLUMNS[0][4], todo.getStatus());
        values.put(TodoTableInterface.COLUMNS[0][5], String.valueOf(todo.getPriority()));

        // insert row
        long todo_id = db.insert(TodoTableInterface.NAME, null, values);

        return todo_id;
    }

    public Todo getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TodoTableInterface.NAME + " WHERE "
                + TodoTableInterface.COLUMNS[0][0] + " = " + todo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Todo td = new Todo();
        td.setId(c.getInt(c.getColumnIndex(TodoTableInterface.COLUMNS[0][0])));
        td.setListId(c.getInt(c.getColumnIndex(TodoTableInterface.COLUMNS[0][1])));
        td.setText(c.getString(c.getColumnIndex(TodoTableInterface.COLUMNS[0][2])));
        td.setCreationDate(new Date(c.getString(c.getColumnIndex(TodoTableInterface.COLUMNS[0][3]))));
        td.setStatus(c.getString(c.getColumnIndex(TodoTableInterface.COLUMNS[0][4])));
        td.setPriority(Todo.choosePriority(c.getString(c.getColumnIndex(TodoTableInterface.COLUMNS[0][5]))));

        return td;
    }
}
