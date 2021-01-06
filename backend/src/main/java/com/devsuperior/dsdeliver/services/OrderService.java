package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true) //readOnly pois o método é somente leitura não havendo o lock
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		
		//converte a lista de Order para lista DTO com map.
		//com stream para cada entidade passando por map, transforma em DTO 
		//e junta em uma List de novo com o collect
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional //sem readOnly pois o BD será alterado
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		
		//montar a lista de produtos dentro de Order
		for (ProductDTO p : dto.getProducts()) {
			//getOne instancia um produto sem ir ao BD, cria entidade gerenciada pelo JPA 
			//para ao salvar Order salve também as associações dos quais produtos estão no pedido
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
}
	
