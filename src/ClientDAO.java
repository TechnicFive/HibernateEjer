

import java.util.List;

import javax.persistence.Column;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;




public abstract class ClientDAO {
	
  public static void insertClients(Session s, int numClients) {
    //for (int id = 1; id <= numClients; id++) {
      insertClient(s, numClients);
    //}
  }
  	//Insercion
	public static void insertClient(Session s, int codigo) {
		String nombre=JOptionPane.showInputDialog("Nombre del departamento");
		int codResponsable = Integer.parseInt(JOptionPane.showInputDialog("Introduce la id del responsable"));
		//public Departamento(int codigo, String nombre, int codResponsable) aqui hacemos la declaracion de insertar departamento
		Departamento client = new Departamento(codigo, nombre, codResponsable);
		client.toString();
		s.save(client);
		
	}

	// hql queries
	public static List<Departamento> getAllClients() {
		return getAllClients(HibernateUtil.retrieveSession());
	}
	
	public static List<Departamento> getAllClients(Session s) {
		String hQuery = "from Departamento";
		List<Departamento> clientList = s.createQuery(hQuery, Departamento.class)
				   	   			           .list();
		return clientList;
	}
	
	//Actualizacion
	public static void actualizacionDepar(Session s, int clientId) {
		Departamento depar=new Departamento();
		System.out.println("Que dato desea modificar");
		System.out.println("1.- Nombre departamento");
		System.out.println("2.- codigo de responsable");
		int decision=Integer.parseInt(JOptionPane.showInputDialog("Que quiere modificar"));
		
		if(decision==1) {
			depar.setCodigo(clientId);
			depar.setNombre(JOptionPane.showInputDialog("Cual es el nuevo nombre de departamento"));
			depar.getCodResponsable();
			s.update(Departamento.class.getName(), depar);
		}else if(decision==2) {
			depar.setCodigo(clientId);
			depar.getNombre();
			depar.setCodResponsable(Integer.parseInt(JOptionPane.showInputDialog("Escriba el nuevo codigo de responsable")));
			s.update(Departamento.class.getName(), depar);
		}
	}
	//Lectura
	
	//Borrado
	public static void getDelete(Session s, int clientId) {
		Departamento depar= new Departamento();
		depar.setCodigo(clientId);
		depar.setNombre("");
		depar.setCodResponsable(1);
		s.delete(Departamento.class.getName(),depar);
	}
	
	// Native queries
	/*public static List<Client> getClientsWithStatements(Session s) {
	  String nQuery = 
	      " select c.* from CLIENTE c " +
        " left join FACTURA f " +
	      "        on f.ID_CLIENTE = c.ID" +
	      "       and f.ID_FACTURA = (select max(ID_FACTURA) from FACTURA" +
	      "                           where ID_CLIENTE = c.ID)" +
	      " where f.ID_FACTURA is not null";
	  List<Client> clientList = s.createNativeQuery(nQuery)
	                             .addEntity(Client.class)
                               .list();
	  return clientList;	  
	}*/
	
	
	// Criteria queries
}
