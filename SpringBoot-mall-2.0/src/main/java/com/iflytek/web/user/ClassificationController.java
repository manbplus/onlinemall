package com.iflytek.web.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iflytek.entity.Classification;
import com.iflytek.service.ClassificationService;

@Controller
@RequestMapping("/classification")
public class ClassificationController {
	
	@Autowired
	private ClassificationService classificationService;
	
	@RequestMapping("/seachName.do")
	
	//@GetMapping(value="/seachName.do",produces="application/json;charset=utf-8")
	//@ResponseBody
	public void seach(String cname, HttpServletResponse response) {
		
		List<Classification>i=classificationService.select(cname);
		System.out.println(i);
	    int j = 0;
		if(cname.equals("电脑")) {
			
			j=2;
		}else if(cname.equals("服装")){
			j=1;
		}else if(cname.equals("手机")){
			j=3;
		}else if(cname.equals("食品")){
			j=4;
		}else if(cname.equals("家居")){
			j=9;
		}
		 try {
			response.sendRedirect("/mall/product/category.html?cid="+j);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
