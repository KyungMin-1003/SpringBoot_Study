### 1. 핵심 키워드 정리

##RESTful API
API는 여러가지가 있지만 일단 대표적으로 2가지가 있다.
REST API (REpresentational State Transfer Application Programming Interface ): 웹(HTTP)의 기존 장점과 규칙을 최대한 활용해서 API를 자원 중심으로 깔끔하게 디자인하는 방법론이라고 한다. 
Open API : 누구나 접근해서 사용할 수 있도록 공공기관이나 기업에서 무료 OR 조건부로 공개한 API이다.

RESTFUL API는 REST API 잘 지킨 API라고 생각하면된

자원 - 모든 자원은 고유한 ID를 가지는 URL로 표현된다.라고 하는데 쉽게 말해서 명사들이라고 생각하면 됩니다.  user, members 
행위(Verb) HTTP Method - 자원에 대한 액션은 HTTP 표준 메서드를 그래도 사용한다. CRUD(create, read, update, delete)

이렇게 대응된다고 생각하면 된다.

표현 (repersentation) - 클라이언트가 자원을 요청하면 서버는 그에 맞는 형태로 보내줍니다. 과거에는 XMl도 많이 쓰였지만 현재는 대부분 JSON 형식을 사용한다. 

## url만드는 규칙
- 마지막에 / 포함하지 않는다
- _(underbar)대신 -(dash)사용 -도 최소한으로 사용하기
- 소문자 사용
- method는 URL에 포함하지 않는다 (GET,POST 등의 방법을 사용해서 간접적으로 표현해야함, 직접적인 metod를 사용해서는 안된다.)
- 컨트롤 자원을 의미하는 URL 예외적으로 동사를 허용한다. 대신 동사는 맨 마지막에 사용.


##멱등성이란
연산을 여러번 적용하더라도 결과가 달라지지 않고 똑같은 성질을 의미한다. 

GET,PUT,DELETE = 멱등함. 

GET- 데이터를 조회만 하기 때문에 서버 데이터는 변하지 않고 똑같은 상태 유지
PUT - 데이터 수정하는 메서드인데 예로 이름을 ‘홍길동’으로 수정해줘 라는 요청은 몇번을 보내도 데이터는 홍길동으로 유지됨
DELETE - 한번 삭제요청하면 그뒤로 계속 삭제 요청을 보내도 404에러를 받을지언정 서버에는 게시글이 지워진것은 변함이 없기 때문

POST, PATCH = 멱등하지 않음. 
POST - 자원을 새로 생성하는 메서드이기 때문에 100번 요청하면 100개의 데이터가 새로 생성되서 멱등하지 않는다.
여기서   PUT를 POST 처럼 쓸 수 있는데 이러한 경우는 멱등하지 않을 수 있다. 
- put을 기존 데이터를 덮어쓰는게 아니라, 요청이 올 때마다 데이터를 누적하거나 추가하도록 하면 멱등성이 깨진다.

## 비 정상적인 방법으로 API 서버를 이용하려는 경우

429 Too Many Requests 오류 응답과 함께 일정시간 뒤에 요청할 것을 나타낸다. 

비 정상적인 방법은 DoS, Brute-force attack 이 있음.
DoS - 우리가 아는 공격 가짜 요청을 초당 수만개를 보내 서버를 마비시키는 공격
Brute-force attack - 비밀번호를 알아내려고 로그인을 수천 번 연속으로 시도하는 공격

이런 공격을 감지하면 429 오류를 내뱉는다. 
이거와 함께 **Retry-After** 이라고 언제 다시 요청가능한지 알려준다.

/longin  전통적인 내 사이트 로그인 방식
/auth 더 안전하고 고도화된 현대적 인증 방식(토큰, 소셜 로그인 등)을 처리할 때 사용

/longin 아이디,비밀번호 기반 로그인 
- 사용자가 직접 화면에 아이디와 비밀번호를 타이핑해서 서버로 전송, 서버는
- 서버는 DB에서 해당 아이디를 찾고 비밀번호가 일치하는지 대조 후 맞으면 통과
- 특징으로는 주소가 매우 직관적이다. POST/login 또는 POST/auth/login 처럼 설계된다.

