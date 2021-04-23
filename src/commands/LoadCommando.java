package commands;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import shapes.Ellipse;
import shapes.Rectangle;
import program.PainterProgram;
import shapes.Figure;

public class LoadCommando implements Command{

    private final String fileName = "resources/Fileio.txt";
    private PainterProgram painter;

    public LoadCommando(PainterProgram painter){
        this.painter = painter;
    }

    @Override
    public void execute(){
        try {
            if (!painter.getFigures().isEmpty()){
            painter.getFigures().clear();
            }
            int x,y,width,height;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferdReader = new BufferedReader(fileReader); 
            int chars;
            StringBuilder response= new StringBuilder();
            while ((chars = bufferdReader.read()) != -1) {
                response.append( (char)chars ) ; 
            }
            String result = response.toString();
            String[] parts = result.split("\n");
            for (int i = parts.length - 1; i >= 0; i--){
                String[] figure = parts[i].split(" ");
                String figureName = figure[0];
                if (figureName.equals("group")){
                    CreateGroupCommand createGroupCommand = new CreateGroupCommand(painter, new ArrayList<>(painter.getFigures()));
                    painter.executeCommand(createGroupCommand);
                }
                else if (figureName.equals("rectangle")){
                    x = Integer.parseInt(figure[1]);
                    y = Integer.parseInt(figure[2]);
                    width = Integer.parseInt(figure[3]);
                    height = Integer.parseInt(figure[4]);
                    painter.addFigure(new Rectangle(x, y , width, height));
                }
                else if (figureName.equals("ellipse")){
                    x = Integer.parseInt(figure[1]);
                    y = Integer.parseInt(figure[2]);
                    width = Integer.parseInt(figure[3]);
                    height = Integer.parseInt(figure[4]);
                    painter.addFigure(new Ellipse(x, y , width, height));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void undo(){

    }

}
