package com.example.DesafioProfissional.domains;
import com.example.DesafioProfissional.domains.enums.ClassePersonagem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TBL_PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private List<ItemMagico> list = new ArrayList<>();
    private ClassePersonagem classe;
    private Integer level;
    private Integer forca;
    private Integer defesa;

    public Personagem(Long id, String nome, String nomeAventureiro, List<ItemMagico> list, ClassePersonagem classe, Integer level, Integer forca, Integer defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.list = list;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Personagem() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personagem that = (Personagem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
