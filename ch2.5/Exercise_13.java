/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

public class Exercise_13 {

    /*  java Exercise_12_input 30 | java Exercise_13 10*/
    /*  java Exercise_12_input 30 | java Exercise_13 10 > filename.txt */

    /* job class ******************************************************************************/
    public static class Jobs implements Comparable<Jobs> {
        private String name;
        private int time;

        public Jobs(String n, int t) {
            this.name = n;
            this.time = t;
        }

        public int compareTo(Jobs that) {
            if (this.time > that.time) return 1;
            else if (this.time < that.time) return -1;
            else return 0;
        }

        public String toString() {
            return String.format("%-6s", this.name) + String.format("%4d", this.time) + " s";
        }

    }

    /* processor class ******************************************************************************/
    private static class Processor implements Comparable<Processor> {
        private int totalTime;
        private Jobs[] tasks;
        private int index;  /* tasks的总数， length*/
        private final String name;

        public Processor(String name) {
            this.name = name;
            totalTime = 0;
            tasks = new Jobs[20];  /* 可优化*/
        }

        public void add(Jobs job) {
            tasks[index++] = job;
            totalTime += job.time;
        }

        public int compareTo(Processor that) {
            if (totalTime < that.totalTime) return -1;
            else if (totalTime > that.totalTime) return 1;
            else return 0;
        }
    }

    /* main **************************************************************************************/
    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        String[] s = StdIn.readAllLines();

        int n = s.length;
        Jobs[] box = new Jobs[n];
        int len_box = 0;

        for (int i = 0; i < n; i++) {  /* 所有输入任务 name and time*/
            String[] temp = s[i].split("\\s+");  /* \s表示匹配任何空白字符，+表示匹配一次或多次。*/
            String name = temp[0];
            int time = Integer.parseInt(temp[1]);
            box[len_box++] = new Jobs(name, time);
        }


        /* insertion sort box from hi to lo*/
        for (int i = 0; i < len_box; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (box[j].compareTo(box[j - 1]) > 0) {
                    Jobs larger = box[j];
                    box[j] = box[j - 1];
                    box[j - 1] = larger;
                }
            }
        }

        /* 初始化 processor 优先队列*/
        MinPQ<Processor> processors = new MinPQ<Processor>(m + 1);  /* 数组实现 */
        for (int i = 0; i < m; i++) {
            processors.insert(new Processor("Processor " + String.format("%-2s", i)));
        }

        /* 安排任务 */
        for (int i = 0; i < len_box; i++) {
            Processor min = processors.delMin();
            min.add(box[i]);
            processors.insert(min);
        }

        /* 打印输出*/
        for (Processor p : processors) {
            System.out.print(p.name + " :  ");
            for (int i = 0; i < p.index; i++) {
                System.out.print(p.tasks[i] + "  -->  ");
            }
            System.out.println("Total Time = " + p.totalTime);
            // System.out.println();
        }

    }
}

