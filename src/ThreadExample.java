import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        boolean flag = true;
        String menu = "1 - задание\n2 - задание\n3 - задание";
        System.out.println(menu);
        a = scanner.nextInt();
        switch (a) {
            case 1:
                Thread myThread = new Thread(new Runnable() {
                    public void run() {
                        System.out.println("Мой поток запущен");
                    }
                });

                System.out.println("Начальное состояние потока: " + myThread.getState());
                myThread.start();
                System.out.println("Состояние запущенного потока: " + myThread.getState());

                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Состояние потока после завершения: " + myThread.getState());
                break;
            case 2:
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            System.out.println("Первый");
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Thread.yield();
                    }
                });

                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 10; i++) {
                            System.out.println("Второй");
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Thread.yield();

                    }
                });

                thread1.start();
                thread2.start();
                break;
            case 3:
                int MaxProducts = 10;
                List<Integer> products = new ArrayList<>();
                Thread producer = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < MaxProducts; i++) {
                            try {
                                products.add(i);
                                System.out.println("Произвёл продукт под номером: " + i);
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

                Thread customer = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < MaxProducts; i++) {
                            try {
                                products.remove(0);
                                System.out.println("Получил продукт под номером: " + i);
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

                producer.start();


                customer.start();
                break;
            default:
                System.out.println("Не то");
                break;
        }


    }
}
