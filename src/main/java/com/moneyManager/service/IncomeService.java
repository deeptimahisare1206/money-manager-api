package com.moneyManager.service;

import com.moneyManager.dto.IncomeDTO;
import com.moneyManager.entity.CategoryEntity;
import com.moneyManager.entity.IncomeEntity;
import com.moneyManager.entity.ProfileEntity;
import com.moneyManager.repository.CategoryRepository;
import com.moneyManager.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final CategoryRepository categoryRepository;
    private final IncomeRepository incomeRepository;
    private final ProfileService profileService;

    public IncomeDTO addIncome(IncomeDTO dto){
        ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found."));
        IncomeEntity newIncome=toEntity(dto,profile,category);
        newIncome=incomeRepository.save(newIncome);
        return toDto(newIncome);
    }
    //helper methods
    private IncomeEntity toEntity(IncomeDTO incomeDto, ProfileEntity profile, CategoryEntity category ){
        return IncomeEntity.builder()
                .name(incomeDto.getName())
                .icon(incomeDto.getIcon())
                .amount(incomeDto.getAmount())
                .date(incomeDto.getDate())
                .profile(profile)
                .category(category)
                .build();
    }
    private IncomeDTO toDto(IncomeEntity entity){
        return IncomeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .icon(entity.getIcon())
                .categoryId(entity.getCategory()!=null?entity.getCategory().getId():null)
                .categoryName(entity.getCategory()!=null?entity.getCategory().getName():"N/A")
                .amount(entity.getAmount())
                .date(entity.getDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

    }
}
