package com.farmacia.v1.controller;

import com.farmacia.v1.dto.PoliceDTO;
import com.farmacia.v1.dto.PreAnalysisDTO;
import com.farmacia.v1.service.impl.GradeServiceImpl;
import com.farmacia.v1.service.impl.PreAnalysisServiceImpl;
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
@RequestMapping("/api/v1/pre_analysis")
@PreAuthorize("hasRole('ADMIN')")
public class PreAnalysisController {

    @Autowired
    private PreAnalysisServiceImpl service;

    @PostMapping
    public ResponseEntity<PreAnalysisDTO> create(@RequestBody PreAnalysisDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/getAllPaginated")
    public ResponseEntity<Page<PreAnalysisDTO>> getAllPaginated(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.getAllPaginated(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PreAnalysisDTO>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }




    @GetMapping("/{id}")
    public ResponseEntity<PreAnalysisDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreAnalysisDTO> update(@PathVariable Integer id, @RequestBody PreAnalysisDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* SOFT DELETE */
    @GetMapping
    public ResponseEntity<List<PreAnalysisDTO>> listActive() {
        return ResponseEntity.ok(service.listActive());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<PreAnalysisDTO>> listDeleted() {
        return ResponseEntity.ok(service.listDeleted());
    }



    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Integer id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }
}
