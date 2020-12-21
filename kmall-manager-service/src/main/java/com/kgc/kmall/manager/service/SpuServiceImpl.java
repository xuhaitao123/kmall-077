package com.kgc.kmall.manager.service;

import com.kgc.kmall.bean.PmsBaseSaleAttr;
import com.kgc.kmall.bean.PmsProductImage;
import com.kgc.kmall.bean.PmsProductInfo;
import com.kgc.kmall.bean.PmsProductInfoExample;
import com.kgc.kmall.manager.mapper.PmsBaseSaleAttrMapper;
import com.kgc.kmall.manager.mapper.PmsProductImageMapper;
import com.kgc.kmall.manager.mapper.PmsProductInfoMapper;
import com.kgc.kmall.service.SpuService;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.List;

@Component
@org.apache.dubbo.config.annotation.Service
public class SpuServiceImpl implements SpuService{
    @Resource
    PmsProductInfoMapper PmsProductInfoMapper;

    @Resource
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsProductInfo> spuList(Long catalog3Id) {
        PmsProductInfoExample example=new PmsProductInfoExample();
        example.createCriteria().andCatalog3IdEqualTo(catalog3Id);
        List<PmsProductInfo> pmsProductInfos = PmsProductInfoMapper.selectByExample(example);
        return pmsProductInfos;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> saleAttrList = pmsBaseSaleAttrMapper.selectByExample(null);
        return saleAttrList;
    }


}
