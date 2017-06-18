package org.lelecaps.anki;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) throws IOException {
        List<Card> cards = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name to start the session: ");
        String filename = input.nextLine();
        if (filename.isEmpty()) {
            filename = "resources/deck.txt";    // sample file
        }
        File file = new File(filename);
        input = new Scanner(file);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] string = line.split("\\|");
            if (string.length == 3) {
                cards.add(new Card(string[0], string[1], string[2]));
            } else {
                cards.add(new Card(string[0], string[1], ""));
            }
        }
        input.close();

        Boolean goOn = true;
        int greenCards = 0;
        while (!cards.isEmpty() && goOn) {
            greenCards = 0;
            goOn = false;
            for (Card card : cards) {
                if (card.getBox() == Box.RED) {
                    System.out.println("\nQuestion: " + card.getQuestion());
                    System.out.println("(press enter to see the answer...)");
                    int answer;
                    try (Scanner scanner = new Scanner(System.in)) {
                        scanner.nextLine();
                        System.out.println("Answer: " + card.getAnswer() + "\n");
                        System.out.println("Your answer was...");
                        System.out.println("1. Correct");
                        System.out.println("2. Partially correct");
                        System.out.println("3. Wrong");
                        System.out.print("input: ");
                        answer = scanner.nextInt();
                    }

                    switch (answer) {
                        case 1:
                            card.setBox(Box.GREEN);
                            greenCards++;
                            break;
                        case 2:
                            card.setBox(Box.ORANGE);
                            break;
                        case 3:
                        default:
                            card.setBox(Box.RED);
                            goOn = true;
                            break;
                    }
                }
            }
        }
        if (cards.size() == greenCards) {
            System.out.println("\nJob completed. Congratulations!");
        } else {
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (Card card : cards) {
                    if (card.getBox() == Box.GREEN) {
                        // green -> orange
                        card.setBox(Box.ORANGE);
                        greenCards++;
                    } else if (card.getBox() == Box.ORANGE) {
                        // orange -> red
                        card.setBox(Box.RED);
                    }
                    fileWriter.write(card.toString());
                }
            }
            System.out.println("\nThe session is over. Goodbye!");
        }
    }
}
