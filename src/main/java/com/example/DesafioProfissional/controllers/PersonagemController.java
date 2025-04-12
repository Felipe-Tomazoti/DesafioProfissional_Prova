package com.example.DesafioProfissional.controllers;

import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.Personagem;
import com.example.DesafioProfissional.domains.dtos.PersonagemDto;
import com.example.DesafioProfissional.services.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Lista todos os personagens",
            description = "Esta rota retorna todos os personagens cadastrados no sistema. A resposta é uma lista de personagens.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de personagens",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Exemplo de Personagem",
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "nome": "Gabriel",
                                                        "nomeAventureiro": "gabriel@email.com",
                                                        "classe": "GUERREIRO",
                                                        "level": 12,
                                                        "forca": 4,
                                                        "defesa": 6,
                                                        "itensMagicos": [
                                                          {
                                                            "nome": "Escudo do Dragão",
                                                            "tipoItem": "AMULETO",
                                                            "forca": 9,
                                                            "defesa": 1
                                                          }
                                                        ]
                                                      }
                                                    ]
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Personagem>> getAll() {
        return ResponseEntity.ok(personagemService.getAll());
    }

    @Operation(
            summary = "Lista o personagem de acordo com o ID",
            description = "Esta rota retorna o personagem com o ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Personagem encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Exemplo de Personagem",
                                            value = """
                                                    {
                                                        "id": 1,
                                                        "nome": "Gabriel",
                                                        "nomeAventureiro": "gabriel@email.com",
                                                        "classe": "GUERREIRO",
                                                        "level": 12,
                                                        "forca": 4,
                                                        "defesa": 6,
                                                        "itensMagicos": [
                                                            {
                                                                "nome": "Escudo do Dragão",
                                                                "tipoItem": "AMULETO",
                                                                "forca": 9,
                                                                "defesa": 1
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getById(id));
    }

    @Operation(
            summary = "Lista os itens mágicos de acordo com o ID do personagem",
            description = "Esta rota retorna todos os itens mágicos de um personagem com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de itens mágicos do personagem",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Exemplo de Itens Mágicos",
                                            value = """
                                                    [
                                                        {
                                                            "id": 2,
                                                            "nome": "Escudo do Dragão",
                                                            "tipoItem": "AMULETO",
                                                            "forca": 9,
                                                            "defesa": 1
                                                        },
                                                        {
                                                            "id": 3,
                                                            "nome": "Escudo do Dragão",
                                                            "tipoItem": "ARMA",
                                                            "forca": 9,
                                                            "defesa": 0
                                                        }
                                                    ]
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("getItensMagicos/{id}")
    public ResponseEntity<List<ItemMagico>> getItensMagicos(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getItensMagicos(id));
    }

    @Operation(
            summary = "Lista o item de amuleto de acordo com o ID do personagem",
            description = "Esta rota retorna o item de amuleto de um personagem com base no ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item de amuleto do personagem",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Exemplo de Amuleto",
                                            value = """
                                                    {
                                                        "id": 2,
                                                        "nome": "Escudo do Dragão",
                                                        "tipoItem": "AMULETO",
                                                        "forca": 9,
                                                        "defesa": 1
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("getAmuleto/{id}")
    public ResponseEntity<ItemMagico> getAmuleto(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getAmuleto(id));
    }

    @Operation(
            summary = "Criação de um personagem",
            description = "Esta rota cria um novo personagem com os dados fornecidos. O JSON de entrada deve incluir o nome, nome aventureiro, classe, nível, força, defesa e uma lista de itens mágicos. O personagem criado será retornado com o ID gerado.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Personagem",
                                    value = """
                                            {
                                                "nome": "Gabriel",
                                                "nomeAventureiro": "novo@email.com",
                                                "classe": "GUERREIRO",
                                                "level": 12,
                                                "forca": 4,
                                                "defesa": 6,
                                                "itensMagicos": [
                                                    {
                                                        "nome": "Escudo do Dragão",
                                                        "tipoItem": "AMULETO",
                                                        "forca": 9,
                                                        "defesa": 1
                                                    },
                                                    {
                                                        "nome": "Ataque do Dragão",
                                                        "tipoItem": "ARMA",
                                                        "forca": 9,
                                                        "defesa": 1
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            )
    )
    @PostMapping
    public ResponseEntity<Map<Long, String>> create(@RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemService.create(personagemDto);
        Map<Long, String> response = Map.of(personagem.getId(), "Personagem criado!");
        URI uri = UriComponentsBuilder.fromPath("/personagem").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(
            summary = "Altera todos os atributos de um personagem",
            description = "Esta rota altera todos os atributos de um personagem existente com base no ID fornecido. O JSON de entrada deve incluir o nome, nome aventureiro, classe, nível, força, defesa e uma lista de itens mágicos. O personagem será atualizado com sucesso e o ID do personagem editado será retornado.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de Personagem Atualizado",
                                    value = """
                                            {
                                              "nome": "Gabriel Atualizado",
                                              "nomeAventureiro": "novo@email.com",
                                              "classe": "GUERREIRO",
                                              "level": 12,
                                              "forca": 4,
                                              "defesa": 6,
                                              "itensMagicos": [
                                                {
                                                        "nome": "Escudo do Dragão",
                                                        "tipoItem": "AMULETO",
                                                        "forca": 9,
                                                        "defesa": 1
                                                    },
                                                    {
                                                        "nome": "Ataque do Dragão",
                                                        "tipoItem": "ARMA",
                                                        "forca": 9,
                                                        "defesa": 1
                                                    }
                                              ]
                                            }
                                            """
                            )
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<Map<Long, String>> update(@PathVariable Long id, @RequestBody PersonagemDto personagemDto) {
        Personagem personagem = personagemService.update(id, personagemDto);
        Map<Long, String> response = Map.of(personagem.getId(), "Personagem editado!");
        return ResponseEntity.status(202).body(response);
    }

    @Operation(
            summary = "Altera o campo nomeAventureiro do personagem",
            description = "Esta rota altera o campo 'nomeAventureiro' de um personagem existente com base no ID fornecido. O JSON de entrada deve incluir o campo 'nomeAventureiro' com o novo valor.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de alteração do nomeAventureiro",
                                    value = """
                                            {
                                              "nomeAventureiro": "gabriel@gmail.com"
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Campo editado com sucesso",
                            content = @Content()
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Map<Long, String>> updateField(@PathVariable Long id, @RequestBody Map<String, Object> field) {
        Personagem personagem = personagemService.updateField(id, field);
        Map<Long, String> response = Map.of(personagem.getId(), "Campo editado!");
        return ResponseEntity.status(202).body(response);
    }

    @Operation(
            summary = "Deleta o personagem de acordo com o ID",
            description = "Esta rota deleta o personagem com o ID fornecido. Caso o personagem seja encontrado e deletado com sucesso, a resposta será um status 204 (Sem conteúdo).",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Personagem deletado com sucesso",
                            content = @Content()
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}