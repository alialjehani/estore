package com.example.CRUDRESTapi.controller;

import com.example.CRUDRESTapi.service.BranchService;
import com.example.CRUDRESTapi.service.dto.BranchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Annotation used to tell this is the class for REST api end-point
@RestController
public class BranchController {

    //Annotation for dependency injection that connects this class with the object from BranchService
    @Autowired
    private BranchService branchService;

    @GetMapping("api/Branches")
    public ResponseEntity<List<BranchDto>> getAllBranch(){
        return ResponseEntity.ok().body(branchService.getAllBranch());
    }
    @GetMapping("api/Branches/{id}")
    public ResponseEntity<BranchDto> getBranchByID(@PathVariable long id){
        return ResponseEntity.ok().body(branchService.getBranchById(id));
    }
    @PostMapping("api/Branches")
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto){
        return ResponseEntity.ok().body(branchService.createBranch(branchDto));
    }
    @PutMapping("api/Branches/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable long id, @RequestBody BranchDto branchDto){
        branchDto.setId(id);
        return ResponseEntity.ok().body(this.branchService.updateBranch(branchDto));
    }
    @DeleteMapping("api/Branches/{id}")
    public HttpStatus deleteBranch(@PathVariable long id){
        this.branchService.deleteBranch(id);
        return HttpStatus.OK;
    }
}