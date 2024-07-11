Creational
    Factory 
        Imagine you want to get a burger but you don't want to worring about getting all the ingredients and putting them together
        If it takes list of ingredients to create a burger, we can use factory that will instantiate the burger for us and return it to us weather it is cheese burger or delux cheese burger or vegan burger, all we have to do is tell the factory what type of burger we want.
        Disadvantage: you will never know or choose the ingredients used to make these burgers

        Alternatively, if you want little control over how burger is made you can go with builder pattern.
    Builder:
        The idea is if you want to make a burger you don't immediately have to pass all the ingredients we can use BurgerBuilder instead.
        We can create individual methods for adding buns, patty, sauce, lettuce,etc,each one will return a reference to the builder and finally we will have a build method that will return the final product then we can instatiate the actual BurgerBuilder adds the buns we want, lettuce we want, cheese we want,etc. And then finally call the build method to return the object of the Burger. **It will give us the exact buger we want**


Behavioral
    Observer:
        How to insure that all the components have the single source of truth answer is Oberserver/pubSub Pattern
        
        

Structural
