package com.latewind.module.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 展示商品
 * @author lsqwell
 *
 */

import com.latewind.module.category.entity.SubCategory;
import com.latewind.module.category.entity.ThirdCategory;
import com.latewind.module.category.entity.TopCategory;
import com.latewind.module.category.service.ICategoryManService;
import com.latewind.module.category.service.ISubCategoryService;
import com.latewind.module.category.service.IThirdCategoryService;
import com.latewind.module.product.entity.ProductInfo;
import com.latewind.module.product.entity.PrtComment;
import com.latewind.module.product.service.IProductService;
@Controller
public class ViewProductController {
	@Resource 
	private IProductService productService=null;
	
	@Resource
	private IThirdCategoryService thirdCategoryService=null;
	
	@Resource
	private ISubCategoryService subCategoryService=null;
	
	@Resource
	private ICategoryManService categoryManService=null;
	Logger logger = Logger.getLogger(ViewProductController.class);
	@RequestMapping("/front/product/{prtId}")
	public String viewProductPage(HttpServletRequest request,@PathVariable Integer prtId,Model model){
		logger.info("产品ID为"+prtId);	
		//更改点击量
		productService.updateClickCount(prtId);
		//获取商品
		ProductInfo productInfo =productService.getProductAllInfoById(prtId);
		
		Integer thirdId=productInfo.getThirdId();
		List<ProductInfo> recmmPrts=productService.randomListProduct(thirdId, 4);
		
		System.out.println(recmmPrts.size());
		
		
		model.addAttribute("recomment", recmmPrts);
		
		//TODO 添加评论 评论头像
		List<PrtComment> comments=productService.listPrtCommentByPrtId(prtId, null, null);
		model.addAttribute("comments", comments);
		
		//获取商品分类1-2-3级
		Map categoryMap=categoryManService.getProduct123Category(prtId);
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("productInfo", productInfo);
		
		
		//同类产品销量前10
		List<ProductInfo> topPrt=productService.listProductByThirdId(thirdId, 0, 10, "sell");
		model.addAttribute("topPrts", topPrt);
		
		
		return "front/product/product";
	}
}
