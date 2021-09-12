# API

## 버스 정류장 키오스크 정보 가져오기
버스정류장에 비치된 키오스크 내용에 대한 정보를 가져오는 기능입니다.
GET 으로 요청하고, 응답은 JSON 객체로 받습니다.

❗️ 공공 데이터 포털 (https://www.data.go.kr/) 의 API를 활용하였습니다. 

### Request
- URL
  - GET localhost:8080/bus
- Parameter
  - id (정류소ID)

### Response
- Array
  - 각 요소
    - statusPos (남은 정거장 갯수: Long)
    - stopName (정류장 이름: String)
    - extimeMin (남은 시간[분] : Long)
    - routeNo (버스 번호: Long)
    - lastStopId (마지막으로 지나친 정류장 이름: String)
    - destination (종점 이름: String)

### Sample
- request : localhost:8080/bus?id=8001097
- response
```json
[
    {
        "statusPos": 7,
        "stopName": "복합터미널",
        "extimeMin": 10,
        "routeNo": 105,
        "lastStopId": "한남오거리",
        "destination": "비래삼호A"
    },
    {
        "statusPos": 6,
        "stopName": "복합터미널",
        "extimeMin": 7,
        "routeNo": 102,
        "lastStopId": "한남병원",
        "destination": "대전역"
    },
    {
        "statusPos": 5,
        "stopName": "복합터미널",
        "extimeMin": 7,
        "routeNo": 622,
        "lastStopId": "대전역",
        "destination": "비래동"
    },
    {
        "statusPos": 2,
        "stopName": "복합터미널",
        "extimeMin": 4,
        "routeNo": 607,
        "lastStopId": "홍도치안센터",
        "destination": "비래동종점"
    },
    {
        "statusPos": 6,
        "stopName": "복합터미널",
        "extimeMin": 9,
        "routeNo": 701,
        "lastStopId": "으능정이거리",
        "destination": "탑립동"
    },
    {
        "statusPos": 14,
        "stopName": "복합터미널",
        "extimeMin": 15,
        "routeNo": 802,
        "lastStopId": "원촌교네거리",
        "destination": "보문산공원"
    },
    {
        "statusPos": 14,
        "stopName": "복합터미널",
        "extimeMin": 17,
        "routeNo": 611,
        "lastStopId": "용운동주민센터",
        "destination": "신대차고지"
    },
    {
        "statusPos": 5,
        "stopName": "복합터미널",
        "extimeMin": 11,
        "routeNo": 106,
        "lastStopId": "남선공원네거리",
        "destination": "비래동"
    },
    {
        "statusPos": 5,
        "stopName": "복합터미널",
        "extimeMin": 8,
        "routeNo": 602,
        "lastStopId": "현암교",
        "destination": "비래동"
    },
    {
        "statusPos": 10,
        "stopName": "복합터미널",
        "extimeMin": 15,
        "routeNo": 501,
        "lastStopId": "효동네거리",
        "destination": "비래동"
    },
    {
        "statusPos": 5,
        "stopName": "복합터미널",
        "extimeMin": 6,
        "routeNo": 616,
        "lastStopId": "신안동",
        "destination": "대전역"
    },
    {
        "statusPos": 10,
        "stopName": "복합터미널",
        "extimeMin": 15,
        "routeNo": 601,
        "lastStopId": "가장교오거리",
        "destination": "비래동"
    },
    {
        "statusPos": 6,
        "stopName": "복합터미널",
        "extimeMin": 9,
        "routeNo": 201,
        "lastStopId": "목척교",
        "destination": "대전IC"
    },
    {
        "statusPos": 2,
        "stopName": "복합터미널",
        "extimeMin": 5,
        "routeNo": 2,
        "lastStopId": "대전역",
        "destination": "봉산동"
    }
]
```
    
