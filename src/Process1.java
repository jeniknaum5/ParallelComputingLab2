class Process1 implements Runnable {

    QueueClass queue;
    CPU cpu;
    int generateNumber;

    Process1(QueueClass q, int gN, CPU cpu) {
        this.queue = q;
        this.generateNumber = gN;
        this.cpu = cpu;
    }

    private void destroy() throws InterruptedException {
        Main.deleted++;
    }

    public void run() {
        long generateDelay;
        for (int i = 0; i < generateNumber; i++) {
            int randMin = 10;
            int randMax = 40; // rand = [10,50]
            generateDelay = randMin + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("PRO 1 : Задача 1 была сгенерирована за (" + generateDelay + ")");
                if (!cpu.checkWork()) {
                    cpu.setData("1");
                    System.out.println("PRO 1 : Задача 1 пошла на CPU");
                } else if (cpu.checkWork() && cpu.data.equals("1")) { // перехожим на 2 CPU с проверкой
                    destroy();
                    System.out.println("PRO 1 : Задача 1 удалена // CPU занят задачей 1 // deleted = " + Main.deleted);
                } else if (cpu.checkWork() && cpu.data.equals("2")) {
                    System.out.println("---");
                    queue.put(cpu.getData());
                    if (!cpu.isInterrupted() )
                        cpu.interrupt();
                    Main.inter++;
                    cpu.setData("1");
                    System.out.println("PRO 1 : Задача 1  пошла на CPU 1, Задача 2 прервана c CPU и пошла в очередь // interrupt = " + Main.inter);
                    System.out.println("---");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PRO 1 : задачи закончились" + queue.getMaxSize());
        try {
            Thread.sleep(3300);
            System.out.println("\n\n\n-----------------------------------\nМаксимальная длина очереди = " + queue.getMaxSize());
            System.out.println("Процент уничтоженых задач для первого потока = " +(double) Main.deleted/Main.processToGenerate*100);
            System.out.println("Процент прерваных задач для второго потока = " +(double) Main.inter/Main.processToGenerate*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
