Class diagram is one of the UML (Unified Modelling diagram) in software engineering.

In the class diagram the classes have various relationships with other/related or dependent classes to function properly

|Relationship| Type | Symbol | Usage  |
|-|-|-|-|
Generalization | Is-a(Inheritance) |![inheritance](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/08kwnnb9xjk0u45xpmld.png)| Car,Truck,Bike are Vehicle |
Realization | Implementation |![interface](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/vaddb247rmh512y8htym.png)|IPrinter is an interface which is implemented by HpPrinter and CanonPrinter (Overriding the methods of Iprinter interface)|
| Association|bi-directional (between parent and child)|![association](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/cihddilo8aurcr5u4ixn.png)|Library has many book and a Book belongs to a specific library ( here Library can be parent and Book can be child|
|Directed Association| One class  is associated with another |![dirAssociation](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/bhbrepbsbj9ed6zp9pkj.png)|Teacher class has an associated relation with the Class Course that Teacher teaches|
|Aggregation|Whole-part relationship|![aggregation](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/27hcic2yefmx952isri0.png)|It is specialized form of association, where one class(whole) is composed of another class(part), but the part class can exists independently e.g CompanyABC(Whole) composed of Empoyee(Part), but if The Company ceases to exist the Employee can still survive|
|Composition |Whole-part relationship|![composition](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/adtl691m7nnaukjb6emm.png)|It is more stronger form of aggregation, where part can not exist without the Whole e.g. DigitalContactPage(Whole) has Contact(Part), but if the DigitalContactPage is deled then the Contact will be lost forever|
|Dependency|client-supplier relationship|![dependency](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/xpfu4f0zjcduy7gnqwuv.png)|Temporary and weaker(relationship is not long-lived.); the client class depends on the supplier class but does not "own" it. e.g.Person class depends on Book class to read it, but the Book class is not dependent on Person class, and use of Book class is done as soon as the Person class is done using it|
|Usage(dependency)|client-supplier relationship|![usageDependency](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/a69mm5r83ojk60d3wdjf.png)|A usage dependency relationship in a UML class diagram indicates that one class (the client) utilizes or depends on another class (the supplier) to perform certain tasks or access certain functionality. e.g.Consider a scenario where a “Car” class depends on a “FuelTank” class to manage fuel consumption.|

_Note: while choosing relationship between classes there could be various ways to pick which relationship will best describe the relation, it can vary from context to context as and when you practice you will be able to get hold of these relationships_


![association and its typs](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/0cfkcw6x5no009w8lf2f.png)


**Class diagram of class Car**:

![car](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/7fcpnungm06d8r0d408h.png)
It is composed of class name, attributes, and methods

**Visible notations**:

|Symbol|Use|
|-|-|
|+| for public (visible to all classes)|
|- |for private (visible only within the class)|
|#| for protected (visible to subclasses)|
|~| for package or default visibility (visible to classes in the same package)|

