public class Main {

    static int deleted = 0;
    static int inter = 0;
    static int processToGenerate = 20;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Задач будет сгенерировано " + processToGenerate *2 + "\n");
        QueueClass queue = new QueueClass();

        CPU cpu = new CPU(queue);

        Process1 process1 = new Process1(queue, processToGenerate, cpu);
        Process2 process2 = new Process2(queue, processToGenerate, cpu);

        new Thread(process1).start();
        new Thread(process2).start();

        cpu.start();
    }
}


