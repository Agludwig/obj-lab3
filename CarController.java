import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
*/

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    //static WorkShop<Volvo240> volvoWorkshop = new WorkShop<>(10,300,300);
    ArrayList<WorkShop<? extends Car>> workshops = new ArrayList<>();
    //methods:

    // OCP: collision behavior is pluggable without changing the controller logic
   

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        WorkShop<Volvo240> volvoWorkshop = new WorkShop<>(10,300,300);
        cc.workshops.add(volvoWorkshop);
        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        for (int i = 0; i < cc.cars.size(); i++) {
            cc.cars.get(i).setPosition(0, i * 100);
        }
       
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        //System.out.println(cc.cars.get(1).getPosition()[0]);
        // Start the timer
        cc.timer.start();
    }

    
    private final GeneralCollisionBehavior collisionBehavior = new GeneralCollisionBehavior();
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                if (willCollideWithWall(car)) {
                    collisionBehavior.onWallCollision(car);
                }
                
                // Check for workshop collision
                for (WorkShop<? extends Car> workshop : workshops) {
                    if (willCollideWithWorkshop(car, workshop)) {
                        collisionBehavior.onWorkshopCollision(car, workshop);
                    }
                }

                car.move();
                
                /* int x = (int) Math.round(car.getPosition()[0]);
                int y = (int) Math.round(car.getPosition()[1]);
                frame.drawPanel.moveit(x, y); */
                
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    private boolean willCollideWithWall(Car car) {
        double[] pos = car.getPosition();
        double[] dir = car.getDirection().toPosition();

        double nextX = pos[0] + dir[0] * car.getCurrentSpeed();
        double nextY = pos[1] + dir[1] * car.getCurrentSpeed();

        // Walls at x=[0, worldWidth], y=[0, worldHeight]
        // Use a small margin so the sprite doesn't go out of bounds.
        double margin = 100;

        return nextX < 0
                || nextY < 0
                || nextX > (frame.drawPanel.getWidth() - margin)
                || nextY > (frame.drawPanel.getHeight() - margin);
    }

    private boolean willCollideWithWorkshop(Car car, WorkShop<? extends Car> workshop) {
        double[] carPos = car.getPosition();
        double workshopX = workshop.getXPosition();
        double workshopY = workshop.getYPosition();
        
        // Define collision radius (workshop sprite size)
        double collisionRadius = 50;
        
        double distance = Math.sqrt(
            Math.pow(carPos[0] - workshopX, 2) + 
            Math.pow(carPos[1] - workshopY, 2)
        );
        
        return distance < collisionRadius && !car.isLoadedInWorkshop();
    }
    
    // Method to unload a car from the workshop
    public void unloadCarFromWorkshop(WorkShop<? extends Car> workshop) {
        try {
            Car unloadedCar = workshop.unload();
            unloadedCar.setLoadedInWorkshop(false);
            System.out.println("Car unloaded from workshop");
        } catch (IllegalStateException e) {
            System.out.println("Workshop is empty: " + e.getMessage());
        }
    }
    
    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }
    void startAllCars() {
    for (Car car : cars) car.startEngine();
    }

    void stopAllCars() {
    for (Car car : cars) car.stopEngine();
    }

    void setTurboOn() {
    for (Car car : cars) {
        if (car instanceof Saab95) {
            ((Saab95) car).setTurboOn();
            }   
        }
    }

    void setTurboOff() {
    for (Car car : cars) {
        if (car instanceof Saab95) {
            ((Saab95) car).setTurboOff();
            }
        }   
    }
    void liftBed() {
    for (Car car : cars) {
        if (car instanceof Scania) {
            ((Scania) car).setLoadAngle(0); 
            }
        }
    }

    void lowerBed() {
    for (Car car : cars) {
        if (car instanceof Scania) {
            ((Scania) car).setLoadAngle(70); 
            }
        }
    }
}