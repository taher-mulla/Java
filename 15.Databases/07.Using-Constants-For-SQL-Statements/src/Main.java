import java.sql.*;

public class Main {

    public static final String DB_NAME = "database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/6.JavaIntegrationIntroduction/"+DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";

    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            //DROP TABLE IF EXISTS
            statement.execute("DROP TABLE IF EXISTS "+TABLE_CONTACTS);

            //CREATE TABLE IS NOT EXISTS
            statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE_CONTACTS+
                    "("+COLUMN_NAME+" TEXT, "+COLUMN_PHONE+" INTEGER, "+COLUMN_EMAIL+" TEXT"+")");

            //Adding values to table
            statement.execute("INSERT INTO "+TABLE_CONTACTS+
                    "("+COLUMN_NAME+" , "+COLUMN_PHONE+" , "+COLUMN_EMAIL+" "+")"+
                    "VALUES ('Taher', 9999999, 'taher.com')");
            statement.execute("INSERT INTO "+TABLE_CONTACTS+
                    "("+COLUMN_NAME+" , "+COLUMN_PHONE+" , "+COLUMN_EMAIL+" "+")"+
                    "VALUES ('Sohail', 888888, 'sohail.com')");
            statement.execute("INSERT INTO "+TABLE_CONTACTS+
                    "("+COLUMN_NAME+" , "+COLUMN_PHONE+" , "+COLUMN_EMAIL+" "+")"+
                    "VALUES ('Haseef', 77777777, 'haseef.com')");

            //update
            statement.execute("UPDATE "+TABLE_CONTACTS+" SET "+COLUMN_PHONE+"=11111"+" WHERE "+COLUMN_NAME+"='Taher'");

            //delete
            statement.execute("DELETE FROM "+TABLE_CONTACTS+" WHERE "+COLUMN_NAME+"='haseef'");



            ResultSet results2 = statement.executeQuery("SELECT * FROM "+TABLE_CONTACTS);
            while(results2.next()){
                System.out.println(results2.getString(COLUMN_NAME)+" | "+
                        results2.getInt(COLUMN_PHONE)+" | "+
                        results2.getString(COLUMN_EMAIL));
            }
            results2.close();



            statement.close();
            conn.close();

        }catch(SQLException e){
            System.out.println("Catch "+e.getMessage());
        }


    }
}
