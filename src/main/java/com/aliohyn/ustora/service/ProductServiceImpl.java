package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.Product;
import com.aliohyn.ustora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private EntityManager em;

    public List<Product> getItemsByPage(int page, int size){
        int firstResult = (page - 1) * size;
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Product> byPageCriteria = builder.createQuery(Product.class);
        Root<Product> from = byPageCriteria.from(Product.class);

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
        Root<Product> criteriaByPageRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaByPageRoot));

        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
