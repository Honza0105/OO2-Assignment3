package dao;

import java.math.BigInteger;

import javafx.collections.ObservableList;

public interface IDao<T> {
	ObservableList<T> getAll();
	T get(BigInteger id);
	T update(T item);
	T delete(T item);
	
}
