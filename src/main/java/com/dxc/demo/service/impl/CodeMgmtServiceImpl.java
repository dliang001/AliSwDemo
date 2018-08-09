package com.dxc.demo.service.impl;

import com.dxc.demo.data.domain.*;
import com.dxc.demo.data.dto.RuleCreationItem;
import com.dxc.demo.data.dto.ValidationCreationItem;
import com.dxc.demo.data.mapping.RuleMapper;
import com.dxc.demo.data.otd.CodeItem;
import com.dxc.demo.data.otd.RuleItem;
import com.dxc.demo.data.otd.ValidationItem;
import com.dxc.demo.data.otd.ValidationResultItem;
import com.dxc.demo.data.repositories.*;
import com.dxc.demo.service.CodeMgmtService;
import com.dxc.demo.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CodeMgmtServiceImpl implements CodeMgmtService {
    @Autowired
    private SwRuleRepository swRuleRepository;

    @Autowired
    private SwRuleDetailRepository swRuleDetailRepository;

    @Autowired
    private SwRuleCodeRepository swRuleCodeRepository;

    @Autowired
    private SwRuleValidationRepository swRuleValidationRepository;

    @Autowired
    private SwRuleValidationResultRepository swRuleValidationResultRepository;

    @Autowired
    private ValidationRepository validationRepository;

    @Override
    public GeneralPagingResult<List<RuleItem>> getRules(Integer page, Integer size) throws Exception {
        GeneralPagingResult<List<RuleItem>> result = new GeneralPagingResult<>();
        Pageable pageable = PageableResult.createPageRequest(page, size, Sort.Direction.DESC, "createDate");
        Page<SwRule> pageDomains = swRuleRepository.findAll(pageable);
        List<SwRule> swRules = pageDomains.getContent();
        log.debug("swRules = {}", swRules);
        List<RuleItem> ruleItems = new ArrayList<>();
        if(ListUtils.isNotEmpty(swRules)){
            ruleItems = swRules.stream().map(RuleMapper::mapToDomain).collect(Collectors.toList());
        }
        PageableResult<List<RuleItem>> pageableResult = new PageableResult<>(pageable.getPageNumber(), pageable.getPageSize(), pageDomains.getTotalElements(), ruleItems);
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(pageableResult.getContent());
        result.setPageInfo(pageableResult.getPageInfo());
        return result;
    }

    @Override
    public GeneralResult createOrUpdateRule(RuleCreationItem ruleCreationItem) throws Exception {
        SwRule swRule = RuleMapper.mapToDomain(ruleCreationItem);
        // 修改时：save 前清空item
        if(!StringUtils.isEmpty(ruleCreationItem.getId())){
            swRuleDetailRepository.deleteByRuleId(swRule); // 先清空
        }
        log.debug("swRuleRepository.save({})", swRule);
        swRuleRepository.save(swRule); // 创建或修改
        GeneralResult result = new GeneralResult();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        return result;
    }

    @Override
    public GeneralResult deleteRule(String id) throws Exception {
        swRuleRepository.deleteById(id);
        GeneralResult result = new GeneralResult();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        return result;
    }

    @Override
    public GeneralPagingResult<List<CodeItem>> getCodes(Integer page, Integer size) throws Exception {
        GeneralPagingResult<List<CodeItem>> result = new GeneralPagingResult<>();
        Pageable pageable = PageableResult.createPageRequest(page, size, Sort.Direction.DESC, "createDate");
        Page<SwRuleCode> pageDomains = swRuleCodeRepository.findAll(pageable);
        List<SwRuleCode> swRuleCodes = pageDomains.getContent();
        log.debug("swRuleCodes = {}", swRuleCodes);
        List<CodeItem> codeItems = new ArrayList<>();
        if(ListUtils.isNotEmpty(swRuleCodes)){
            codeItems = swRuleCodes.stream().map(RuleMapper::mapToDomain).collect(Collectors.toList());
        }
        PageableResult<List<CodeItem>> pageableResult = new PageableResult<>(pageable.getPageNumber(), pageable.getPageSize(), pageDomains.getTotalElements(), codeItems);
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(pageableResult.getContent());
        result.setPageInfo(pageableResult.getPageInfo());
        return result;
    }

    @Override
    public GeneralContentResult<CodeItem> getCodeById(String id) throws Exception {
        SwRuleCode swRuleCode = swRuleCodeRepository.findById(id).orElse(new SwRuleCode());
        GeneralContentResult<CodeItem> result = new GeneralContentResult<>();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(RuleMapper.mapToDomain(swRuleCode));
        return result;
    }

    @Override
    public GeneralPagingResult<List<ValidationItem>> getValidations(Integer page, Integer size) throws Exception {
        GeneralPagingResult<List<ValidationItem>> result = new GeneralPagingResult<>();
        Pageable pageable = PageableResult.createPageRequest(page, size, Sort.Direction.DESC, "createDate");
        Page<SwRuleValidation> pageDomains = swRuleValidationRepository.findAll(pageable);
        List<SwRuleValidation> swRuleValidations = pageDomains.getContent();
        log.debug("swRuleValidations = {}", swRuleValidations);
        List<ValidationItem> validationItems = new ArrayList<>();
        if(ListUtils.isNotEmpty(swRuleValidations)){
            validationItems = swRuleValidations.stream().map(RuleMapper::mapToDomain).collect(Collectors.toList());
        }
        PageableResult<List<ValidationItem>> pageableResult = new PageableResult<>(pageable.getPageNumber(), pageable.getPageSize(), pageDomains.getTotalElements(), validationItems);
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(pageableResult.getContent());
        result.setPageInfo(pageableResult.getPageInfo());
        return result;
    }

    @Override
    public GeneralResult createOrUpdateValidation(ValidationCreationItem validationCreationItem) throws Exception {
        SwRuleValidation swRuleValidation = RuleMapper.mapToDomain(validationCreationItem);
        swRuleValidationRepository.save(swRuleValidation); // 创建或修改
        GeneralResult result = new GeneralResult();
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        return result;
    }

    @Override
    public void scanRules(String id) throws Exception {
        List<Validation> validations = validationRepository.findAll();
        if(ListUtils.isNotEmpty(validations)){
            List<String> listA = validations.stream().map(Validation::getA).collect(Collectors.toList());
            List<String> listB = validations.stream().map(Validation::getB).collect(Collectors.toList());
            List<String> listC = validations.stream().map(Validation::getC).collect(Collectors.toList());
            log.debug("listA = {}, listB = {}, listC = {}", listA, listB, listC);
            SwRuleValidation swRuleValidation = swRuleValidationRepository.getOne(id);
            // 1. 读取被校验表数据
            String resName = swRuleValidation.getResName();
            List<String> validationList = new ArrayList<>();
            if(resName.contains("A")){
                validationList = listA;
            } else if(resName.contains("B")){
                validationList = listB;
            } else if(resName.contains("C")){
                validationList = listC;
            }
            log.debug("待校验的数据 validationList = {}", validationList);
            // 2. 筛选出不符合规则的数据
            String ruleName = swRuleValidation.getRuleName();
            // 根据ruleName抽象出正则表达式
            String pattern = "";
            if("客户".equals(ruleName)){
                pattern = "^K-[0-9]{2}[a-z][0-9]{3}\\w{3}$";
            } else if("水表".equals(ruleName)){
                pattern = "^M-G[0-9]{1}-\\w{6}$";
            } else if("网点".equals(ruleName)){
                pattern = "^W-[a-z][0-9]{3}\\w{2}$";
            }
            Pattern r = Pattern.compile(pattern);
            List<String> ng = new ArrayList<>();
            for(String str : validationList){
                Matcher m = r.matcher(str);
                if(!m.matches()){
                    ng.add(str);
                }
            }
            log.debug("不符合的数据 ng = {}", ng);
            // 3. 入库供下次查询
            if(ListUtils.isNotEmpty(ng)){
                List<SwRuleValidationResult> slist = ng.stream().map(s -> RuleMapper.mapToDomain(s, resName)).collect(Collectors.toList());
                swRuleValidationResultRepository.deleteByResName(resName); // 先清空旧数据
                swRuleValidationResultRepository.saveAll(slist);
            }
        }
    }

    @Override
    public GeneralContentResult<List<ValidationResultItem>> getValidationResults(String id) throws Exception {
        GeneralContentResult<List<ValidationResultItem>> result = new GeneralContentResult<>();
        SwRuleValidation swRuleValidation = swRuleValidationRepository.getOne(id);
        List<SwRuleValidationResult> swRuleValidationResults = swRuleValidationResultRepository.findByResName(swRuleValidation.getResName());
        List<ValidationResultItem> results = new ArrayList<>();
        if(ListUtils.isNotEmpty(swRuleValidationResults)){
            results = swRuleValidationResults.stream().map(RuleMapper::mapToDomain).collect(Collectors.toList());
        }
        result.setResultCode(ResultCode.OPERATION_SUCCESS);
        result.setResultContent(results);
        return result;
    }

    public static void main(String args[]) {
        String str = "K-15d001abc";
        String pattern = "^K-[0-9]{2}[a-z][0-9]{3}\\w{3}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
