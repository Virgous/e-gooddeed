package com.enigma.egooddeed.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "t_gooddeed")
public class GoodDeed {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "transaction_id")
    private String transactionId;

    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date transactionDate;

    @OneToMany(mappedBy = "goodDeed",cascade = CascadeType.ALL)
    Set<GoodDeedDetail> goodDeedDetailSet = new HashSet<>();

    public void addGoodDeedDetail(GoodDeedDetail detail){
        detail.setGoodDeed(this);
        goodDeedDetailSet.add(detail);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Set<GoodDeedDetail> getGoodDeedDetailSet() {
        return goodDeedDetailSet;
    }

    public void setGoodDeedDetailSet(Set<GoodDeedDetail> goodDeedDetailSet) {
        this.goodDeedDetailSet = goodDeedDetailSet;
    }
}
