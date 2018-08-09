package com.dxc.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.javatuples.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.demo.data.dto.InformationResourceCreate;
import com.dxc.demo.data.dto.InformationResourceInfo;
import com.dxc.demo.data.dto.InformationResourceUpdate;
import com.dxc.demo.service.InformationResourceMgmtService;
import com.dxc.demo.utils.GeneralContentResult;
import com.dxc.demo.utils.GeneralPagingResult;
import com.dxc.demo.utils.GeneralResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/resource")
@Api(tags = {"信息资源目录"})
public class InformationResourceMgmtController {
    @Autowired
    private InformationResourceMgmtService informationResourceService;

    @ApiOperation(value = "信息资源列表", notes = "根据输入的关键字查询信息资源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示的件数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "String") })
    @RequestMapping(value = "/all/page/{page}/size/{size}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralPagingResult<List<InformationResourceInfo>> findAllByPage(@PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        return informationResourceService.findAllByPage(keyword, page, size);
    }

    @ApiOperation(value = "新建信息资源", notes = "新建信息资源")
    @ApiImplicitParam(name = "dto", value = "信息资源信息", required = true, dataType = "InformationResourceCreate")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public GeneralContentResult<String> save(@Valid @RequestBody InformationResourceCreate dto) {
        return informationResourceService.saveOrUpdate(dto);
    }

    @ApiOperation(value = "获取信息资源", notes = "根据url指定的id获取信息资源")
    @ApiImplicitParam(name = "id", value = "元数据ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GeneralContentResult<InformationResourceInfo> getById(@PathVariable String id) {
        return informationResourceService.getById(id);
    }

    @ApiOperation(value = "编辑信息资源", notes = "根据url指定的id编辑信息资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "信息资源ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "dto", value = "信息资源信息", required = true, dataType = "InformationResourceUpdate") })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public GeneralContentResult<String> update(@PathVariable String id, @Valid @RequestBody InformationResourceUpdate dto) {
        dto.setId(id);
        return informationResourceService.saveOrUpdate(dto);
    }

    @ApiOperation(value = "删除信息资源", notes = "根据url指定的id删除信息资源")
    @ApiImplicitParam(name = "id", value = "信息资源ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public GeneralResult delete(@PathVariable String id) {
        return informationResourceService.delete(id);
    }

    @ApiOperation(value = "字典列表(资源名称)")
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<KeyValue<String, String>>> findAllForKV() {
        return informationResourceService.findAllForKV();
    }
}
