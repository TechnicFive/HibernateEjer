

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "FACTURA")
public class Statement implements Serializable {

  @EmbeddedId
  private StatementPk statementPk;
  
  @MapsId("idFromClient")
  @ManyToOne
  @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", updatable = false, nullable = false)
  private Empleado codigo;
	
	@Column(name = "IMPORTE", nullable = false)
  private int amount;
	
	@Column(name = "FECHA_EMISION", nullable = false, columnDefinition = "char(14)", length = 14)
  private String billingDate;

	public Statement() {
		
	}

  public Statement(StatementPk statementPk, int amount, String billingDate) {
    super();
    this.statementPk = statementPk;
    this.amount = amount;
    this.billingDate = billingDate;
  }

  public StatementPk getStatementPk() {
    return statementPk;
  }

  public void setStatementPk(StatementPk statementPk) {
    this.statementPk = statementPk;
  }

  public Empleado getIdFromClient() {
    return codigo;
  }

  public void setIdFromClient(Empleado codigo) {
    this.codigo = codigo;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getBillingDate() {
    return billingDate;
  }

  public void setBillingDate(String billingDate) {
    this.billingDate = billingDate;
  }

  @Override
  public String toString() {
    return "Statement [statementPk=" + statementPk + ", amount=" + amount + ", billingDate=" + billingDate + "]";
  }
}
