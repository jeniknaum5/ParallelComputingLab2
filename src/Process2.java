class Process2 implements Runnable {

    QueueClass queue;
    CPU cpu;
    int generateNumber;


    Process2(QueueClass q, int gN, CPU cpu) {
        this.queue = q;
        this.generateNumber = gN;
        this.cpu = cpu;
    }

    public void run() {
        long generateDelay;
        for (int i = 0; i < generateNumber; i++) {
            int randMin = 10;
            int randMax = 50; // rand = [10,50]
            generateDelay = randMin + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("PRO 2 : Задача 2 была сгенерирована за (" + generateDelay + ")");
                if (!cpu.checkWork()) {
                    cpu.setData("2");
                    System.out.println("PRO 2 : Задача 2 пошла на CPU");
                } else {
                    System.out.println("PRO 2 : Задача 2 пошла на очередь");
                    queue.put("2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PRO 2 : задачи закончились" + queue.getMaxSize());
    }
}
