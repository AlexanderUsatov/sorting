import java.util.*;

public class Main {

    public static void main(String[] args) {

        Integer[] arr = generateArr(10_000_000, 0, 10_000_000);
        Integer[] a1 = arr.clone();
        Integer[] a2 = arr.clone();
        Integer[] a3 = arr.clone();
        long start = System.currentTimeMillis();
        usatovSort(arr);
        System.out.println(System.currentTimeMillis() - start + " mills Usatov sort");

        ArrayList<Integer> list = new ArrayList(Arrays.asList(a1));

        start = System.currentTimeMillis();
        list.sort(Comparator.naturalOrder());
        System.out.println(System.currentTimeMillis() - start + " mills qsort");

        start = System.currentTimeMillis();
        usatovProkuratSortUsingHashMap(a2);
        System.out.println(System.currentTimeMillis() - start + " Usatov-Prokurat sort");

        start = System.currentTimeMillis();
        usatovProkuratSortUsingMyHashTable(a3);
        System.out.println(System.currentTimeMillis() - start + " Usatov-Prokurat sort on my Hashtable");
    }

    static void usatovProkuratSortUsingMyHashTable(Integer[] arr) {
        MyHashTable mht = new MyHashTable();
        for (Integer i : arr)
            mht.add(i);
        MyPair[] res = mht.getPairs();
        int ptr = 0;
        Arrays.sort(res, Comparator.comparingInt(o -> o.first));
        for (MyPair mp : res)
            for (int i = 0; i < mp.second; i++)
                arr[ptr++] = mp.first;
    }

    static void usatovSort(Integer[] arr) {
        TreeSet<MyPair> tree = new TreeSet<>();
        MyPair temp;
        MyPair mp = new MyPair();
        for (int i : arr) {
            temp = mp;
            temp.first = i;
            temp = tree.ceiling(temp);
            if (temp != null && temp.first == i) // порядок условий важнен, т.к если первое не выполняется, то проверка второго не производится
                temp.second++;
            else tree.add(new MyPair(i, 1));
        }
        int ptr = 0;
        while (!tree.isEmpty()) {
            temp = tree.pollFirst();
            for (int i = 0; i < temp.second; i++)
                arr[ptr++] = temp.first;
        }
    }

    static void usatovProkuratSortUsingHashMap(Integer[] arr) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        Integer temp;
        for (Integer i : arr) {
            temp = hm.get(i);
            if (temp == null) hm.put(i, 1);
            else hm.put(i, temp + 1);
        }

        ArrayList<Integer> keys = new ArrayList<>(hm.keySet().size());
        keys.addAll(hm.keySet());
        keys.sort(Comparator.naturalOrder());
        int ptr = 0;
        for (Integer i : keys)
            for (int j = 0; j < hm.get(i); j++)
                arr[ptr++] = i;
    }

    static Integer[] generateArr(int size, int a, int b) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++)
            arr[i] = (int) (Math.random() * (b - a + 1) + a);
        return arr;
    }

    static void printArr(Integer[] arr) {
        for (Integer i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

}

