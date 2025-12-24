package com.example.demo.service;

import com.example.demo.dto.ClientCreateDto;
import com.example.demo.dto.ClientDto;
import com.example.demo.dto.ClientUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repo;

    public List<ClientDto> all() {
        return repo.findAll().stream().map(this::toDto).toList();
    }
    public long count() {
        return repo.count();
    }
    public ClientDto one(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
    }

    public ClientDto create(ClientCreateDto dto) {
        Client c = new Client();
        c.setFullName(dto.getFullName());
        c.setPhone(dto.getPhone());
        repo.save(c);
        return toDto(c);
    }

    public ClientDto update(Long id, ClientUpdateDto dto) {
        Client c = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));

        c.setFullName(dto.getFullName());
        c.setPhone(dto.getPhone());

        repo.save(c);
        return toDto(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private ClientDto toDto(Client c) {
        ClientDto dto = new ClientDto();
        dto.setId(c.getId());
        dto.setFullName(c.getFullName());
        dto.setPhone(c.getPhone());
        return dto;
    }
}
