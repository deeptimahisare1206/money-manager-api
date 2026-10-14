package com.moneyManager.service;

import com.moneyManager.dto.CategoryDTO;
import com.moneyManager.dto.ExpenseDTO;
import com.moneyManager.entity.CategoryEntity;
import com.moneyManager.entity.ExpenseEntity;
import com.moneyManager.entity.ProfileEntity;
import com.moneyManager.repository.CategoryRepository;
import com.moneyManager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ProfileService profileService;

    public ExpenseDTO addExpense(ExpenseDTO dto){
        ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found."));
        ExpenseEntity newExpense=toEntity(dto,profile,category);
        newExpense=expenseRepository.save(newExpense);
        return toDto(newExpense);
    }
    //helper methods
    private ExpenseEntity toEntity(ExpenseDTO expenseDTO, ProfileEntity profile,CategoryEntity category ){
        return ExpenseEntity.builder()
                .name(expenseDTO.getName())
                .icon(expenseDTO.getIcon())
                .amount(expenseDTO.getAmount())
                .date(expenseDTO.getDate())
                .profile(profile)
                .category(category)
                .build();
    }
    private ExpenseDTO toDto(ExpenseEntity entity){
        return ExpenseDTO.builder()
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
