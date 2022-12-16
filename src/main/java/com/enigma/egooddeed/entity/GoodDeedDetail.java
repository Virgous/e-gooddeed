package com.enigma.egooddeed.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_gooddeed_detail")
public class GoodDeedDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "transaction_detail_id")
    private String transactionDetailId;

    @ManyToOne
    @JoinColumn(name = "gooddeed_id")
    GoodDeed goodDeed;

    public String getTransactionDetailId() {
        return transactionDetailId;
    }

    public void setTransactionDetailId(String transactionDetailId) {
        this.transactionDetailId = transactionDetailId;
    }

    public GoodDeed getGoodDeed() {
        return goodDeed;
    }

    public void setGoodDeed(GoodDeed goodDeed) {
        this.goodDeed = goodDeed;
    }
}
