package core.ejb;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import core.enums.ClientType;
import core.enums.IncomeType;

@Entity
@Table(name="INCOME")
@NamedQueries({
	@NamedQuery(name="getAllIncome", query="SELECT i FROM Income AS i"),
	@NamedQuery(name="incomeByCustomer", query="SELECT i FROM Income AS i WHERE i.name = :name AND i.client = :clientType"),
	@NamedQuery(name="incomeByCompany", query="SELECT i FROM Income AS i WHERE i.name = :name AND i.client = :company")
	})
public class Income implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Date date;
	private ClientType client;
	private IncomeType description;
	private double amount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", columnDefinition="BIGINT")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
		
	@Column(name="NAME", columnDefinition="VARCHAR")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
		
	@Column(name="CLIENT_TYPE")
	public ClientType getClient() {
		return client;
	}
	public void setClient(ClientType client) {
		this.client = client;
	}
	
	@Column(name="DESCRIPTION")
	public IncomeType getDescription() {
		return description;
	}
	public void setDescription(IncomeType description) {
		this.description = description;
	}
	
	@Column(name="AMOUNT")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
