package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.domain.Address;
import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.repositories.AddressRepository;
import com.alex.spring.vendas.repositories.ClientRepository;
import com.alex.spring.vendas.requests.ClientPost;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ClientController {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientController(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @PostMapping(path = "clients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewClient(@RequestBody @Valid ClientPost form) {
        Client clientToBeSaved = form.newClient();

        for (Address address : clientToBeSaved.getAddress()) {
            address.setClient(clientToBeSaved);
        }
        clientRepository.save(clientToBeSaved);
        return null;
    }
}