package br.com.basicgui.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.List;

public class TutorialPanel extends JPanel {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel stepLabel;

    private final List<String> tutorialSteps = List.of(
            "files.png",
            "pattern.png",
            "colors.png",
            "speed.png"
    );

    private int currentStepIndex = 0;

    private static final int PREFERRED_WIDTH = 650;
    private static final int PREFERRED_HEIGHT = 400;

    public TutorialPanel() {
        // Sets up the panel layout and background.
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));

        // Sets up the CardLayout panel for image switching.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        loadTutorialImages();

        // Initializes navigation components.
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        stepLabel = new JLabel("", SwingConstants.CENTER);

        // Adds navigation actions.
        prevButton.addActionListener(e -> navigateTutorial(-1));
        nextButton.addActionListener(e -> navigateTutorial(1));

        JPanel navPanel = new JPanel(new BorderLayout(10, 5));
        navPanel.setBackground(getBackground());

        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonGroup.setBackground(getBackground());
        buttonGroup.add(prevButton);
        buttonGroup.add(nextButton);

        navPanel.add(stepLabel, BorderLayout.NORTH);
        navPanel.add(buttonGroup, BorderLayout.CENTER);

        add(cardPanel, BorderLayout.CENTER);
        add(navPanel, BorderLayout.SOUTH);

        updateNavigationButtons();
    }

    /** Loads tutorial images from resources and adds them as cards. */
    private void loadTutorialImages() {
        if (tutorialSteps.isEmpty()) {
            cardPanel.add(new JLabel("No tutorial steps defined.", SwingConstants.CENTER), "empty");
            return;
        }

        for (int i = 0; i < tutorialSteps.size(); i++) {
            String fileName = tutorialSteps.get(i);
            String cardName = "Step" + (i + 1);
            String resourcePath = "/images/tutorial/" + fileName;

            try (InputStream imageStream = getClass().getResourceAsStream(resourcePath)) {
                if (imageStream != null) {
                    ImageIcon originalIcon = new ImageIcon(imageStream.readAllBytes());

                    // Resizes the image to fit the panel dimensions.
                    Image originalImage = originalIcon.getImage();
                    Image resizedImage = originalImage.getScaledInstance(PREFERRED_WIDTH, PREFERRED_HEIGHT, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);

                    JLabel imageLabel = new JLabel(resizedIcon, SwingConstants.CENTER);
                    cardPanel.add(imageLabel, cardName);
                } else {
                    JLabel errorLabel = new JLabel("Image not found: " + fileName, SwingConstants.CENTER);
                    errorLabel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
                    cardPanel.add(errorLabel, cardName);
                    System.err.println("Could not load image: " + resourcePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** Navigates the tutorial to the previous (-1) or next (1) step. */
    private void navigateTutorial(int direction) {
        int nextIndex = currentStepIndex + direction;

        if (nextIndex >= 0 && nextIndex < tutorialSteps.size()) {
            currentStepIndex = nextIndex;
            String cardName = "Step" + (currentStepIndex + 1);
            cardLayout.show(cardPanel, cardName);
            updateNavigationButtons();
        }
    }

    /** Updates the state of the navigation buttons and the step counter label. */
    private void updateNavigationButtons() {
        prevButton.setEnabled(currentStepIndex > 0);
        nextButton.setEnabled(currentStepIndex < tutorialSteps.size() - 1);
        stepLabel.setText("Step " + (currentStepIndex + 1) + " of " + tutorialSteps.size());
    }
}