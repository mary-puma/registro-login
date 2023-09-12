package org.api.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="{error.empty_field}")
    @Column(nullable = false, updatable = true)
    private String firstname;
    
    @NotNull(message="{error.empty_field}")
    @Column(nullable = false, updatable = true)
    private String lastname;
    
    @NotNull(message="{error.empty_field}")
    @Column(nullable = false, updatable = true, unique = true)
    private String email;
    
    @NotNull(message="{error.empty_field}")
    @Column(nullable = false, updatable = true)
    private String password;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;

}
