package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by tzurc on 4/15/2017.
 */
@Component
public class Cart implements List<Book> {

    List<Book> books;

    public Cart(){
        books = new ArrayList();
    }

    @Override
    public int size() {
        return books.size();
    }

    @Override
    public boolean isEmpty() {
        return books.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return books.contains(o);
    }

    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Book book) {
        return books.add(book);
    }

    @Override
    public boolean remove(Object o) {
        return books.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Book> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Book> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        books.clear();
    }

    @Override
    public Book get(int index) {
        return books.get(index);
    }

    @Override
    public Book set(int index, Book element) {
        return books.set(index, element);
    }

    @Override
    public void add(int index, Book element) {
        books.add(index, element);
    }

    @Override
    public Book remove(int index) {
        return books.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return books.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return books.lastIndexOf(o);
    }

    @Override
    public ListIterator<Book> listIterator() {
        return books.listIterator();
    }

    @Override
    public ListIterator<Book> listIterator(int index) {
        return books.listIterator();
    }

    @Override
    public List<Book> subList(int fromIndex, int toIndex) {
        return null;
    }
}
