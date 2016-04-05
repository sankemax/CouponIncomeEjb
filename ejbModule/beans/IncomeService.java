package beans;

import java.util.Collection;

import javax.ejb.Remote;

import core.ejb.Income;

@Remote
public interface IncomeService {

	public void storeIncome(Income income);
	public Collection<Income> viewAllIncome();
	public Collection<Income> viewIncomeByCustomer(String name);
	public Collection<Income> viewAllIncomeByCompany(String name);
}
