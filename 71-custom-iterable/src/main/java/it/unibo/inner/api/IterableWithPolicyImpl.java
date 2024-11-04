package it.unibo.inner.api;

import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    T[] elements;
    

    public IterableWithPolicyImpl(T[] elements) {
        this.elements = elements;
    }


    class IteratorImpl implements Iterator<T>{
        
        int tmp = 0;
    
        @Override
        public boolean hasNext() {
            if(elements[tmp+1] == new IndexOutOfBoundsException()){
                return false;
            }
            else{
                return true;
            }
        }

        @Override
        public T next() {
            return elements[tmp++]; 
        }
        
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        //TODO
    }

    
    @Override
    public Iterator iterator() {
        IteratorImpl tmpObject = new IteratorImpl();
        return tmpObject;
    }

}
