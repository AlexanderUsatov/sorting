public class MyPair implements Comparable<MyPair> {
    public int first;
    public int second;

    public MyPair() {
    }

    public MyPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(MyPair o) {
        return first - o.first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPair myPair = (MyPair) o;
        return first == myPair.first;
    }

    @Override
    public int hashCode() {
        return first;
    }
}
