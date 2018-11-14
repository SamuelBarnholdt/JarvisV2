package Utils;

import java.io.Serializable;

public class Tuple<E,K> implements Serializable {
    private E e;
    private K k;
    public Tuple(E a, K b) {
        e = a;
        k = b;
    }
    public E fst(){
        return this.e;
    }
    public K snd(){
        return this.k;
    }

    public String toString(){
        return "(" + e + "," + k + ")";
    }

}