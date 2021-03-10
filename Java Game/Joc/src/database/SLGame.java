package database;
import java.sql.*;
public class SLGame {

    public static void create() throws Exception{

    Connection c = null;

    try
    {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:info.db");
        System.out.println("SQLite DB connected");
    }
    catch(Exception e)
    {
        System.out.println(e);
    }}
}
