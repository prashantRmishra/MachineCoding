Give a shopping cart with products and coupons, calculate the net price of the products after applying the coupons.

Coupons can be of different types with discounts like
```
x% off e.g 10% off for all the individuals
p% off on next item
d% off on nth item of type T
Sequently apply all the coupons and get the net price of the in the cart.
```

This can be done using decorator pattern
We will have list of products with us
We will create various decorators of the products like PercentageCouponDecorator, TypeCouponDecorator etc then we will decorate the products with these decorator which will updated the price of individual products
Finally we can get the total price of the decorated products and that will the total effective price after applying all the coupons on the products present in the shopping cart.
