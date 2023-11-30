import java.util.NoSuchElementException;

/**
 * A list that is a collection of linked Nodes that contain data
 * 
 * @param <E> - a generic which represents the user-defined object type
 *        stored by the GenericLinkedList
 * 
 * @author Pranav
 * @version 12/18/20
 */
public class GenericLinkedList<E> {
    /**
     * An object that contains data and links to the Node following
     * 
     * @param <E> - a generic which represents the user-defined object type
     *        stored by the Node
     * 
     * @author Pranav
     * @version 12/18/20
     */
    class Node<E> {
        private E data;
        private Node next;

        /**
         * Instantiates a Node without any data and without linking it to
         * another Node
         */
        public Node() {
            data = null;
            next = null;
        }

        /**
         * Instantiates a Node and sets its data to the data variable, but
         * does not link it to another node
         * 
         * @param data - the data in the Node
         */
        public Node(E data) {
            this.data = data;
            next = null;
        }

        /**
         * Instantiates a Node, sets its data to the data variable, and
         * links it to the next Node
         * 
         * @param data - the data in the Node
         * @param next - the following Node that is linked
         */
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    /**
     * Instantiates a GenericLinkedList with a size of 0 and no elements
     * within
     */
    public GenericLinkedList() {
        size = 0;
    }

    /**
     * Instantiates a GenericLinkedList with the elements and size of the
     * genLinLis parameter
     * 
     * @param genLinLis - a GenericLinkedList with the elements to be added
     * 
     */
    public GenericLinkedList(GenericLinkedList genLinLis) {
        head = new Node(genLinLis.head.data);
        Node temp = head;
        Node tempOther = genLinLis.head;
        while (tempOther.next != null) {
            temp.next = new Node(tempOther.next.data);
            temp = temp.next;
            tempOther = tempOther.next;
        }
        tail = temp;
        size = genLinLis.size();
    }

    /**
     * Returns the size of the GenericLinkedList
     * 
     * @return the number of elements in the GenericLinkedList
     */
    public int size() {
        return size;
    }

    /**
     * Returns a boolean deciding whether the GenericLinkedList is empty
     * 
     * @return a boolean deciding whether the size of the GenericLinkedList
     *         equals 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * end of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     * @return true if the data is successfully added
     */
    public boolean add(E e) {
        /*
         * if (size == 0) { head = new Node(e); tail = head; } else if
         * (size == 1){ tail = new Node(e); head.next = tail; } else {
         * tail.next = new Node(e); tail = tail.next; } size++;
         */
        add(size, e);
        return true;
    }

