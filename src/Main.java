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







public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);
	
	static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		String methodName = Main.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int codigo=Integer.parseInt(JOptionPane.showInputDialog("Escriba la id del empleado"));//Poner JOptionpane
			ClientDAO.insertClient(session, codigo);
			
			// Recuperamos y listamos departamentos
			List<Departamento> clients = ClientDAO.getAllClients(session);
			logger.info(String.format("%1$s: number of clients = %2$s.", methodName, clients.size()));
			clients.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
			//session.save(clients);
			tx.commit();
		
		}catch(Exception e){
			if (tx != null) {
		    tx.rollback();
			}
		}
	}

}


