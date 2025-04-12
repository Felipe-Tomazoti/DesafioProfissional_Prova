package com.example.DesafioProfissional.services.validations;

import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.domains.enums.TipoItem;
import com.example.DesafioProfissional.services.exceptions.PersonagemException;
import org.springframework.stereotype.Service;

@Service
public class ItemMagicoValidacao {
    public ItemMagicoDto verificarTipoItemMagico(ItemMagicoDto itemMagicoDto) {
        if (itemMagicoDto.getTipoItem() == TipoItem.ARMA) {
            itemMagicoDto.setDefesa(0);
        }
        if (itemMagicoDto.getTipoItem() == TipoItem.ARMADURA) {
            itemMagicoDto.setForca(0);
        }
        return itemMagicoDto;
    }

    public void verificarPontos(Integer forca, Integer defesa) {
        if (forca < 0 || defesa < 0) {
            throw new PersonagemException("Força ou defesa do Item Mágico, não pode ser menor que 0!");
        }
        if (forca + defesa > 10) {
            throw new PersonagemException("A soma de força e defesa do Item Mágico, deve ser de no máximo 10 pontos!");
        }
        if (forca == 0 && defesa == 0) {
            throw new PersonagemException("O Item Mágico não pode conter zero de Defesa e zero de Força!");
        }
        if (forca + defesa != 10) {
            throw new PersonagemException("A soma de força e defesa do Item Mágico, deve ser de 10 pontos!");
        }
    }
}
