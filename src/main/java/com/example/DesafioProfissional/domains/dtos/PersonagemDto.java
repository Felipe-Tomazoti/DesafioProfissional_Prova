package com.example.DesafioProfissional.domains.dtos;

import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.enums.ClassePersonagem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonagemDto {

    private Long id;
    private String nome;
    private String nomeAventureiro;
    private List<ItemMagicoDto> itensMagicos = new ArrayList<>();
    private ClassePersonagem classe;
    private Integer level;
    private Integer forca;
    private Integer defesa;

    public PersonagemDto(String nome, String nomeAventureiro, List<ItemMagicoDto> itensMagicos, ClassePersonagem classe, Integer level, Integer forca, Integer defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.itensMagicos = itensMagicos;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public PersonagemDto() {
    }

    public PersonagemDto(Personagem personagem) {
        this.nome = personagem.getNome();
        this.nomeAventureiro = personagem.getNomeAventureiro();
        this.classe = personagem.getClasse();
        this.level = personagem.getLevel();
        this.forca = personagem.getForca();
        this.defesa = personagem.getDefesa();
        if (personagem.getItensMagicos() != null) {
            this.itensMagicos = personagem.getItensMagicos().stream().map(item -> {

                ItemMagicoDto itemDto = new ItemMagicoDto();

                itemDto.setNome(item.getNome());
                itemDto.setTipoItem(item.getTipoItem());
                itemDto.setForca(item.getForca());
                itemDto.setDefesa(item.getDefesa());

                return itemDto;
            }).collect(Collectors.toList());
        }
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

    public List<ItemMagicoDto> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagicoDto> list) {
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
}
