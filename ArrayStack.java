//David Isti 
import java.lang.reflect.Array;
import java.util.EmptyStackException;
import java.util.IllegalFormatCodePointException;

/*
 *This Java Program uses a generic array to implement an unlimited stack as well as a push, pop, peek, and size
 *functions.
 */

public class ArrayStack <T>{
    private int top;
    private int size;
    T[] arr;
    Class c;

    public ArrayStack(String type) {
        this.top = -1;
        this.size = 0;
        if(type.equals("string")) {
            this.arr = (T[]) Array.newInstance(String.class, 10);
            this.c = String.class;
        }
        else{
            this.arr = (T[])Array.newInstance(Integer.class, 10);
            this.c = Integer.class;
        }
    }

    /*
     Generic method that pushes elements and increments top
     @param Element to be add to the stack
     */
    public void push(T item) throws IllegalStateException {
        if (top == arr.length - 1) {
            throw new IllegalStateException("Stack is full");
        }
        top++;
        arr[top] = item;
        this.size++;
        if(this.arr.length - 1 == size){
            //Stack is full
            this.resize();
        }
    }
    /*
     Doubles the size of the stack when it is full.
     */

    public void resize() {
        int newSize = this.size * 2;
        T[] newArr = (T[])Array.newInstance(c, newSize);
        for(int i = 0; i < this.size; i++){
            newArr[i] = this.arr[i];
        }
        this.arr = newArr;
    }

    /*
     Returns the topmost element of the stack.
     @return item
     */


    public T pop() {
        if(top == -1)
        {
            throw new IllegalArgumentException("Stack is empty");
        }
        T item = arr[top];
        arr[top] = null;
        top--;
        return item;
    }

    /*
     Informs the user of the topmost element in the stack.
     @return arr[top]
     */

    public T peek() {
        if (top == -1)
            throw new IllegalArgumentException("Stack is empty");
        return arr[top];
    }

    /*
     Returns the size of the stack.
     @return size
     */
    public int size() {

        return size;
    }


}