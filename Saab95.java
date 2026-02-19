import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95() {
        super(2, Color.red, 125, "Saab 95");
        this.turboOn = false;
        this.stopEngine();
    }
    
    public void setTurboOn() {
	    this.turboOn = true;
    }

    public void setTurboOff() {
	    this.turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (this.turboOn) turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
    }

  
}