/auth  OAuth, JWT 기반 인증 리소스 요청 현대적인 웹/앱 환경에서 안전하게 권한을 주고 받기 위한 인증 절차.
OAuth (소셜 로그인 / 권한 위임) - 회원가입할 때 카카오로그인, 네이버로그인으로 시자가는게 이 방식, 사용자가 내 서버에 직접 아이디/비밀번호를 입력하지 않고, 카카오나, 구글 같은 신뢰할 수 있는 대기업 서버를 통해 신원을 인증받는 방식
JWT (JSON Web Token - 암호화된 입장권) -인터넷은 무상태성이라 매번 새로고침하면 로그인이 풀리게 된다. 그렇다고 글을 읽을 때 마다 매번 서버에 아이디/비번을 보낼 수 없음.
그래서 로그인이 성공하면 서버는 사용자에게 암호화된 디지털 입장권인 JWT(토큰)을 발급해준다. 사용자는 이 토큰을 저장함. 
서버는 토큰이 유효한지 확인하고 유효하면 데이터를 보내준다.

## 429 Too Many Requests
case 1 인증실패 비밀번호를 계속 틀리는 상황
계속 로그인이나 토큰을 발행하는 주소에 틀린정보를 보냈을 때

1번 방안은 로그인이 특정횟수 이상틀리면 429 Too Many Requests에러를 보내고 Retry-After: n보내서 일정시간동안 로그인을 못하게 밴을 거는 방식

2번 방안은 시간이지나면 풀리는 것이 아니라 해당 IP에 계정을 잠가버리는 방식 401 응답과함께 해당 사용자가 특수한 절차가 필요하다는 메세지 응답. 
보통 401 Unauthorized 응답을 보냄 이거와함께 계정이 잡겼다고 알려주고 고객센터에 문의하라는 글을 함께 남김. Retry-After와 관계없음. 

case 2 로그인을 정상적으로 한 유저가 악의 적으로 서버를 마비시키기위한 행위를 할때 
예시 - n시간동안 n회 이상 과도하게 요청을 보냄
해결방안 - 그냥 429 코드를 던지고 요청을 막음, Retry-After 보내서 일정시간 밴함.

##페이징 처리
페이징 처리 - 한번에 많은 데이터를 보내면 서버에 무리가 가기 때문에 페이지를 만들어서 페이지당 적정한 데이터를 보여준다. ex) 1페이지 100개 2페이지 100개 
HTTP/1.1 200 OK
Link: https://api.test.com/users?page=3&per_page=100; rel="next",
https://api.test.com/users?page=50&per_page=100; rel="last" 

page=3&per_page=100 → 한페이지당 100개이고 3번째 페이지(page=3) rel(관계relation)

rel 속성값 
next(다음 페이지 주소),
last(데이터 맨 마지막 페이지 주소)
first(맨 처음 페이지 주소)
prev(바로 이전 페이지 주소)


collection-자원들의 집합/폴더 주소 뒤에 ID 붙지 않음.

POST /users: 사용자 폴더에 데이터를 집어넣는 것이므로, 새로운 사용자를 생성 한다는 뜻이다.
GET /users: 사용자 폴더를 열어보는 것이므로, 사용자 전체를 목록으로 조회하겠다는 뜻이다.
PUT /users: 사용자 폴더 전체를 새것으로 갈아 끼우겠다는 뜻이다.
DELETE /users: 사용자 폴더 자체를 날려버리는 것이므로, 모든 사용자 데이터를 전체 삭제하겠다는 뜻이다.

Document - 집합 안에 속한 단 하나 또는 한명의 객체 데이터 주소 뒤에 고유 식별자가 붙는다. 
POST /users/hak - 이건 405에러 특정사용자 파일인 hak를 가리키고있는데 또 무언가를 생성할 수 없어서 에러가 생긴다. 
GET /users/hak - hak 사용자 단 한명의 정보만 조회
PUT /users/hak - hak에 있던 데이터를 통째로 덮어쓰기(수정)
DELETE /users/hak - hak 하나만 삭제한다는 뜻




## HTTP 메서드 종류
OPTIONS - 내가 어떤 메서드를 사용가능한지 질문
HEAD - 헤더정보만 응답
PATCH - 부분 수정 (부분수정이 필요하면 put보다 patch사용, put는 수정할 부분 말고도 전체를 써줘야함.)
Get :자원조회 /행위: read
post : 자원생성 /행위:  create
put/patch : 자원수정 /행위: update
delete 자원삭제 /행위: delete

