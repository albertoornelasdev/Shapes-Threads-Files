package services.menus;

import config.UnitSetting;
import abstracts.Menu;
import enums.TypesShapesEnum;
import services.calculates.CalculateService;
import services.files.GenerateFileService;;
import java.util.stream.Stream;
import static constants.MenuMessages.SELECT_OPTION;
import static constants.MenuMessages.SELECT_SHAPE;
import static constants.ShapeMessages.*;

public class ShapesMenu extends Menu {
    @Override
    public void run() throws InterruptedException {
        TypesShapesEnum typesShapesEnum = null;
        StringBuilder sbMenu = fillMenuText();
        CalculateService c = new CalculateService(new UnitSetting());
        String shapeData = "";
        boolean running = true;
        do {
            typesShapesEnum = (TypesShapesEnum) getOptionSelected(String.valueOf(sbMenu), typesShapesEnum);
            switch (typesShapesEnum) {
                case CIRCLE:
                    shapeData = c.calculate(typesShapesEnum, ASKING_RATIO);
                    GenerateFileService.generate(shapeData);
                    break;
                case SQUARE:
                    shapeData = c.calculate(typesShapesEnum, ASKING_SIDE);
                    GenerateFileService.generate(shapeData);
                    break;
                case RECTANGLE:
                    shapeData = c.calculate(typesShapesEnum, ASKING_HEIGHT, ASKING_WIDTH);
                    GenerateFileService.generate(shapeData);
                    break;
                case EQUILATERAL_TRIANGLE :
                    shapeData = c.calculate(typesShapesEnum, ASKING_SIDE_A);
                    GenerateFileService.generate(shapeData);
                    break;
                case ISOSCELES_TRIANGLE :
                    shapeData = c.calculate(typesShapesEnum, ASKING_SIDE_A, ASKING_SIDE_B);
                    GenerateFileService.generate(shapeData);
                    break;
                case BACK:
                    running = false;
                    break;
            }
        }while (running);
    }

    @Override
    public StringBuilder fillMenuText() {
        StringBuilder textMenu = new StringBuilder(SELECT_SHAPE);
        for(TypesShapesEnum t : TypesShapesEnum.values()) {
            textMenu.append(String.format(SELECT_OPTION, t.getOption(), t.getOptionName()));
        }
        return textMenu;
    }

    @Override
    public Enum getOptionName(int option) {
        return Stream.of(TypesShapesEnum.values())
            .filter(o -> o.getOption() == option).findFirst().orElse(null);
    }
}
