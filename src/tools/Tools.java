package tools;

import program.PainterProgram;

public class Tools {

    /**
     * Static (constant) tool objects, there's no need to constantly make new objects
     */
    public static Tool TOOL_SELECT;
    public static Tool TOOL_RECTANGLE;
    public static Tool TOOL_ELLIPSE;
    public static Tool TOOL_ORNAMENT;

    /**
     * Create all tools
     * @param painter The painter program
     */
    public static void createTools(PainterProgram painter) {
        TOOL_SELECT = new ToolSelect(painter);
        TOOL_RECTANGLE = new ToolRectangle(painter);
        TOOL_ELLIPSE = new ToolEllipse(painter);
        TOOL_ORNAMENT = new ToolOrnament(painter);
    }
}
