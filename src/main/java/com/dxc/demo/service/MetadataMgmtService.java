package com.dxc.demo.service;

import java.util.List;

import org.javatuples.KeyValue;

import com.dxc.demo.data.dto.MetadataInfo;
import com.dxc.demo.utils.GeneralContentResult;
import com.dxc.demo.utils.GeneralPagingResult;
import com.dxc.demo.utils.GeneralResult;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public interface MetadataMgmtService {
    GeneralPagingResult<List<MetadataInfo>> findAllByPage(String keyword, Integer page, Integer size);

    GeneralContentResult<MetadataInfo> getById(String id);

    GeneralContentResult<String> saveOrUpdate(MetadataInfo dto);

    GeneralResult delete(String id);

    GeneralContentResult<List<KeyValue<String, String>>> findByAttributes(String attributes);
}
