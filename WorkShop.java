import java.util.ArrayList;
import java.util.List;

public class WorkShop<T extends Car> implements Storage<T> {
    private final int capacity;
    private final List<T> Cars;
    private final int xPosition;
    private final int yPosition;

    public WorkShop(int capacity, int xPosition, int yPosition) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.Cars = new ArrayList<>();
    }

    @Override
    public void load(T Car) {
        if (this.isFull()) {
            throw new IllegalStateException("AutoShop is full");
        }
        Cars.add(Car);
    }

    @Override
    public T unload() {
        if (this.isEmpty()) {
            throw new IllegalStateException("No Cars in shop");
        }
        return this.Cars.remove(this.Cars.size() - 1);
    }

    public boolean isEmpty() {
        return this.Cars.isEmpty();
    }
    public int getXPosition() {
        return this.xPosition;
    }
    public int getYPosition() {
        return this.yPosition;
    }
    public boolean isFull() {
        return this.Cars.size() >= this.capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getNumberOfCars() {
        return this.Cars.size();
    }
}