package org.serratec.ecommerce.petshop.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String senha;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable (name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_idPerfil"))
    private List<Role> roles;

    public User() {
    }

    public User(String email, String senha, List<Role> roles) {
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    public User(Integer id, String email, String senha, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
