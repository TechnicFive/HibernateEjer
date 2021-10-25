import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateMain {
	
	private static Logger logger = LogManager.getLogger(HibernateMain.class);
	
	static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		String methodName = HibernateMain.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));
		// La SessionFactory se instancia 1 vez por ejecución del programa.
		// Todas las sesiones de hibernate se obtienen de esa SessionFactory;	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
		  // Las opereaciones save/update/delete
			BasicConfigurator.configure();
			tx = session.beginTransaction();
			int codigo=Integer.parseInt(JOptionPane.showInputDialog("Escriba la id del empleado"));//Poner JOptionpane

			
			// Insertamos proveedores
			ClientDAO.insertClients(session, codigo);
			
			/*// Recuperamos y listamos proveedores
			List<Departamento> providers = ClientDAO.getAllClients(session);
			logger.info(String.format("%1$s: number of providers = %2$s.", methodName, providers.size()));
			providers.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
			
			// Actualizamos proveedor 1
			Departamento provider = providers.stream()
  										 .filter(x -> x.getCodigo() == 5)
  										 .findFirst()
  										 .orElse(null);
			if (provider != null) {
				provider.setNombre("One Moment Please");
			}
			
			// Insertamos clientes
			int numClients = 2;
			ProviderDAO.insertProviders(session, numClients);
			
			// Recuperamos y listamos clientes
			List<Empleado> clients = ProviderDAO.getAllClients(session);
			logger.info(String.format("%1$s: number of clients = %2$s.", methodName, clients.size()));
			clients.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
			
			// Insertamos factura
			int clientId = numClients; // por ejemplo, el último insertado
			Empleado client = clients.stream().filter(x -> x.getCodigo() == clientId).findFirst().orElse(null);
			StatementPk statementPk = new StatementPk(client.getCodigo());
			Statement statement = new Statement();
			statement.setStatementPk(statementPk);
			statement.setIdFromClient(client);
			statement.setAmount(100);
			LocalDateTime billingDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
			statement.setBillingDate(billingDate.format(formatter));
			session.save(statement);*/
			tx.commit();
			
		
		}catch (Exception e) {
			  if (tx != null) {
				    tx.rollback();
				  }
					logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
				}
				finally {
					if (session != null) {
						session.close();
					}
				}
		
		logger.info(String.format("%1$s: >>>>>> Main execution finished.", methodName));
	}	
}
