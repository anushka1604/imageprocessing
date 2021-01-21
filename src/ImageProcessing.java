import processing.core.PApplet;
public class ImageProcessing extends PApplet {
    final int NUM_PANELS_HORIZONTAL = 4; // the horizontal quantity of panels
    final int NUM_PANELS_VERTICAL = 4; // the vertical quantity of panels
    Panel[] panels;

    public static void main(String[] args) {
        PApplet.main("ImageProcessing");
    }

    public void setup(){
        size(600, 600);
        panels = new Panel[NUM_PANELS_HORIZONTAL * NUM_PANELS_VERTICAL];

        int index = 0;
        for (int i = 0; i < NUM_PANELS_VERTICAL; i++){
            for (int j = 0; j < NUM_PANELS_HORIZONTAL; j++){
                int w = width/NUM_PANELS_HORIZONTAL;
                int h = height/NUM_PANELS_VERTICAL;
                int x = j * w;
                int y = i * h;
                Panel s;
                if (i % 4 == 0){
                    s = new Panel(this, x, y, w, h, index);
                } else if (i % 4 == 1) {
                    s = new Tinted( this, x, y, w, h, index);
                } else if (i % 4 == 2){
                    s = new Contrasted( this, x, y, w, h, index);
                } else {
                    s = new Tinted( this, x, y, w, h, index);
                }
                s.setupImage("data/bunny.png");
                panels[index] = s;
                index++;
            }
        }
    }

    public void draw(){
        fancyBackground();

        for (int i = 0; i < panels.length; i++){
            Panel s = panels[i];
            s.display();
        }
    }

    public void mouseClicked(){
        for (int i = 0; i < panels.length; i++){
            Panel s = panels[i];
            s.handleMouseClicked(mouseX, mouseY);
        }
    }

    private void fancyBackground(){
        loadPixels();

        for (int x = 0; x < width; x++ ) {
            for (int y = 0; y < height; y++ ) {

                int loc = x + y * width;

                if (x % 2 == 0) {
                    pixels[loc] = color(255);
                } else {
                    pixels[loc] = color(0);
                }
            }
        }

        updatePixels();
    }

    private static PApplet app;

    public ImageProcessing(){
        super();
        app = this;
    }

    public static PApplet getApp(){ return app; }
}