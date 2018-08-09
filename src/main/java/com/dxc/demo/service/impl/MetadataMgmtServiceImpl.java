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

import com.dxc.demo.data.domain.Metadata;
import com.dxc.demo.data.dto.MetadataInfo;
import com.dxc.demo.data.repositories.DictionaryRepository;
import com.dxc.demo.data.repositories.MetadataRepository;
import com.dxc.demo.service.MetadataMgmtService;
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
public class MetadataMgmtServiceImpl implements MetadataMgmtService {
    @Autowired
    private MetadataRepository metadataRepository;
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralPagingResult<List<MetadataInfo>> findAllByPage(String keyword, Integer page, Integer size) {
        GeneralPagingResult<List<MetadataInfo>> result = new GeneralPagingResult<>();
        Pageable pageable = PageableResult.createPageRequest(page, size, Sort.Direction.ASC, "attributes", "name", "code");
        Page<Metadata> pageDomains = metadataRepository
                .findByNameIgnoreCaseContainingOrCodeIgnoreCaseContainingOrAttributesIgnoreCaseContaining(keyword, keyword, keyword, pageable);
        List<Metadata> content = pageDomains.getContent();

        List<MetadataInfo> list = content.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
        PageableResult<List<MetadataInfo>> pageableResult = new PageableResult<>(pageable.getPageNumber(),
                pageable.getPageSize(), pageDomains.getTotalElements(), list);
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(pageableResult.getContent());
        result.setPageInfo(pageableResult.getPageInfo());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<MetadataInfo> getById(String id) {
        MetadataInfo dto = metadataRepository.findById(id).map(e->convertToDto(e)).orElse(null);
        GeneralContentResult<MetadataInfo> result = new GeneralContentResult<MetadataInfo>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(dto);
        return result;
    }

    @Override
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    public GeneralContentResult<String> saveOrUpdate(MetadataInfo dto) {
        Metadata entity = convertToEntity(dto);
        entity = metadataRepository.saveAndFlush(entity);
        GeneralContentResult<String> result = new GeneralContentResult<String>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(entity.getId());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public GeneralResult delete(String id) {
        metadataRepository.deleteById(id);
        GeneralResult result = new GeneralResult();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public GeneralContentResult<List<KeyValue<String, String>>> findByAttributes(String attributes) {
        List<Metadata> content = metadataRepository.findByAttributesQuery(attributes);
        List<KeyValue<String, String>> list = content.stream().map(entity -> convertToKeyValue(entity)).collect(Collectors.toList());
        GeneralContentResult<List<KeyValue<String, String>>> result = new GeneralContentResult<List<KeyValue<String, String>>>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(list);
        return result;
    }

    private MetadataInfo convertToDto(Metadata entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }

        MetadataInfo dto = modelMapper.map(entity, MetadataInfo.class);
        dto.setAttributesName(Optional.ofNullable(dto.getAttributes())
                .map(code -> dictionaryRepository.findByCode(code)).map(dic -> dic.getValue()).orElse(""));
        return dto;
    }

    private KeyValue<String, String> convertToKeyValue(Metadata entity) {
        if (entity == null) {
            log.warn("entity is null.");
            return null;
        }
        return KeyValue.with(entity.getId(), entity.getName());
    }

    private Metadata convertToEntity(MetadataInfo dto) {
        return modelMapper.map(dto, Metadata.class);
    }
}
