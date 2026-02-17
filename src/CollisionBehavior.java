public interface CollisionBehavior {
    /**
     * Called when a car is about to collide with a wall.
     * Implementations decide how to react (stop, reverse, teleport, etc.).
     */
    void onWallCollision(Car car);
}
