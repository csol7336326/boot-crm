package com.gz.controller;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.common.utils.Page;
import com.gz.po.BaseDict;
import com.gz.po.Customer;
import com.gz.po.User;
import com.gz.service.BaseDictService;
import com.gz.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private BaseDictService baseDictService;
	// 客户来源
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	// 客户所属行业
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	// 客户级别
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;

	@RequestMapping(value = "/customer/list.action")
	public String list(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="10")Integer rows, 
			String custName, String custSource, String custIndustry,
			String custLevel, Model model) {
		// 条件查询所有客户
		Page<Customer> customers = customerService
				.findCustomerList(page, rows, custName, 
                                        custSource, custIndustry,custLevel);
		model.addAttribute("page", customers);
		// 客户来源
		List<BaseDict> fromType = baseDictService
				.findBaseDictByTypeCode(FROM_TYPE);
		// 客户所属行业
		List<BaseDict> industryType = baseDictService
				.findBaseDictByTypeCode(INDUSTRY_TYPE);
		// 客户级别
		List<BaseDict> levelType = baseDictService
				.findBaseDictByTypeCode(LEVEL_TYPE);
		// 添加参数
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		model.addAttribute("custName", custName);
		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		model.addAttribute("custLevel", custLevel);
		return "customer";
	}
	

	@RequestMapping("/customer/create.action")
	@ResponseBody
	public String customerCreate(Customer customer,HttpSession session) {

	    User user = (User) session.getAttribute("USER_SESSION");
	    customer.setCust_create_id(user.getUser_id());
	    Date date = new Date();
	    Timestamp timeStamp = new Timestamp(date.getTime());
	    customer.setCust_createtime(timeStamp);
	    // 执行Service层中的创建方法，返回的是受影响的行数
	    int rows = customerService.createCustomer(customer);
	    if(rows > 0){
	        return "OK";
	    }else{
	        return "FAIL";
	    }
	}


	@RequestMapping("/customer/getCustomerById.action")
	@ResponseBody
	public Customer getCustomerById(Integer id) {
	    Customer customer = customerService.getCustomerById(id);
	    return customer;
	}

	@RequestMapping("/customer/update.action")
	@ResponseBody
	public String customerUpdate(Customer customer) {
	    int rows = customerService.updateCustomer(customer);
	    if(rows > 0){
	        return "OK";
	    }else{
	        return "FAIL";
	    }
	}


	@RequestMapping("/customer/delete.action")
	@ResponseBody
	public String customerDelete(Integer id) {
	    int rows = customerService.deleteCustomer(id);
	    if(rows > 0){
	        return "OK";
	    }else{
	        return "FAIL";
	    }
	}

}
