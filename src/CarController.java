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

    //methods:

    // OCP: collision behavior is pluggable without changing the controller logic
   

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        
        // Start the timer
        cc.timer.start();
    }

    //collision behavior is pluggable without changing the controller logic
    private final CollisionBehavior collisionBehavior = new BounceCollisionBehavior();
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                if (willCollideWithWall(car)) {
                    collisionBehavior.onWallCollision(car);
                }

                car.move();
                int x = (int) Math.round(car.getPosition()[0]);
                int y = (int) Math.round(car.getPosition()[1]);
                frame.drawPanel.moveit(x, y);
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
}