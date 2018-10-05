package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private static final int KEY_COUNT = 256;

    private static Vector2 mousePosition;

    private enum KeyState {
        RELEASED, PRESSED, ONCE
    }

    private static boolean[] currentKeys = null;
    private static KeyState[] keys = null;

    public Input() {
        currentKeys = new boolean[KEY_COUNT];
        keys = new KeyState[KEY_COUNT];
        for (int i = 0; i < KEY_COUNT; ++i) {
            keys[i] = KeyState.RELEASED;
        }
        mousePosition = new Vector2();
    }

    public static synchronized void poll() {
        for (int i = 0; i < KEY_COUNT; ++i) {

            if (currentKeys[i]) {

                if (keys[i] == KeyState.RELEASED)
                    keys[i] = KeyState.ONCE;
                else
                    keys[i] = KeyState.PRESSED;
            } else {
                keys[i] = KeyState.RELEASED;
            }
        }
    }

    public static boolean keyDown(int keyCode) {
        return keys[keyCode] == KeyState.ONCE || keys[keyCode] == KeyState.PRESSED;
    }

    public static boolean keyDownOnce(int keyCode) {
        return keys[keyCode] == KeyState.ONCE;
    }

    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            currentKeys[keyCode] = true;
        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            currentKeys[keyCode] = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    public static int getX() {
        return mousePosition.x;
    }

    public static int getY() {
        return mousePosition.y;
    }

}
