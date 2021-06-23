package ecommerce.visacardserviceChecker.model;

import javax.persistence.*;

@Entity
public class visacard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String card_number;
    private String holder_name;
    private Integer cvv;
    private String expiration_date;

    public visacard(){

    }

    public visacard(String card_number, String holder_name, Integer cvv, String expiration_date) {
        this.card_number = card_number;
        this.holder_name = holder_name;
        this.cvv = cvv;
        this.expiration_date = expiration_date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "card_number", nullable = false)
    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    @Column(name = "holder_name", nullable = false)
    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    @Column(name = "cvv", nullable = false)
    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    @Column(name = "expiration_date", nullable = true)
    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }
}
