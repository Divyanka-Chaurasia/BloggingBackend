package com.dollop.app.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.bean.Address;
import com.dollop.app.service.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private IAddressService service;
	
	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody Address address)
	{
		return new ResponseEntity<>(service.createAddress(address),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/{userId}")
	public ResponseEntity<?> update(@Validated @RequestBody Address address,@PathVariable Integer id,@PathVariable Integer userId)
	{
		return new ResponseEntity<>(service.updateAddress(address, id,userId),HttpStatus.CREATED);
	}
	
	@PutMapping("/deactivate/{addressId}/{userId}")
	public ResponseEntity<?> deActivate(@PathVariable Integer addressId,@PathVariable Integer userId)
	{
		return new ResponseEntity<>(service.deActivate(addressId,userId),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id)
	{
		service.deleteAddress(id);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "address deleted");
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
}