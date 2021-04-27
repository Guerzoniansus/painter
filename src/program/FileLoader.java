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
            String line = null;

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

        else {
            line = line.trim();

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
                    System.out.println("Check your save file, something is wrong on this line:");
                    System.out.println(line);
                    return null;
            }
        }
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

        String nextLine = null;

        while(i + 1 < lines.size()) {
            i++;
            nextLine = lines.get(i);

            // If the whitespaces don't match, then this figure doesn't belong to this group (but an inner gruop)
            if (startsWithExactWhiteSpace(whiteSpace, nextLine)) {
                figures.add(parseFigure(lines, i, whiteSpace));
            }

            else {
                // This prevents parsing figures that belong to a different group
                if (nextLine.contains("group")) {
                    break;
                }
            }
        }

        // Reset the white spaces
        whiteSpace = whiteSpace.substring(0, whiteSpace.length() - 2);

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
