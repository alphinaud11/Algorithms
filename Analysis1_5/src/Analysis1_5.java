import java.util.Arrays;

public class Analysis1_5 {

    public static int[] person; //array where each index is how many solutions a person has

    public static void getMinMessages() {
        int last = person.length - 1;
        int messages = 0;
        for (int i=0; i<last; i++) {
            person[i+1] += person[i];
            messages++;
        }
        for (int i=last-1; i>=0; i--) {
            person[i] = person[last];
            messages++;
        }
        System.out.println(messages);
    }

    public static void main(String[] args) {
        int n = 10;
        person = new int[n];
        Arrays.fill(person, 1);
        getMinMessages();
    }
}
