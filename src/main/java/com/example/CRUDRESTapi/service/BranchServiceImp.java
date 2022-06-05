package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.exception.ResourceNotFoundException;
import com.example.CRUDRESTapi.mapper.BranchMapper;
import com.example.CRUDRESTapi.repository.BranchRepository;
import com.example.CRUDRESTapi.repository.model.JpaBranch;
import com.example.CRUDRESTapi.repository.model.JpaProduct;
import com.example.CRUDRESTapi.service.dto.BranchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BranchServiceImp implements BranchService{
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        JpaBranch jpaBranch = branchRepository.save(
                BranchMapper.INSTANCE.BranchDtoToEntity(branchDto));
            return BranchMapper.INSTANCE.EntityToDto(jpaBranch);
    }
    @Override
    public BranchDto updateBranch(BranchDto branchDto) {
         Optional<JpaBranch> updateBranch = branchRepository.findById(branchDto.getId());
         if(updateBranch.isPresent()){
             JpaBranch jpaBranch = branchRepository.save(
                     BranchMapper.INSTANCE.BranchDtoToEntity(branchDto));
             return BranchMapper.INSTANCE.EntityToDto(jpaBranch);
         }else{
             throw new ResourceNotFoundException("Resource Not Found");
         }
    }
    @Override
    public List<BranchDto> getAllBranch() {
           List<JpaBranch> branchList = new ArrayList<>();
           branchList = branchRepository.findAll();
           return BranchMapper.INSTANCE.EntityListToDto(branchList);
        }


    @Override
    public BranchDto getBranchById(long branchID) {
        Optional<JpaBranch> jpaBranch = branchRepository.findById(branchID);
        if(jpaBranch.isPresent()){
          return BranchMapper.INSTANCE.EntityToDto(jpaBranch.get());
        }
        else {
            throw new ResourceNotFoundException("Resources not found" + branchID);
        }
    }
    @Override
    public void deleteBranch(long branchID) {
        Optional<JpaBranch> BranchDB = branchRepository.findById(branchID);
        if (BranchDB.isPresent()){
            this.branchRepository.delete(BranchDB.get());
        }
        else{
            throw new ResourceNotFoundException("The Resource is not found");
        }
    }
}
