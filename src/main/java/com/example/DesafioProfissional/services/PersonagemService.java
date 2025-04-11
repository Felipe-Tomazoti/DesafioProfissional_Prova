package com.example.DesafioProfissional.services;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.PersonagemDto;
import com.example.DesafioProfissional.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public List<Personagem> getAll() {
        return personagemRepository.findAll();
    }

    public Personagem getById(@RequestParam Long id) {
        return personagemRepository.findById(id).orElse(null);
    }

    public Personagem create(@RequestBody PersonagemDto personagemDto) {
        return personagemRepository.save(new Personagem(personagemDto));
    }

    public Personagem update(@PathVariable Long id, @RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        personagem.setNome(personagemDto.getNome());
        personagem.setNomeAventureiro(personagemDto.getNomeAventureiro());
        personagem.setClasse(personagemDto.getClasse());
        personagem.setLevel(personagemDto.getLevel());
        personagem.setForca(personagemDto.getForca());
        personagem.setDefesa(personagemDto.getDefesa());
        return personagemRepository.save(personagem);
    }

    public void delete(@RequestParam Long id) {
        personagemRepository.deleteById(id);
    }
}
