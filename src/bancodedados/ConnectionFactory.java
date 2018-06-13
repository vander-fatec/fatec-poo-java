package bancodedados;

import bancodedados.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionPool pool;
    
    public ConnectionFactory(){
        if(pool == null){
            Properties props = new Properties();
            props.put("user","usuario" );
            props.put("password", "123456");

            try {
                pool = new ConnectionPool.Builder()
                        .setInitialCapacity(3)
                        .setMaxCapacity(10)
                        .setStep(2)
                        .setConnectionString("jdbc:mysql://localhost:3306/SisIngressos")
                        .setDriver("com.mysql.jdbc.Driver")
                        .setProperties(props)
                        .setTimeOut(0)
                        .build();
            } catch (ClassNotFoundException ex ) {
                throw new RuntimeException(ex); 
            } catch(SQLException ex){
                throw new RuntimeException(Integer.toString(ex.getErrorCode()),ex.getCause()); 
            }
        }
    }
    
    public Connection getConnection(){
        Connection con = null;
        try{           
            con = pool.checkOut();
        }catch(SQLException ex){
            throw new RuntimeException(Integer.toString(ex.getErrorCode()),ex.getCause()); 
        }
        return con;
    }
}