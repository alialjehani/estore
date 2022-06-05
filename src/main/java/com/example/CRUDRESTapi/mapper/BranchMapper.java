package com.example.CRUDRESTapi.mapper;

import com.example.CRUDRESTapi.repository.model.JpaBranch;
import com.example.CRUDRESTapi.service.dto.BranchDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
    public interface BranchMapper {
        BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);
        JpaBranch BranchDtoToEntity(BranchDto branchDto);
        BranchDto EntityToDto(JpaBranch jpaBranch);
        List<BranchDto> EntityListToDto(List<JpaBranch> jpaBranche);
}
