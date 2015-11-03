package com.javacents.ormpitfalls.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.javacents.ormpitfalls.model.BusinessPartner;

@Repository
public class BusinessPartnerDao extends BaseDao<BusinessPartner, String> {
	public BusinessPartnerDao() {
		super(BusinessPartner.class);
	}

	@SuppressWarnings("unchecked")
	public List<BusinessPartner> getAllNaive() {
		String jpql = "FROM " + BusinessPartner.class.getName();
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<BusinessPartner> getAllWithFetches() {
		String jpql = "FROM " + BusinessPartner.class.getName() + " bp JOIN FETCH bp.city ct JOIN FETCH ct.country";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
