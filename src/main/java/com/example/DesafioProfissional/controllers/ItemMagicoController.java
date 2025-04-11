package com.example.DesafioProfissional.controllers;
import com.example.DesafioProfissional.domains.ItemMagico;
import com.example.DesafioProfissional.domains.dtos.ItemMagicoDto;
import com.example.DesafioProfissional.services.ItemMagicoService;
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

    @GetMapping
    public ResponseEntity<List<ItemMagico>> getAll() {
        return ResponseEntity.ok(itemMagicoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemMagicoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Map<Long, String>> create(@RequestBody ItemMagicoDto itemMagicoDto) {
        ItemMagico itemMagico = itemMagicoService.create(itemMagicoDto);
        Map<Long, String> response = Map.of(itemMagico.getId(), "Item Mágico criado!");
        URI uri = UriComponentsBuilder.fromPath("/itemMagico").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<Long, String>> update(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto) {
        ItemMagico itemMagico = itemMagicoService.update(id, itemMagicoDto);
        Map<Long, String> response = Map.of(itemMagico.getId(), "Item Mágico atualizado!");
        return ResponseEntity.status(202).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemMagicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
