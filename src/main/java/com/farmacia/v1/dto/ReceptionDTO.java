package com.farmacia.v1.dto;

import com.farmacia.v1.entity.LocationEntity;
import com.farmacia.v1.entity.PoliceEntity;
import com.farmacia.v1.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceptionDTO {
    private Integer id;
    private String number;
    private String date_reception;
    private String of_number;
    private String of_number_date;
    private LocationDTO location;
    private PoliceDTO police;
    private UserDTO user_origin;
    private UserDTO user_destination;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
