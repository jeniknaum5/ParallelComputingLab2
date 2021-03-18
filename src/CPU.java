class CPU extends Thread {

    QueueClass queue;
    String data = "";

    private boolean isWork = false;

    public boolean checkWork() {
        if (isWork) return true;
        return false;
    }

    CPU(QueueClass q) {
        this.queue = q;
    }

    public String getData(){
        return data;
    }

    public void setData(String newData){
        data = newData;
    }

    @Override
    public void run() {
        long processingTime;
        while (true) {
            int randMin = 20;
            int randMax = 80; // rand = [20,100]
            processingTime = randMin + (int) (Math.random() * randMax);
            try {
                if (data != "") {
                    isWork = true;
                    System.out.println("CPU 1 : Начал работу напрямую - задача " + data + "; Время работы (" + processingTime + ")");
                    Thread.sleep(processingTime);
                    setData("");
                    isWork = false;
                    System.out.println("CPU 1 : Закончил работу");
                } else if (!queue.queueIsEmpty()) {
                    isWork = true;
                    data = queue.get();
                    System.out.println("CPU 1 : Начал работу с очереди - задача " + data + "; Время работы (" + processingTime + ")");
                    Thread.sleep(processingTime);
                    setData("");
                    isWork = false;
                    System.out.println("CPU 1 : Закончил работу");
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}
