# Hospital Queue System

A hospital patient queue system simulation based on a Java CLI (Command Line Interface). This project serves as an implementation of the **Priority Queue** data structure (priority-based queuing using a *Linked List*) and the **Circular Queue** (circular queuing using an *Array*).

This project was created to fulfill a Data Structures course assignment.

## 🚀 Key Features
- **Priority Queue (ER / UGD)**: 
  - Stores a queue of patients based on priority (1: Emergency, 2: High Priority, 3: Regular).
  - Patients with emergency conditions will always be prioritized, regardless of their arrival time.
- **Circular Queue (Clinics / Poli)**:
  - There are 3 types of clinic queues (General Clinic, Dental Clinic, Pediatric Clinic) with static capacities.
  - Every patient slot that is successfully served (*dequeued*) will be emptied and can be refilled without wasting any remaining memory from the static array.
- **Terminal Queue Visualization**:
  - Built with a colorful CLI interface and interactive slot/capacity indicators.

## 📁 Code Structure
- `HospitalQueue.java` - Main class (Main program), contains the CLI logic menu and display.
- `PriorityQueue.java` - *Linked List*-based implementation of a *Priority Queue*.
- `PatientNode.java` - Linked List node for the queue above.
- `CircularQueue.java` - *Array*-based implementation of a *Circular Queue* with slot visualization.

## 🛠️ How to Run
1. Ensure that you have the Java Development Kit (JDK) installed on your computer (version 17 or newer is recommended).
2. Clone/download this repository.
3. Compile and run the program using an integrated terminal or an IDE (such as IntelliJ IDEA / VS Code / Eclipse).
```bash
cd src
javac HospitalQueue.java
java HospitalQueue
```

## 📝 Demo Data
The program comes pre-loaded with mock/demo data. When first launched, the queue will already contain a few patients (not completely empty) so the queue mechanics are ready to be tested and observed.
