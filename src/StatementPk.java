package com.serbatic.course.datamodel.entities.annotations;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

@Embeddable
public class StatementPk implements Serializable {  
  
  @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", updatable = false, nullable = false)
	private int idFromClient;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_FACTURA")
  private int statementId;

	public StatementPk() {
		
	}

  public StatementPk(int idFromClient) {
    super();
    this.idFromClient = idFromClient;
  }

  public int getIdFromClient() {
    return idFromClient;
  }

  public void setIdFromClient(int idFromClient) {
    this.idFromClient = idFromClient;
  }

  public int getStatementId() {
    return statementId;
  }

  public void setStatementId(int statementId) {
    this.statementId = statementId;
  }

  @Override
  public String toString() {
    return "StatementPk [idFromClient=" + idFromClient + ", statementId=" + statementId + "]";
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof StatementPk) {
      StatementPk pk = (StatementPk)obj;
      return idFromClient == pk.idFromClient && statementId == pk.statementId;
    } else {
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    return (int)(idFromClient + statementId);
  }
}
