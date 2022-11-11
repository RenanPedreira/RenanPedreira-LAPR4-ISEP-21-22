User Story 1001: Specify new Product
================================================

# 1.User story description #
* As Sales Clerk, I want to specify a new product


# 2.Actor #
* Sales Clerk.


# 3.Acceptance Criteria #
* No acceptance criterias were given on the user story's description.


# 4.Client clarifications #

Question 1: Regarding the barcode of a given product what should the coding standard be?

A1: Any of the supported standards. It might be set by configuration at system implantation time.

Question 2: Still regarding the barcode should the identifier be sequential, if not what method should we use to generate the identifier?

A2. Barcodes are manually introduced by users since each product barcode is provided by the product manufacture.

Question 3: It was mentioned in a previous question that both the production code and the internal code have a set regular expression, could an example of said regular expressions be given?

A3: For example, 4 letters followed by a dot (".") and ending with 5 digits.

Question 4:Can the regular expression be changed by an employee, if so which one?

A4: No! Such regular expressions are set by configuration at system implantation time.

Question 5:Can the Product be a part of a Super Category and a Category or will the Product be a part of a Category and that Category is a part of the Super Category? 

A5: "By simplicity, a category consists only of an alphanumeric code, and a description. Each product belongs mandatorily to a single category." As so, and contrary to what is suggested in your question, there is no hierarchy between categories.

Question 6:How many Categories should the product belong to?

A6: "Each product belong mandatorily to a single category." from the overview (2.1) of the project.

Question 7:In a product registration the attributes "photo", "internal code", "short description", "extended description", "technical description", "brand", "reference", "production code" are mandatory and how big they are?

A7: - photo: it might be of any common format (e.g. png, jpeg, svg);

    - short description: not empty having 30 chars maximum;

    - extended description: not empty with a minimum of 20 chars and 100 chars maximum;

    - technical description: multiple lines of text, preferably with no limit or the biggest possible.

    - brand: not empty having 50 chars maximum;

    - reference: not empty alphanumeric code with at 23 chars maximum;

    - production code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.

    - internal code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.

 
Mandatory attributes: internal code, short and extended description.
In accordance with the specification document, other atributes might also be mandatory.

Question 8:In document "brand name and reference." - Reference refers to the brand reference or product reference?

A8: The complete sentence is "Products basic information comprehends a unique internal code, a short and an extended description as well as a more technical description, a set of photos, a brand name and reference.".

The intent was to express that a product has:

    -a brand name (e.g.: "Samsung", "Nokia", "Bic");

    -the product reference set by the brand, which is an alphanumeric code (max. 23 chars).


# 5.Relevant business aspects
* Products can have three distinct descriptions: short, extended and technical.
* Each Product belongs to a single category.











