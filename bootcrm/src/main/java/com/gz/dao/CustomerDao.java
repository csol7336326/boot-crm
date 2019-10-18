package com.gz.dao;
import java.util.List;
import com.gz.po.Customer;
/**
 * Customer接口
 */
public interface CustomerDao {

	public List<Customer> selectCustomerList(Customer customer);

	public Integer selectCustomerListCount(Customer customer);
	

	public int createCustomer(Customer customer);

	public Customer getCustomerById(Integer id);

	public int updateCustomer(Customer customer);

	int deleteCustomer(Integer id);

}
