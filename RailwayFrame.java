import java.awt.*;
import java.awt.event.*;

public class RailwayFrame extends Frame
        implements ActionListener {

    TextField nameField, ageField, pnrField;
    Choice genderChoice, trainChoice;
    TextArea output;

    Button bookButton, cancelButton, checkButton;

    Train[] trains = {
        new Train(101, "Rajdhani Express",
                  "Delhi", "Kolkata", 100),

        new Train(102, "Shatabdi Express",
                  "Delhi", "Chandigarh", 80),

        new Train(103, "Vande Bharat",
                  "Kolkata", "Patna", 90)
    };

    Reservation reservation = new Reservation();

    RailwayFrame() {

        setTitle("Railway Reservation System");
        setSize(700, 650);
        setLayout(new BorderLayout(15, 15));

        Color background = new Color(240, 245, 252);

        setBackground(background);

        // Header
        add(new HeaderPanel(), BorderLayout.NORTH);

        // Main Form Panel
        Panel form = new Panel(new GridLayout(5, 2, 15, 15));

        form.setBackground(Color.WHITE);

        form.setFont(new Font("Arial", Font.BOLD, 15));

        nameField = new TextField();
        ageField = new TextField();
        pnrField = new TextField();

        genderChoice = new Choice();
        genderChoice.add("Male");
        genderChoice.add("Female");
        genderChoice.add("Other");

        trainChoice = new Choice();

        for (Train t : trains) {
            trainChoice.add(t.toString());
        }

        // Input styling
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        ageField.setFont(new Font("Arial", Font.PLAIN, 14));
        pnrField.setFont(new Font("Arial", Font.PLAIN, 14));

        genderChoice.setFont(new Font("Arial", Font.PLAIN, 14));
        trainChoice.setFont(new Font("Arial", Font.PLAIN, 14));

        // Form labels
        addLabel(form, "Passenger Name:");
        form.add(nameField);

        addLabel(form, "Passenger Age:");
        form.add(ageField);

        addLabel(form, "Gender:");
        form.add(genderChoice);

        addLabel(form, "Select Train:");
        form.add(trainChoice);

        addLabel(form, "PNR Number:");
        form.add(pnrField);

        Panel formContainer = new Panel(new BorderLayout());
        formContainer.setBackground(background);
        formContainer.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 35, 20, 35);
        gbc.weightx = 1;
        gbc.weighty = 1;

        formContainer.add(form, gbc);

        add(formContainer, BorderLayout.CENTER);

        // Buttons
        Panel buttons = new Panel(
            new FlowLayout(FlowLayout.CENTER, 15, 15)
        );

        bookButton = createButton("Book Ticket",
                    new Color(25, 135, 84));

        cancelButton = createButton("Cancel Ticket",
                    new Color(220, 53, 69));

        checkButton = createButton("Check Seats",
                    new Color(13, 110, 253));

        buttons.add(bookButton);
        buttons.add(cancelButton);
        buttons.add(checkButton);

        bookButton.addActionListener(this);
        cancelButton.addActionListener(this);
        checkButton.addActionListener(this);

        // Output Area
        output = new TextArea(8, 50);
        output.setEditable(false);

        output.setFont(
            new Font("Monospaced", Font.PLAIN, 14)
        );

        output.setBackground(new Color(248, 250, 255));
        output.setForeground(new Color(25, 45, 75));

        Panel bottom = new Panel(new BorderLayout(5, 5));
        bottom.setBackground(background);

        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(output, BorderLayout.CENTER);

        add(bottom, BorderLayout.SOUTH);

        // Window closing
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Label design
    void addLabel(Panel p, String text) {

        Label label = new Label(text);

        label.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        label.setForeground(new Color(25, 45, 75));

        p.add(label);
    }

    // Button design
    Button createButton(String text, Color color) {

        Button b = new Button(text);

        b.setBackground(color);
        b.setForeground(Color.WHITE);

        b.setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        return b;
    }

    // Button event handling
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookButton) {

            try {

                String name = nameField.getText().trim();

                int age = Integer.parseInt(
                    ageField.getText()
                );

                if (name.isEmpty() || age <= 0 || age > 120) {

                    output.setText(
                        "Please enter valid passenger details."
                    );

                    return;
                }

                Passenger p = new Passenger(
                    name,
                    age,
                    genderChoice.getSelectedItem()
                );

                Train t = trains[
                    trainChoice.getSelectedIndex()
                ];

                Ticket ticket = reservation.bookTicket(p, t);

                if (ticket != null) {

                    output.setText(
                        ticket.getTicketDetails()
                    );

                    nameField.setText("");
                    ageField.setText("");

                } else {

                    output.setText(
                        "Sorry! No seats available."
                    );
                }

            } catch (NumberFormatException ex) {

                output.setText(
                    "Please enter a valid age."
                );
            }
        }

        else if (e.getSource() == cancelButton) {

            try {

                int pnr = Integer.parseInt(
                    pnrField.getText()
                );

                if (reservation.cancelTicket(pnr)) {

                    output.setText(
                        "Ticket cancelled successfully!"
                    );

                } else {

                    output.setText(
                        "Invalid PNR or ticket already cancelled."
                    );
                }

            } catch (NumberFormatException ex) {

                output.setText(
                    "Please enter a valid PNR number."
                );
            }
        }

        else if (e.getSource() == checkButton) {

            Train t = trains[
                trainChoice.getSelectedIndex()
            ];

            output.setText(
                "========== TRAIN DETAILS ==========\n\n"
                + "Train Name      : " + t.trainName + "\n"
                + "Train Number    : " + t.trainNo + "\n"
                + "Source          : " + t.source + "\n"
                + "Destination     : " + t.destination + "\n"
                + "Total Seats     : " + t.totalSeats + "\n"
                + "Available Seats : " + t.availableSeats
                + "\n\n==================================="
            );
        }
    }
}