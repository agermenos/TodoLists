package agermenos.codepath.todolists.sql;

/**
 * Created by Alejandro on 10/13/15.
 */
public class TodoListTable extends BaseTable {
    public static final String COLUMNS[][] =
            {
                    {"ID","integer", "PRIMARY KEY"},
                    {"TEXT", "varchar",""}
            };
    public static final String NAME="TODO_LIST";


    @Override
    public String getColumns(int x, int y) {
        return COLUMNS[x][y];
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getSize(){
        return COLUMNS.length;
    }
}