    /**
     * Adds the data in the parameter to a new Node which is added to a
     * certain index in the GenericLinkedList
     * 
     * @param index - the index in the GenericLinkedList that the new Node
     *              is being added to
     * @param e     - data that is being appended to the GenericLinkedList
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than the size of the
     *                                   GenericLinkedList
     */
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            tail = new Node(e);
            head = tail;
        }
        else if (index == 0) {
            head = new Node(e, head);
        }
        else if (index == size) {
            tail.next = new Node(e);
            tail = tail.next;
        }
        else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = new Node(e, temp.next);
        }
        size++;
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * front of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     */
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node(e);
            head = tail;
            size++;
        }
        else {
            head = new Node(e, head);
            size++;
        }
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * end of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     */
    public void addLast(E e) {
        if (size == 0) {
            tail = new Node(e);
            head = tail;
            size++;
        }
        else {
            tail.next = new Node(e);
            tail = tail.next;
            size++;
        }
    }

    /**
     * Adds all of the data in another GenericLinkedList to the end of the
     * current GenericLinkedList
     * 
     * @param genLinLis - the GenericLinkedList that has the datas to be
     *                  added to the current GenericLinkedList
     * @return true if GenericLinkedList changed in size
     */
    public boolean addAll(GenericLinkedList genLinLis) {
        return addAll(size, genLinLis);
        /*
         * Node temp; Node tempOther; if (size == 0) { head = new
         * Node(genLinLis.head.data); temp = head; tempOther =
         * genLinLis.head.next; } else { temp = tail; tempOther =
         * genLinLis.head; } while (tempOther != null) { temp.next = new
         * Node(tempOther.data); temp = temp.next; tempOther =
         * tempOther.next; } tail = temp; return true;
         */
    }

    /**
     * Adds all of the data in another GenericLinkedList to the current
     * GenericLinkedList starting with the specified index
     * 
     * @param index     - the starting index for the data of the
     *                  GenericLinkedList in the parameter to be added to
     * @param genLinLis - the GenericLinkedList that has the data to be
     *                  added to the current GenericLinkedList
     * @return true if GenericLinkedList changed in size
     */
    public boolean addAll(int index, GenericLinkedList genLinLis)
            throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (genLinLis.head == null) {
            return false;
        }
        int priorSize = size;
        Node temp;
        Node tempOther = genLinLis.head.next;
        Node link = head;
        if (index == 0) {
            head = new Node(genLinLis.head.data, head);
            temp = head;
        }
        else if (index < size) {
            temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            link = temp.next;
            temp.next = new Node(genLinLis.head.data, link);
            temp = temp.next;
        }
        else {
            temp = tail;
            temp.next = new Node(genLinLis.head.data);
            temp = temp.next;
        }
        while (tempOther != null) {
            temp.next = new Node(tempOther.data);
            temp = temp.next;
            tempOther = tempOther.next;
        }
        if (index < size) {
            temp.next = link;
        }
        else {
            tail = temp;
        }
        size += genLinLis.size;
        return size > priorSize;
    }

    /**
     * Clears the GenericLinkedList of all its Nodes and data
     */
    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    /**
     * Returns true if the GenericLinkedList contains the object in the
     * parameter
     * 
     * @param obj - the object that is being looked for in the
     *            GenericLinkedList
     * @return true if the object in the parameter exists in the
     *         GenericLinkedList
     */
    public boolean contains(Object obj) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(obj)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns the data of the head/first Node in the GenericLinkedList
     * 
     * @return the data of the head/first Node in the GenericLinkedList
     */
    public E element() {
        return (E) head.data;
    }

    /**
     * Returns true if the object parameter is equal to the
     * GenericLinkedList
     * 
     * @param obj - the object that is being compared with the
     *            GenericLinkedList
     * @return true if the object in the parameter is equal to the
     *         GenericLinkedList
     */
    public boolean equals(Object obj) {
        if (obj instanceof GenericLinkedList) {
            GenericLinkedList genLinLis = (GenericLinkedList) obj;
            Node temp = head;
            Node genLinLisTemp = genLinLis.head;
            while (temp != null && genLinLisTemp != null) {
                if (!temp.data.equals(genLinLisTemp.data)) {
                    return false;
                }
                temp = temp.next;
                genLinLisTemp = genLinLisTemp.next;
            }
            return (temp == null && genLinLisTemp == null);
        }
        return false;
    }

    /**
     * Returns the data in the stated index of the GenericLinkedList
     * 
     * @param index - the index in the GenericLinkedList of the data being
     *              returned
     * @return the data in the stated index of the GenericLinkedList
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than or equal to the size
     *                                   of the GenericLinkedList
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return (E) temp.data;
    }

    /**
     * Returns the data data of the head/first Node in the
     * GenericLinkedList
     * 
     * 
     * @return the data data of the head/first Node in the
     *         GenericLinkedList
     * 
     */
    public E getFirst() throws NoSuchElementException {
        if (head != null) {
            return (E) head.data;
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns the data of the tail/last Node in the GenericLinkedList
     * 
     * 
     * @return the data of the tail/last Node in the GenericLinkedList
     * 
     */
    public E getLast() throws NoSuchElementException {
        if (tail != null) {
            return (E) tail.data;
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns the first index in the GenericLinkedList where obj exists
     * 
     * @param obj - the object being looked for in the GenericLinkedList
     * @return the first index in the GenericLinkedList where obj exists
     */
    public int indexOf(Object obj) {
        return indexOf(head, obj, 0, -1);
    }

    /**
     * Recursively loops through the GenericLinkedList and returns the
     * first index in the GenericLinkedList where obj exists
     * 
     * @param obj - the object being looked for in the GenericLinkedList
     * @return the first index in the GenericLinkedList where obj exists
     */
    private int indexOf(Node n, Object obj, int index, int foundIndex) {
        if (n != null) {
            if (foundIndex == -1 && n.data.equals(obj)) {
                return index;
            }
            foundIndex = indexOf(n.next, obj, index + 1, foundIndex);
        }
        return foundIndex;
    }

    /**
     * Returns the last index in the GenericLinkedList where obj exists
     * 
     * @param obj - the object being looked for in the GenericLinkedList
     * @return the last index in the GenericLinkedList where obj exists
     */
    public int lastIndexOf(Object obj) {
        return lastIndexOf(head, obj, 0, -1);
    }

    /**
     * Recursively loops through the GenericLinkedList and returns the last
     * index in the GenericLinkedList where obj exists
     * 
     * @param obj - the object being looked for in the GenericLinkedList
     * @return the last index in the GenericLinkedList where obj exists
     */
    private int lastIndexOf(Node n, Object obj, int index,
            int foundIndex) {
        if (n != null) {
            foundIndex = lastIndexOf(n.next, obj, index + 1, foundIndex);
            if (foundIndex == -1 && n.data.equals(obj)) {
                return index;
            }
        }
        return foundIndex;
        /*
         * if (n != null && n.data.equals(obj)) { return index; } else if
         * (n != null) { index = lastIndexOf(n.next, obj, index++); }
         * return -1;
         */
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * end of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     * @return true if the data is successfully added
     */
    public boolean offer(E e) {
        if (size == 0) {
            head = new Node(e);
            tail = head;
        }
        else if (size == 1) {
            tail = new Node(e);
            head.next = tail;
        }
        else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
        return true;
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * front of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     * @return true if the data is successfully added
     */
    public boolean offerFirst(E e) {
        if (size == 0) {
            head = new Node(e);
            tail = head;
        }
        else {
            head = new Node(e, head);
        }
        size++;
        return true;
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * end of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     * @return true if the data is successfully added
     */
    public boolean offerLast(E e) {
        if (size == 0) {
            head = new Node(e);
            tail = head;
        }
        else if (size == 1) {
            tail = new Node(e);
            head.next = tail;
        }
        else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
        return true;
    }

    /**
     * Finds and returns the data of the first Node in the
     * GenericLinkedList
     * 
     * @return the data in the first Node of the GenericLinkedList
     */
    public E peek() {
        if (size == 0 || head == null) {
            return null;
        }
        return (E) head.data;
    }

    /**
     * Finds and returns the data of the first Node in the
     * GenericLinkedList
     * 
     * @return the data in the first Node of the GenericLinkedList
     */
    public E peekFirst() {
        if (size == 0 || head == null) {
            return null;
        }
        return (E) head.data;
    }

    /**
     * Finds the last Node in the GenericLinkedList and returns its data
     * 
     * @return the data in the last Node of the GenericLinkedList
     */
    public E peekLast() {
        if (size == 0 || tail == null) {
            return null;
        }
        return (E) tail.data;
    }

    /**
     * Removes the first Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the first Node in the GenericLinkedList which is
     *         being removed
     */
    public E poll() {
        Node remove = head;
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            head = null;
            tail = null;
            size--;
            return (E) remove.data;
        }
        else {
            head = head.next;
            size--;
            return (E) remove.data;
        }
    }

    /**
     * Removes the first Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the first Node in the GenericLinkedList which is
     *         being removed
     */
    public E pollFirst() {
        Node remove = head;
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            head = null;
            tail = null;
            size--;
            return (E) remove.data;
        }
        else {
            head = head.next;
            size--;
            return (E) remove.data;
        }
    }

    /**
     * Removes the last Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the last Node in the GenericLinkedList which is
     *         being removed
     */
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        Node remove = tail;
        if (size == 1) {
            tail = null;
            head = null;
            size--;
            return (E) remove.data;
        }
        Node temp = head;
        for (int i = 0; i < size - 2; i++) {
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
        return (E) remove.data;
    }

    /**
     * Adds the data in the parameter to a new Node which is added to the
     * front of the GenericLinkedList
     * 
     * @param e - data that is being appended to the GenericLinkedList
     */
    public void push(E e) {
        if (size == 0) {
            head = new Node(e);
            tail = head;
        }
        else {
            head = new Node(e, head);
        }
        size++;
    }

    /**
     * Removes the first Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the first Node in the GenericLinkedList which is
     *         being removed
     * @throws NoSuchElementException - if there is nothing in the
     *                                GenericLinkedList
     */
    public E pop() throws NoSuchElementException {
        Node remove = head;
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else if (size == 1) {
            head = null;
            tail = null;
            size--;
            return (E) remove.data;
        }
        else {
            head = head.next;
            size--;
            return (E) remove.data;
        }
    }

    /**
     * Removes the first Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the first Node in the GenericLinkedList which is
     *         being removed
     * @throws NoSuchElementException - if there is nothing in the
     *                                GenericLinkedList
     */
    public E remove() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    /**
     * Removes a data from a stated index on the GenericLinkedList and
     * returns the removed data
     * 
     * @param index - the index of the data to be removed
     * @return the data being removed from the GenericLinkedList
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = new Node("", head);
        Node remove;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        remove = temp.next;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return (E) remove.data;
        }
        else if (index == 0) {
            head = head.next;
            size--;
            return (E) remove.data;
        }
        temp.next = temp.next.next;
        if (index == size - 1) {
            tail = temp;
        }
        size--;
        return (E) remove.data;
    }

    /**
     * If the object exists in the GenericLinkedList, removes the object
     * and returns true, else returns false
     * 
     * @param obj - object attempting to be removed from the
     *            GenericLinkedList
     * @return true if the object existed in the GenericLinkedList and was
     *         removed
     */
    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    /**
     * Removes all of the Nodes from the current GenericLinkedList that
     * exist in another GenericLinkedList
     * 
     * @param genLinLis - the GenericLinkedList that has data that will be
     *                  removed from the current GenericLinkedList
     * @return true if the GenericLinkedList size changes
     */
    public boolean removeAll(GenericLinkedList genLinLis) {
        int priorSize = size;
        Node temp;
        Node tempOther = genLinLis.head;
        int index;
        while (tempOther != null) {
            index = indexOf(tempOther.data);
            if (size == 1 && index == 0) {
                head = null;
                tail = null;
                size--;
            }
            else if (index == 0) {
                head = head.next;
                size--;
            }
            else if (index > 0) {
                temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                temp.next = temp.next.next;
                if (index == size - 1) {
                    tail = temp;
                }
                size--;
            }
            tempOther = tempOther.next;
        }
        return priorSize > size;
    }

    /**
     * Removes the first Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the first Node in the GenericLinkedList which is
     *         being removed
     */
    public E removeFirst() {
        Node remove = head;
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            head = null;
            tail = null;
            size--;
            return (E) remove.data;
        }
        else {
            head = head.next;
            size--;
            return (E) remove.data;
        }
    }

    /**
     * Removes the last Node in the GenericLinkedList and returns its data
     * 
     * @return the data of the last Node in the GenericLinkedList which is
     *         being removed
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        Node remove = tail;
        if (size == 1) {
            tail = null;
            head = null;
            size--;
            return (E) remove.data;
        }
        Node temp = head;
        for (int i = 0; i < size - 2; i++) {
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
        return (E) remove.data;
    }

    /**
     * Replaces the original data in the stated index with the data in the
     * parameter and returns the original data
     * 
     * @param index - the index in the GenericLinkedList of the data being
     *              replaced
     * @param e     - the data that is replacing the original data
     * @return the original data in the stated index
     * @throws IndexOutOfBoundsException - if the index is less than 0 or
     *                                   greater than or equal to the size
     *                                   of the GenericLinkedList
     */
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        E tempStr = (E) temp.data;
        temp.data = e;
        return tempStr;
    }

    /**
     * Returns a portion of the GenericLinkedList beginning from beginIndex
     * up to but not including endIndex
     * 
     * @param beginIndex - the starting index and the index of the first
     *                   Node in the subList
     * @param endIndex   - the ending index which ends the subList but its
     *                   Node is excluded from it
     * @return a subList from the GenericLinkedList which is bounded by the
     *         indices provided
     * @throws IndexOutOfBoundsException - if startIndex or endIndex are
     *                                   not valid indices in the
     *                                   GenericLinkedList or startIndex is
     *                                   greater than endIndex
     */
    public GenericLinkedList<E> subList(int beginIndex, int endIndex)
            throws IndexOutOfBoundsException {
        if (beginIndex < 0 || beginIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        else if (endIndex < 0 || endIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        else if (beginIndex > endIndex) {
            throw new IndexOutOfBoundsException();
        }
        GenericLinkedList<E> subList = new GenericLinkedList<E>();
        Node temp = head;
        for (int i = 0; i < beginIndex; i++) {
            temp = temp.next;
        }
        subList.head = temp;
        for (int i = beginIndex; i < endIndex - 1; i++) {
            temp = temp.next;
        }
        subList.tail = temp;
        subList.size = endIndex - beginIndex;
        return subList;
    }

    /**
     * Returns an object array with a length of the size of the
     * GenericLinkedList and all of the data in the GenericLinkedList
     * 
     * @return an object array with the length of the GenericLinkedList and
     *         the contents of the GenericLinkedList
     */
    public Object[] toArray() {
        Object[] tempArray = new Object[size];
        Node temp = head;
        for (int i = 0; i < size; i++) {
            tempArray[i] = temp.data;
            temp = temp.next;
        }
        return tempArray;
    }

    /**
     * Returns a String that displays all of the data in the
     * GenericLinkedList in brackets
     * 
     * @return a Strings that displays all of the data in the
     *         GenericLinkedList in brackets
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String displayedList = "[";
        Node temp = head;
        while (temp != tail) {
            displayedList += temp.data + ", ";
            temp = temp.next;
        }
        return displayedList + tail.data + "]";
    }
}
