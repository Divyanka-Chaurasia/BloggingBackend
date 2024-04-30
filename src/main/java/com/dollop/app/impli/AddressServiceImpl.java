package com.dollop.app.impli;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.Address;
import com.dollop.app.bean.User;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IAddressRepo;
import com.dollop.app.repo.IUserRepo;
import com.dollop.app.service.IAddressService;
@Service
public class AddressServiceImpl implements IAddressService 
{
	@Autowired
	private IAddressRepo repo;
	@Autowired
	private IUserRepo userRepo;
	Map<String,Object> map = new HashMap<>();
	@Override
	public Map<String, Object> createAddress(Address a) {
		Optional<User> user = userRepo.findById(a.getUser().getUserId());
		if(user.isPresent())
		{		
			Map<String,Object> map = new HashMap<>();
			map.put("message", "address is created");
			map.put("address", repo.save(a));
			return map;
		}
          throw new ResourceNotFoundException("user not found");
	}
	@Override
	public Map<String, Object> updateAddress(Address a, Integer id,Integer userId) {
		Optional<User> user = userRepo.findByUserId(userId);
		if(user.isPresent())
		{
			Address address = repo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("address not found")
					);
			if(address.isActive())
			{
				address.setCity(a.getCity());
				address.setStreet(a.getStreet());
				address.setSuite(a.getSuite());
				address.setZipCode(a.getZipCode());
				map.put("address", repo.save(address));		
				return map;
			}
			else 
				throw new ResourceNotFoundException("address is deActivate");
		}
		else
		    throw new ResourceNotFoundException("user not found");
	}
	@Override
	public void deleteAddress(Integer id) {
	  Address a = repo.findById(id).get();
		if(a.isActive())
		{
			a.setActive(false);
			repo.save(a);
		}
		else 
			throw new ResourceNotFoundException("address not exist");	
		}
	@Override
	public Map<String, Object> deActivate(Integer id, Integer userId) {
		Optional<User> user = userRepo.findByUserId(userId);
		if(user.isPresent())
		{
			Address address = repo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("address not found")
					);
			if(!address.isActive())
			{
				address.setActive(true);
				map.put("address", repo.save(address));		
				return map;
			}
			else 
				throw new ResourceNotFoundException("address is deActivate");
		}
		else
		    throw new ResourceNotFoundException("user not found");
	   }
}