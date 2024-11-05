**3.3.2**

_Output for all of my TripRoute endpoints:_

### Create a Trip
POST http://localhost:7070/api/trips

HTTP/1.1 201 Created
Date: Mon, 04 Nov 2024 19:10:00 GMT
Content-Type: application/json
Content-Length: 184

{
"id": 1,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Malta",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY",
"packingItems": null
}
Response file saved.
> 2024-11-04T201000.201.json

Response code: 201 (Created); Time: 65ms (65 ms); Content length: 184 bytes (184 B)

### Get a Trip by ID
GET http://localhost:7070/api/trips/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:11:40 GMT
Content-Type: application/json
Content-Encoding: gzip
Content-Length: 611

{
"id": 1,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Malta",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY",
"packingItems": [
{
"name": "Beach Umbrella",
"weightInGrams": 1200,
"quantity": 1,
"description": "Sunshade umbrella for beach outings.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Sunny Store",
"shopUrl": "https://shop3.com",
"price": 50.0
},
{
"shopName": "Beach Essentials",
"shopUrl": "https://shop4.com",
"price": 55.0
}
]
},
{
"name": "Beach Water Bottle",
"weightInGrams": 500,
"quantity": 1,
"description": "High-capacity water bottle for hot climates.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Hydration Depot",
"shopUrl": "https://shop6.com",
"price": 25.0
}
]
},
{
"name": "Beach Cooler",
"weightInGrams": 3000,
"quantity": 1,
"description": "Insulated cooler to keep beverages cold at the beach.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Beach Supplies",
"shopUrl": "https://shop21.com",
"price": 70.0
}
]
},
{
"name": "Beach Towel",
"weightInGrams": 300,
"quantity": 1,
"description": "Large, quick-drying beach towel.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Beach Essentials",
"shopUrl": "https://shop1.com",
"price": 15.0
}
]
},
{
"name": "Beach Ball",
"weightInGrams": 100,
"quantity": 1,
"description": "Inflatable beach ball for games.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Beach Fun Shop",
"shopUrl": "https://shop2.com",
"price": 5.0
}
]
},
{
"name": "Sunscreen SPF 50",
"weightInGrams": 200,
"quantity": 1,
"description": "High-SPF sunscreen for beach days.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Sunny Shop",
"shopUrl": "https://shop3.com",
"price": 10.0
}
]
},
{
"name": "Beach Chair",
"weightInGrams": 2000,
"quantity": 1,
"description": "Foldable, lightweight beach chair.",
"category": "beach",
"createdAt": "2024-10-30T17:44:58.547Z",
"updatedAt": "2024-10-30T17:44:58.547Z",
"buyingOptions": [
{
"shopName": "Beach Supplies",
"shopUrl": "https://shop4.com",
"price": 25.0
}
]
}
]
}
Response file saved.
> 2024-11-04T201141.200.json

Response code: 200 (OK); Time: 447ms (447 ms); Content length: 2362 bytes (2,36 kB)

### Get All Trips
GET http://localhost:7070/api/trips

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:12:33 GMT
Content-Type: application/json
Content-Length: 751

[
{
"id": 1,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Malta",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY",
"packingItems": null
},
{
"id": 2,
"startTime": "2024-12-01T09:00:00",
"endTime": "2024-12-01T17:00:00",
"startPosition": "Paris",
"name": "Cultural City Tour",
"price": 300.0,
"category": "CULTURAL",
"packingItems": null
},
{
"id": 3,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Maldives",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY",
"packingItems": null
},
{
"id": 4,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
}
]
Response file saved.
> 2024-11-04T201233.200.json

Response code: 200 (OK); Time: 172ms (172 ms); Content length: 751 bytes (751 B)

### Update a Trip
PUT http://localhost:7070/api/trips/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:13:56 GMT
Content-Type: application/json
Content-Length: 173

{
"id": 1,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE"
}
Response file saved.
> 2024-11-04T201356.200.json

Response code: 200 (OK); Time: 33ms (33 ms); Content length: 173 bytes (173 B)

### Delete a Trip
DELETE http://localhost:7070/api/trips/3

HTTP/1.1 204 No Content
Date: Mon, 04 Nov 2024 19:14:38 GMT
Content-Type: text/plain

<Response body is empty>

Response code: 204 (No Content); Time: 32ms (32 ms); Content length: 0 bytes (0 B)

