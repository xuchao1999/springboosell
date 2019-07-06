package com.xc.sell.controller;

import com.xc.sell.dataobject.ProductCategory;
import com.xc.sell.dataobject.ProductInfo;
import com.xc.sell.service.ProductCategoryService;
import com.xc.sell.service.ProductInfoService;
import com.xc.sell.util.ResultVOUtil;
import com.xc.sell.viewObject.ProductInfoVO;
import com.xc.sell.viewObject.ProductVO;
import com.xc.sell.viewObject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("buyer/product")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productService;

    @Autowired
    private ProductCategoryService categoryService;


    @GetMapping("/list")
    public ResultVO list() {
        /*1.获取全部类目*/
        List<ProductCategory> productCategories = categoryService.findAll();

        List<ProductVO> productVOS = new ArrayList<>();

        for (ProductCategory productCategory : productCategories) {

            /*通过categoryId和productStatus获取上架商品列表*/
            List<ProductInfo> productInfos = productService.findByCtegoryIdAndProductStatus(productCategory.getCategoryId(), Integer.valueOf(0));

            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                ProductInfoVO pIVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, pIVO);

                productInfoVOS.add(pIVO);
            }

            ProductVO pVO = new ProductVO(productCategory.getCategoryName(), productCategory.getCategoryId(), productInfoVOS);

            productVOS.add(pVO);
        }
        return ResultVOUtil.success(productVOS);
    }

    @GetMapping("detail")
    public ResultVO detail(@RequestParam("productId") String productId){
        ProductInfo productInfo = productService.findOne(productId);
        return ResultVOUtil.success(productInfo);
    }

}
