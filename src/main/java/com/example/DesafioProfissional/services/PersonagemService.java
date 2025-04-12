package com.example.DesafioProfissional.services;

import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.domains.dtos.PersonagemDto;
import com.example.DesafioProfissional.domains.enums.TipoItem;
import com.example.DesafioProfissional.repositories.PersonagemRepository;
import com.example.DesafioProfissional.services.validations.ItemMagicoValidacao;
import com.example.DesafioProfissional.services.validations.PersonagemValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private PersonagemValidacao personagemValidacao;

    @Autowired
    private ItemMagicoValidacao itemMagicoValidacao;

    public List<Personagem> getAll() {
        return personagemValidacao.somarItensMagicos(personagemRepository.findAll());
    }

    public Personagem getById(@RequestParam Long id) {
        return personagemRepository.findById(id).orElse(null);
    }

    public Personagem create(@RequestBody PersonagemDto personagemDto) {
        personagemValidacao.verificarPontos(personagemDto.getForca(), personagemDto.getDefesa());
        personagemValidacao.verificarItemAmuleto(personagemDto.getItensMagicos());
        if (personagemDto.getItensMagicos() != null) {
            for (ItemMagicoDto itemMagicoDto : personagemDto.getItensMagicos()) {
                itemMagicoValidacao.verificarPontos(itemMagicoDto.getForca(), itemMagicoDto.getDefesa());
                itemMagicoValidacao.verificarTipoItemMagico(itemMagicoDto);
            }
        }

        return personagemRepository.save(new Personagem(personagemDto));
    }

    public Personagem update(@PathVariable Long id, @RequestBody PersonagemDto personagemDto) {
        personagemValidacao.verificarItemAmuleto(personagemDto.getItensMagicos());
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        personagem.setNome(personagemDto.getNome());
        personagem.setNomeAventureiro(personagemDto.getNomeAventureiro());
        personagem.setClasse(personagemDto.getClasse());
        personagem.setLevel(personagemDto.getLevel());
        personagem.setForca(personagemDto.getForca());
        personagem.setDefesa(personagemDto.getDefesa());
        personagem.setItensMagicos(personagem.getItensMagicos());
        if (personagemDto.getItensMagicos() != null) {
            for (ItemMagicoDto itemMagicoDto : personagemDto.getItensMagicos()) {
                itemMagicoValidacao.verificarPontos(itemMagicoDto.getForca(), itemMagicoDto.getDefesa());
                itemMagicoValidacao.verificarTipoItemMagico(itemMagicoDto);
            }
        }
        return personagemRepository.save(personagem);
    }

    public Personagem updateField(@PathVariable Long id, @RequestBody Map<String, Object> field) {
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        personagem.setNomeAventureiro(field.get("nomeAventureiro").toString());
        return personagemRepository.save(personagem);
    }

    public void delete(@RequestParam Long id) {
        personagemRepository.deleteById(id);
    }

    public List<ItemMagico> getItensMagicos(Long id) {
        return personagemRepository.findById(id).orElse(null).getItensMagicos();
    }

    public ItemMagico getAmuleto(Long id) {
        Personagem personagem = getById(id);
        for (ItemMagico item : personagem.getItensMagicos()) {
            if (item.getTipoItem() == TipoItem.AMULETO) {
                return item;
            }
        }
        return null;
    }
}
