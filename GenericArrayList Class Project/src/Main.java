
public class Main {

    public static void main(String[] args) {
        GenericArrayList<Integer> genArrLis = new GenericArrayList<Integer>();
        genArrLis.add(5);
        genArrLis.add(6);
        genArrLis.add(7);
        genArrLis.add(8);
        System.out.println(genArrLis);
        System.out.println(genArrLis.subList(1,3));
        System.out.println(genArrLis.subList(1,3).getCapacity());
        // TODO Auto-generated method stub

    }

}
