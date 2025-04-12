package com.example.DesafioProfissional.services;

import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.repositories.ItemMagicoRepository;
import com.example.DesafioProfissional.services.validations.ItemMagicoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Autowired
    private ItemMagicoValidacao itemMagicoValidacao;

    @Autowired
    private PersonagemService personagemService;

    public List<ItemMagico> getAll() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagico getById(@RequestParam Long id) {
        return itemMagicoRepository.findById(id).orElse(null);
    }

    public ItemMagico create(@RequestBody ItemMagicoDto itemMagicoDto) {
        itemMagicoValidacao.verificarPontos(itemMagicoDto.getForca(), itemMagicoDto.getDefesa());
        ItemMagicoDto item = itemMagicoValidacao.verificarTipoItemMagico(itemMagicoDto);
        return itemMagicoRepository.save(new ItemMagico(item));
    }

    public ItemMagico update(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto) {
        itemMagicoValidacao.verificarPontos(itemMagicoDto.getForca(), itemMagicoDto.getDefesa());
        ItemMagicoDto item = itemMagicoValidacao.verificarTipoItemMagico(itemMagicoDto);
        ItemMagico itemMagico = itemMagicoRepository.findById(id).orElse(null);
        itemMagico.setForca(item.getForca());
        itemMagico.setDefesa(item.getDefesa());
        itemMagico.setNome(itemMagicoDto.getNome());
        itemMagico.setTipoItem(itemMagicoDto.getTipoItem());

        return itemMagicoRepository.save(itemMagico);
    }

    public void delete(@RequestParam Long id) {
        itemMagicoRepository.deleteById(id);
    }

    public ItemMagico add(Long id, ItemMagicoDto itemMagicoDto) {
        itemMagicoValidacao.verificarPontos(itemMagicoDto.getForca(), itemMagicoDto.getDefesa());
        itemMagicoValidacao.verificarTipoItemMagico(itemMagicoDto);

        Personagem personagem = personagemService.getById(id);

        ItemMagico item = new ItemMagico(itemMagicoDto);
        item.setPersonagem(personagem);


        return itemMagicoRepository.save(item);
    }
}
