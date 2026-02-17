import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Car {

    private int capacity;
    private boolean rampUp = true;
    private ArrayList<Car> loadedCars;

    public CarTransport(int capacity) {
        super(2, Color.blue, 150, "CarTransport");
        this.capacity = capacity;
        this.stopEngine();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isRampUp() {
        return rampUp;
    }

    public void switchRamp() {
        if (this.getCurrentSpeed() != 0) {
            System.out.println("Cannot operate ramp while moving");
            return;
        }
        this.rampUp = !this.rampUp;
    }

    @Override
    public void startEngine () {
        if (!rampUp) {
            System.out.println("Cannot move with ramp down");
            return;
        }
        super.startEngine();
    }

    @Override
    public void gas(double amount) {
        if (!rampUp) {
            System.out.println("Cannot move with ramp down");
            return;
        }
        super.gas(amount);
    }

    public void loadCar(Car car) {
        if (!rampUp) {
            System.out.println("Cannot load when ramp is up.");
            return;
        }
        if (loadedCars.size() >= getCapacity()) {
            System.out.println("The car transport i full.");
            return;
        }
        loadedCars.add(car);
    }

    public Car unloadCar() {
        if (rampUp) {
            throw new IllegalStateException("Ramp must be down to unload car!");
        }
        if (loadedCars.isEmpty()) {
            throw new IllegalStateException("No cars to unload");
        }
        return loadedCars.remove(loadedCars.size() - 1);
    }

}