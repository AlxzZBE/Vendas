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

    @GetMapping(params = "id")
    public ResponseEntity<ClientGetOne> findClientById(@RequestParam Integer id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }
}