package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Address;
import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Gender;
import com.alex.spring.vendas.domain.Level;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.ClientRepository;
import com.alex.spring.vendas.requests.client.ClientGetList;
import com.alex.spring.vendas.requests.client.ClientGetOne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Integer saveClient(Client client) {
        for (Address address : client.getAddress()) {
            address.setClient(client);
        }
        return clientRepository.save(client).getId();
    }

    public Page<ClientGetList> findClients(Pageable pageable) {
        return clientRepository.findAll(pageable).map(ClientGetList::new);
    }

    public ClientGetOne findClientById(Integer id) {
        return new ClientGetOne(clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Client with id `%d`.".formatted(id))));
    }

    public Page<ClientGetList> findClientByName(String name, Pageable pageable) {
        return clientRepository.findByNameIgnoreCase(name, pageable).map(ClientGetList::new);
    }

    public Page<ClientGetList> findClientByGender(String gender, Pageable pageable) {
        return clientRepository.findByGender(Gender.newGender(gender), pageable).map(ClientGetList::new);
    }

    public Page<ClientGetList> findClientByLevel(String level, Pageable pageable) {
        return clientRepository.findByLevel(Level.newLevel(level), pageable).map(ClientGetList::new);
    }
}