## 에러 종류
# 성공응답은 2XX번때 로 응답
 - 200 OK - 서버가 요청을 잘 처리했다는 범용적인 응답

 - 201 Created - 새로운 리소스가 생성되었을때 성공을 더 명확히 알려주기위해 HTTP표준코드 사용

 - 200과 달리 요청에 성공하고 새로운 리소스를 만든 경우에 응답한다. POST, PUT에 사용한다.

 - 202 Accepted :클라이언트 요청을 받은 후, 요청은 유효하나 서버가 아직 처리하지 않은 경우에 응답한다
 
 - 204 No Content -  응답 body가 필요 없는 자원 삭제 요청(DELETE) 같은 경우 응답한다.200 응답 후 body에 null,{},[],false로 응답하는 것과 다름. 
204의 경우 HTTP body가 아예 없는 경우

# 실패는 4XX로 응답. 
- 400 Bad Request-  클라이언트 요청이 미리 정의된 파라미터 요구사항을 위반한 경우 파라미터의 위치, 사용자 입력값, 에러 이유 등을 반드시 알린다.

- 401 Unauthorized - 인증을 잘못했을경우 접근거부 에러 

- 403 Forbidden - 해당 요청은 유효하나 서버 작업 중 접근이 허용되지 않은 자원을 조회하려는 경우,접근 권한이 전체가 아닌 일부만 허용되어 요청자의 접근이 불가한 자원에 접근 시도한 경우 응답한다.

- 404 Not Found - 페이지 없음

- 429 Too Many Requests - 서버가 보낼 수 있는 경고장 중 하나,서버가 허용한 초당/분당 요청 횟수 제한을 초과했을 때 보냅니다.

5XX 에러는 사용자에게 나타내지 말기 500 - 서비스 장애 

## HATEOAS
이 개념은 웹사이트는 화면에 다음버튼이나 상세보기 버튼이 있어서 마우스로 눌러 확인하면 되는데, 데이터만 주는 REST API는 다음주소를 안알려운다. 
API도 데이터 안에 다음에 갈 주소( Link) 목록을 실어서 보내달라는 것. 
짧게 -API안에 다음에 갈 Link를 실어보내는것
예로 회원을 등록했음, 그다음 행동에대한 정보가 없음.  네이버경우 회원가입 완료하면 탈퇴, 내정보보기 등등의 버튼이 보임.
사용자는 다음주소를 외울 필요없이 화면에 나타난 버튼을 마우스로 클릭하기만 하면 다음 상태로 부드럽게 넘어감. → 이것을 상태전이된다 라고 한다. 
그래서 HATEOAS는  HTML의 편리한 방식을 도입한 것이다.


   
### 2. 미션 API 설계하기

# 마이페이지 
API Name -  내 정보 조회
Method - GET
Endpoint - /user/me
Header - Authorization: bearer{ Token }
Request Body - x
Query Parameter -x
Path Variable - x

API Name -  내 포인트 조회
Method - GET
Endpoint - /user/me/points
Header - Authorization: bearer{ Token }
Request Body - x
Query Parameter -x
Path Variable - x

# 회원가입

API Name -  회원가입
Method - POST
Endpoint - /auth/singup
Header - Content-Type: application/json
Request Body - loginId, password, name, phoneNumber, 
Query Parameter - x
Path Variable - x
    
API Name -  로그인
Method - POST
Endpoint -  /auth/login
Header - Content-Type: application/json
Request Body - email, password
Query Parameter - x
Path Variable - x

API Name -  탈퇴
Method - Delete
Endpoint - /user/me
Header - Content-Type: application/json
Request Body - password
Query Parameter - x
Path Variable - x

# 독후감
API Name -  독후감 작성
Method - POST
Endpoint - /reads/{readId}/reviews
Header - Content-Type: application/json, Authorization: bearer{ Token }
Request Body - title, content, level
Query Parameter - x
Path Variable - readId

# 포인트
API Name -  포인트 수령
Method - POST
Endpoint - /user/recive
Header - Content-Type: application/json, Authorization: bearer{ Token }
Request Body - return : 5
Query Parameter - x
Path Variable -  x

API Name -  포인트 사용 상점
Method - POST
Endpoint - /user/store
Header - Content-Type: application/json, Authorization: bearer{ Token }
Request Body - x
Query Parameter - x
Path Variable -  x

API Name -  우수회원 조회
Method - GET
Endpoint - /user/king
Header - Authorization: bearer{ Token }
Request Body - x
Query Parameter - minimum = 20
Path Variable - x
