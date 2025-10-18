package com.exam.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
	public Long id;
	public Long customerId;
	public LocalDateTime orderDate;
	public List<OrderLineDTO> lines;
}
