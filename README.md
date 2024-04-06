
# Create user information and get user information by user id

This project uses get, post methods to create user information and get user information by user id. It also includes test cases used to compare the actual and expected response of the API.

## Tech Stack

**Tools:** Selenium

**Programming:** Java

**Libraries:** Selenium(version 3.141.59), TestNG(version 7.8.0), rest-assured(version 4.4.0), hamcrest(version 2.2), jackson-databind(version 2.9.8),Chrome driver(version 123.0.6312.86)

**Frameworks:** TestNG


## API Reference

#### Post creat a new user

```http
  POST https://reqres.in/api/users
```
#### Body

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required**: user's name |
| `gender` | `string` | **Required**: user's gender |
| `age` | `int` | **Required**: user's age |
| `job` | `string` | **Required**: user's job |

#### Expected output
- Returns results in JSON format, including complete information about the fields just created. There is also the user's id and when the user was created.

#### Conditions for passing test cases
- The returned result must have HTTP Status Code = 201 or 400 if name or gender is null or empty.
- The returned result must contain the user's id field and the createAt field.
- The returned results must have all created fields and field information must match.
- In case a 404 is received, the response must have a message field, and this field must contain the field name missing from the request.

#### Get user information

```http
  GET https://reqres.in/api/users/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `int` | **Required**: user id |

#### Expected output
Returns results in JSON format, including complete information about the user's fields as follows:

{

    "data": {
        "id",
        "email": 
        "first_name": ,
        "last_name": ,
        "avatar": 
    },
    "support": {
        "url"
        "text"
    }
}

#### Given data:
- Valid userId : 2
- Invalid userId: 40

#### Conditions for passing test cases

- The returned result must have HTTP Status Code = 200 or 404 if userId does not exist.
- The returned results must have full user information as described above (id, email, first_name, last_name, avatar)
- The id field returned must match the search user id.
- In case 404 is received, the response must include message = “User not found!” field.


## Solution
● Creates a User object containing fields: name, gender, age, job.

● Create test cases for valid body.

● Create test cases for invalid body.

● Create a Test Suite, name it and list the test cases of the java class

● Run test suite.

## Used By

This project is used by the following companies:

- FUNIX


