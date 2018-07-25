public class MyHashtable {

    private MyPair[] hashArr;
    private int add = 0;
    private double count = 0;
    private double loadFactor = 0.75;

    public MyHashtable() {
        hashArr = new MyPair[20];
    }

    public MyHashtable(double loadFactor) {
        hashArr = new MyPair[20];
        this.loadFactor = loadFactor;
    }

    public MyHashtable(int capacity) {
        hashArr = new MyPair[capacity];
    }

    public MyHashtable(int capacity, double loadFactor) {
        hashArr = new MyPair[capacity];
        this.loadFactor = loadFactor;
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
        try {
            while (true)
                if (hashArr[(key + add) % hashArr.length].first == key) {
                    return hashArr[(key + add) % hashArr.length];
                } else if (add++ == hashArr.length) return null;
        } finally {
            add = 0;
        }
    }

    public void add(int key) {
        if (count / hashArr.length >= loadFactor) newHash();
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
        add = 0;
    }

    public void add(MyPair newMP) {
        if (count / hashArr.length >= loadFactor) newHash();
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
        add = 0;
    }

    private void newHash() {
        MyPair[] oldHash = hashArr;
        hashArr = new MyPair[hashArr.length * 2];
        for (MyPair i : oldHash)
            if (i != null)
                innerAdd(i);
    }

    private void innerAdd(MyPair mp) {
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
        add = 0;
    }

}
