import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		try {
			//cargar el driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:odbc:ejemploAccess");
			
			//Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT * FROM departamentos");
			
			//Recorremos el resultado para visualizar cada fila
			//Se hace un bucle mientras haya registros, se van visualizando
			
			while(resul.next()){
				System.out.println(resul.getInt(1) + " " + resul.getString(2)+ " " + resul.getString(3));
			}
			resul.close(); //Cerrar ResultSet
			sentencia.close(); //Cerrar Statement
			conexion.close(); //Cerrar conexion
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
