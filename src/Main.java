import java.util.Random;

/**
 * Created by Robin on 1/31/14.
 */
public class Main {

    //TODO: Refactor

    public static void main(String[] args) {
        int teller = 0;
        while (teller < 10) {
            try {
                Thread[] threads = new Thread[8];
                for (int i = 0; i < threads.length; i++) {
                    Runnable runnable = new CsvRunnable();
                    threads[i] = new Thread(runnable);
                    threads[i].start();
                    Thread.sleep(new Random().nextInt(1000));
                }
                for (int i = 0; i < threads.length; i++) {
                    threads[i].join();
                }
                teller++;
            } catch (InterruptedException irE) {
                exceptionMessage(irE);
            } catch (NullPointerException npE) {
                exceptionMessage(npE);
            }
        }
    }

    private static void exceptionMessage(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
        e.printStackTrace();
    }

}
