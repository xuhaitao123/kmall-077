package com.kgc.kmall.manager.controller;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.service.AttrService;
import org.apache.dubbo.config.annotation.Reference;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class AttrController {

    @Reference
    AttrService  AttrService;

    @RequestMapping("/spuList")
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id){
        List<PmsBaseAttrInfo> infoList = AttrService.select(catalog3Id);
        return infoList;
    }


}
