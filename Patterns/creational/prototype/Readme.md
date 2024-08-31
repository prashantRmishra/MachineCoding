It is one of the creational design pattern.
Used to create duplicate/shallow copy of the given object.
This pattern is useful when creation of objects directly is costly example: If an object is created after querying a large database, then creating the object again and again is not economical in terms of performance.
Hence once the object is created we cache the object and upon need of the same object in future we get it from cache instead of creating it again from the database and updated the database as and when needed to reduce the database calls


note: `Cloneable` is a marker interface, it does not contain any methods, it signals that a class can be cloned, which means creating a copy of an object.

*`Object.clone()` method*
creates shallow Copy by Default
By default, the `clone()` method performs a shallow copy of the object. This means that it creates a new object and copies all the fields from the original object to the new object. However, if the object contains references to other objects (e.g., arrays, lists, or custom objects), the references themselves are copied, not the actual objects they point to.
As a result, both the original and the cloned object will reference the same objects for those fields. Any changes made to the referenced objects through one instance will reflect in the other.

/**
         * both Circle and Rectangle are following Liskov Substitution principle(SOLID principle) which states that
         * Object should be replaceable with their subtypes without affecting the correctness of the code
         * 
        */

```console
Shape [id=1, shape=Circle]
Shape [id=2, shape=Rectangle]
```        