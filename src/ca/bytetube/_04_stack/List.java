package ca.bytetube._04_stack;

public interface List<E> {

    public static final int ELEMENT_NOT_FOUND = -1;

    public int size();

    // Is it empty
    public boolean isEmpty();

    // Clear all the elements
    public void clear();


    // Add elements to the end
    public void add(E element);

    // Add elements to the index position
    public void add(int index, E element);

    // Returns the element corresponding to the index position
    public E get(int index);


    // Set the element at the index position
    public E set(int index, E element);


    // Delete elements to the index position
    public E remove(int index);


    // Return the index of the element
    public int indexOf(E element);

    //Contains a certain element
    public boolean contains(E element);

}
