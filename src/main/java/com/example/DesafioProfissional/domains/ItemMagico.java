package com.example.DesafioProfissional.domains;

import com.example.DesafioProfissional.domains.enums.TipoItem;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TBL_ITEM_MAGICO")
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private TipoItem tipoItem;
    private Integer forca;
    private Integer defesa;

    public ItemMagico(Long id, String nome, TipoItem tipoItem, Integer forca, Integer defesa) {
        this.id = id;
        this.nome = nome;
        this.tipoItem = tipoItem;
        this.forca = forca;
        this.defesa = defesa;
    }

    public ItemMagico() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemMagico that = (ItemMagico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
