// ============================================================
//  CircularQueue.java
//  Antrian melingkar berbasis array dengan kapasitas tetap
//  Digunakan untuk antrian Poli (Umum, Gigi, Anak)
// ============================================================
public class CircularQueue {
    private String[] queue;
    private int front, rear, size, capacity;
    private String poliName;

    public CircularQueue(String poliName, int capacity) {
        this.poliName = poliName;
        this.capacity = capacity;
        this.queue    = new String[capacity];
        this.front    = 0;
        this.rear     = -1;
        this.size     = 0;
    }

    // Enqueue: tambahkan pasien ke belakang antrian
    public boolean enqueue(String patientName) {
        if (isFull()) {
            System.out.println("\n❌ Antrian " + poliName + " PENUH! Kapasitas maksimal: " + capacity);
            return false;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = patientName;
        size++;
        System.out.println("\n✅ [" + patientName + "] masuk antrian " + poliName + " (slot " + rear + ")");
        return true;
    }

    // Dequeue: ambil pasien dari depan antrian
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("\n❌ Antrian " + poliName + " kosong!");
            return null;
        }
        String patient = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;
        return patient;
    }

    // Peek: lihat pasien terdepan tanpa menghapus
    public String peek() {
        if (isEmpty()) return null;
        return queue[front];
    }

    public boolean isEmpty()    { return size == 0; }
    public boolean isFull()     { return size == capacity; }
    public int getSize()        { return size; }
    public int getCapacity()    { return capacity; }
    public String getPoliName() { return poliName; }

    // Tampilkan antrian beserta visualisasi slot array
    public void display() {
        System.out.println("   Poli       : " + poliName);
        System.out.println("   Kapasitas  : " + capacity + " slot");
        System.out.println("   Terisi     : " + size + " pasien");
        System.out.println("   Front idx  : " + front + "  |  Rear idx: " + rear);
        System.out.print("   Slot       : ");

        for (int i = 0; i < capacity; i++) {
            if (queue[i] != null) {
                String marker = "";
                if (i == front && size > 0) marker = "(F)";
                if (i == rear  && size > 0) marker += "(R)";
                System.out.print("[" + queue[i] + marker + "] ");
            } else {
                System.out.print("[  KOSONG  ] ");
            }
        }
        System.out.println();
    }
}