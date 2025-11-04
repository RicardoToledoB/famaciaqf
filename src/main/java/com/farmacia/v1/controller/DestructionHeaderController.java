package com.farmacia.v1.controller;

import com.farmacia.v1.dto.DestructionHeaderDTO;
import com.farmacia.v1.service.impl.DestructionHeaderServiceImpl;
import com.farmacia.v1.service.impl.GradeServiceImpl;
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
@RequestMapping("/api/v1/destructions_headers")
@PreAuthorize("hasRole('ADMIN')")
public class DestructionHeaderController {

    @Autowired
    private DestructionHeaderServiceImpl service;

    @PostMapping
    public ResponseEntity<DestructionHeaderDTO> create(@RequestBody DestructionHeaderDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DestructionHeaderDTO>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }


    @GetMapping("/getAllPaginated")
    public ResponseEntity<Page<DestructionHeaderDTO>> getAllPaginated(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.getAllPaginated(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestructionHeaderDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestructionHeaderDTO> update(@PathVariable Integer id, @RequestBody DestructionHeaderDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* SOFT DELETE */
    @GetMapping
    public ResponseEntity<List<DestructionHeaderDTO>> listActive() {
        return ResponseEntity.ok(service.listActive());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<DestructionHeaderDTO>> listDeleted() {
        return ResponseEntity.ok(service.listDeleted());
    }



    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Integer id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }
}
