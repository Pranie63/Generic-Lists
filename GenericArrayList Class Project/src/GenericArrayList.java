
/**
 * A type of List that is an array with a modifiable length
 * 
 * @param <E> - a generic which represents the user-defined object type
 *        stored by the GenericArrayList
 * @author Pranav
 * @version 10/21/2020
 */
public class GenericArrayList<E> {

    private int size;
    private E[] genArray;

    /**
     * Instantiates a GenericArrayList with a background array length of 8
     * and 0 elements within it
     * 
     */
    GenericArrayList() {
        genArray = (E[]) (new Object[8]);
        size = 0;
    }

    /**
     * Instantiates a GenericArrayList with a background array length of
     * length and 0 elements within it
     * 
     * @param length - the length or capacity of the GenericArrayList
     */
    GenericArrayList(int length) {
        genArray = (E[]) (new Object[length]);
        size = 0;
    }

    /**
     * Instantiates a GenericArrayList, copying the elements from the
     * genArrLis parameter and creating a background array length twice the
     * number of elements
     * 
     * @param genArrLis - the GenericArrayList which has elements that will
     *                  be added into the GenericArrayList when it is
     *                  instantiated
     */
    GenericArrayList(GenericArrayList<E> genArrLis) {
        size = genArrLis.size();
        genArray = (E[]) (new Object[size * 2]);
        for (int i = 0; i < size; i++) {
            genArray[i] = genArrLis.get(i);
        }
    }

    /**
     * Returns the size of the GenericArrayList
     * 
     * @return the number of elements in the GenericArrayList
     */
    public int size() {
        return size;
    }

    /**
     * Returns a boolean deciding whether the GenericArrayList is empty
     * 
     * @return a boolean deciding whether the size of the GenericArrayList
     *         equals 0
     */
    public boolean isEmpty() {
        return (size <= 0);
    }

    /**
     * Adds the element in the parameter to the end of the GenericArrayList
     * 
     * @param e - element that is being appended to the GenericArrayList
     * @return true when the element is added to the GenericArrayList
     */
    public boolean add(E e) {
        add(size, e);
        return true;
        /*
         * if (size == genArray.length){ E[] replace = new
         * String[genArray.length * 2]; for (int i = 0; i <
         * genArray.length; i++){ replace[i] = genArray[i]; } genArray =
         * replace; } genArray[size] = e; isEmpty = false; size++;
         */
    }

    /**
     * Adds the element in the parameter to a certain index in the
     * GenericArrayList
     * 
     * @param pos - the index in the GenericArrayList that the element is
     *            being added to
     * @param e   - element that is being appended to the GenericArrayList
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than the size of the
     *                                   GenericArrayList
     */
    public void add(int pos, E e) throws IndexOutOfBoundsException {
        if (size == genArray.length && genArray.length == 0) {
            E[] replace = (E[]) new Object[(genArray.length + 1) * 2];
            for (int i = 0; i < genArray.length; i++) {
                replace[i] = genArray[i];
            }
            genArray = replace;
        }
        else if (size == genArray.length) {
            E[] replace = (E[]) new Object[genArray.length * 2];
            for (int i = 0; i < genArray.length; i++) {
                replace[i] = genArray[i];
            }
            genArray = replace;
        }
        if (pos > size || pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = size - 1; i >= pos; i--) {
            genArray[i + 1] = genArray[i];
        }
        genArray[pos] = e;
        size++;
    }

