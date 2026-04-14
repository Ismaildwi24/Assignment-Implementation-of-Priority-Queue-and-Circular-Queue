import java.util.Scanner;

// ============================================================
//  HospitalQueue.java
//  Main Program - Sistem Antrian Rumah Sakit
//  Menggunakan: Priority Queue + Circular Queue
//  Dibuat untuk tugas Struktur Data
// ============================================================
public class HospitalQueue {

    static Scanner sc = new Scanner(System.in);

    // Warna terminal (ANSI)
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";
    static final String BLUE   = "\u001B[34m";

    public static void main(String[] args) {

        // Inisialisasi struktur data
        PriorityQueue ugdQueue  = new PriorityQueue();
        CircularQueue poliUmum  = new CircularQueue("Poli Umum", 6);
        CircularQueue poliGigi  = new CircularQueue("Poli Gigi", 4);
        CircularQueue poliAnak  = new CircularQueue("Poli Anak", 5);

        // Load data demo
        loadDemoData(ugdQueue, poliUmum, poliGigi, poliAnak);

        int choice;
        do {
            printMainMenu();
            choice = readInt("Pilih menu: ");
            System.out.println();

            switch (choice) {
                case 1 -> menuPriorityQueue(ugdQueue);
                case 2 -> menuCircularQueue(poliUmum, poliGigi, poliAnak);
                case 3 -> displayAll(ugdQueue, poliUmum, poliGigi, poliAnak);
                case 0 -> System.out.println(GREEN + "\nTerima kasih! Program selesai." + RESET);
                default -> System.out.println(RED + "Pilihan tidak valid!" + RESET);
            }
        } while (choice != 0);

        sc.close();
    }

