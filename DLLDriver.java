public class DLLDriver {
    
    public static void main(String[] args) {

        // ABC_remove2_AB_testRemoveLast
        IUDoubleLinkedList<Integer> DLL = ABC_remove2_AB();
        System.out.println(DLL.toString());

        System.out.println("EXPECTING: 2, ACTUAL: " + DLL.removeLast());
        System.out.println(DLL.toString());
    
        // ABC_remove2_AB_testRemove1
        DLL = ABC_remove2_AB();
        System.out.println(DLL.toString());

        System.out.println("EXPECTING: 2, ACTUAL: " + DLL.removeLast());
        System.out.println(DLL.toString());

        // ABC_remove2_AB_testGet1
        DLL = ABC_remove2_AB();
        System.out.println(DLL.toString());

        System.out.println("EXPECTING: 2, ACTUAL: " + DLL.get(1));

        // ABC_remove2_AB_testLast
        DLL = ABC_remove2_AB();
        System.out.println(DLL.toString());

        System.out.println("EXPECTING: 2, ACTUAL: " + DLL.last());
    }

    public static IUDoubleLinkedList<Integer> ABC_remove2_AB() {

        IUDoubleLinkedList<Integer> DLL = new IUDoubleLinkedList<>();

        // ABC_remove2_AB
        DLL.add(1);
        DLL.addToRear(2);
        DLL.addToRear(3);

        DLL.remove(2);

        return DLL;
    }

}
