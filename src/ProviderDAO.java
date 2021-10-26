

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;




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

	public static void getProvider(Session s, int providerId) {
		// deprecado desde 5.2
		
		//Esto vale para mostrar muchos
		Criteria criteria = s.createCriteria(Empleado.class);
		List<Empleado> result = (List<Empleado>)criteria.add(Restrictions.eq("codDepartamento", providerId)).list();
		System.out.println();
		System.out.println();
		result.stream().forEach(x -> System.out.println(x));
		System.out.println();
		System.out.println();

		
		//Esto vae para mostrar uno solo
		/*CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Empleado> query = builder.createQuery(Empleado.class);
		Root<Empleado> root = query.from(Empleado.class);
     	query.select(root).where(builder.equal(root.get("codDepartamento"), providerId));
     	Query<Empleado> q = s.createQuery(query);
     	Empleado result = q.getSingleResult();
		System.err.println(result.toString());*/
	}
	
	
}
