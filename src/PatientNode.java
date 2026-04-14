// ============================================================
//  PatientNode.java
//  Node untuk Priority Queue (Linked List)
// ============================================================
public class PatientNode {
    String name;
    int age;
    String complaint;
    int priority;       // 1 = Gawat Darurat, 2 = Prioritas, 3 = Reguler
    String priorityLabel;
    PatientNode next;

    public PatientNode(String name, int age, String complaint, int priority) {
        this.name          = name;
        this.age           = age;
        this.complaint     = complaint;
        this.priority      = priority;
        this.priorityLabel = getPriorityLabel(priority);
        this.next          = null;
    }

    private String getPriorityLabel(int p) {
        switch (p) {
            case 1:  return "🔴 GAWAT DARURAT";
            case 2:  return "🟡 PRIORITAS";
            default: return "🟢 REGULER";
        }
    }
}