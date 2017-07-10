package pressjumptospace.tile.barrier;

import pressjumptospace.tile.meta.OneWayBarrier;

public class LeftOneWayBarrier extends OneWayBarrier {
    public LeftOneWayBarrier() {
        super(new boolean[] {false, false, false, true}, "tile/one-way-barrier-left.png", "Left One-Way Barrier");
    }
}
