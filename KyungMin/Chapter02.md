### 1. 핵심 키워드 정리

## RESTful API

API는 여러 가지가 있지만 일단 대표적으로 2가지가 있다.

### REST API

REST API는 `REpresentational State Transfer Application Programming Interface`의 약자이다.

웹, 즉 HTTP의 기존 장점과 규칙을 최대한 활용해서 API를 자원 중심으로 깔끔하게 디자인하는 방법론이라고 한다.

### Open API

Open API는 누구나 접근해서 사용할 수 있도록 공공기관이나 기업에서 무료 또는 조건부로 공개한 API이다.

### RESTful API

RESTful API는 REST API를 잘 지킨 API라고 생각하면 된다.

---

### 자원

모든 자원은 고유한 ID를 가지는 URL로 표현된다.

쉽게 말해서 명사들이라고 생각하면 된다.

예시:

- `user`
- `members`

---

### 행위 Verb, HTTP Method

자원에 대한 액션은 HTTP 표준 메서드를 그대로 사용한다.

CRUD는 다음과 같이 대응된다고 생각하면 된다.

| CRUD | 의미 | HTTP Method |
|---|---|---|
| Create | 생성 | POST |
| Read | 조회 | GET |
| Update | 수정 | PUT / PATCH |
| Delete | 삭제 | DELETE |

---

### 표현 Representation

클라이언트가 자원을 요청하면 서버는 그에 맞는 형태로 보내준다.

과거에는 XML도 많이 쓰였지만, 현재는 대부분 JSON 형식을 사용한다.

---

## URL 만드는 규칙

- 마지막에 `/`를 포함하지 않는다.
- `_` underbar 대신 `-` dash를 사용한다.
- `-`도 최소한으로 사용한다.
- 소문자를 사용한다.
- Method는 URL에 포함하지 않는다.
    - `GET`, `POST` 등의 방법을 사용해서 간접적으로 표현해야 한다.
    - 직접적인 method를 사용해서는 안 된다.
- 컨트롤 자원을 의미하는 URL에는 예외적으로 동사를 허용한다.
    - 대신 동사는 맨 마지막에 사용한다.

---

## 멱등성이란

멱등성이란 연산을 여러 번 적용하더라도 결과가 달라지지 않고 똑같은 성질을 의미한다.

### 멱등한 메서드

`GET`, `PUT`, `DELETE`는 멱등하다.

### GET

데이터를 조회만 하기 때문에 서버 데이터는 변하지 않고 똑같은 상태를 유지한다.

### PUT

데이터를 수정하는 메서드이다.

예를 들어 이름을 `홍길동`으로 수정해달라는 요청은 몇 번을 보내도 데이터는 `홍길동`으로 유지된다.

### DELETE

한 번 삭제 요청하면 그 뒤로 계속 삭제 요청을 보내도 404 에러를 받을지언정 서버에는 게시글이 지워진 것은 변함이 없다.

---

### 멱등하지 않은 메서드

`POST`, `PATCH`는 멱등하지 않다.

### POST

자원을 새로 생성하는 메서드이기 때문에 100번 요청하면 100개의 데이터가 새로 생성되어 멱등하지 않다.

여기서 `PUT`을 `POST`처럼 쓸 수 있는데, 이러한 경우는 멱등하지 않을 수 있다.

- `PUT`을 기존 데이터를 덮어쓰는 것이 아니라 요청이 올 때마다 데이터를 누적하거나 추가하도록 하면 멱등성이 깨진다.

---

## 비정상적인 방법으로 API 서버를 이용하려는 경우

비정상적인 방법으로 API 서버를 이용하려는 경우, `429 Too Many Requests` 오류 응답과 함께 일정 시간 뒤에 요청할 것을 나타낸다.

비정상적인 방법에는 다음과 같은 것이 있다.

- DoS
- Brute-force attack

### DoS

우리가 아는 공격이다.

가짜 요청을 초당 수만 개 보내 서버를 마비시키는 공격이다.

### Brute-force attack

비밀번호를 알아내려고 로그인을 수천 번 연속으로 시도하는 공격이다.

이런 공격을 감지하면 429 오류를 내보낸다.

이때 함께 `Retry-After`를 보내 언제 다시 요청 가능한지 알려준다.

---

## `/longin`과 `/auth`

### `/longin`

전통적인 내 사이트 로그인 방식이다.

아이디, 비밀번호 기반 로그인이다.

