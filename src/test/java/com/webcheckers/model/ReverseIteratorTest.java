package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Eugene on 3/15/2018.
 */
@Tag("Model-tier")
public class ReverseIteratorTest {
    //Attributes
    private static final  int index = 0;
    private ArrayList<String> list = new ArrayList<>();

    @Test
    public void addList(){
        list.add("Hongda");
        list.add("Karthik");
        list.add("Eugene");
    }
    @Test
    public void test_ReverseIterator(){
        final ReverseIterator reverseIterator = new ReverseIterator(list);
        assertNotNull(reverseIterator);
    }
    @Test
    public void test_hasNext(){
        final ReverseIterator reverseIterator = new ReverseIterator(list);
        assertFalse(reverseIterator.hasNext());
    }
    @Test
    public void test_Next(){
        addList();
        final ReverseIterator reverseIterator = new ReverseIterator(list);
        assertNotNull(reverseIterator.next());
    }
}
