package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository repository) {
        this.eventRepository = repository;
    }

    @Transactional
    public EventDTO update(Long id, EventDTO eventDTO) {
        try {
            Event event = eventRepository.getOne(id);
            event.setDate(eventDTO.getDate());
            event.setUrl(eventDTO.getUrl());
            event.setName(eventDTO.getName());

            City city = new City(eventDTO.getCityId(), "");

            event.setCity(city);
            event = eventRepository.save(event);

            return new EventDTO(event);
        } catch (javax.persistence.EntityNotFoundException e) {
            throw new EntityNotFoundException("Id not found " + id);
        }
    }
}
