package com.farmacia.v1.controller;



import com.farmacia.v1.dto.FileDTO;
import com.farmacia.v1.entity.FileEntity;
import com.farmacia.v1.repository.FileRepository;
import com.farmacia.v1.repository.ReceptionRepository;
import com.farmacia.v1.repository.UserRepository;
import com.farmacia.v1.service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/files")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class FileController {

    private final FileServiceImpl service;
    private final FileRepository repository;
    private final ReceptionRepository receptionRepository;
    private final UserRepository userRepository;

    private static final String UPLOAD_DIR = "uploads/files/";

    // ===== CRUD + Soft Delete =====

    @PostMapping
    public ResponseEntity<FileDTO> create(@RequestBody FileDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileDTO> update(@PathVariable Integer id, @RequestBody FileDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileDTO>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FileDTO>> listActive() {
        return ResponseEntity.ok(service.listActive());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<FileDTO>> listDeleted() {
        return ResponseEntity.ok(service.listDeleted());
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Integer id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllPaginated")
    public ResponseEntity<Page<FileDTO>> getAllPaginated(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer receptionId,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        if (receptionId != null) {
            return ResponseEntity.ok(service.getAllPaginatedByReception(receptionId, pageable));
        }
        if (name != null) {
            return ResponseEntity.ok(service.getAllPaginated(name, pageable));
        }
        return ResponseEntity.ok(service.getAllPaginated(pageable));
    }

    // ===== Upload / Download =====

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDTO> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("reception_id") Integer receptionId,
            @RequestParam("user_id") Integer userId,
            @RequestParam(value = "description", required = false) String description
    ) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // sha256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String checksum = bytesToHex(md.digest(file.getBytes()));

            FileEntity entity = FileEntity.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .path(filePath.toString())
                    .size(file.getSize())
                    .description(description)
                    .checksum(checksum)
                    .reception(receptionRepository.findById(receptionId).orElse(null))
                    .user(userRepository.findById(userId).orElse(null))
                    .build();

            var saved = repository.save(entity);

            FileDTO dto = FileDTO.builder()
                    .id(saved.getId())
                    .name(saved.getName())
                    .type(saved.getType())
                    .path(saved.getPath())
                    .size(saved.getSize())
                    .description(saved.getDescription())
                    .checksum(saved.getChecksum())
                    .build();

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer id) {
        Optional<FileEntity> opt = repository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        var file = opt.get();
        Path path = Paths.get(file.getPath());
        if (!Files.exists(path)) return ResponseEntity.notFound().build();

        Resource resource = new FileSystemResource(path.toFile());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType() != null ? file.getType() : "application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    // util
    private static String bytesToHex(byte[] hashBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    @GetMapping("/by-reception/{receptionId}")
    public ResponseEntity<List<FileDTO>> findByReception(@PathVariable Integer receptionId) {
        return ResponseEntity.ok(service.findByReception(receptionId));
    }
}
