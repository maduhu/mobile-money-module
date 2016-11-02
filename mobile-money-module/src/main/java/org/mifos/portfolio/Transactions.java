package org.mifos.portfolio;

/**
 * Created by daniel on 11/2/16.
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="transaction_history")
public class Transactions {


    // Table names in the transactions
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    // Constructors
    public Transactions() {}

    @Column(name="staff")
    private static String staff;

    @Column(name="clientId")
    private int clientId;

    @Column(name="office")
    private static String office;

    @Column(name="transactionType")
    private String type;

    @Column(name="amount")
    private int amount;

    @Column(name="date")
    private Date date;

    public static String getStaff() {
        return staff;
    }

    public static void setStaff(String staff) {
        Transactions.staff = staff;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public static String getOffice() {
        return office;
    }

    public static void setOffice(String office) {
        Transactions.office = office;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}