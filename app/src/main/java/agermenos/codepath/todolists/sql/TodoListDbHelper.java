package agermenos.codepath.todolists.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandro on 10/13/15.
 */
public class TodoListDbHelper extends SQLiteOpenHelper{
    public final static String DATABASE_NAME="TodoList";
    public final static int DATABASE_VERSION=1;

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
        createScript.replace(createScript.length()-2, createScript.length()-1, ")");
        return createScript.toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoListTableInterface.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TodoTableInterface.NAME);
        // create new tables
        onCreate(db);
    }
}
