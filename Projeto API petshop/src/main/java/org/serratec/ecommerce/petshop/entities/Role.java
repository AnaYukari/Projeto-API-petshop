package org.serratec.ecommerce.petshop.entities;

import jakarta.persistence.*;
import org.serratec.ecommerce.petshop.enuns.RoleEnum;

@Entity
@Table (name = "perfil")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;

    @Enumerated (EnumType.STRING)
    private RoleEnum nome;

    public Role() {
    }

    public Role(Integer idPerfil, RoleEnum nome) {
        this.idPerfil = idPerfil;
        this.nome = nome;
    }

    public Role(RoleEnum name){
        this.nome = name;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public RoleEnum getNome() {
        return nome;
    }

    public void setNome(RoleEnum nome) {
        this.nome = nome;
    }
}
