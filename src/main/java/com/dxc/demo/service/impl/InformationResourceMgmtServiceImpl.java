package com.dxc.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.javatuples.KeyValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.demo.data.domain.InformationResource;
import com.dxc.demo.data.dto.InformationResourceInfo;
import com.dxc.demo.data.repositories.DictionaryRepository;
import com.dxc.demo.data.repositories.InformationResourceRepository;
import com.dxc.demo.service.InformationResourceMgmtService;
import com.dxc.demo.utils.GeneralContentResult;
import com.dxc.demo.utils.GeneralPagingResult;
import com.dxc.demo.utils.GeneralResult;
import com.dxc.demo.utils.PageableResult;
import com.dxc.demo.utils.ResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Service
@Slf4j
public class InformationResourceMgmtServiceImpl implements InformationResourceMgmtService {
    @Autowired
    private InformationResourceRepository informationResourceRepository;
    
    @Autowired
    private DictionaryRepository dictionaryRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralPagingResult<List<InformationResourceInfo>> findAllByPage(String keyword, Integer page, Integer size) {
        GeneralPagingResult<List<InformationResourceInfo>> result = new GeneralPagingResult<>();
        Pageable pageable = PageableResult.createPageRequest(page, size, Sort.Direction.ASC, "name", "system", "databaseType", "databaseName", "tableName", "tableField");
        Page<InformationResource> pageDomains = informationResourceRepository
                .findByNameIgnoreCaseContainingOrSystemIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(keyword, keyword, keyword, pageable);
        List<InformationResource> content = pageDomains.getContent();

        List<InformationResourceInfo> list = content.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
        PageableResult<List<InformationResourceInfo>> pageableResult = new PageableResult<>(pageable.getPageNumber(),
                pageable.getPageSize(), pageDomains.getTotalElements(), list);
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(pageableResult.getContent());
        result.setPageInfo(pageableResult.getPageInfo());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<InformationResourceInfo> getById(String id) {
        InformationResourceInfo dto = informationResourceRepository.findById(id).map(e->convertToDto(e)).orElse(null);
        GeneralContentResult<InformationResourceInfo> result = new GeneralContentResult<InformationResourceInfo>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(dto);
        return result;
    }

    @Override
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    public GeneralContentResult<String> saveOrUpdate(InformationResourceInfo dto) {
        InformationResource entity = convertToEntity(dto);
        entity = informationResourceRepository.saveAndFlush(entity);
        GeneralContentResult<String> result = new GeneralContentResult<String>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(entity.getId());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<List<KeyValue<String, String>>> findAllForKV() {
        List<InformationResource> content = informationResourceRepository.findAll(Sort.by(Sort.Direction.ASC, "name", "system"));
        List<KeyValue<String, String>> list = content.stream().map(entity -> convertToKeyValue(entity)).collect(Collectors.toList());
        GeneralContentResult<List<KeyValue<String, String>>> result = new GeneralContentResult<List<KeyValue<String, String>>>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(list);
        return result;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public GeneralResult delete(String id) {
        informationResourceRepository.deleteById(id);
        GeneralResult result = new GeneralResult();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        return result;
    }

    private KeyValue<String, String> convertToKeyValue(InformationResource entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }
        return KeyValue.with(entity.getId(), entity.getName());
    }
    
    private InformationResourceInfo convertToDto(InformationResource entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }

        InformationResourceInfo dto = modelMapper.map(entity, InformationResourceInfo.class);
//        if (entity.getSwRule() != null) {
//            dto.setRuleId(entity.getSwRule().getId());
//            dto.setRuleName(entity.getSwRule().getRuleName());
//        }

        dto.setDatabaseTypeName(Optional.ofNullable(dto.getDatabaseType())
                .map(code -> dictionaryRepository.findByCode(code)).map(dic -> dic.getValue()).orElse(""));
        return dto;
    }

    private InformationResource convertToEntity(InformationResourceInfo dto) {
        InformationResource entity = modelMapper.map(dto, InformationResource.class);
//        SwRule rule = new SwRule();
//        rule.setId(dto.getRuleId());
//        entity.setSwRule(rule);
        return entity;
    }
}