### Add a Guide to a Trip
PUT http://localhost:7070/api/trips/2/guides/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:15:45 GMT
Content-Type: text/plain
Content-Length: 39

Guide with ID 1 added to Trip with ID 2

Response code: 200 (OK); Time: 18ms (18 ms); Content length: 39 bytes (39 B)

### Get Trips by Guide
GET http://localhost:7070/api/trips/guides/1/trips

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:16:33 GMT
Content-Type: application/json
Content-Length: 378

[
{
"id": 2,
"startTime": "2024-12-01T09:00:00",
"endTime": "2024-12-01T17:00:00",
"startPosition": "Paris",
"name": "Cultural City Tour",
"price": 300.0,
"category": "CULTURAL",
"packingItems": null
},
{
"id": 4,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
}
]
Response file saved.
> 2024-11-04T201633.200.json

Response code: 200 (OK); Time: 109ms (109 ms); Content length: 378 bytes (378 B)

### Get Trips by Category
GET http://localhost:7070/api/trips/categories/ADVENTURE

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:17:43 GMT
Content-Type: application/json
Content-Length: 389

[
{
"id": 4,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
},
{
"id": 1,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
}
]
Response file saved.
> 2024-11-04T201743.200.json

Response code: 200 (OK); Time: 14ms (14 ms); Content length: 389 bytes (389 B)

### Populate the Database with Sample Data
POST http://localhost:7070/api/trips/populate

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:18:17 GMT
Content-Type: text/plain
Content-Length: 48

Database populated with sample trips and guides.

Response code: 200 (OK); Time: 26ms (26 ms); Content length: 48 bytes (48 B)

### Get Packing Items total weight
GET http://localhost:7070/api/trips/1/packing-items/total-weight

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:18:53 GMT
Content-Type: application/json
Content-Length: 4

4250
Response file saved.
> 2024-11-04T201853.200.json

Response code: 200 (OK); Time: 137ms (137 ms); Content length: 4 bytes (4 B)

_Output for all of my GuideRoute endpoints:_

### Create a Guide
POST http://localhost:7070/api/guides

HTTP/1.1 201 Created
Date: Mon, 04 Nov 2024 19:24:04 GMT
Content-Type: application/json
Content-Length: 148

{
"id": 7,
"firstName": "Thomas",
"lastName": "Targaryen",
"email": "Thomas.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 25,
"totalPrice": 0.0
}
Response file saved.
> 2024-11-04T202404.201.json

Response code: 201 (Created); Time: 238ms (238 ms); Content length: 148 bytes (148 B)

### Get all Guides
GET http://localhost:7070/api/guides

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:24:33 GMT
Content-Type: application/json
Content-Length: 1011

[
{
"id": 1,
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 10,
"totalPrice": 1800.0
},
{
"id": 2,
"firstName": "Jane",
"lastName": "Smith",
"email": "jane.smith@example.com",
"phone": "987-654-3210",
"yearsOfExperience": 15,
"totalPrice": 0.0
},
{
"id": 3,
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 10,
"totalPrice": 1800.0
},
{
"id": 4,
"firstName": "Jane",
"lastName": "Smith",
"email": "jane.smith@example.com",
"phone": "987-654-3210",
"yearsOfExperience": 15,
"totalPrice": 5000.0
},
{
"id": 5,
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 10,
"totalPrice": 1800.0
},
{
"id": 6,
"firstName": "Jane",
"lastName": "Smith",
"email": "jane.smith@example.com",
"phone": "987-654-3210",
"yearsOfExperience": 15,
"totalPrice": 5000.0
},
{
"id": 7,
"firstName": "Thomas",
"lastName": "Targaryen",
"email": "Thomas.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 25,
"totalPrice": 0.0
}
]
Response file saved.
> 2024-11-04T202433.200.json

Response code: 200 (OK); Time: 252ms (252 ms); Content length: 1011 bytes (1,01 kB)

### Get Guide Trip overview - Remember to remove @JsonIgnore from GuideDTO to see the trips
GET http://localhost:7070/api/guides/overview

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:28:11 GMT
Content-Type: application/json
Content-Encoding: gzip
Content-Length: 498

