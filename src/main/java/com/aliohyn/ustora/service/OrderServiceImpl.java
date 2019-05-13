package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.Order;
import com.aliohyn.ustora.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private EntityManager em;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getItemsByPage(int page, int size) {
        int firstResult = (page - 1) * size;
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Order> byPageCriteria = builder.createQuery(Order.class);
        Root<Order> from = byPageCriteria.from(Order.class);

        byPageCriteria.select(from);
        byPageCriteria.orderBy(builder.asc(from.get("id")));
        return em.createQuery(byPageCriteria)
                .setFirstResult(firstResult)
                .setMaxResults(size)
                .getResultList();
    }
    public Long getCount() {
        CriteriaBuilder criteriaBuilder  = this.em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Order> criteriaByPageRoot = criteriaQuery.from(Order.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaByPageRoot));

        return em.createQuery(criteriaQuery).getSingleResult();
    }

    public Order getItemById(Long id) {
        Order order = orderRepository.findById(id).get();
        return order;
    }
}
