package pressjumptospace.tile.meta;

import pressjumptospace.tile.meta.material.AcidMaterial;
import pressjumptospace.tile.meta.material.MetalMaterial;
import pressjumptospace.tile.meta.material.GooMaterial;

public abstract class Material {
    public Material() {

    }

    public static Material Metal = new MetalMaterial();
    public static Material Goo = new GooMaterial();
    public static Material Acid = new AcidMaterial();
}