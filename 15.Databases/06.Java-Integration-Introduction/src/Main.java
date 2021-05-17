import java.sql.*;

public class Main {

    public static void main(String[] args) {

        //Database should be always closed when we finish using it
        try{
            Connection conn = DriverManager.getConnection
                    ("jdbc:sqlite:/home/user/Java-Learning/Udemy-Java/19.Databases/6.JavaIntegrationIntroduction/database.db");

            //JDBC sets the commit of ture by default, so the moment a line of code is executed the data base is updated and changed
            //we can change the AutoCommit like this
//            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();
            //it is a good practice to keep the sql code in caps (uppercase) and the user statements in lower
            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");


            //adding data
            //the table will have multiple similar entries if not commented
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Taher', 9999999, 'taher.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Sohail', 111111, 'sohail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Fatema', 2222222, 'fatema.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Tom', 333333, 'tom.com')");

            //updating data
//            statement.execute("UPDATE contacts SET phone=00000 WHERE name='Taher' ");

            //deleting data
//            statement.execute("DELETE FROM contacts WHERE name='Taher'");

            //getting data
            //Method 1 to display data
            statement.execute("SELECT * FROM contacts");
            ResultSet results1 = statement.getResultSet();
            while(results1.next()){
                System.out.println(results1.getString("name")+" "+
                        results1.getInt("phone")+" "+
                        results1.getString("email"));
            }
            results1.close();
            //Method 2 to display data
            ResultSet results2 = statement.executeQuery("SELECT * FROM contacts");
            while(results2.next()){
                System.out.println(results2.getString("name")+" "+
                        results2.getInt("phone")+" "+
                        results2.getString("email"));
            }
            results2.close();




            //the order in which we close resources is also important
            //close statements before connections because the statements use connections, if you close connections the statements are of no use
            statement.close();
            conn.close();

        }catch(SQLException e){
            System.out.println("Catch "+e.getMessage());
        }



    }
}



