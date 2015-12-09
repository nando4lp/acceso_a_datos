import java.sql.*;
import org.h2.jdbcx.JdbcDataSource;
 
public class H2test {
 
    static Connection conn;
 
    public static void main(String[] args) {

    		//conectamos a la base de datos
        creaConexion();
        //creamos la tabla y le metemos datos
       /* runStatement("create table Empleados ("
                + "Empleados_ID INTEGER, "
                + "Nombre VARCHAR(30))");*/
        /*runStatement("insert into Empleados values (1,'Juan')");
        runStatement("insert into Empleados values (2,'Carolina')");
        runStatement("insert into Empleados values (3,'Paola')");
        runStatement("insert into Empleados values (4,'Alicia')");*/
        //hacemos una consulta para ver los datos de la tabla
        hacerConsulta("SELECT * FROM empleados");
        //runStatement("drop table empleados");
    }
 
    public static void creaConexion() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:file:C:/data/test");
        ds.setUser("sa");
        //ds.setPassword("sa");
        try {
            conn = ds.getConnection();
        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        } finally {
        }
    }
 
    public static void runStatement(String sqlstmt) {
        System.out.println(sqlstmt);
 
 
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlstmt);
            stmt.close();
 
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
 
    public static void hacerConsulta(String sqlstmt) {
      
        try {
            Statement select = conn.createStatement();
            ResultSet result = select.executeQuery(sqlstmt);
            ResultSetMetaData resultMetaData = result.getMetaData();
            int numberOfColumns = resultMetaData.getColumnCount();
            int rownum = 0;
            System.out.println("--Consulta--");
            System.out.println(sqlstmt);
            System.out.println("--Resultados--");
            while (result.next()) { 
                rownum++;
                System.out.print(" Linea " + rownum + " | ");
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.print( resultMetaData.getColumnName(i) + " : " + 
                            result.getString(i) );
                    if (i < numberOfColumns) {
                        System.out.print(", ");
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.err.println("SQLException: " + e.getMessage());
        }
 
    }
}