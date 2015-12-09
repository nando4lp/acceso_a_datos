import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

	public static void main(String[] args) {

		if(args.length==7){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Establecemos la conexion con la BD
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "fernando", "fernando");
				
				
			  String apellido=args[1],oficio=args[2];
			  int numeroEmpl=Integer.parseInt(args[0]),numDep=Integer.parseInt(args[6]),salario=Integer.parseInt(args[4]),dir=Integer.parseInt(args[3]),comision=Integer.parseInt(args[5]);
				     
				
				     
				 Statement st = conexion.createStatement();  
				 ResultSet rs = st.executeQuery( "SELECT count(*) FROM empleado WHERE numEmpl='"+numeroEmpl+"'");
				 
			String sql="INSERT INTO empleado values('"+numeroEmpl+"','"+apellido+"','"+oficio+"','"+dir+"','"+salario+"','"+comision+"','"+numDep+"')";
				
				 
				rs.first();
				
				
				if(rs.getInt(1)>0){
					 System.out.println("No se puede insertar");
					 
				
				}
				else{
					rs=st.executeQuery("SELECT count(*) FROM departamento WHERE numDep='"+numDep+"'");
					rs.first();
					if(rs.getInt(1)<=0){
						 System.out.println("No se puede insertar");
					 }
					else{
						if(salario<0){
							System.out.println("No se puede insertar");
						}else{
							rs=st.executeQuery("SELECT count(*) FROM empleado WHERE numEmpl='"+dir+"'");
							rs.first();
							if(rs.getInt(1)<=0){
								 System.out.println("no se puede insertar");
								 
							 }else{
								 st.execute(sql);
								 System.out.println("Ha sido insertada con exito");
							 }
						}
					}
				
				
				}
				rs.close();
				st.close();
				conexion.close();     
				 
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else System.out.println("Ha introducido erroneamente los datos");

	}

}
