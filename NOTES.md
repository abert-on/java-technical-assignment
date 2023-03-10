# Notes

## Design decisions

- Keeping Item as a checkout orientated representation of product for this exercise 
- Promotions would come from some sort of provider interface (that could be implemented by db repo, etc.), but just passing to basket for this
- SKU as a product identifier is common to product and weighed product. Should perhaps encapsulate this in product interface.
- Made the assumption that weighed products wouldn't have the buy x get x free promotion. Could be wrongly triggered by splitting a weighted product in to separate add calls. 

## Other thoughts

- Could possibly have single product class with a variable unit (item/kg/L, etc). Would change the idea of the basket (a set of items with quantities rather than a list) and promotions (need to check which units apply). 
- Use iterator for increasing the amount of items in promotion tests and reduce code duplication