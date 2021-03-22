package Model.UiObjects;

public class Button extends UiObject {
    //private final BufferedImage[] sprite;
    private final ClickListener clicker;

    public Button(float x, float y, int width, int height, ClickListener clicker) {
        super(x, y, width, height);
        //this.sprite = sprite;
        this.clicker = clicker;
    }

    @Override
    public void update() { }

    /* Clicker */
    @Override
    public void onClick() {
        try {
            clicker.onClick();
        } catch (Exception ignored) { }
    }

    @Override
    public int getY() {
        return super.getY();
    }
}
