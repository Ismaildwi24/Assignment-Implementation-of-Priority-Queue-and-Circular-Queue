// ============================================================
//  PriorityQueue.java
//  Antrian berdasarkan prioritas menggunakan Linked List
//  Digunakan untuk antrian UGD
// ============================================================
public class PriorityQueue {
    private PatientNode head;
    private int size;

    public PriorityQueue() {
        head = null;
        size = 0;
    }

    // Enqueue: sisipkan sesuai urutan prioritas (1 paling tinggi)
    public void enqueue(String name, int age, String complaint, int priority) {
        PatientNode newNode = new PatientNode(name, age, complaint, priority);

        // Jika kosong atau prioritas baru lebih tinggi dari head
        if (head == null || newNode.priority < head.priority) {
            newNode.next = head;
            head = newNode;
        } else {
            PatientNode current = head;
            while (current.next != null && current.next.priority <= newNode.priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
        System.out.println("\n✅ Pasien [" + name + "] berhasil didaftarkan sebagai " + newNode.priorityLabel);
    }

    // Dequeue: ambil pasien dengan prioritas tertinggi (head)
    public PatientNode dequeue() {
        if (isEmpty()) {
            System.out.println("\n❌ Antrian Priority kosong! Tidak ada pasien.");
            return null;
        }
        PatientNode served = head;
        head = head.next;
        size--;
        return served;
    }

    // Peek: lihat pasien berikutnya tanpa menghapus
    public PatientNode peek() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    // Tampilkan seluruh antrian
    public void display() {
        if (isEmpty()) {
            System.out.println("   (Antrian kosong)");
            return;
        }
        PatientNode current = head;
        int no = 1;
        System.out.println("   No | Nama Pasien          | Usia | Keluhan              | Status");
        System.out.println("   ---|----------------------|------|----------------------|------------------");
        while (current != null) {
            System.out.printf("   %-3d| %-20s | %-4d | %-20s | %s%n",
                    no++, current.name, current.age, current.complaint, current.priorityLabel);
            current = current.next;
        }
    }
}