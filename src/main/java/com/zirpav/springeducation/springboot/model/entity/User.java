package com.zirpav.springeducation.springboot.model.entity;

import com.zirpav.springeducation.springboot.validation.Create;
import com.zirpav.springeducation.springboot.validation.CustomEmail;
import com.zirpav.springeducation.springboot.validation.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "dict")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    public Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    public String name;

    @Column(name = "email", nullable = false)
    @CustomEmail
    public String email;

    @OneToMany(mappedBy = "user")
    public List<BankBook> bankBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
