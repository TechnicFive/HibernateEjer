

import java.util.List;

import org.hibernate.Session;

import com.mysql.cj.xdevapi.Client;



public abstract class ClientDAO {
	
  public static void insertClients(Session s, int numClients) {
    for (int id = 1; id <= numClients; id++) {
      insertClient(s, id);
    }
  }
  
	public static void insertClient(Session s, int id) {
		String nif = StringUtil.getLeftPaddedWithZeros(id, 8); 
		nif = nif + StringUtil.calculateDniLetter(nif);
		String primerApellido = "1er Apellido " + id;
		String segundoApellido = "2º Apellido " + id;
		String nombre = "Nombre " + id;
		String direccion = "Dirección " + id;
		int telefono = 700000000 + id;
		String email = "cliente" + id + "@gmail.com";
		
		//public Empleado(int codigo, String nombre, String apellido1, String apellido2, String lugarNacimiento, String fechaNacimiento, String direccion, String telefono, String puesto, int codDepartamento)
		//public Departamento(int codigo, String nombre, int codResponsable)
		Departamento client = new Departamento(codigo, nombre, codResponsable);
		s.save(client);
	}

	// hql queries
	public static List<Client> getAllClients() {
		return getAllClients(HibernateUtil.retrieveSession());
	}
	
	public static List<Client> getAllClients(Session s) {
		String hQuery = "from Client";
		List<Client> clientList = s.createQuery(hQuery, Client.class)
				   	   			           .list();
		return clientList;
	}
	
	public static Client getClient(Session s, int clientId) {
	  String hQuery = " from Client c " +
	                  " where c.clientId = :clientId";
	  Client client = s.createQuery(hQuery, Client.class)
	                   .setParameter("clientId", clientId)
	                   .setMaxResults(1)
	                   .uniqueResult();
    return client;
	}
	
	// Native queries
	public static List<Client> getClientsWithStatements(Session s) {
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
	}
	
	
	// Criteria queries
}
