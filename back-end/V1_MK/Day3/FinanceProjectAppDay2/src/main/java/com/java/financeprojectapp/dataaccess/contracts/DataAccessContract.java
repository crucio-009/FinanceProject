package com.java.financeprojectapp.dataaccess.contracts;

import java.util.List;

import com.java.financeprojectapp.exceptions.DataAccessException;

public interface DataAccessContract<T, TId> {
	
	List<T> fetchAll() throws DataAccessException;

	T fetchById(TId id) throws DataAccessException;

	Boolean insert(T data) throws DataAccessException;

	Boolean delete(TId id) throws DataAccessException;

	Boolean update(TId id, T data) throws DataAccessException;

}
