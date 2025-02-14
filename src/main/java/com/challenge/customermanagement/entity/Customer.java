package com.challenge.customermanagement.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.proxy.HibernateProxy;

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

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   private String name;
   private String lastName;
   private LocalDate birthDate;

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
      Customer customer = (Customer) o;
      return getId() != null && Objects.equals(getId(), customer.getId());
   }

   @Override
   public final int hashCode() {
      return this instanceof HibernateProxy hibernateProxy
            ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();
   }
}
