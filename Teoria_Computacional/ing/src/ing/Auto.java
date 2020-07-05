package ing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Auto extends Applet {

    public void paint(Graphics g) {

        int x, y, z;
        int alto, ancho;
        setSize(550, 250);
        setBackground(Color.WHITE);
        g.setColor(Color.GREEN);
        Font f = new Font(Font.DIALOG, Font.BOLD, 15);
        g.setFont(f);
        x = 165;
        y = 15;
        g.drawString("AUTOMATA DE RECONOCIMIENTO 'ING'", x, y);
        
        Graphics2D graph = (Graphics2D) g;
        graph.setColor(Color.BLUE);
        Line2D L1_start = new Line2D.Double(50, 155, 105, 115);
        graph.draw(L1_start);
        Line2D L2_i = new Line2D.Double(140, 100, 230, 100);
        graph.draw(L2_i);
        Line2D L3_n = new Line2D.Double(270, 100, 360, 100);
        graph.draw(L3_n);
        Line2D L4_g = new Line2D.Double(400, 100, 490, 100);
        graph.draw(L4_g);
        
        Arc2D arc01 = new Arc2D.Double(115, 67, 150, 68, 40, 105, 0);
        graph.draw(arc01);
        Arc2D arc02 = new Arc2D.Double(249, 67, 145, 68, 40, 105, 0);
        graph.draw(arc02);
        Arc2D arc03 = new Arc2D.Double(105, 23, 290, 165, 17, 150, 0);
        graph.draw(arc03);
        Arc2D arc04 = new Arc2D.Double(115, 80, 390, 80, 180, 180, 0);
        graph.draw(arc04);
        Arc2D arc05 = new Arc2D.Double(250, 70, 260, 70, 205, 133, 0);
        graph.draw(arc05);
        
        Ellipse2D start_fill = new Ellipse2D.Double(98, 114, 8, 8);
        graph.draw(start_fill);
        graph.fill(start_fill);
        Ellipse2D qi_fill = new Ellipse2D.Double(222, 95, 8, 8);
        graph.draw(qi_fill);
        graph.fill(qi_fill);
        Ellipse2D qn_fill = new Ellipse2D.Double(352, 95, 8, 8);
        graph.draw(qn_fill);
        graph.fill(qn_fill);
        Ellipse2D qg_fill = new Ellipse2D.Double(482, 95, 8, 8);
        graph.draw(qg_fill);
        graph.fill(qg_fill);

        Ellipse2D q0_fill = new Ellipse2D.Double(128, 75, 8, 8);
        graph.draw(q0_fill);
        graph.fill(q0_fill);
        Ellipse2D q1_fill = new Ellipse2D.Double(258, 75, 8, 8);
        graph.draw(q1_fill);
        graph.fill(q1_fill);
        Ellipse2D q2_fill = new Ellipse2D.Double(107, 75, 8, 8);
        graph.draw(q2_fill);
        graph.fill(q2_fill);
        Ellipse2D q3_fill = new Ellipse2D.Double(110, 119, 8, 8);
        graph.draw(q3_fill);
        graph.fill(q3_fill);
        Ellipse2D q3_q1_fill = new Ellipse2D.Double(259, 116, 8, 8);
        graph.draw(q3_q1_fill);
        graph.fill(q3_q1_fill);
        Ellipse2D q0_c = new Ellipse2D.Double(95, 95, 6, 6);
        graph.draw(q0_c);
        graph.fill(q0_c);
        Ellipse2D q1_c = new Ellipse2D.Double(245, 120, 6, 6);
        graph.draw(q1_c);
        graph.fill(q1_c);

        Graphics2D graph02 = (Graphics2D) g;
        graph02.setColor(Color.BLACK);
        Ellipse2D q0 = new Ellipse2D.Double(100, 80, 40, 40);
        graph02.draw(q0);
        Ellipse2D q1 = new Ellipse2D.Double(230, 80, 40, 40);
        graph02.draw(q1);
        Ellipse2D q2 = new Ellipse2D.Double(360, 80, 40, 40);
        graph02.draw(q2);
        Ellipse2D q3 = new Ellipse2D.Double(490, 80, 40, 40);
        graph02.draw(q3);
        Ellipse2D q3_final = new Ellipse2D.Double(495, 85, 30, 30);
        graph02.draw(q3_final);
        Ellipse2D q0_q0 = new Ellipse2D.Double(80, 80, 20, 20);
        graph02.draw(q0_q0);
        Ellipse2D q1_q1 = new Ellipse2D.Double(230, 120, 20, 20);
        graph.draw(q1_q1);
  
        Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        g.setFont(f1);

        g.drawString("start", 55, 135);
        g.drawString("q0", 115, 105);
        g.drawString("q1", 245, 105);
        g.drawString("q2", 375, 105);
        g.drawString("q3", 505, 105);

        g.drawString("i", 185, 115);
        g.drawString("n", 315, 115);
        g.drawString("g", 445, 115);

        g.drawString("not i,n", 170, 80);
        g.drawString("i", 315, 80);

        g.drawString("not i", 280, 175);
        g.drawString("not i or g", 230, 35);

        g.drawString("i", 240, 135);
        g.drawString("not i", 65, 80);
    }

}


