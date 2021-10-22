package com.serbatic.course.datamodel.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.serbatic.course.datamodel.entities.xmlmapping.Provider;
import com.serbatic.course.datamodel.utils.StringUtil;

public abstract class ProviderDAO {

	public static void insertProviders(Session s, int numProvider) {
		for (int id = 1; id <= numProvider; id++) {
			insertProvider(s, id);
		}
	}
	
	public static void insertProvider(Session s, int genId) {
		String cif = StringUtil.getLeftPaddedWithZeros(genId, 6); 
		cif = "A49" + cif;
		String razonSocial = "Razón Social " + genId;
		String direccion = "Dirección " + genId;
		int telefono = 600000000 + genId;
		String email = "proveedor" + genId + "@gmail.com";
		
		Provider provider = new Provider(genId, cif, razonSocial, direccion, Integer.toString(telefono), email);
		s.save(provider);
	}

	// hql queries

	// Native queries
		
	// Criteria queries
	public static List<Provider> getAllProviders(Session s) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Provider.class);
		List<Provider> result = criteria.getExecutableCriteria(s).list();
		return result;
	}
	
	public static Provider getProvider(Session s, int providerId) {
		// deprecado desde 5.2
		Criteria criteria = s.createCriteria(Provider.class);
		Provider result = (Provider)criteria.add(Restrictions.eq("providerId", providerId))
											.setMaxResults(1)
											.uniqueResult();
		
//		CriteriaBuilder builder = s.getCriteriaBuilder();
//		CriteriaQuery<Provider> query = builder.createQuery(Provider.class);
//		Root<Provider> root = query.from(Provider.class);
//     	query.select(root).where(builder.equal(root.get("providerId"), providerId));
//      Query<Provider> q = s.createQuery(query);
//      Provider result = q.getSingleResult();
		return result;
	}
}
