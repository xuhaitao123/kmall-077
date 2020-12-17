package com.kgc.kmall.service;

import com.kgc.kmall.bean.PmsProductImage;
import com.kgc.kmall.bean.PmsProductInfo;

import java.util.List;

public interface SpuService {
    public List<PmsProductInfo> spuList(Long catalog3Id);

  /*  int add(PmsProductImage pmsProductImage);*/
}
