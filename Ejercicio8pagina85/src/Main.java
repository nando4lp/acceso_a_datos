import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;

public class Main {

	static Connection conn;
	
	public static void main(String[] args) {
		try{
			
			//Establecemos la conexion con la BD
			creaConexion();
			
			DatabaseMetaData dbmd = conn.getMetaData(); //Creamos objeto
			ResultSet resul = null;
			
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFROMACIÓN SOBRE LA BASE DE DATOS:");
			System.out.println("===================================");
			System.out.println("Nombre 	: "+ nombre);
			System.out.println("Driver 	: "+ driver);
			System.out.println("URL 	: "+ url);
			System.out.println("Usuario : "+ usuario);
			
			//Obtener información de las tablas y vistas que hay
			resul = dbmd.getTables(null, null, null, new String[] {"TABLE"});
			
			while(resul.next()){
				String catalogo = resul.getString(1); 	//columna 1 que devuelve ResulSet
				String esquema = resul.getString(2);	//columna 2
				String tabla = resul.getString(3);		//columna 3
				String tipo = resul.getString(4);		//columna 4
				System.out.println(tipo + " - Catalogo: "+ catalogo + ", Esquema : "+esquema+", Nombre: "+tabla);
			}
			
			conn.close(); //cerrar conexion
		} 
		catch (SQLException e) {e.printStackTrace();}

	}
	
	 public static void creaConexion() {
	        JdbcDataSource ds = new JdbcDataSource();
	        ds.setURL("jdbc:h2:file:C:/data/ejemplo");
	        ds.setUser("sa");
	        //ds.setPassword("sa");
	        try {
	            conn = ds.getConnection();
	        } catch (Exception e) {
	            System.err.println("IOException: " + e.getMessage());
	        } finally {
	        }
	    }

}
