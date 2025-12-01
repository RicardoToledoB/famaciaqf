package com.farmacia.v1.dto;



import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private Integer id;
    private String name;
    private String type;
    private String path;
    private Long size;
    private String description;
    private String checksum;

    private ReceptionDTO reception; // solo id en el mapping para evitar cargas grandes
    private UserDTO user;           // solo id en el mapping

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
