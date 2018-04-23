package pressjumptospace.menu;

import java.util.ArrayList;
import java.util.List;

public class GraphicalInterfaceContainer extends GraphicalInterfaceComponent {
    public GraphicalInterfaceContainer(int x_, int y_, int w, int h) {
        super(x_, y_, w, h);

        components = new ArrayList<>();
    }

    private List<GraphicalInterfaceComponent> components;


}
