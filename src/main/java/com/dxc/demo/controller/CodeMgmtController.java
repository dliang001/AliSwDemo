package com.dxc.demo.controller;

import com.dxc.demo.data.dto.RuleCreationItem;
import com.dxc.demo.data.dto.ValidationCreationItem;
import com.dxc.demo.data.otd.CodeItem;
import com.dxc.demo.data.otd.RuleItem;
import com.dxc.demo.data.otd.ValidationItem;
import com.dxc.demo.data.otd.ValidationResultItem;
import com.dxc.demo.service.CodeMgmtService;
import com.dxc.demo.utils.GeneralContentResult;
import com.dxc.demo.utils.GeneralPagingResult;
import com.dxc.demo.utils.GeneralResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CodeMgmtController {
    @Autowired
    private CodeMgmtService codeMgmtService;

    @ApiOperation("分页获取编码规则列表")
    @RequestMapping(value = "/rules/page/{page}/size/{size}", method = RequestMethod.POST)
    @ResponseBody
    public GeneralPagingResult<List<RuleItem>> getRules(@PathVariable("page") Integer page, @PathVariable("size") Integer size) throws Exception {
        return codeMgmtService.getRules(page, size);
    }

    @ApiOperation(value = "创建或修改编码规则")
    @RequestMapping(value = "/authsec/alter/rule", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResult createOrUpdateRule(@RequestBody RuleCreationItem ruleCreationItem) throws Exception {
        return codeMgmtService.createOrUpdateRule(ruleCreationItem);
    }

    @ApiOperation(value = "删除一个编码规则")
    @RequestMapping(value = "/authsec/alter/rule/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GeneralResult deleteRule(@PathVariable("id") String id) throws Exception {
        return codeMgmtService.deleteRule(id);
    }

    @ApiOperation("分页获取编码列表")
    @RequestMapping(value = "/codes/page/{page}/size/{size}", method = RequestMethod.POST)
    @ResponseBody
    public GeneralPagingResult<List<CodeItem>> getCodes(@PathVariable("page") Integer page, @PathVariable("size") Integer size) throws Exception {
        return codeMgmtService.getCodes(page, size);
    }

    @ApiOperation(value = "获取编码详情")
    @RequestMapping(value = "/code/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<CodeItem> getCodeById(@PathVariable("id") String id) throws Exception {
        return codeMgmtService.getCodeById(id);
    }

    @ApiOperation("分页获取校验列表")
    @RequestMapping(value = "/validations/page/{page}/size/{size}", method = RequestMethod.POST)
    @ResponseBody
    public GeneralPagingResult<List<ValidationItem>> getValidations(@PathVariable("page") Integer page, @PathVariable("size") Integer size) throws Exception {
        return codeMgmtService.getValidations(page, size);
    }

    @ApiOperation(value = "新建/编辑校验")
    @RequestMapping(value = "/authsec/alter/validation", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResult createOrUpdateValidation(@RequestBody ValidationCreationItem validationCreationItem) throws Exception {
        return codeMgmtService.createOrUpdateValidation(validationCreationItem);
    }

    @ApiOperation(value = "开始校验")
    @RequestMapping(value = "/rule/validation/{id}",method = RequestMethod.GET)
    @ResponseBody
    public void scanRules(@PathVariable("id") String id){
        try {
            codeMgmtService.scanRules(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("获取校验结果")
    @RequestMapping(value = "/rule/validation/results/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<ValidationResultItem>> getValidationResults(@PathVariable("id") String id) throws Exception {
        return codeMgmtService.getValidationResults(id);
    }
}
