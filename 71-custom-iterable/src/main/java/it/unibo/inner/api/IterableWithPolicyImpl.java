package it.unibo.inner.api;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] elements) {
        //this.elements = Arrays.copyOf(elements, elements.length); ORIGINALE
        this(elements, t -> true);
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter){
        this.elements = elements;
        this.filter = filter;
    }

    class IteratorImpl implements Iterator<T>{
        
        int index = 0;
    
        @Override
        public boolean hasNext() {
            return index < elements.length;
        }

        @Override
        public T next() {
            if (hasNext()) {
                while (index < elements.length) {
                    if(filter.test(elements[index])){
                        return elements[index++];
                    }
                }
                return elements[index]; 
            }
            throw new NoSuchElementException();
        }
        
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        this.filter = filter;
    }

    
    @Override
    public Iterator iterator() {
        IteratorImpl tmpObject = new IteratorImpl();
        return tmpObject;
    }

}
