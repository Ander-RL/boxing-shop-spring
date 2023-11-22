# Boxing Shop Backend App
## Connection to H2 DB
**Enter in browser:**
http://localhost:8080/react/h2-console

**JDBC URL:** jdbc:h2:mem:products
<br>_where products can be replaced with another table name_

**User Name:** ander<br>
**Password:** 12345<br>

_Check **application.properties** file for config data_


## Endpoints
### GET List<GetProductDto> getProducts()
http://localhost:8080/react/v1/products
### GET GetProductDto getProduct(@PathVariable Long productId)
http://localhost:8080/react/v1/products/{productId}
### POST GetSelectedProductsDto getSelectedProducts(List<String> selectedProducts)
http://localhost:8080/react/v1/products/selectedProducts

##Open API
http://localhost:8080/react/swagger-ui/index.html <br>
http://localhost:8080/react/v3/api-docs (json)
