import java.awt.*;

public abstract class Car implements Movable{

    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private double xPosition;
    private double yPosition;
    private Direction direction;

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.xPosition = 0;
        this.yPosition = 0;
        this.direction = Direction.NORTH;
        this.stopEngine();
    }

    public int getNrDoors() {
        return this.nrDoors;
    }

    public double getEnginePower() {
        return this.enginePower;
    }

    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public Color getColor() {
        return this.color;
    }

    public String getModelName() {
        return this.modelName;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public double[] getPosition() {
        return new double[] {xPosition, yPosition};
    }

    public void setColor(Color clr) {
	    this.color = clr;
    }

    public void startEngine() {
	    this.currentSpeed = 0.1;
    }

    public void stopEngine() {
	    this.currentSpeed = 0;
    }

    private void incrementSpeed(double amount) {
	    this.currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount) {
        this.currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    protected double speedFactor() {
        return this.enginePower * 0.01;
    }

    protected void gas(double amount) {
        if (amount > 1 || amount < 0) {
            System.out.println("Amount must be between 0 and 1");
            return;
        }
        incrementSpeed(amount);
    }

    protected void brake(double amount) {
        if (amount > 1 || amount < 0) {
            System.out.println("Amount must be between 0 and 1");
            return;
        }
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        double[] dirCoordinates = this.direction.toPosition();
        this.xPosition += dirCoordinates[0] * this.currentSpeed;
        this.yPosition += dirCoordinates[1] * this.currentSpeed;
    }

    @Override
    public void turnLeft() {
        this.direction  = this.direction.turnLeft(); 
    }

    @Override
    public void turnRight() {
        this.direction  = this.direction.turnRight();
    }
    public abstract String getJPGName();
}