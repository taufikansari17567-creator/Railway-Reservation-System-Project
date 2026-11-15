import java.awt.*;

public class HeaderPanel extends Panel {

    public HeaderPanel() {
        setPreferredSize(new Dimension(600, 100));
        setBackground(new Color(15, 45, 85));
    }

    public void paint(Graphics g) {

        // Background
        g.setColor(new Color(15, 45, 85));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Decorative line
        g.setColor(new Color(255, 190, 60));
        g.fillRect(0, 92, getWidth(), 5);

        // Main heading
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));

        String heading = "RAILWAY RESERVATION SYSTEM";
        FontMetrics fm = g.getFontMetrics();

        int x = (getWidth() - fm.stringWidth(heading)) / 2;

        g.drawString(heading, x, 42);

        // Subtitle
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(new Color(210, 225, 245));

        String subtitle = "Welcome to Online Railway Booking";
        int x2 = (getWidth() - g.getFontMetrics()
                  .stringWidth(subtitle)) / 2;

        g.drawString(subtitle, x2, 70);
    }
}