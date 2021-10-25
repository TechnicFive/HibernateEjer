

import java.util.List;

import org.hibernate.Session;




public abstract class ProviderDAO {
	
  public static void insertProviders(Session s, int numClients) {
    for (int id = 1; id <= numClients; id++) {
    	insertProvider(s, id);
    }
  }
  
	public static void insertProvider(Session s, int codigo) {
		String nombre="Pedro";
		String apellido1="Aullador";
		String apellido2="Pereira";
		String lugarNacimineto="Zamora";
		String fechaNacimiento="15121995";
		String direccion="C/ Me Falta un Tornillo";
		String telefono="980656332";
		String puesto="Humanos";
		int codDepartamento = 50;
		
		//public Empleado(int codigo, String nombre, String apellido1, String apellido2, String lugarNacimiento, String fechaNacimiento, String direccion, String telefono, String puesto, int codDepartamento)		
		Empleado provider = new Empleado(codigo, nombre, apellido1, apellido2, lugarNacimineto, fechaNacimiento, direccion, telefono, puesto, codDepartamento);
		s.save(provider);
	}

	// hql queries
	public static List<Empleado> getAllClients() {
		return getAllClients(HibernateUtil.retrieveSession());
	}
	
	public static List<Empleado> getAllClients(Session s) {
		String hQuery = "from Empleado";
		List<Empleado> clientList = s.createQuery(hQuery, Empleado.class)
				   	   			           .list();
		return clientList;
	}

	
	/*public static Client getClient(Session s, int clientId) {
	  String hQuery = " from Client c " +
	                  " where c.clientId = :clientId";
	  Client client = s.createQuery(hQuery, Client.class)
	                   .setParameter("clientId", clientId)
	                   .setMaxResults(1)
	                   .uniqueResult();
    return client;
	}*/
	
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
