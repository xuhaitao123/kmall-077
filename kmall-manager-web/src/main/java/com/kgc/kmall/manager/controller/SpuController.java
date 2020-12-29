package com.kgc.kmall.manager.controller;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FilenameUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "商品信息管理",description = "商品属性spu管理")
public class SpuController {
    @Reference
    SpuService spuService;

    @Value("${fileServer.url}")
    String fileUrl;
    @ApiOperation("根据三级分类id查询商品信息")
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(Long catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return  pmsProductInfos;
    }
    @ApiOperation("保存图片信息")
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file){

        try {
            String confFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init("E:\\第三期\\kmall-077\\kmall-manager-web\\target\\classes\\tracker.conf");
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String orginalFilename= file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(orginalFilename);
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);

            String path=fileUrl;
            for (int i = 0; i < upload_file.length; i++) {
                String s = upload_file[i];
                System.out.println("s = " + s);
                path+="/"+s;
            }
            System.out.println(path);
            //文件上传
            //返回文件上传后的路径
            return path;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("添加属性销售值")
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> saleAttrList = spuService.baseSaleAttrList();
        return saleAttrList;
    }

    @ApiOperation("添加属性值保存")
    @PostMapping(value = "/spuSaleAttrList",produces = "application/json;charset=UTF-8")
    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody @ApiParam(name = "spuId",value = "用户对象",required = true)  PmsProductInfo pmsProductInfo){
        //保存数据库
        Integer integer = spuService.saveSpuInfo(pmsProductInfo);
        return integer>0?"success":"fail";
    }

    @ApiOperation("获取spu值")

    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrList=spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrList;
    }

    @ApiOperation("获取spu值")
    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(Long spuId){
        List<PmsProductImage> pmsProductImageList = spuService.spuImageList(spuId);
        return pmsProductImageList;
    }


}