    // ─── MENU UTAMA ────────────────────────────────────────
    static void printMainMenu() {
        System.out.println("\n" + CYAN + BOLD);
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║        SISTEM ANTRIAN RUMAH SAKIT            ║");
        System.out.println("║   Priority Queue  +  Circular Queue          ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Kelola Antrian UGD (Priority Queue)      ║");
        System.out.println("║  2. Kelola Antrian Poli (Circular Queue)     ║");
        System.out.println("║  3. Tampilkan Semua Antrian                  ║");
        System.out.println("║  0. Keluar                                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print(RESET);
    }

    // ─── MENU PRIORITY QUEUE ───────────────────────────────
    static void menuPriorityQueue(PriorityQueue ugd) {
        int choice;
        do {
            System.out.println(YELLOW + BOLD);
            System.out.println("┌─────────────────────────────────────────┐");
            System.out.println("│       ANTRIAN UGD - PRIORITY QUEUE      │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  1. Daftarkan Pasien Baru               │");
            System.out.println("│  2. Panggil Pasien (Dequeue)            │");
            System.out.println("│  3. Lihat Pasien Berikutnya (Peek)      │");
            System.out.println("│  4. Tampilkan Semua Antrian UGD         │");
            System.out.println("│  0. Kembali ke Menu Utama               │");
            System.out.println("└─────────────────────────────────────────┘" + RESET);

            choice = readInt("Pilih: ");

            switch (choice) {
                case 1 -> {
                    System.out.println("\n─── Daftarkan Pasien Baru ───");
                    sc.nextLine();
                    System.out.print("Nama pasien   : ");
                    String name = sc.nextLine();
                    int age     = readInt("Usia          : ");
                    sc.nextLine();
                    System.out.print("Keluhan       : ");
                    String complaint = sc.nextLine();
                    System.out.println("Tingkat Prioritas:");
                    System.out.println("  1 = 🔴 Gawat Darurat");
                    System.out.println("  2 = 🟡 Prioritas (Lansia/Hamil/Disabilitas)");
                    System.out.println("  3 = 🟢 Reguler");
                    int prio = readInt("Pilih prioritas: ");
                    if (prio < 1 || prio > 3) prio = 3;
                    ugd.enqueue(name, age, complaint, prio);
                }
                case 2 -> {
                    PatientNode p = ugd.dequeue();
                    if (p != null) {
                        System.out.println("\n🏥 Memanggil pasien...");
                        System.out.println("─────────────────────────────");
                        System.out.printf("  Nama    : %s%n", p.name);
                        System.out.printf("  Usia    : %d tahun%n", p.age);
                        System.out.printf("  Keluhan : %s%n", p.complaint);
                        System.out.printf("  Status  : %s%n", p.priorityLabel);
                        System.out.println("─────────────────────────────");
                        System.out.println("  ✅ Pasien segera ditangani!");
                    }
                }
                case 3 -> {
                    PatientNode p = ugd.peek();
                    if (p != null) {
                        System.out.println("\n👁  Pasien berikutnya:");
                        System.out.printf("  %s  |  %s  |  %s%n",
                                p.name, p.priorityLabel, p.complaint);
                    } else {
                        System.out.println("\n❌ Antrian kosong.");
                    }
                }
                case 4 -> {
                    System.out.println("\n📋 Daftar Antrian UGD (" + ugd.getSize() + " pasien):");
                    ugd.display();
                }
                case 0 -> {}
                default -> System.out.println(RED + "Pilihan tidak valid!" + RESET);
            }
        } while (choice != 0);
    }

    // ─── MENU CIRCULAR QUEUE ───────────────────────────────
    static void menuCircularQueue(CircularQueue poliUmum,
                                  CircularQueue poliGigi,
                                  CircularQueue poliAnak) {
        int choice;
        do {
            System.out.println(BLUE + BOLD);
            System.out.println("┌─────────────────────────────────────────┐");
            System.out.println("│       ANTRIAN POLI - CIRCULAR QUEUE     │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  1. Masukkan Pasien ke Poli (Enqueue)   │");
            System.out.println("│  2. Panggil Pasien dari Poli (Dequeue)  │");
            System.out.println("│  3. Lihat Pasien Pertama (Peek)         │");
            System.out.println("│  4. Tampilkan Status Semua Poli         │");
            System.out.println("│  0. Kembali ke Menu Utama               │");
            System.out.println("└─────────────────────────────────────────┘" + RESET);

            choice = readInt("Pilih: ");

            if (choice >= 1 && choice <= 3) {
                CircularQueue poli = choosePoli(poliUmum, poliGigi, poliAnak);
                if (poli == null) continue;

                switch (choice) {
                    case 1 -> {
                        if (poli.isFull()) {
                            System.out.println(RED + "\n❌ Antrian " + poli.getPoliName() + " penuh!" + RESET);
                        } else {
                            sc.nextLine();
                            System.out.print("Nama pasien: ");
                            String name = sc.nextLine();
                            poli.enqueue(name);
                        }
                    }
                    case 2 -> {
                        String served = poli.dequeue();
                        if (served != null) {
                            System.out.println("\n🏥 Pasien dipanggil masuk: " + GREEN + BOLD + served + RESET);
                        }
                    }
                    case 3 -> {
                        String next = poli.peek();
                        System.out.println(next != null
                                ? "\n👁  Pasien berikutnya di " + poli.getPoliName() + ": " + BOLD + next + RESET
                                : "\n❌ Antrian kosong.");
                    }
                }
            } else if (choice == 4) {
                System.out.println("\n📋 Status Antrian Poli:");
                System.out.println("─────────────────────────────────────────────────────────");
                for (CircularQueue poli : new CircularQueue[]{poliUmum, poliGigi, poliAnak}) {
                    poli.display();
                    System.out.println();
                }
            } else if (choice != 0) {
                System.out.println(RED + "Pilihan tidak valid!" + RESET);
            }
        } while (choice != 0);
    }

    // ─── PILIH POLI ────────────────────────────────────────
    static CircularQueue choosePoli(CircularQueue poliUmum,
                                    CircularQueue poliGigi,
                                    CircularQueue poliAnak) {
        System.out.println("\nPilih Poli:");
        System.out.println("  1. " + poliUmum.getPoliName() + " (" + poliUmum.getSize() + "/" + poliUmum.getCapacity() + ")");
        System.out.println("  2. " + poliGigi.getPoliName() + " (" + poliGigi.getSize() + "/" + poliGigi.getCapacity() + ")");
        System.out.println("  3. " + poliAnak.getPoliName() + " (" + poliAnak.getSize() + "/" + poliAnak.getCapacity() + ")");
        int poli = readInt("Pilih poli: ");
        return switch (poli) {
            case 1 -> poliUmum;
            case 2 -> poliGigi;
            case 3 -> poliAnak;
            default -> { System.out.println(RED + "Poli tidak valid!" + "\u001B[0m"); yield null; }
        };
    }

    // ─── TAMPILKAN SEMUA ───────────────────────────────────
    static void displayAll(PriorityQueue ugd,
                           CircularQueue poliUmum,
                           CircularQueue poliGigi,
                           CircularQueue poliAnak) {
        System.out.println("\n" + CYAN + "═══════════════════════════════════════════════════════");
        System.out.println("              STATUS SELURUH ANTRIAN RS");
        System.out.println("═══════════════════════════════════════════════════════" + RESET);

        System.out.println(YELLOW + "\n[PRIORITY QUEUE] Antrian UGD ─── " + ugd.getSize() + " pasien" + RESET);
        ugd.display();

        System.out.println(BLUE + "\n[CIRCULAR QUEUE] Antrian Poli ───" + RESET);
        System.out.println("─────────────────────────────────────────────────────────────────────────────");
        for (CircularQueue poli : new CircularQueue[]{poliUmum, poliGigi, poliAnak}) {
            poli.display();
            System.out.println();
        }
    }

    // ─── DATA DEMO ─────────────────────────────────────────
    static void loadDemoData(PriorityQueue ugd,
                             CircularQueue poliUmum,
                             CircularQueue poliGigi,
                             CircularQueue poliAnak) {
        System.out.println(GREEN + "\n[INFO] Memuat data demo..." + RESET);

        // Priority Queue UGD
        ugd.enqueue("Budi Santoso",     45, "Sesak nafas berat",      1);
        ugd.enqueue("Siti Aminah",      70, "Tekanan darah tinggi",   2);
        ugd.enqueue("Andi Wijaya",      30, "Demam & batuk",          3);
        ugd.enqueue("Dewi Rahayu",      28, "Kecelakaan lalu lintas", 1);
        ugd.enqueue("Hendra Kurniawan", 55, "Nyeri dada",             2);

        // Circular Queue Poli
        poliUmum.enqueue("Rina Marlina");
        poliUmum.enqueue("Teguh Prasetyo");
        poliUmum.enqueue("Yuni Astuti");

        poliGigi.enqueue("Fajar Nugroho");
        poliGigi.enqueue("Lestari Dewi");

        poliAnak.enqueue("Ibnu Hakim");
        poliAnak.enqueue("Putri Andini");
        poliAnak.enqueue("Rizky Aditya");

        System.out.println(GREEN + "[INFO] Data demo berhasil dimuat!\n" + RESET);
    }

    // ─── HELPER: baca integer aman ─────────────────────────
    static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Input harus angka! " + prompt);
            sc.next();
        }
        int val = sc.nextInt();
        return val;
    }
}