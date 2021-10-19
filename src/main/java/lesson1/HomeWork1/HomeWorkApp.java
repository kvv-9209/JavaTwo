package lesson1.HomeWork1;


import java.util.Arrays;

public class HomeWorkApp {
    public static void main(String[] args) {


        Participants[] part = new Participants[]{new Cat("Vaska", 25, 15),
                new Human("Tolya", 25, 25),
                new Robot("C3PO", 125, 5)};


        Obstacle[] obst = new Obstacle[4];
        obst[0] = new Treadmill(25);
        obst[1] = new Wall(10);
        obst[2] = new Treadmill(10);
        obst[3] = new Wall(25);

        for (int i = 0; i < part.length; i++) {
            for (int j = 0; j < obst.length; j++) {
                if (part[i].obstacle(obst[j]) == true) {
                    if (obst[j].getTredmill() != 0) {
                        part[i].run();
                    } else if (obst[j].getWall() != 0) {
                        part[i].jump();
                    }
                } else {
                    System.out.println(part[i].getName() + " не проходит дальше");
                    break;
                }
            }
            System.out.println();
        }
    }
}

