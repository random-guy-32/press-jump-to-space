package pressjumptospace.tile.barrier;

import pressjumptospace.tile.meta.OneWayBarrier;

public class DownOneWayBarrier extends OneWayBarrier {
    public DownOneWayBarrier() {
        super(new boolean[] {false, false, true, false}, "tile/one-way-barrier-down.png", "Down One-Way Barrier");
    }
}