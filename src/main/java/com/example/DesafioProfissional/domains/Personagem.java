package com.example.DesafioProfissional.domains;

import com.example.DesafioProfissional.domains.dtos.PersonagemDto;
import com.example.DesafioProfissional.domains.enums.ClassePersonagem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "TBL_PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeAventureiro;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ClassePersonagem classe;
    private Integer level;
    private Integer forca;
    private Integer defesa;

    public Personagem(Long id, String nome, String nomeAventureiro, List<ItemMagico> itensMagicos, ClassePersonagem classe, Integer level, Integer forca, Integer defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.itensMagicos = itensMagicos;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Personagem(PersonagemDto personagemDto) {
        this.nome = personagemDto.getNome();
        this.nomeAventureiro = personagemDto.getNomeAventureiro();
        this.classe = personagemDto.getClasse();
        this.level = personagemDto.getLevel();
        this.forca = personagemDto.getForca();
        this.defesa = personagemDto.getDefesa();
        if (personagemDto.getItensMagicos() != null) {
            this.itensMagicos = personagemDto.getItensMagicos().stream().map(itemDto -> {
                ItemMagico item = new ItemMagico();
                item.setNome(itemDto.getNome());
                item.setTipoItem(itemDto.getTipoItem());
                item.setPersonagem(this);
                item.setDefesa(itemDto.getDefesa());
                item.setForca(itemDto.getForca());
                return item;
            }).collect(Collectors.toList());
        }
    }

    public Personagem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> list) {
        this.itensMagicos = list;
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
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
