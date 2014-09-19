package com.learningandroid.datasource;

import java.util.List;

import android.database.SQLException;

public interface DataSource<T> {
	T fetch(long id) throws SQLException;
	List<T> fetchAll() throws SQLException;
	T create(T t);
	T update(T t);
	boolean delete(long id);
}
