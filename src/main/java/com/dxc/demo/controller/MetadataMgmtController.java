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

import com.dxc.demo.data.dto.MetadataCreate;
import com.dxc.demo.data.dto.MetadataInfo;
import com.dxc.demo.data.dto.MetadataUpdate;
import com.dxc.demo.service.MetadataMgmtService;
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
@RequestMapping(value = "/metadata")
@Api(tags = {"元数据管理"})
//@SwaggerDefinition(
//    info = @Info(
//            description = "元数据管理1",
//            version = "V0.0.1",
//            title = "元数据管理 API",
//            contact = @Contact(
//               name = "Huanfeng Cai",
//               email = "huanfeng.cai@dxc.com",
//               url = "https://www.dxc.technology"
//            )
//    ),
//    schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
//    tags = {
//            @Tag(name = "元数据管理", description = "信息资源列表及新建信息资源")
//    }
//)
public class MetadataMgmtController {
    @Autowired
    private MetadataMgmtService metadataService;

    @ApiOperation(value = "元数据列表", notes = "根据输入的关键字查询元数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示的件数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "String") })
    @RequestMapping(value = "/all/page/{page}/size/{size}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralPagingResult<List<MetadataInfo>> findAllByPage(@PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        return metadataService.findAllByPage(keyword, page, size);
    }

    @ApiOperation(value = "新建元数据", notes = "新建元数据")
    @ApiImplicitParam(name = "dto", value = "元数据信息", required = true, dataType = "MetadataCreate")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public GeneralContentResult<String> save(@Valid @RequestBody MetadataCreate dto) {
        return metadataService.saveOrUpdate(dto);
    }

    @ApiOperation(value = "获取元数据", notes = "根据url指定的id获取元数据")
    @ApiImplicitParam(name = "id", value = "元数据ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GeneralContentResult<MetadataInfo> getById(@PathVariable String id) {
        return metadataService.getById(id);
    }

    @ApiOperation(value = "编辑元数据", notes = "根据url指定的id编辑元数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "元数据ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "dto", value = "元数据信息", required = true, dataType = "MetadataUpdate") })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public GeneralContentResult<String> update(@PathVariable String id, @Valid @RequestBody MetadataUpdate dto) {
        dto.setId(id);
        return metadataService.saveOrUpdate(dto);
    }

    @ApiOperation(value = "删除元数据", notes = "根据url指定的id删除元数据")
    @ApiImplicitParam(name = "id", value = "元数据ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public GeneralResult delete(@PathVariable String id) {
        return metadataService.delete(id);
    }

    @ApiOperation(value = "字典列表(属性)", notes = "根据输入的字段查询字典列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attributes", value = "属性", required = true, dataType = "String", paramType = "path") })
    @RequestMapping(value = "/attributes/{attributes}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralContentResult<List<KeyValue<String, String>>> findByField(@PathVariable("attributes") String attributes) {
        return metadataService.findByAttributes(attributes);
    }
}
