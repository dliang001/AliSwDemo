package com.dxc.demo.service;

import com.dxc.demo.data.dto.RuleCreationItem;
import com.dxc.demo.data.dto.ValidationCreationItem;
import com.dxc.demo.data.otd.CodeItem;
import com.dxc.demo.data.otd.RuleItem;
import com.dxc.demo.data.otd.ValidationItem;
import com.dxc.demo.data.otd.ValidationResultItem;
import com.dxc.demo.utils.GeneralContentResult;
import com.dxc.demo.utils.GeneralPagingResult;
import com.dxc.demo.utils.GeneralResult;

import java.util.List;

public interface CodeMgmtService {
    GeneralPagingResult<List<RuleItem>> getRules(Integer page, Integer size) throws Exception;
    GeneralResult createOrUpdateRule(RuleCreationItem ruleCreationItem) throws Exception;
    GeneralResult deleteRule(String id) throws Exception;
    GeneralPagingResult<List<CodeItem>> getCodes(Integer page, Integer size) throws Exception;
    GeneralContentResult<CodeItem> getCodeById(String id) throws Exception;
    GeneralPagingResult<List<ValidationItem>> getValidations(Integer page, Integer size) throws Exception;
    GeneralResult createOrUpdateValidation(ValidationCreationItem validationCreationItem) throws Exception;
    void scanRules(String id) throws Exception;
    GeneralContentResult<List<ValidationResultItem>> getValidationResults(String id) throws Exception;
}
