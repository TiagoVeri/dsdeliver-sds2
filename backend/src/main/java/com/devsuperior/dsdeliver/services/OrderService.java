package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true) //readOnly pois o método é somente leitura não havendo o lock
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		
		//converte a lista de Order para lista DTO com map.
		//com stream para cada entidade passando por map, transforma em DTO 
		//e junta em uma List de novo com o collect
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