[
{
"id": 1,
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 10,
"trips": [
{
"id": 2,
"startTime": "2024-12-01T09:00:00",
"endTime": "2024-12-01T17:00:00",
"startPosition": "Paris",
"name": "Cultural City Tour",
"price": 300.0,
"category": "CULTURAL",
"packingItems": null
},
{
"id": 4,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
}
],
"totalPrice": 1800.0
},
{
"id": 2,
"firstName": "Jane",
"lastName": "Smith",
"email": "jane.smith@example.com",
"phone": "987-654-3210",
"yearsOfExperience": 15,
"trips": [],
"totalPrice": 0.0
},
{
"id": 3,
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 10,
"trips": [
{
"id": 5,
"startTime": "2024-12-01T09:00:00",
"endTime": "2024-12-01T17:00:00",
"startPosition": "Paris",
"name": "Cultural City Tour",
"price": 300.0,
"category": "CULTURAL",
"packingItems": null
},
{
"id": 7,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
}
],
"totalPrice": 1800.0
},
{
"id": 4,
"firstName": "Jane",
"lastName": "Smith",
"email": "jane.smith@example.com",
"phone": "987-654-3210",
"yearsOfExperience": 15,
"trips": [
{
"id": 6,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Maldives",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY",
"packingItems": null
}
],
"totalPrice": 5000.0
},
{
"id": 5,
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 10,
"trips": [
{
"id": 8,
"startTime": "2024-12-01T09:00:00",
"endTime": "2024-12-01T17:00:00",
"startPosition": "Paris",
"name": "Cultural City Tour",
"price": 300.0,
"category": "CULTURAL",
"packingItems": null
},
{
"id": 10,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE",
"packingItems": null
}
],
"totalPrice": 1800.0
},
{
"id": 6,
"firstName": "Jane",
"lastName": "Smith",
"email": "jane.smith@example.com",
"phone": "987-654-3210",
"yearsOfExperience": 15,
"trips": [
{
"id": 9,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Maldives",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY",
"packingItems": null
}
],
"totalPrice": 5000.0
},
{
"id": 7,
"firstName": "Thomas",
"lastName": "Targaryen",
"email": "Thomas.doe@example.com",
"phone": "123-456-7890",
"yearsOfExperience": 25,
"trips": [],
"totalPrice": 0.0
}
]
Response file saved.
> 2024-11-04T202811.200.json

Response code: 200 (OK); Time: 373ms (373 ms); Content length: 2591 bytes (2,59 kB)

### Delete a Guide
DELETE http://localhost:7070/api/guides/1

HTTP/1.1 204 No Content
Date: Mon, 04 Nov 2024 19:30:25 GMT
Content-Type: text/plain

<Response body is empty>

Response code: 204 (No Content); Time: 84ms (84 ms); Content length: 0 bytes (0 B)

### Get Guides ID and Total Price
GET http://localhost:7070/api/guides/totalprice

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 19:32:10 GMT
Content-Type: application/json
Content-Length: 199

[
{
"guideId": 2,
"totalPrice": 0.0
},
{
"guideId": 3,
"totalPrice": 1800.0
},
{
"guideId": 4,
"totalPrice": 5000.0
},
{
"guideId": 5,
"totalPrice": 1800.0
},
{
"guideId": 6,
"totalPrice": 5000.0
},
{
"guideId": 7,
"totalPrice": 0.0
}
]
Response file saved.
> 2024-11-04T203210.200.json

Response code: 200 (OK); Time: 410ms (410 ms); Content length: 199 bytes (199 B)

**3.3.5**

3.3.5 Why Use PUT Instead of POST for Adding a Guide to a Trip?
When we add a guide to a trip, we are updating an existing resource (the trip) by adding a relationship or attribute 
(the guide) rather than creating a new resource from scratch. Here’s why we prefer PUT over POST for this action:

Idempotence: The PUT method is idempotent, meaning that calling it multiple times with the same data will have the 
same effect as calling it once. If we repeatedly assign the same guide to a trip, the outcome doesn’t change; 
the guide is simply associated with that trip. This behavior aligns well with the idea of adding or 
updating relationships within a resource.

Resource Modification: PUT is conventionally used to modify or replace an existing resource or parts of it, 
such as adding a guide to an already existing trip. POST, on the other hand, is generally used for creating 
new resources. Since the trip resource already exists, adding a guide is an update operation rather than a 
creation of a new resource.

Clear Semantics: Using PUT for associating resources makes the API's intent clearer. When clients see a PUT request, 
they understand it as an update to an existing resource, whereas POST would imply creating something new. 
This distinction can help maintain intuitive API design and improve code readability for future developers.

