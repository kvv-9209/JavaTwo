package lesson1;

import lesson1.interfaces.Obstacles;
import lesson1.interfaces.Participants;

public class App {

    public static void main(String[] args) {

        Participants[] part = new Participants[6];
        part[0] = new Cat("Vaska", 10, 20);
        part[1] = new Human("Tolya", 40, 20);
        part[2] = new Robot("R2D2", 0, 150);
        part[3] = new Cat("Muska", 25, 40);
        part[4] = new Human("Kuzya", 45, 30);
        part[5] = new Robot("C3PO", 12, 100);

        Obstacles[] obst = new Obstacles[5];
        obst[0] = new Wall(30);
        obst[1] = new Treadmill(10);
        obst[2] = new Wall(5);
        obst[3] = new Treadmill(15);
        obst[4] = new Wall(20);

        for (int i = 0; i < part.length; i++) {
            for (int j = 0; j < obst.length; j++) {
                if (part[i].obstacle(obst[j])==true) {
                    System.out.println(part[i].getName() + " прошел дистанцию");

                } else {
                    System.out.println(part[i].getName() + " Не прошел дистанцию");
                    break;
                }
            }

        }
    }
}

