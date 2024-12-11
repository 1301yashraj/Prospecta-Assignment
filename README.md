 
###### The **Store API** is a comprehensive RESTful service designed to manage product data efficiently. It provides endpoints for retrieving product information by category, adding new products with flexible category management options, and converting various file formats into CSV. This API is tailored for seamless integration into e-commerce or inventory management systems, offering high flexibility and robust error handling.
---

## API Documentation

### Endpoint 1: Get Products by Category

**Method**: `GET`

**URL**: `/products/category/{categoryName}`

**Description**: Retrieves a list of products based on the provided category name.

#### Request Parameters:

- **Path Parameter**: `categoryName` (String)
  - The name of the category for which the products are to be fetched.

#### Request Example:

```
GET /products/category/Automobile
```

#### Response:

- **Status Code**: `200 OK`
- **Response Body** (JSON):

```json
[
    {
        "productId": 52,
        "productName": "Lexus L3",
        "category": "Automobile",
        "price": 99999.99,
        "description": "Luxury Export to US - 01"
    },
    {
        "productId": 53,
        "productName": "Lexus L6",
        "category": "Automobile",
        "price": 79999.99,
        "description": "Economical Export to EU"
    }
]
```

#### Possible Errors:

- **`404 Not Found`**: If category is not present.
  - **Response Body**:
    ```json
    {
        "error": "Category not found with name: {categoryName}"
    }
    ```
- **`500 Bad Request`**: if some other error occurs , with the stack trace of what went wrong in the error message.

---

### Endpoint 2: Add a Product

**Method**: `POST`

**URL**: `/products`

**Description**: Adds a new product to the store. Supports adding the product to an existing category or creating a new category if specified.

#### Request Body:

- **Fields**:
  - `productName` (String) - Name of the product (required).
  - `category` (String) - Name of the category (required).
  - `price` (Number) - Price of the product (required).
  - `description` (String) - Description of the product (optional).
  - `createNewCategory` (Boolean) - Flag to specify if a new category should be created if it does not exist (required).

#### Request Examples:

**Example 1**: Add to an existing category

```
POST /products
```

**Request Body**:

```json
{
    "productName": "Lexus L8",
    "category": "Automobile",
    "price": 99999.99,
    "description": "Luxury Export to US - 01",
    "createNewCategory": true
}
```

**Response**:

- **Status Code**: `200 OK`
- **Response Body** (JSON):

```json
{
    "productId": 102,
    "productName": "Lexus L8",
    "category": "Automobile",
    "price": 99999.99,
    "description": "Luxury Export to US - 01"
}
```

**Example 2**: Attempt to add to a non-existent category with `createNewCategory` set to `false`

```
POST /products
```

**Request Body**:

```json
{
    "productName": "Lexus L8",
    "category": "Bike",
    "price": 99999.99,
    "description": "Luxury Export to US - 01",
    "createNewCategory": false
}
```

**Response**:

- **Status Code**: `404 Not Found`
- **Response Body** (JSON):

```json
{
    "statusCode": 404,
    "route": "/products",
    "id": null,
    "category": null,
    "message": "Category not found with name: Bike"
}
```

#### Possible Errors:

- **`404 Not Found`**: If the category does not exist and `createNewCategory` is `false`.

- **`500 Bad Request`**: If any required field is missing or invalid.
   ```JSON
{
    "statusCode": 500,
    "route": "/products",
    "id": null,
    "category": null,
    "message": "An unexpected error occurred: JSON parse error: Unexpected        character ('}' (code 125)): was expecting double-quote to start field name"
}
```

#### Notes:

- If `createNewCategory` is `true` and the category does not exist, a new category will be created.
- If the category exists, the product will be added to the existing category regardless of the `createNewCategory` flag.
- You can also ignore the `createNewCategory`  property in the request body. 

---

### Endpoint 3: CSV Formatter API

**Method**: `POST`

**URL**: `/products/csv`

**Description**: Converts an uploaded file into a CSV format file.

#### Request Body:

- **Fields**:
  - `file` (Binary) - The file to be converted into CSV format (required).

#### Request Example:

```
POST /products/csv
```

**Request**:

- **Headers**:
  - `Content-Type: multipart/form-data`

- **Body**:
  Upload the file to be converted.

#### Response:

- **Status Code**: `200 OK`
- **Response Body**: Returns a downloadable CSV file.

#### Possible Errors:

- **`400 Bad Request`**: If the input file format is invalid or the file is missing.

#### Notes:

- This endpoint accepts file uploads in formats like JSON, Excel, or plain text.
- The output CSV will be formatted with proper headers and row data extracted from the file.

---


