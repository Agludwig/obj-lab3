
public class GeneralCollisionBehavior {

    
    public void onWallCollision(Car car) {
        // Stop completely
        car.decrementSpeed(car.getCurrentSpeed());

        // Invert direction (180 degrees) without exposing Car internals
        car.turnLeft();
        car.turnLeft();

        // Start again
        car.startEngine();
    }
    
   
    public void onWorkshopCollision(Car car, WorkShop<? extends Car> workshop) {
        // Only Volvo240 cars can be loaded into workshops
        if (!(car instanceof Volvo240)) {
            return;
        }
        
        // Stop the car completely
        car.decrementSpeed(car.getCurrentSpeed());
        car.stopEngine();
        
        try {
            // Try to load the car into the workshop
            @SuppressWarnings("unchecked")
            WorkShop<Volvo240> volvoWorkshop = (WorkShop<Volvo240>) workshop;
            volvoWorkshop.load((Volvo240) car);
            car.setLoadedInWorkshop(true);
        } catch (IllegalStateException e) {
            System.out.println("Workshop is full: " + e.getMessage());
        }
    }
}