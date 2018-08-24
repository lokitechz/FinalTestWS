/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kaizu
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "FinalTestWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public List<Product> findAll() {
        Query query = em.createNamedQuery("Product.findAll");
        List<Product> list = query.getResultList();
        return list;
    }

    @Override
    public void create(Product entity) {
        super.create(entity);
    }

    @Override
    public String sellProduct(int productId, int quanlity) {
        Product p = em.find(Product.class, productId);
        if (quanlity > p.getQuantity()) {
            return "Fail";
        } else {
            p.setQuantity(p.getQuantity() - quanlity);
            return ("success");
        }
    }

}
