package lesson1.HomeWork1;


public class HomeWorkApp {
    public static void main(String[] args) {

        Participants[] part = new Participants[3];
        part[0] = new Cat("Vaska", 12, 50);
        part[1] = new Human("Tolya", 10, 25);
        part[2] = new Robot("C3PO", 5, 125);
        Obstacle[] obst = new Obstacle[4];
        obst[0] = new Treadmill(25);
        obst[1] = new Wall(40);
        obst[2] = new Treadmill(10);
        obst[3] = new Wall(5);

        /*Cat cat = new Cat("Barsik");
        Human human = new Human("Vasiliy");
        Robot robot = new Robot("C3PO");*/
        // Treadmill tred = new Treadmill(true);
        // Wall wall = new Wall(true);

        /*part[0].obstacle(obst[0]);
        part[1].obstacle(obst[1]);
        part[2].obstacle(obst[0]);*/
        for (int i = 0; i < part.length; i++){
            for(int j = 0; j < obst.length; j++){
                if(part[i].obstacle(obst[j]) == true){ //instanseof
                    part[i].obstacle(obst[j]);
                } else{
                    break;
                }
            }
        }
    }
}

