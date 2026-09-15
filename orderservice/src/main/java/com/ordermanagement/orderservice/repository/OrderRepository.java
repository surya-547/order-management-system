package src.main.java.com.ordermanagement.orderservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.main.java.com.ordermanagement.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}