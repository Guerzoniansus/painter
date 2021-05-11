package program;

import shapes.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileLoader {

    private FileLoader() {}

    public static List<Figure> loadFigures(String fileName) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Figure> figures = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        try {
            String line;

            // Add all lines
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Parse the root group
            figures.add(parseGroup(lines, 0, ""));
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return figures;
    }


    /**
     * Parse a figure
     * @param lines The list of lines to parse
     * @param i The index of the line to parse
     * @param whiteSpace A string with the amount of starting whitespaces before this figure
     * @return A new figure from the specified line
     */
    private static Figure parseFigure(List<String> lines, int i, String whiteSpace) {
        String line = lines.get(i);

        if (line.contains("group")) {
            return parseGroup(lines, i, whiteSpace);
        }

        else if (line.contains("ornament")) {
            return parseOrnament(lines, i, whiteSpace);
        }

        else {
            return parseShape(lines, i, whiteSpace);
        }
    }

    /**
     * Parse a Shape
     * @param lines The list of lines to parse
     * @param i The index of the line to parse
     * @param whiteSpace A string with the amount of starting whitespaces before this figure
     * @return A new figure from the specified line
     */
    private static Shape parseShape(List<String> lines, int i, String whiteSpace) {
        String line = lines.get(i).trim();

        String[] parts = line.split(" ");

        String name = parts[0];
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int width = Integer.parseInt(parts[3]);
        int height = Integer.parseInt(parts[4]);

        switch (name) {
            case "rectangle":
                return new Rectangle(x, y, width, height);
            case "ellipse":
                return new Ellipse(x, y, width, height);
            default:
                System.out.println("Check your save file, something is wrong on this line " + i + ":");
                System.out.println(line);
                return null;
        }
    }

    /**
     * Parse an ornament
     * @param lines The list of parsable lines
     * @param i The index of the line to parse
     * @param whiteSpace A string with the amount of starting whitespaces before this figure
     * @return A new Ornament from the specified line
     */
    private static Ornament parseOrnament(List<String> lines, int i, String whiteSpace) {
        String line = lines.get(i).trim();

        String position = line.split(" ")[1];

        StringBuilder sb = new StringBuilder();

        // Load the ornament text
        {
            boolean seenFirstQuote = false; // The opening quote of the ornament text
            char[] characters = line.toCharArray();

            // characters.length - 1 because the last character is the closing quote "
            for (int charCounter = 0; charCounter < characters.length - 1; charCounter++) {
                char character = characters[charCounter];

                if (seenFirstQuote) {
                    sb.append(character);
                }

                else {
                    if (character == '"')
                        seenFirstQuote = true;
                }
            }
        }

        String text = sb.toString();

        // Under the ornament is the decoratedFigure that belongs to it
        Figure decoratedFigure = parseFigure(lines, i + 1, whiteSpace);

        return new Ornament(text, position, decoratedFigure);
    }

    /**
     * Parse a group
     * @param lines The list of lines to parse
     * @param i The index of the line where this group is defined
     * @param whiteSpace The whitespaces at the start of this group's line
     * @return A new Group object
     */
    private static Group parseGroup(List<String> lines, int i, String whiteSpace) {
        List<Figure> figures = new ArrayList<>();

        // Add two white spaces for the figures that belong to this group
        whiteSpace += "  ";

        while(i + 1 < lines.size()) {
            i++;
            String nextLine = lines.get(i);

            // If the whitespaces don't match, then this figure doesn't belong to this group (but an inner group)
            if (startsWithExactWhiteSpace(whiteSpace, nextLine)) {

                // i - 1 >= 0 to prevent index out of bounds
                // After that, checking if previous line was "ornament", and then skipping a line
                // because ornament already parses the figure underneath it
                if (i - 1 >= 0 && lines.get(i - 1).contains("ornament") == true)
                    continue;

                else {
                    figures.add(parseFigure(lines, i, whiteSpace));
                }
            }

        }

        return new Group(figures);
    }

    /**
     * Check if a given string starts with a specific amount of white spaces
     * @param whiteSpace A string of white spaces to check for
     * @param line The line to check
     * @return True if the line STARTS with EXACTLY the same amount of white spaces, otherwise false
     */
    private static boolean startsWithExactWhiteSpace(String whiteSpace, String line) {
        Pattern pattern = Pattern.compile("^" + whiteSpace + "[^ ]");
        return pattern.matcher(line).find();
    }

}
