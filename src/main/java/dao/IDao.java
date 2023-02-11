package dao;

import javafx.collections.ObservableList;

import java.math.BigInteger;

public interface IDao<T> {
	ObservableList<T> getAll();
	T get(BigInteger id);
	T update(T item);
	T delete(T item);
	
}
