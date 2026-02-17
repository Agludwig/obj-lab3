public class Main {
    public static void main(String[] args) {
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();

        System.out.println("Saab95 speed factor: " + saab.speedFactor());
        System.out.println("Volvo240 speed factor: " + volvo.speedFactor());
        System.out.println("Volvo240 position: " + volvo.getPosition()[0] + " " + volvo.getPosition()[1]);
        volvo.gas(0.5);
        System.out.println("Volvo240 speed: " + volvo.getCurrentSpeed());

    }
}