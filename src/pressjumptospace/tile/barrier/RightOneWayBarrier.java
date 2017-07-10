package pressjumptospace.tile.barrier;

import pressjumptospace.tile.meta.OneWayBarrier;

public class RightOneWayBarrier extends OneWayBarrier {
    public RightOneWayBarrier() {
        super(new boolean[] {false, true, false, false}, "tile/one-way-barrier-right.png", "Right One-Way Barrier");
    }
}