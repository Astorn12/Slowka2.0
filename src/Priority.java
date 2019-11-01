import java.util.LinkedList;
import java.util.List;

/**
 * Created by osiza on 27.09.2019.
 */
public  class Priority {
    public static int priority=1;

    public static void loadPriority(){
        Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
        List<PriorityFake> priorityFakes= new LinkedList<>();

        pomocnik_plikowy.zczytywanie_z_pliku("priority.txt",',',priorityFakes,PriorityFake.class);
        setPriority(priorityFakes.get(0).prio);
    }

    private static void setPriority(int priority) {
        Priority.priority = priority;
    }


}