- 사용자가 직접 화면에 아이디와 비밀번호를 타이핑해서 서버로 전송한다.
- 서버는 DB에서 해당 아이디를 찾고 비밀번호가 일치하는지 대조 후 맞으면 통과시킨다.
- 특징으로는 주소가 매우 직관적이다.
- `POST /login` 또는 `POST /auth/login`처럼 설계된다.

---

### `/auth`

더 안전하고 고도화된 현대적 인증 방식이다.

토큰, 소셜 로그인 등을 처리할 때 사용한다.

OAuth, JWT 기반 인증 리소스 요청은 현대적인 웹/앱 환경에서 안전하게 권한을 주고받기 위한 인증 절차이다.

---

### OAuth

OAuth는 소셜 로그인 또는 권한 위임 방식이다.

회원가입할 때 카카오 로그인, 네이버 로그인으로 시작하는 것이 이 방식이다.

사용자가 내 서버에 직접 아이디와 비밀번호를 입력하지 않고, 카카오나 구글 같은 신뢰할 수 있는 대기업 서버를 통해 신원을 인증받는 방식이다.

---

### JWT

JWT는 `JSON Web Token`의 약자이다.

암호화된 입장권이라고 생각하면 된다.

인터넷은 무상태성이기 때문에 매번 새로고침하면 로그인이 풀리게 된다.  
그렇다고 글을 읽을 때마다 매번 서버에 아이디와 비밀번호를 보낼 수 없다.

그래서 로그인이 성공하면 서버는 사용자에게 암호화된 디지털 입장권인 JWT, 즉 토큰을 발급해준다.

사용자는 이 토큰을 저장한다.

서버는 토큰이 유효한지 확인하고, 유효하면 데이터를 보내준다.

---

## 429 Too Many Requests

### Case 1. 인증 실패, 비밀번호를 계속 틀리는 상황

계속 로그인이나 토큰을 발행하는 주소에 틀린 정보를 보냈을 때이다.

### 1번 방안

로그인이 특정 횟수 이상 틀리면 `429 Too Many Requests` 에러를 보내고, `Retry-After: n`을 보내서 일정 시간 동안 로그인을 못하게 밴을 거는 방식이다.

### 2번 방안

시간이 지나면 풀리는 것이 아니라 해당 IP에 계정을 잠가버리는 방식이다.

`401 Unauthorized` 응답과 함께 해당 사용자가 특수한 절차가 필요하다는 메시지를 응답한다.

보통 `401 Unauthorized` 응답을 보낸다.

이것과 함께 계정이 잠겼다고 알려주고 고객센터에 문의하라는 글을 함께 남긴다.

`Retry-After`와 관계없다.

---

### Case 2. 로그인을 정상적으로 한 유저가 악의적으로 서버를 마비시키기 위한 행위를 할 때

예시:

- n시간 동안 n회 이상 과도하게 요청을 보냄

해결 방안:

- 그냥 429 코드를 던지고 요청을 막음
- `Retry-After`를 보내서 일정 시간 밴함

---

## 페이징 처리

페이징 처리는 한 번에 많은 데이터를 보내면 서버에 무리가 가기 때문에 페이지를 만들어서 페이지당 적정한 데이터를 보여주는 방식이다.

예시:

- 1페이지 100개
- 2페이지 100개

```http
HTTP/1.1 200 OK
Link: https://api.test.com/users?page=3&per_page=100; rel="next",
https://api.test.com/users?page=50&per_page=100; rel="last"
```

`page=3&per_page=100`은 한 페이지당 100개이고, 3번째 페이지라는 뜻이다.

`rel`은 관계, 즉 relation을 의미한다.

### rel 속성값

| rel | 의미 |
|---|---|
| next | 다음 페이지 주소 |
| last | 데이터 맨 마지막 페이지 주소 |
| first | 맨 처음 페이지 주소 |
| prev | 바로 이전 페이지 주소 |

---

## Collection

Collection은 자원들의 집합 또는 폴더이다.

주소 뒤에 ID가 붙지 않는다.

### 예시

`POST /users`

사용자 폴더에 데이터를 집어넣는 것이므로, 새로운 사용자를 생성한다는 뜻이다.

`GET /users`

사용자 폴더를 열어보는 것이므로, 사용자 전체를 목록으로 조회하겠다는 뜻이다.

`PUT /users`

사용자 폴더 전체를 새것으로 갈아 끼우겠다는 뜻이다.

`DELETE /users`

사용자 폴더 자체를 날려버리는 것이므로, 모든 사용자 데이터를 전체 삭제하겠다는 뜻이다.

---

## Document

Document는 집합 안에 속한 단 하나 또는 한 명의 객체 데이터이다.

