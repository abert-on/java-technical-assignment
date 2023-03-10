# Notes

Please add here any notes, assumptions and design decisions that might help us understand your thought process.

## Design decisions

- Keeping Item as a checkout orientated representation of product
- Promotions would come from some sort of provider interface (that could be implemented by db repo, etc.), but just passing to basket for now
- SKu is common to product and weighed product. Possibly need an interface to capture this. 