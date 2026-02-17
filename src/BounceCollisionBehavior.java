
public class BounceCollisionBehavior implements CollisionBehavior {

    @Override
    public void onWallCollision(Car car) {
        // Stop completely
        car.stopEngine();

        // Invert direction (180 degrees) without exposing Car internals
        car.turnLeft();
        car.turnLeft();

        // Start again
        car.startEngine();
        
        
    }   
}