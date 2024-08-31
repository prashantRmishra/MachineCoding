# Some of the widely used design patters 

[Reference](https://www.youtube.com/watch?v=tAuRQs_d9F8&t=273s)

## Creational
**Factory** 
    Imagine you want to get a burger but you don't want to worring about getting all the ingredients and putting them together
    If it takes list of ingredients to create a burger, we can use factory that will instantiate the burger for us and return it to us weather it is cheese burger or delux cheese burger or vegan burger, all we have to do is tell the factory what type of burger we want.
    Disadvantage: you will never know or choose the ingredients used to make these burgers
    Alternatively, if you want little control over how burger is made you can go with builder pattern.

**Builder**
    The idea is if you want to make a burger you don't immediately have to pass all the ingredients we can use BurgerBuilder instead.
    We can create individual methods for adding buns, patty, sauce, lettuce,etc,each one will return a reference to the builder and finally we will have a build method that will return the final product then we can instatiate the actual BurgerBuilder adds the buns we want, lettuce we want, cheese we want,etc. And then finally call the build method to return the object of the Burger. **It will give us the exact buger we want**

## Behavioral
**Oberserver or pubsub**
    Take example of youtube, there is a youtube channel(owned by a youtuber) and there are subscriber of this youtube channel.
    When ever the youtube channel posts new video a notification is sent to the subscribers.

**Iterator**
    Defines how values in an object can be iterated through

```
    for(int i : list){
        System.out.println(i) ; // where list is ArrayList<Integer> list  = Ararys.toList(arr);//arr[] = {1,2,3,5};
    }

```

Similary we can create our own Iterator to iterate through custom objects like linked list

**Strategy**
To modify or extend the Behavioral of a class without changing it we can go for Strategy pattern.
It follows open-closed design principle(one of the *solid* principles)
Open for extension but closed for modification
For more understanding [refer]([url](https://dev.to/prashantrmishra/solid-principles-in-java-35lf))

## Structural

**Adapter**
If you have usb cable and usb port then you can easily plug usb cable into usb port
But if you have micro usb cable then you will need micro usb to usb adapter to plug micro usb cable into usb port
For detailed understanding go through Adapter folder 

**Facade**
A outword appearance that is used to conceal a less plesant or credible reality

<a href=""><image src= "../Patterns/Structural/Facade/facade.png" /></a>

Example: Dynamic array like ArrayList in java is an example of this, because ArrayLists are constantly being resized under the hood when the defined capacity is reached.

