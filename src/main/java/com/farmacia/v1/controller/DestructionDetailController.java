package com.farmacia.v1.controller;

import com.farmacia.v1.dto.DestructionDetailDTO;
import com.farmacia.v1.service.impl.CommuneServiceImpl;
import com.farmacia.v1.service.impl.DestructionDetailServiceImpl;
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
@RequestMapping("/api/v1/destructions_details")
@PreAuthorize("hasRole('ADMIN')")
public class DestructionDetailController {

    @Autowired
    private DestructionDetailServiceImpl service;

    @PostMapping
    public ResponseEntity<DestructionDetailDTO> create(@RequestBody DestructionDetailDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DestructionDetailDTO>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }


    @GetMapping("/getAllPaginated")
    public ResponseEntity<Page<DestructionDetailDTO>> getAllPaginated(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.getAllPaginated(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestructionDetailDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestructionDetailDTO> update(@PathVariable Integer id, @RequestBody DestructionDetailDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* SOFT DELETE */
    @GetMapping
    public ResponseEntity<List<DestructionDetailDTO>> listActive() {
        return ResponseEntity.ok(service.listActive());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<DestructionDetailDTO>> listDeleted() {
        return ResponseEntity.ok(service.listDeleted());
    }



    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Integer id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }
}