주소 뒤에 고유 식별자가 붙는다.

### 예시

`POST /users/hak`

이건 405 에러이다.

특정 사용자 파일인 `hak`를 가리키고 있는데, 또 무언가를 생성할 수 없어서 에러가 생긴다.

`GET /users/hak`

`hak` 사용자 단 한 명의 정보만 조회한다.

`PUT /users/hak`

`hak`에 있던 데이터를 통째로 덮어쓰기, 즉 수정한다.

`DELETE /users/hak`

`hak` 하나만 삭제한다는 뜻이다.

---

## HTTP 메서드 종류

| Method | 설명 |
|---|---|
| OPTIONS | 내가 어떤 메서드를 사용 가능한지 질문 |
| HEAD | 헤더 정보만 응답 |
| PATCH | 부분 수정 |
| GET | 자원 조회, 행위는 Read |
| POST | 자원 생성, 행위는 Create |
| PUT / PATCH | 자원 수정, 행위는 Update |
| DELETE | 자원 삭제, 행위는 Delete |

부분 수정이 필요하면 `PUT`보다 `PATCH`를 사용한다.

`PUT`은 수정할 부분 말고도 전체를 써줘야 한다.

---

## 에러 종류

# 성공 응답은 2XX번대로 응답

### 200 OK

서버가 요청을 잘 처리했다는 범용적인 응답이다.

### 201 Created

새로운 리소스가 생성되었을 때 성공을 더 명확히 알려주기 위해 HTTP 표준 코드를 사용한다.

200과 달리 요청에 성공하고 새로운 리소스를 만든 경우에 응답한다.

`POST`, `PUT`에 사용한다.

### 202 Accepted

클라이언트 요청을 받은 후, 요청은 유효하나 서버가 아직 처리하지 않은 경우에 응답한다.

### 204 No Content

응답 body가 필요 없는 자원 삭제 요청, 즉 `DELETE` 같은 경우 응답한다.

200 응답 후 body에 `null`, `{}`, `[]`, `false`로 응답하는 것과 다르다.

204의 경우 HTTP body가 아예 없는 경우이다.

---

# 실패는 4XX로 응답

### 400 Bad Request

클라이언트 요청이 미리 정의된 파라미터 요구사항을 위반한 경우이다.

파라미터의 위치, 사용자 입력값, 에러 이유 등을 반드시 알린다.

### 401 Unauthorized

인증을 잘못했을 경우 접근 거부 에러이다.

### 403 Forbidden

해당 요청은 유효하나 서버 작업 중 접근이 허용되지 않은 자원을 조회하려는 경우이다.

접근 권한이 전체가 아닌 일부만 허용되어 요청자의 접근이 불가한 자원에 접근 시도한 경우 응답한다.

### 404 Not Found

페이지 없음이다.

### 429 Too Many Requests

서버가 보낼 수 있는 경고장 중 하나이다.

서버가 허용한 초당/분당 요청 횟수 제한을 초과했을 때 보낸다.

---

# 5XX 에러

5XX 에러는 사용자에게 나타내지 말기.

### 500

서비스 장애이다.

---

## HATEOAS

이 개념은 웹사이트에는 화면에 다음 버튼이나 상세보기 버튼이 있어서 마우스로 눌러 확인하면 되는데, 데이터만 주는 REST API는 다음 주소를 알려주지 않는다는 점에서 시작한다.

API도 데이터 안에 다음에 갈 주소, 즉 Link 목록을 실어서 보내달라는 것이다.

짧게 말하면 API 안에 다음에 갈 Link를 실어 보내는 것이다.

예를 들어 회원을 등록했는데, 그 다음 행동에 대한 정보가 없을 수 있다.

네이버의 경우 회원가입이 완료되면 탈퇴, 내 정보 보기 등등의 버튼이 보인다.

사용자는 다음 주소를 외울 필요 없이 화면에 나타난 버튼을 마우스로 클릭하기만 하면 다음 상태로 부드럽게 넘어간다.

이것을 상태 전이된다고 한다.

그래서 HATEOAS는 HTML의 편리한 방식을 도입한 것이다.

---

### 2.미션 API 설계하기


### 마이페이지

