Builder: This interface defines all the steps required to build a product.
Concrete Builder: Implements the Builder interface to construct and assemble parts of the product.
Director: Oversees the construction process and ensures that all the steps are followed in order.
Product: The complex object that is being built.

```console
House [foundation=Good quality foundation set, walls=new wall has been added, windows=new windows has been installed, hasPool=false, hasGarage=false]
House [foundation=Good quality luxury foundation set, walls=new wall of has been added that can handle nuclear explosion, windows=new windows has been installed that are bullet proof, hasPool=true, hasGarage=true]
House [foundation=Good quality foundation set, walls=new wall has been added, windows=new windows has been installed, hasPool=false, hasGarage=false]
House [foundation=Good quality luxury foundation set, walls=new wall of has been added that can handle nuclear explosion, windows=new windows has been installed that are bullet proof, hasPool=true, hasGarage=true]
```