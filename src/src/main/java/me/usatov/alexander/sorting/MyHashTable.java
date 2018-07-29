package me.usatov.alexander.sorting;

public class MyHashTable {

    private MyPair[] hashArr;
    private double count = 0;
    private double loadFactor = 0.5;
    private double expansivity = 2;
    private static final int DEFAULT_CAPACITY = 20;

    public MyHashTable() {
        hashArr = new MyPair[DEFAULT_CAPACITY];
    }

    public MyHashTable(double loadFactor) {
        hashArr = new MyPair[DEFAULT_CAPACITY];
        this.loadFactor = loadFactor;
    }

    public MyHashTable(int capacity) {
        hashArr = new MyPair[capacity];
    }

    public MyHashTable(int capacity, double loadFactor) {
        hashArr = new MyPair[capacity];
        this.loadFactor = loadFactor;
    }

    public MyHashTable(int capacity, double loadFactor, double expansivity) {
        hashArr = new MyPair[capacity];
        this.loadFactor = loadFactor;
        this.expansivity = expansivity;
    }

    public MyPair[] getPairs() {
        MyPair[] pairs = new MyPair[(int) count];
        int ptr = 0;
        for (MyPair i : hashArr)
            if (i != null)
                pairs[ptr++] = i;
        return pairs;
    }

    public MyPair get(int key) {
        int add = 0;
        while (true)
            if (hashArr[(key + add) % hashArr.length].first == key) {
                return hashArr[(key + add) % hashArr.length];
            } else if (add++ == hashArr.length) return null;
    }

    public void add(int key) {
        if (count / hashArr.length >= loadFactor) grow();
        int add = 0;
        while (true) {
            if (hashArr[(key + add) % hashArr.length] == null) {
                hashArr[(key + add) % hashArr.length] = new MyPair(key, 1);
                count++;
                break;
            }
            if (hashArr[(key + add) % hashArr.length].first == key) {
                hashArr[(key + add) % hashArr.length].second++;
                break;
            }
            add++;
        }
    }

    public void add(MyPair newMP) {
        if (count / hashArr.length >= loadFactor) grow();
        int add = 0;
        while (true) {
            if (hashArr[(newMP.first + add) % hashArr.length] == null) {
                hashArr[(newMP.first + add) % hashArr.length] = newMP;
                count++;
                break;
            }
            if (hashArr[(newMP.first + add) % hashArr.length].first == newMP.first) {
                hashArr[(newMP.first + add) % hashArr.length].second += newMP.second;
                break;
            }
            add++;
        }
    }

    private void grow() {
        MyPair[] oldHash = hashArr;
        hashArr = new MyPair[(int) (expansivity * hashArr.length)];
        for (MyPair i : oldHash)
            if (i != null)
                innerAdd(i);
    }

    private void innerAdd(MyPair mp) {
        int add = 0;
        while (true) {
            if (hashArr[(mp.first + add) % hashArr.length] == null) {
                hashArr[(mp.first + add) % hashArr.length] = mp;
                break;
            }
            if (hashArr[(mp.first + add) % hashArr.length].first == mp.first) {
                hashArr[(mp.first + add) % hashArr.length].second += mp.second;
                break;
            }
            add++;
        }
    }
}
