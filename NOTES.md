# Notes

## Design decisions

- Keeping Item as a checkout orientated representation of product for this exercise 
- Promotions would come from some sort of provider interface (that could be implemented by db repo, etc.), but just passing to basket for this
- SKU is common to product and weighed product. Should perhaps encapsulate this in product interface.
- Made the assumption that weighted products wouldn't have the buy x get x free promotion. If this isn't true the promotion could be wrongly triggered by splitting a weighted product in to separate scans. 

## Other thoughts

- Is  weighted item/product necessary? Could possibly have product with a variable unit (item/kg/L, etc). Although in that case you'd have to check for the item already being present at the point of scan/add to basket to update quantity and assume items are then unique in checkout. 