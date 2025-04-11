package com.example.DesafioProfissional.controllers;
import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.PersonagemDto;
import com.example.DesafioProfissional.services.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    @Operation(summary = "Lista todos os personagens", description = "Retorna todos os personagens cadastrados")
    @GetMapping
    public ResponseEntity<List<Personagem>> getAll() {
        return ResponseEntity.ok(personagemService.getAll());
    }

    @Operation(summary = "Busca personagem por ID", description = "Retorna um personagem pelo ID informado")
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getById(id));
    }

    @Operation(summary = "Lista itens mágicos de um personagem", description = "Retorna todos os itens mágicos associados ao personagem pelo ID")
    @GetMapping("/getItensMagicos/{id}")
    public ResponseEntity<List<ItemMagico>> getItensMagicos(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getItensMagicos(id));
    }

    @Operation(summary = "Retorna o amuleto de um personagem", description = "Busca o item mágico do tipo AMULETO de um personagem específico")
    @GetMapping("/getAmuleto/{id}")
    public ResponseEntity<ItemMagico> getAmuleto(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getAmuleto(id));
    }

    @Operation(
            summary = "Cria um novo personagem",
            description = "Cria um personagem completo com ou sem itens mágicos associados",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de personagem com item mágico",
                                    value = """
                                                {
                                                    "nome": "Gabriel",
                                                    "nomeAventureiro": "lolo.com",
                                                    "classe": "GUERREIRO",
                                                    "level": 10,
                                                    "forca": 3,
                                                    "defesa": 7,
                                                    "itensMagicos": [
                                                        {
                                                            "nome": "Machado de Guerra",
                                                            "tipoItem": "AMULETO",
                                                            "forca": 5,
                                                            "defesa": 5
                                                        }
                                                    ]
                                                }
                                            """
                            )
                    )
            )
    )
    @PostMapping
    public ResponseEntity<Map<Long, String>> create(
            @org.springframework.web.bind.annotation.RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemService.create(personagemDto);
        Map<Long, String> response = Map.of(personagem.getId(), "Personagem criado!");
        URI uri = UriComponentsBuilder.fromPath("/personagem").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @Operation(summary = "Atualiza completamente um personagem", description = "Atualiza todos os campos de um personagem existente")
    @PutMapping("/{id}")
    public ResponseEntity<Map<Long, String>> update(@PathVariable Long id, @RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemService.update(id, personagemDto);
        Map<Long, String> response = Map.of(personagem.getId(), "Personagem editado!");
        return ResponseEntity.status(202).body(response);
    }

    @Operation(summary = "Atualiza apenas um campo do personagem", description = "Edita um campo individual do personagem, como o nomeAventureiro")
    @PatchMapping("/{id}")
    public ResponseEntity<Map<Long, String>> updateField(@PathVariable Long id, @RequestBody Map<String, Object> field) {
        Personagem personagem = personagemService.updateField(id, field);
        Map<Long, String> response = Map.of(personagem.getId(), "Campo editado!");
        return ResponseEntity.status(202).body(response);
    }

    @Operation(summary = "Remove um personagem pelo ID", description = "Deleta um personagem específico com base no ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personagemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}