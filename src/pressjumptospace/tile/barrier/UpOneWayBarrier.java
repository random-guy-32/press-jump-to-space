package pressjumptospace.tile.barrier;

import pressjumptospace.tile.meta.OneWayBarrier;

public class UpOneWayBarrier extends OneWayBarrier {
    public UpOneWayBarrier() {
        super(new boolean[] {true, false, false, false}, "tile/one-way-barrier-up.png", "Up One-Way Barrier");
    }
}