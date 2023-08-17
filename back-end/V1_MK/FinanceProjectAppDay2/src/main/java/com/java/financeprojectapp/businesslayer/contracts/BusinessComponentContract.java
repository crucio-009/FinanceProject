package com.java.financeprojectapp.businesslayer.contracts;

import java.util.List;

import com.java.financeprojectapp.exceptions.BusinessComponetException;

public interface BusinessComponentContract<T,TId> {
	
	List<T> getAll() throws BusinessComponetException;

	T getById(TId id) throws BusinessComponetException;

	Boolean add(T data) throws BusinessComponetException;

	Boolean remove(TId id) throws BusinessComponetException;

	Boolean modify(TId id, T data) throws BusinessComponetException;
	
}
