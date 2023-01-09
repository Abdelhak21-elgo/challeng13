package com.backend.backend_challeng13.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

import lombok.*;



@Setter
@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String prenome;
    private String nomeArabe;
    private String prenomeArab;
    private String cin;
    private String profession;
    private String dateNaissance;
    private String typeCarte;


    @OneToOne( cascade = CascadeType.ALL)
    private ImageModel userImage;
    
    public User(String nome, String prenome, String nomeArabe, String prenomeArab, String cin, String profession,
            String dateNaissance, String typeCarte, ImageModel userImage) {
        this.nome = nome;
        this.prenome = prenome;
        this.nomeArabe = nomeArabe;
        this.prenomeArab = prenomeArab;
        this.cin = cin;
        this.profession = profession;
        this.dateNaissance = dateNaissance;
        this.typeCarte = typeCarte;
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "Carte Professionnel [ nome=" + nome + ", prenome=" + prenome + ", nomeArabe=" + nomeArabe + ", prenomeArab="
                + prenomeArab + ", cin=" + cin + ", profession=" + profession + ", dateNaissance=" + dateNaissance
                + ", typeCarte=" + typeCarte + "]";
    }    
}
