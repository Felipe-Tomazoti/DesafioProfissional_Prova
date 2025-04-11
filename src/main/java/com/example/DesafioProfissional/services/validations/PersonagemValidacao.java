package com.example.DesafioProfissional.services.validations;

import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.domains.enums.TipoItem;
import com.example.DesafioProfissional.services.exceptions.PersonagemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemValidacao {
    public void verificarPontos(Integer forca, Integer defesa) {
        if (forca < 0 || defesa < 0) {
            throw new PersonagemException("Força ou defesa não podem ser menor que 0!");
        }
        if (forca + defesa > 10) {
            throw new PersonagemException("A soma de força e defesa, deve ser de no máximo 10 pontos!");
        }
    }

    public void verificarItemAmuleto(List<ItemMagicoDto> itensMagicos) {
        int cont = 0;
        for (ItemMagicoDto item : itensMagicos){
            if (item.getTipoItem() == TipoItem.AMULETO){
                cont++;
            }
        }
        if (cont > 1){
            throw new PersonagemException("O Personagem só pode possuir 1 item mágico do tipo AMULETO!");
        }
    }

    public List<Personagem> somarItensMagicos(List<Personagem> listaPersonagens) {
        for (Personagem personagem : listaPersonagens){
            for (ItemMagico item : personagem.getItensMagicos()) {
                personagem.setForca(personagem.getForca() + item.getForca());
                personagem.setDefesa(personagem.getDefesa() + item.getDefesa());
            }
        }
        return listaPersonagens;
    }
}