    /**
     * Returns the element in the stated index of the GenericArrayList
     * 
     * @param i - the index in the GenericArrayList of the element being
     *          returned
     * @return the element in the stated index of the GenericArrayList
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than or equal to the size
     *                                   of the GenericArrayList
     */
    public E get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return genArray[i];
    }

    /**
     * Replaces the original element in the stated index with the element
     * in the parameter and returns the original element
     * 
     * @param i - the index in the GenericArrayList of the element being
     *          replaced
     * @param e - the element that is replacing the original element
     * @return the original element in the stated index
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than or equal to the size
     *                                   of the GenericArrayList
     */
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if (i > size - 1 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        E temp = genArray[i];
        genArray[i] = e;
        return temp;
    }

    /**
     * Returns true if the object parameter is equal to the
     * GenericArrayList
     * 
     * @param o - the object that is being compared with the
     *          GenericArrayList
     * @return true if the object in the parameter is equal to the
     *         GenericArrayList
     */
    public boolean equals(Object o) {
        if (o instanceof GenericArrayList) {
            GenericArrayList<E> subO = (GenericArrayList<E>) o;
            for (int i = 0; i < subO.size(); i++) {
                if (!subO.get(i).equals(genArray[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
        // return (o instanceof GenericArrayList &&
        // ((GenericArrayList)o).equals(genArray));
    }

    /**
     * returns an object array with a length of the size of the
     * GenericArrayList and all of the elements in the GenericArrayList
     * 
     * @return an object array with the length of the GenericArrayList and
     *         the contents of the GenericArrayList
     */
    public Object[] toArray() {
        Object[] objectArray = new Object[size];
        for (int i = 0; i < size; i++) {
            objectArray[i] = genArray[i];
        }
        return objectArray;
    }

    /**
     * returns true if the GenericArrayList contains the object in the
     * parameter
     * 
     * @param obj - the object that is being looked for in the
     *            GenericArrayList
     * @return true if the object in the parameter exists in the
     *         GenericArrayList
     */
    public boolean contains(Object obj) {
        for (int i = 0; i < size; i++) {
            if (genArray[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns the index of the first time an object appears in the
     * GenericArrayList or -1 if it does not exist in the GenericArrayList
     * 
     * @param obj - the object that is being searched for
     * @return the index of the first time an object appears in the
     *         GenericArrayList or -1 if it does not exist in the
     *         GenericArrayList
     */
    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (genArray[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * returns the index of the last time an object appears in the
     * GenericArrayList or -1 if it does not exist in the GenericArrayList
     * 
     * @param obj - the object that is being searched for
     * @return the index of the last time an object appears in the
     *         GenericArrayList or -1 if it does not exist in the
     *         GenericArrayList
     */
    public int lastIndexOf(Object obj) {
        for (int i = size - 1; i >= 0; i--) {
            if (genArray[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * removes an object from a stated index on the GenericArrayList,
     * shifts the remaining objects to the left, and returns the removed
     * object
     * 
     * @param index - the index of the string to be removed
     * @return the object being removed from the GenericArrayList
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than or equal to the size
     *                                   of the GenericArrayList
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E temp = genArray[index];
        for (int i = index; i < size - 1; i++) {
            genArray[i] = genArray[i + 1];
        }
        genArray[size - 1] = null;
        size--;
        return temp;
    }

    /**
     * if the object exists in the GenericArrayList, removes the object,
     * shifts the following objects to the left, and returns true, else
     * returns false
     * 
     * @param obj - object attempting to be removed from the
     *            GenericArrayList
     * @return true if the object existed in the GenericArrayList and was
     *         removed
     */
    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * clears the entire GenericArrayList and makes the GenericArrayList
     * contain nothing
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            genArray[i] = null;
        }
        size = 0;
    }

    /**
     * adds all of the elements in another GenericArrayList to the end of
     * the current GenericArrayList
     * 
     * @param genArrLis - the GenericArrayList that has the elements to be
     *                  added to the current GenericArrayList
     * @return true if GenericArrayList changed in size
     */
    public boolean addAll(GenericArrayList<E> genArrLis) {
        if (genArrLis instanceof GenericArrayList) {
            int replaceLength = genArray.length;
            if (genArrLis.size() > replaceLength - size) {
                while (genArrLis.size() > replaceLength - size) {
                    replaceLength *= 2;
                }
                E[] replace = (E[]) new Object[replaceLength];
                for (int i = 0; i < genArray.length; i++) {
                    replace[i] = genArray[i];
                }
                genArray = replace;
            }
            int priorSize = size;
            for (int i = size; i < size + genArrLis.size(); i++) {
                genArray[i] = genArrLis.get(i - size);
            }
            size += genArrLis.size();
            return size > priorSize;
        }
        return false;
    }

    /**
     * adds all of the elements in another GenericArrayList to the current
     * GenericArrayList starting with the specified index
     * 
     * @param index     - the starting index for the elements of the
     *                  GenericArrayList in the parameter to be added to
     * @param genArrLis - the GenericArrayList that has the elements to be
     *                  added to the current GenericArrayList
     * @return true if GenericArrayList changed in size
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than or equal to the size
     *                                   of the GenericArrayList
     */
    public boolean addAll(int index, GenericArrayList<E> genArrLis)
            throws IndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (genArrLis instanceof GenericArrayList) {
            int replaceLength = genArray.length;
            if (genArrLis.size() > replaceLength - size) {
                while (genArrLis.size() > replaceLength - size) {
                    replaceLength *= 2;
                }
                E[] replace = (E[]) new Object[replaceLength];
                for (int i = 0; i < genArray.length; i++) {
                    replace[i] = genArray[i];
                }
                genArray = replace;
            }
            int priorSize = size;
            for (int i = size - 1; i >= index; i--) {
                genArray[i + genArrLis.size()] = genArray[i];
            }
            for (int i = index; i < index + genArrLis.size(); i++) {
                genArray[i] = genArrLis.get(i - index);
            }
            size += genArrLis.size();
            return size > priorSize;
        }
        return false;
    }

    /**
     * removes all of the elements from the current GenericArrayList that
     * exist in another GenericArrayList
     * 
     * @param genArrLis - the GenericArrayList that has elements that will
     *                  be removed from the current GenericArrayList
     * @return true if the GenericArrayList size changes
     */
    public boolean removeAll(GenericArrayList<E> genArrLis) {
        int priorSize = size;
        int index;
        for (int i = 0; i < genArrLis.size(); i++) {
            while (indexOf(genArrLis.get(i)) != -1) {
                index = indexOf(genArrLis.get(i));
                for (int j = index; j < size; j++) {
                    genArray[j] = genArray[j + 1];
                }
                size--;
            }
        }
        return size < priorSize;
    }

    /**
     * returns a String that displays all of the elements in the
     * GenericArrayList in brackets
     * 
     * @return a String that displays all of the elements in the
     *         GenericArrayList in brackets
     */
    public String toString() {
        String listOfArrayElements = "[";
        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                listOfArrayElements += genArray[i] + ", ";
            }
            return listOfArrayElements + genArray[size - 1] + "]";
        }
        return listOfArrayElements + "]";
    }

    /**
     * trims the length of the background array of the GenericArrayList to
     * the size of the GenericArrayList
     */
    public void trimToSize() {
        E[] replace = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            replace[i] = genArray[i];
        }
        genArray = replace;
    }

    /**
     * returns the capacity of the GenericArrayList or in other words the
     * length of the background array
     * 
     * @return length of the background array
     */
    public int getCapacity() {
        return genArray.length;
    }

    /**
     * returns a new GenericArrayList that has the elements from the
     * specified range in the current GenericArrayList
     * 
     * @param startIndex - the starting index for the range of elements
     *                   from the current GenericArrayList that will be
     *                   added to the new GenericArrayList
     * @param endIndex   - the ending index which is excluded for the range
     *                   of elements from the current GenericArrayList that
     *                   will be added to the new GenericArrayList
     * @return a GenericArrayList<E> from the specified range in the
     *         current GenericArrayList
     * @throws IndexOutOfBoundsException - if startIndex is greater than or
     *                                   equal to size or if endIndex is
     *                                   greater than size or if both
     *                                   startIndex or endIndex are less
     *                                   than 0
     */

    public GenericArrayList<E> subList(int startIndex, int endIndex)
            throws IndexOutOfBoundsException {
        if (startIndex >= size || startIndex < 0 || endIndex > size
                || endIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        GenericArrayList<E> temp = new GenericArrayList<E>(
                endIndex - startIndex);
        for (int i = 0; i < endIndex - startIndex; i++) {
            temp.genArray[i] = genArray[i + startIndex];
            temp.size++;
        }
        return temp;
    }

}
