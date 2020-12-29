package com.kgc.kmall.manager.controller;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.service.AttrService;
import io.swagger.annotations.*;
import net.bytebuddy.asm.Advice;
import org.apache.dubbo.config.annotation.Reference;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "平台属性列表接口",description = "")
public class AttrController {

    @Reference
    AttrService  AttrService;

    @ApiOperation("根据三级分类id查询平台属性")
    @RequestMapping("/attrInfoList")
    @ApiImplicitParam(name = "id",value = "三级分类id",required = true)
    @ApiResponses({
            @ApiResponse(code = 400,message = "请求参数问题"),
            @ApiResponse(code = 404,message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405,message = "请求类型不正确"),
            @ApiResponse(code = 500,message = "后端程序出错")
    })
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id){
        List<PmsBaseAttrInfo> infoList = AttrService.select(catalog3Id);
        return infoList;
    }
    @ApiOperation("添加平台属性")
    /*@RequestMapping("/saveAttrInfo")*/
    @PostMapping(value = "/saveAttrInfo",produces = "application/json;charset=UTF-8")
    public Integer  baseSaleAttrList(@RequestBody @ApiParam(name = "attrInfo",value = "用户对象",required = true) PmsBaseAttrInfo attrInfo){
        Integer add = AttrService.add(attrInfo);
        return  add;
    }



}
