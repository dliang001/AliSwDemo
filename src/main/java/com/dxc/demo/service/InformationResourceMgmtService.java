package com.dxc.demo.service;

import java.util.List;

import org.javatuples.KeyValue;

import com.dxc.demo.data.dto.InformationResourceInfo;
import com.dxc.demo.utils.GeneralContentResult;
import com.dxc.demo.utils.GeneralPagingResult;
import com.dxc.demo.utils.GeneralResult;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public interface InformationResourceMgmtService {
    GeneralPagingResult<List<InformationResourceInfo>> findAllByPage(String keyword, Integer page, Integer size);

    GeneralContentResult<InformationResourceInfo> getById(String id);

    GeneralContentResult<String> saveOrUpdate(InformationResourceInfo dto);

    GeneralResult delete(String id);

    GeneralContentResult<List<KeyValue<String, String>>> findAllForKV();
}
