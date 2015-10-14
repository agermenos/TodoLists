package agermenos.codepath.todolists.sql;

/**
 * Created by Alejandro on 10/13/15.
 */
public class TodoListTableInterface implements TableInterface {
    public static String COLUMNS[][] =
            {
                    {"ID","integer", "PRIMARY KEY"},
                    {"TEXT", "varchar",""}
            };
    public static String NAME="TODO_LIST";
}
