package com.moneyManager.dto;

import com.moneyManager.entity.ProfileEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;
    private Long profileId;
    private String name;
    private String icon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String type;
    private ProfileEntity profile;
}
