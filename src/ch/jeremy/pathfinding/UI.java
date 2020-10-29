/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.jeremy.pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class UI implements MouseListener {

    JPanel panel;
    Graphics2D g;
    Map m;
    JFrame window;

    public UI() {
        x = 0;
        y = 0;
        window = new JFrame();

        window.setTitle("PathFinding A*");
        window.setSize(640, 360);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.setResizable(false);

        panel = new JPanel();
        window.setContentPane(panel);
        panel.setBackground(Color.WHITE);
        window.addMouseListener(this);
        panel.setVisible(true);
        window.setVisible(true);
        Graphics graphics = panel.getGraphics();
        g = (Graphics2D) graphics;

        m = new Map();
        drawMap();

    }

    private void drawMap() {
        draw = true;
        new Thread(() -> {
            while (draw) {

                g.setColor(Color.black);
                Case[][] map = m.getMap();
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        if (map[i][j].isWalkable()) {
                            g.fillRect(i * 25, j * 25, 23, 23);
                        }

                    }

                }
            }
        }).start();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        draw = false;

        int x = e.getX() / 25;
        int y = e.getY() / 25 - 1;
        System.out.println(x + " : " + y);
        if (x >= 0 && y >= 0 && x < m.getMap().length && y < m.getMap()[0].length) {
            if (m.getMap()[x][y].isWalkable()) {
                PathFinding pf = new PathFinding(m.getMap(), this.x, this.y, x, y);

                PathFindingNode node = pf.getLastCase();
                if (node != null) {
                    int red = (int) (Math.random() * 100) + 100;
                    int green = (int) (Math.random() * 100) + 100;
                    int blue = (int) (Math.random() * 100) + 100;
                    this.x = node.getX();
                    this.y = node.getY();
                    while (node.getParent() != null) {
                        g.setColor(new Color(red, green, blue));
                        g.fillRect(node.getX() * 25, node.getY() * 25, 25, 25);

                        node = node.getParent();

                    }
                    g.fillRect(node.getX() * 25, node.getY() * 25, 25, 25);

                }
            } else {
                System.out.println("Not Walkable");
            }
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private int x;
    private int y;
    private boolean draw;

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // Nothing
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // Nothing
    }
}
