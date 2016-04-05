package beans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import core.ejb.Income;
import core.enums.ClientType;

@Stateless(name = "IncomeBean")
public class IncomeServiceBean implements IncomeService {

	@PersistenceContext(unitName = "IncomePersistenceUnit")
	private EntityManager em;

	public IncomeServiceBean() {}

	public void storeIncome(Income income) {
		em.persist(income);
	}

	@SuppressWarnings("unchecked")
	public Collection<Income> viewAllIncome() {
		return em.createNamedQuery("getAllIncome").getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Income> viewIncomeByCustomer(String name) {
		Query query = em.createNamedQuery("incomeByCustomer");
		query.setParameter(1, name);
		query.setParameter(2, ClientType.CUSTOMER);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Income> viewAllIncomeByCompany(String name) {
		Query query = em.createNamedQuery("incomeByCompany");
		query.setParameter(1, name);
		query.setParameter(2, ClientType.COMPANY);
		return query.getResultList();
	}

}
