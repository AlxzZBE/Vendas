package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.requests.client.ClientGetList;
import com.alex.spring.vendas.requests.client.ClientGetOne;
import com.alex.spring.vendas.requests.client.ClientPost;
import com.alex.spring.vendas.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewClient(@RequestBody @Valid ClientPost form) {
        Integer clientSavedId = clientService.saveClient(form.newClient());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", clientSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientGetList>> findClients(Pageable pageable) {
        return ResponseEntity.ok(clientService.findClients(pageable));
    }

    @GetMapping(params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientGetOne> findClientById(@RequestParam Integer id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientGetList>> findClientByName(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(clientService.findClientByName(name, pageable));
    }

    @GetMapping(params = "gender", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientGetList>> findClientByGender(@RequestParam String gender, Pageable pageable) {
        return ResponseEntity.ok(clientService.findClientByGender(gender, pageable));
    }

    @GetMapping(params = "level", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientGetList>> findClientByLevel(@RequestParam String level, Pageable pageable) {
        return ResponseEntity.ok(clientService.findClientByLevel(level, pageable));
    }
}