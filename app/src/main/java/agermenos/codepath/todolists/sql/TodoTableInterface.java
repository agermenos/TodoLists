package agermenos.codepath.todolists.sql;

/**
 * Created by Alejandro on 10/13/15.
 */
public class TodoTableInterface implements TableInterface {
    public static String NAME = "TODOS";
    public static String COLUMNS[][] =
            {   {"ID","integer","PRIMARY KEY"},
                    {"LIST_ID", "integer",""},
                            {"TEXT", "varchar",""},
                    {"CREATION_DATE", "date",""},
                    {"STATUS", "varchar",""},
                    {"PRIORITY", "varchar",""},
        };
}
