import java.awt.*;

public class Scania extends Car {

    private double  loadAngle = 0;

    public Scania () {
        super(2, Color.white, 150, "Scania");
        this.stopEngine();
    }

    public double getLoadAngle() {
        return loadAngle;
    }

    public void setLoadAngle(double angle) {
        if (angle > 70 || angle < 0) {
            System.out.println("Angle must be between 0 and 70!");
            return;
        }
        if (this.getCurrentSpeed() > 0) {
            System.out.println("Cannot change angle while driving!");
            return;
        }
        this.loadAngle = angle;
    }

    @Override
    public void startEngine() {
        if (this.noDriving()) {
            System.out.println("Cannot drive when ramp is up.");
            return;
        }
        super.startEngine();
    }

    @Override
    protected void gas(double amount) {
        if (amount > 1 || amount < 0 ) {
            System.out.println("The amount must be between 0 and 1.");
            return;
        }
        if (this.noDriving()) {
            System.out.println("Cannot drive when ramp is up.");
            return;
        }
        super.gas(amount);
    }

    private boolean noDriving() {
        return loadAngle > 0;
    }
}
