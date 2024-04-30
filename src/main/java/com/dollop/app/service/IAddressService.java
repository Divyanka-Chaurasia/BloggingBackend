package com.dollop.app.service;
import java.util.Map;
import com.dollop.app.bean.Address;
public interface IAddressService {
	public Map<String,Object> createAddress(Address a);
	public Map<String,Object> updateAddress(Address a, Integer id,Integer userId);
	public void deleteAddress(Integer id); 
	public Map<String,Object> deActivate(Integer id, Integer userId);
}
