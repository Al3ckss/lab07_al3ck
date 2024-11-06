package it.unibo.inner.api;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] elements) {
        //this.elements = Arrays.copyOf(elements, elements.length); ORIGINALE
        this(elements, new Predicate<T>() {
            public boolean test(T element){
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter){
        this.elements = elements;
        this.filter = filter;
    }

    class IteratorImpl implements Iterator<T>{
        
        int index = 0;
    
        public boolean hasNext() {
            while (index < elements.length && !filter.test(elements[index])) {
                index++;
            }
            return index < elements.length;
        }
    
        @Override
        public T next() {
            if (hasNext()) {
                return elements[index++];
            }
            throw new NoSuchElementException();
        }
        
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    
    @Override
    public Iterator<T> iterator() {
        IteratorImpl tmpObject = new IteratorImpl();
        return tmpObject;
    }

}
