package com.example.entity;

import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by tzurc on 4/15/2017.
 */
@Component
public class Cart implements List<Journal> {

    List<Journal> journals;

    private float totalPrice;

    @MyAnnotation(min = 4, max = 15)
    private int repeat;

    public Cart(){
        journals = new ArrayList();
    }

    public Map<Journal, Integer> getMap(){
        Map<Journal, Integer> map = new HashMap<>();
        for(Journal journal: journals){
            if(map.containsKey(journal)){
                int count = map.get(journal);
                map.put(journal, count + 1);
            }else{
                map.put(journal, 1);
            }
        }
        return map;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    @Override
    public int size() {
        return journals.size();
    }

    @Override
    public boolean isEmpty() {
        return journals.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return journals.contains(o);
    }

    @Override
    public Iterator<Journal> iterator() {
        return journals.iterator();
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
    public boolean add(Journal journal) {
        return journals.add(journal);
    }

    @Override
    public boolean remove(Object o) {
        return journals.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Journal> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Journal> c) {
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
        journals.clear();
    }

    @Override
    public Journal get(int index) {
        return journals.get(index);
    }

    @Override
    public Journal set(int index, Journal element) {
        return journals.set(index, element);
    }

    @Override
    public void add(int index, Journal element) {
        journals.add(index, element);
    }

    @Override
    public Journal remove(int index) {
        return journals.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return journals.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return journals.lastIndexOf(o);
    }

    @Override
    public ListIterator<Journal> listIterator() {
        return journals.listIterator();
    }

    @Override
    public ListIterator<Journal> listIterator(int index) {
        return journals.listIterator();
    }

    @Override
    public List<Journal> subList(int fromIndex, int toIndex) {
        return null;
    }

    public float getTotalPrice() {
        totalPrice = 0;
        for(Journal journal : journals){
            totalPrice += journal.getPrice();
        }
        return totalPrice;
    }
}
