package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.repository.model.JpaBranch;
import com.example.CRUDRESTapi.service.dto.BranchDto;
import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto);
    BranchDto updateBranch(BranchDto branchDto);
    List<BranchDto> getAllBranch();
    BranchDto getBranchById(long BranchID);
    void deleteBranch(long id);
}
