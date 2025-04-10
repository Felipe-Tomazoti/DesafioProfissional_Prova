package com.example.DesafioProfissional.domains.dtos;
import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.enums.ClassePersonagem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDto {

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

    public PersonagemDto(String nome, String nomeAventureiro, List<ItemMagico> list, ClassePersonagem classe, Integer level, Integer forca, Integer defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.list = list;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public PersonagemDto() {
    }

}
