package com.farmacia.v1.controller;

import com.farmacia.v1.dto.TemplateDTO;
import com.farmacia.v1.service.impl.GradeServiceImpl;
import com.farmacia.v1.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/templates")
@PreAuthorize("hasRole('ADMIN')")
public class TemplateController {

    @Autowired
    private TemplateServiceImpl service;

    @PostMapping
    public ResponseEntity<TemplateDTO> create(@RequestBody TemplateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TemplateDTO>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }




    @GetMapping("/{id}")
    public ResponseEntity<TemplateDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateDTO> update(@PathVariable Integer id, @RequestBody TemplateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* SOFT DELETE */
    @GetMapping
    public ResponseEntity<List<TemplateDTO>> listActive() {
        return ResponseEntity.ok(service.listActive());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<TemplateDTO>> listDeleted() {
        return ResponseEntity.ok(service.listDeleted());
    }



    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Integer id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }
}
