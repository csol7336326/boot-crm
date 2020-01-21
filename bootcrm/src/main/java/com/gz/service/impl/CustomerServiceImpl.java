package com.gz.service.impl;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gz.common.utils.Page;
import com.gz.dao.CustomerDao;
import com.gz.po.Customer;
import com.gz.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public Page<Customer> findCustomerList(Integer page, Integer rows, 
			String custName,  String custSource,String custIndustry,
                                                          String custLevel) {

         Customer customer = new Customer();
		// 判断客户名称
		if(StringUtils.isNotBlank(custName)){
			customer.setCust_name(custName);
		}
		// 判断客户信息来源
		if(StringUtils.isNotBlank(custSource)){
			customer.setCust_source(custSource);
		}
		// 判断客户所属行业
		if(StringUtils.isNotBlank(custIndustry)){
			customer.setCust_industry(custIndustry);
		}
		// 判断客户级别
		if(StringUtils.isNotBlank(custLevel)){
			customer.setCust_level(custLevel);
		}
		// 当前页
		customer.setStart((page-1) * rows) ;
		// 每页数
		customer.setRows(rows);
		// 查询客户列表
		List<Customer> customers = 
                            customerDao.selectCustomerList(customer);
		// 查询客户列表总记录数
		Integer count = customerDao.selectCustomerListCount(customer);
		// 创建Page返回对象
		Page<Customer> result = new Page<>();
		result.setPage(page);
		result.setRows(customers);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}

	@Override
	public int createCustomer(Customer customer) {
	    return customerDao.createCustomer(customer);
	}

	@Override
	public Customer getCustomerById(Integer id) {	
	    Customer customer = customerDao.getCustomerById(id);
	    return customer;		
	}

	@Override
	public int updateCustomer(Customer customer) {
	    return customerDao.updateCustomer(customer);
	}

	@Override
	public int deleteCustomer(Integer id) {
	    return customerDao.deleteCustomer(id);	
	}

	
}