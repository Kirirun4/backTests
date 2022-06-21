tests MiniMar.

№1 CreateProductTest
Positive
Create product by using POST request
response.isSuccessful(), CoreMatchers.is(true)
request is successful
Delete product by using DELETE request
response.isSuccessful(), CoreMatchers.is(true)
request is successful

№2 GetCategoryTest
Positive
Get product by ID of category by using GET request
response.isSuccessful(), CoreMatchers.is(true)
request is successful
response.body().getId(), equalTo(1)
true
response.body().getTitle(), equalTo("Food")
true
product.getCategoryTitle(), equalTo("Food")
true
Negative
Get product by ID of category by using GET request
response.code(), equalTo(404)
true
response.isSuccessful(), CoreMatchers.is(false)
true

№3 GetProductByIdTest
Positive
Get product by ID by using GET request
response.isSuccessful(), CoreMatchers.is(true)
request is successful
response.code(), equalTo(200)
true
assert response.body() != null
true
response.body().getId(), equalTo(2)
true
response.body().getTitle(), equalTo("Bread")
true
response.body().getPrice(), equalTo(25)
true
response.body().getCategoryTitle(), equalTo("Food")
true
Negative
Get product by ID by using GET request
response.isSuccessful(), CoreMatchers.is(false)
true
response.code(), equalTo(404)
true

№4 GetProductsTest
Positive
Get products by using GET request
response.isSuccessful(), CoreMatchers.is(true)
request is successful
response.code(), equalTo(200)
true

№5 ModifyProductTest
Get product by id by using GET request
response.isSuccessful(), CoreMatchers.is(true)
request is successful
assert response.body() != null
true
Get values from response
Modify values of product by using PUT request
setUp("Laptop", 45000, "Electronic")
response.isSuccessful(), CoreMatchers.is(true)
true
response.code(), equalTo(200)
true
assert response.body() != null
true
response.body().getTitle() != titleBeforeChanges, is (true)
true
response.body().getPrice() != priceBeforeChanges, is (true)
true
response.body().getCategoryTitle() != categoryBeforeChanges, is (true)
true

