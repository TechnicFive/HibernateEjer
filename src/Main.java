import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);
	
	static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
	}

}
