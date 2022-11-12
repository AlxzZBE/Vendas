package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Address;
import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Gender;
import com.alex.spring.vendas.domain.Level;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Long saveClient(Client client) {
        for (Address address : client.getAddress()) {
            address.setClient(client);
        }
        return clientRepository.save(client).getId();
    }

    public Page<Client> findClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client findClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Client with id `%d`.".formatted(id)));
    }

    public Page<Client> findClientByName(String name, Pageable pageable) {
        return clientRepository.findByNameIgnoreCase(name, pageable);
    }

    public Page<Client> findClientByGender(String gender, Pageable pageable) {
        return clientRepository.findByGender(Gender.newGender(gender), pageable);
    }

    public Page<Client> findClientByLevel(String level, Pageable pageable) {
        return clientRepository.findByLevel(Level.newLevel(level), pageable);
    }
}