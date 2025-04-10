package com.example.DesafioProfissional.domains.dtos;

import com.example.DesafioProfissional.domains.enums.TipoItem;
import jakarta.persistence.*;

public class ItemMagicoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private TipoItem tipoItem;
    private Integer forca;
    private Integer defesa;

    public ItemMagicoDto(String nome, TipoItem tipoItem, Integer forca, Integer defesa) {
        this.nome = nome;
        this.tipoItem = tipoItem;
        this.forca = forca;
        this.defesa = defesa;
    }

    public ItemMagicoDto() {
    }

}
