package com.enigma.egooddeed.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_finance")
public class Finance {
    @Id@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String financeId;

    @Column(name = "finance_name")
    private String financeName;

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }
}

