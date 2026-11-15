import java.awt.*;

public class HeaderPanel extends Panel {

    public void paint(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 23));

        g.drawString(
            "RAILWAY RESERVATION SYSTEM",
            60, 40
        );

        g.setFont(new Font("Arial", Font.PLAIN, 13));

//g.drawString(
  //          "Java AWT and Graphics Project",
    //        170, 65
      //  );
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 85);
    }
}