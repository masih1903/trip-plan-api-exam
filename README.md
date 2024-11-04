**3.3.2**

POST http://localhost:7070/api/trips

HTTP/1.1 201 Created
Date: Mon, 04 Nov 2024 11:56:20 GMT
Content-Type: application/json
Content-Length: 167

{
"id": 4,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Maldives",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY"
}
Response file saved.
> 2024-11-04T125620.201.json

Response code: 201 (Created); Time: 90ms (90 ms); Content length: 167 bytes (167 B)


GET http://localhost:7070/api/trips/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:56:46 GMT
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
> 2024-11-04T125646.200.json

Response code: 200 (OK); Time: 41ms (41 ms); Content length: 173 bytes (173 B)


GET http://localhost:7070/api/trips

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:57:04 GMT
Content-Type: application/json
Content-Length: 511

[
{
"id": 1,
"startTime": "2025-01-15T06:00:00",
"endTime": "2025-01-25T18:00:00",
"startPosition": "Himalayas",
"name": "Adventure Mountain Trek",
"price": 1500.0,
"category": "ADVENTURE"
},
{
"id": 3,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Maldives",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY"
},
{
"id": 4,
"startTime": "2024-07-10T15:00:00",
"endTime": "2024-07-20T10:00:00",
"startPosition": "Maldives",
"name": "Family Beach Vacation",
"price": 5000.0,
"category": "FAMILY"
}
]
Response file saved.
> 2024-11-04T125704.200.json

Response code: 200 (OK); Time: 161ms (161 ms); Content length: 511 bytes (511 B)


PUT http://localhost:7070/api/trips/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:57:40 GMT
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
> 2024-11-04T125740.200.json

Response code: 200 (OK); Time: 18ms (18 ms); Content length: 173 bytes (173 B)


DELETE http://localhost:7070/api/trips/3

HTTP/1.1 204 No Content
Date: Mon, 04 Nov 2024 11:58:03 GMT
Content-Type: text/plain

<Response body is empty>

Response code: 204 (No Content); Time: 26ms (26 ms); Content length: 0 bytes (0 B)

