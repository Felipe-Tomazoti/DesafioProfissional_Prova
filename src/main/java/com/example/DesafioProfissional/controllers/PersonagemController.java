package com.example.DesafioProfissional.controllers;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.PersonagemDto;
import com.example.DesafioProfissional.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @GetMapping
    public ResponseEntity<List<Personagem>> getAll() {
        return ResponseEntity.ok(personagemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Map<Long, String>> create(@RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemService.create(personagemDto);
        Map<Long, String> response = Map.of(personagem.getId(), "Personagem criado!");
        URI uri = UriComponentsBuilder.fromPath("/personagem").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<Long, String>> update(@PathVariable Long id, @RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemService.update(id, personagemDto);
        Map<Long, String> response = Map.of(personagem.getId(), "Personagem editado!");
        return ResponseEntity.status(202).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
