package com.example.DesafioProfissional.services;
import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.repositories.ItemMagicoRepository;
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

    public List<ItemMagico> getAll() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagico getById(@RequestParam Long id) {
        return itemMagicoRepository.findById(id).orElse(null);
    }

    public ItemMagico create(@RequestBody ItemMagicoDto itemMagicoDto) {
        return itemMagicoRepository.save(new ItemMagico(itemMagicoDto));
    }

    public ItemMagico update(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto) {
        ItemMagico itemMagico = itemMagicoRepository.findById(id).orElse(null);
        itemMagico.setNome(itemMagicoDto.getNome());
        itemMagico.setForca(itemMagicoDto.getForca());
        itemMagico.setDefesa(itemMagicoDto.getDefesa());
        itemMagico.setTipoItem(itemMagicoDto.getTipoItem());
        return itemMagicoRepository.save(itemMagico);
    }

    public void delete(@RequestParam Long id) {
        itemMagicoRepository.deleteById(id);
    }
}
