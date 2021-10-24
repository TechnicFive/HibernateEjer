

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;




public abstract class ClientDAO {
	
  public static void insertClients(Session s, int numClients) {
    for (int id = 1; id <= numClients; id++) {
      insertClient(s, id);
    }
  }
  
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
		String hQuery = "from Client";
		List<Departamento> clientList = s.createQuery(hQuery, Departamento.class)
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
