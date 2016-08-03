package botMk3;

import org.sqlite.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by TB on 2016-08-03.
 */
public class Sqlite {
    private Connection c;
    private Statement stmt;
    public Sqlite() {
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SKBot.db");
            System.out.println("Opened database successfully");

            try {
                stmt = c.createStatement();
                String sql = "CREATE TABLE Link " +
                        "(id INT PRIMARY KEY     NOT NULL," +
                        " url           TEXT    NOT NULL) ";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();

                System.out.println("Created tables");
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
