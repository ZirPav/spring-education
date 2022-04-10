package com.zirpav.springeducation.springboot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bankbook", schema = "dict")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(name = "number", nullable = false)
    public String number;

    @Column(name = "amount", nullable = false)
    public BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    public Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankBook bankBook = (BankBook) o;
        return id != null && Objects.equals(id, bankBook.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
