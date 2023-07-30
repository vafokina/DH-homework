package com.dh.homework.bid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    @SequenceGenerator(name = "sq_user", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 36, max = 36)
//    @Column(name = "external_id")
    private String externalId;

    @NotNull
    @Size(min = 1, max = 100)
//    @Column(name = "first_name")
    private String firstName;

    @Size(min = 1, max = 100)
//    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Size(min = 1, max = 100)
//    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
//    @Column(name = "email")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\d{10,13}$")
//    @Column(name = "phone")
    private String phone;

}
