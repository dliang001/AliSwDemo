package com.dxc.demo.service;

import java.util.List;

import org.javatuples.KeyValue;

import com.dxc.demo.data.dto.DictionaryInfo;
import com.dxc.demo.utils.GeneralContentResult;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public interface DictionaryService {
    GeneralContentResult<List<DictionaryInfo>> findAll();

    GeneralContentResult<List<KeyValue<String, String>>> findByField(String field);

    GeneralContentResult<List<KeyValue<String, String>>> findByAttributes();
}
