import java.util.ArrayList;
import java.util.PriorityQueue;

public class Analysis1_13 {

    public static ArrayList<Function> program = new ArrayList<>();
    public static int time = 0;

    public static class Function {
        String parent;
        String name;
        ArrayList<Object> parameters;
        int depth;
        PriorityQueue<Function> nestedFunctions = new PriorityQueue<>((f1, f2) -> {
            if (f1.depth < f2.depth)
                return 1;
            else if (f1.depth > f2.depth)
                return -1;
            return 0;
        });

        Function (String parent, String name, ArrayList<Object> parameters) {
            this.parent = parent;
            this.name = name;
            this.parameters = parameters;
            int max = Integer.MIN_VALUE;
            for (Object par : parameters) {
                if (par instanceof Function) {
                    nestedFunctions.add((Function) par);
                    max = Math.max(((Function) par).depth, max);
                }
            }
            depth = (max < 0) ? 0 : (max + 1);
        }
    }

    public static void run() {
        System.out.println("time " + (time++) + ": call f()");

        while (!program.isEmpty()) {
            ArrayList<Function> functionsToBeDeleted = new ArrayList<>(); //functions to be deleted from program
            ArrayList<Function> functionsToBeCalled = new ArrayList<>(); //functions to be called in this time step

            for (Function f : program) {
                if (!f.nestedFunctions.isEmpty())
                    functionsToBeCalled.add(f.nestedFunctions.poll());
                if (f.nestedFunctions.isEmpty())
                    functionsToBeDeleted.add(f);
            }

            program.removeAll(functionsToBeDeleted);
            program.addAll(functionsToBeCalled);

            for (int i=0; i<functionsToBeCalled.size(); i++) { //printing time step
                if (i == 0)
                    System.out.print("time " + (time++) + ": ");
                if (i == functionsToBeCalled.size()-1) {
                    System.out.print(functionsToBeCalled.get(i).parent + " calls " + functionsToBeCalled.get(i).name);
                    System.out.println();
                }
                else
                    System.out.print(functionsToBeCalled.get(i).parent + " calls " + functionsToBeCalled.get(i).name + " and ");
            }

        }
        System.out.print("time " + (time++) + ": all done!");
    }

    public static void main(String[] args) {
        ArrayList<Object> gParams = new ArrayList<>();
        gParams.add(1); // variable x
        Function g = new Function("f", "g()", gParams);
        //g(x)
        ArrayList<Object> hParams = new ArrayList<>();
        hParams.add(1); //variable y
        hParams.add(new Function("h", "k()", new ArrayList<>()));
        Function h = new Function("f", "h()", hParams);
        //h(y,k())
        ArrayList<Object> fParams = new ArrayList<>();
        fParams.add(g);
        fParams.add(h);
        Function f = new Function(null, "f()", fParams);
        //f(g(x),h(y,k()))
        program.add(f);
        run();
    }
}
