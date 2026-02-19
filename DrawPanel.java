import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    int workshopX;
    int workshopY;
    
    //BufferedImage volvoImage;
    // To keep track of a single car's position
    //Point volvoPoint = new Point();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint;
    
    // List of cars from the controller
    ArrayList<Car> cars;
    private final java.util.Map<String, BufferedImage> carImages = new java.util.HashMap<>();

   
    /*void moveit(int x, int y){
        volvoPoint.x = x;
        volvoPoint.y = y;
    }*/


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars, ArrayList<WorkShop<? extends Car>> workshops) {
    this.cars = cars;
    this.workshopX = workshops.get(0).getXPosition();
    this.workshopY = workshops.get(0).getYPosition();
    this.volvoWorkshopPoint = new Point(workshopX, workshopY);
    this.setDoubleBuffered(true);
    this.setPreferredSize(new Dimension(x, y));
    this.setBackground(Color.green);

    try {
        for (Car car : cars) {
            String file = car.getModelName().replaceAll("\\s+", ""); // tar bort mellanslag
            BufferedImage img = ImageIO.read(
                DrawPanel.class.getResourceAsStream("pics/" + file + ".jpg")
            );
            carImages.put(car.getModelName(), img);
        }

        volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    // This method is called each time the panel updates/refreshes/repaints itself
    
    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (Car car : cars) {
        BufferedImage img = carImages.get(car.getModelName());
            if (img == null) continue;

        int x = (int) Math.round(car.getPosition()[0]);
        int y = (int) Math.round(car.getPosition()[1]);

        g.drawImage(img, x, y, null);
    }

    g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
}
}
