package com.challenge.customermanagement.entity;

import java.util.Objects;
import java.util.UUID;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;

   @Column(unique = true)
   private String username;
   private String password;

   @Override
   public final boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null) {
         return false;
      }
      Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
      Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy
            ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
      if (thisEffectiveClass != oEffectiveClass) {
         return false;
      }
      User user = (User) o;
      return getId() != null && Objects.equals(getId(), user.getId());
   }

   @Override
   public final int hashCode() {
      return this instanceof HibernateProxy hibernateProxy
            ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();
   }
}
