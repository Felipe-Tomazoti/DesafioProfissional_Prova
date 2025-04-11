package com.example.DesafioProfissional.controllers;
import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.services.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "itemMagico")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @Operation(summary = "Lista todos os itens mágicos", description = "Retorna todos os itens mágicos cadastrados")
    @GetMapping
    public ResponseEntity<List<ItemMagico>> getAll() {
        return ResponseEntity.ok(itemMagicoService.getAll());
    }

    @Operation(summary = "Busca item mágico por ID", description = "Retorna um item mágico específico baseado no ID")
    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemMagicoService.getById(id));
    }

    @Operation(
            summary = "Cria um novo item mágico",
            description = "Cria um item mágico individual sem associá-lo a um personagem",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de item mágico",
                                    value = """
                        {
                            "nome": "ARMADURA do Felipe",
                            "tipoItem": "ARMADURA",
                            "forca": 0,
                            "defesa": 10
                        }
                        """
                            )
                    )
            )
    )
    @PostMapping
    public ResponseEntity<Map<Long, String>> create(@RequestBody ItemMagicoDto itemMagicoDto) {
        ItemMagico itemMagico = itemMagicoService.create(itemMagicoDto);
        Map<Long, String> response = Map.of(itemMagico.getId(), "Item Mágico criado!");
        URI uri = UriComponentsBuilder.fromPath("/itemMagico").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Adiciona item mágico a um personagem", description = "Cria e associa um item mágico ao personagem com ID informado")
    @PostMapping("/{id}")
    public ResponseEntity<Map<Long, String>> add(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto) {
        ItemMagico itemMagico = itemMagicoService.add(id, itemMagicoDto);
        Map<Long, String> response = Map.of(itemMagico.getId(), "Item Mágico criado!");
        URI uri = UriComponentsBuilder.fromPath("/itemMagico").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Atualiza item mágico", description = "Edita um item mágico já existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Map<Long, String>> update(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto) {
        ItemMagico itemMagico = itemMagicoService.update(id, itemMagicoDto);
        Map<Long, String> response = Map.of(itemMagico.getId(), "Item Mágico atualizado!");
        return ResponseEntity.status(202).body(response);
    }

    @Operation(summary = "Deleta item mágico", description = "Remove permanentemente um item mágico do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemMagicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
