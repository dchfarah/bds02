package com.devsuperior.bds02.resources;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.CityService;
import com.devsuperior.bds02.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventResource {

    private final EventService service;

    public EventResource(EventService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        eventDTO = service.update(id, eventDTO);
        return ResponseEntity.ok().body(eventDTO);
    }
}