| API Name | Method | Endpoint | Header | Request Body | Query Parameter | Path Variable | Response |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 회원가입 | POST | `/members/signup` | `Content-Type: application/json` | `name`, `email`, `password` | x | x | `memberId`, `name`, `email` |
| 로그인 | POST | `/members/login` | `Content-Type: application/json` | `email`, `password` | x | x | `accessToken`, `memberId`, `name` |
| 로그아웃 | POST | `/members/logout` | `Authorization: Bearer {token}` | x | x | x | `message` |
| 내 정보 조회 | GET | `/members/me` | `Authorization: Bearer {token}` | x | x | x | `memberId`, `name`, `email`, `point` |
| 회원 정보 수정 | PATCH | `/members/me` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `name`, `password` | x | x | `memberId`, `name` |
| 회원 탈퇴 | DELETE | `/members/me` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `password` | x | x | `message` |
|  |  |  |  |  |  |  |  |

### 포인트(point)


| API Name | Method | Endpoint | Header | Request Body | Query Parameter | Path Variable | Response |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 포인트 조회 | GET | `/members/{memberId}/points` | `Authorization: Bearer {token}` | x | x | `memberId` | `memberId`, `point` |
| 포인트 지급 | POST | `/members/{memberId}/points/reward` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `rewardPoint`, `reason` | x | `memberId` | `memberId`, `rewardPoint`, `totalPoint`, `message` |
| 포인트 사용 | POST | `/members/{memberId}/points/use` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `usedPoint`, `storeName` | x | `memberId` | `memberId`, `usedPoint`, `remainingPoint`, `message` |
| 포인트 사용 상점 조회 | GET | `/point-stores` | x | x | `page`, `size` | x | `stores`, `pageInfo` |

### review

| API Name | Method | Endpoint | Header | Request Body | Query Parameter | Path Variable | Response |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 독후감 작성 | POST | `/books/{bookId}/reviews` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `memberId`, `content`, `rating` | x | `bookId` | `reviewId`, `bookId`, `memberId`, `createdAt` |
| 도서 리뷰 목록 조회 | GET | `/books/{bookId}/reviews` | x | x | `page`, `size` | `bookId` | `reviews`, `pageInfo` |
| 회원 리뷰 목록 조회 | GET | `/members/{memberId}/reviews` | `Authorization: Bearer {token}` | x | `page`, `size` | `memberId` | `reviews`, `pageInfo` |
| 독후감 수정 | PATCH | `/reviews/{reviewId}` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `content`, `rating` | x | `reviewId` | `reviewId`, `content` |
| 독후감 삭제 | DELETE | `/reviews/{reviewId}` | `Authorization: Bearer {token}` | x | x | `reviewId` | `reviewId`, `message` |


### 우수회원(Excellent Member)

| API Name | Method | Endpoint | Header | Request Body | Query Parameter | Path Variable | Response |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 독서 우수 회원 조회 | GET | `/members/excellent` | `Authorization: Bearer {token}` | x | `minimumRentalCount` | x | `members` |
| 우수 회원 포인트 지급 | POST | `/members/excellent/reward` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `minimumRentalCount`, `rewardPoint` | x | x | `rewardedMembers`, `rewardPoint`, `message` |

### 도서대여(rental)

| API Name | Method | Endpoint | Header | Request Body | Query Parameter | Path Variable | Response |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 도서 대여 | POST | `/rentals` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `memberId`, `bookId` | x | x | `rentalId`, `memberId`, `bookId`, `rentalDate`, `dueDate` |
| 회원 대여 목록 조회 | GET | `/members/{memberId}/rentals` | `Authorization: Bearer {token}` | x | `status` | `memberId` | `rentals` |
| 도서 반납 | PATCH | `/rentals/{rentalId}/return` | `Authorization: Bearer {token}` | x | x | `rentalId` | `rentalId`, `returned`, `rewardPoint`, `totalPoint`, `message` |

### 책(book)
| API Name | Method | Endpoint | Header | Request Body | Query Parameter | Path Variable | Response |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 도서 등록 | POST | `/books` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `title`, `author`, `publisher`, `category` | x | x | `bookId`, `title` |
| 도서 목록 조회 | GET | `/books` | x | x | `page`, `size`, `keyword`, `category` | x | `books`, `pageInfo` |
| 도서 상세 조회 | GET | `/books/{bookId}` | x | x | x | `bookId` | `bookId`, `title`, `author`, `publisher`, `category`, `isAvailable` |
| 도서 정보 수정 | PATCH | `/books/{bookId}` | `Authorization: Bearer {token}`, `Content-Type: application/json` | `title`, `author`, `publisher`, `category` | x | `bookId` | `bookId`, `title` |
| 도서 삭제 | DELETE | `/books/{bookId}` | `Authorization: Bearer {token}` | x | x | `bookId` | `bookId`, `message` |

















