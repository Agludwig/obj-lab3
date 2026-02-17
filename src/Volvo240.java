import java.awt.*;

public class Volvo240 extends Car {

    private double trimFactor = 1.25;

    public Volvo240() {
        super(4, Color.black, 100, "Volvo 240");
        this.stopEngine();
    }

    @Override
    protected double speedFactor() {
        return this.getEnginePower() * 0.01 * this.trimFactor;
    }
}
