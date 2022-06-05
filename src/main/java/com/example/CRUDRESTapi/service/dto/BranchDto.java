package com.example.CRUDRESTapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BranchDto {
        @NotBlank
        @NotNull
        private long id;
        @NotBlank
        @NotNull
        private String branch_name;



    }

