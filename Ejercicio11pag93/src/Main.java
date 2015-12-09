import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "fernando", "fernando");

			int dep = 1;

			Statement s = conexion.createStatement();

			ResultSet resultado = s.executeQuery("SELECT count(*) FROM departamento WHERE numDep='" + dep + "'");
			resultado.first();
			if (resultado.getInt(1) <= 0) {
				System.out.println("no existe ese departamento");

			} else {
				PreparedStatement st = conexion.prepareStatement(
						"SELECT apellido,salario,oficio,departamento.nombre FROM empleado,departamento WHERE empleado.numDep=? and empleado.numDep=departamento.numDep");

				st.setInt(1, dep);

				resultado = st.executeQuery();

				while (resultado.next()) {
					System.out.println("El apellido: " + resultado.getString(1) + " su salario es:"
							+ resultado.getInt(2) + " su oficio es:" + resultado.getString(3) + " Nombre departamento: "
							+ resultado.getString(4));
				}

				st = conexion.prepareStatement(
						"SELECT AVG(salario) AS salarioMedio,COUNT(numEmpl) as NumeroEmpleado FROM empleado,departamento WHERE empleado.numDep=? and empleado.numDep=departamento.numDep");
				st.setInt(1, dep);
				resultado = st.executeQuery();
				
				
				resultado.first();
					if(resultado.getInt(1)==0){
						System.out.println("No existe el departamento");
					}
					else{
					System.out.println("El salario medio: " + resultado.getInt(1) + " El numero de empleados: "
							+ resultado.getInt(2));
					}
					
					resultado.close();
					st.close();
			}
			
			s.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
