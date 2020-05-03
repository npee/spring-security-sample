# oauth2-with-custom-provider
`CommonOAuth2Provider`에서 제공하지 않는 제공자를 이용한 인증
## `CommonOAuth2Provider` 외 추가된 제공자  
* <a href="https://developers.naver.com/main/">naver</a>
* <a href="https://developers.kakao.com/">kakao</a>
## 환경 및 개발 기반 모듈
- <a href="https://github.com/npee/spring-security-sample/tree/master/oauth2-with-common-provider">oauth2-with-common-provider</a>
## 추가 설정
### 주요 방식
#### application 설정 파일 내 값 수정
추가된 부분은 진하게 표시됨
* 파일 위치
    * resources/<a href="https://github.com/npee/spring-security-sample/blob/master/oauth2-with-custom-provider/src/main/resources/application.yml">application.yml</a>
* 속성
    * spring.security.oauth2.client.registration.{registrationId}
    * **spring.security.oauth2.client.provider.{registrationId}**
* 값 (registration)
    * Client ID
    * Client Secret
    * **redirect-uri**
    * **authorization-grant-type**
    * **scope**
    * **client-name**
* **값 (provider)**
    * **authorization-uri**
    * **token-uri**
    * **user-info-uri**
    * **user-name-attribute**
#### 원리
기본 제공자 이외의 제공자를 사용하기 위해 필요한 설정을 직접 해줘야 한다.
속성 값 이름의 의미 그대로이며 실제 필요한 정보는 `user-name-attribute`에 포함되어 있다.
#### 유의할 점
제공자 마다 구조가 다르므로 주의한다.  
예를 들어 **naver**처럼 필요한 정보가 `user-name-attribute`의 최상위 노드에 있지 않으면 정보에 직접 접근할 수 없으므로 `OAuth2UserService`를 커스터마이징하여 하위 노드에 접근하는 로직을 만들어 사용해야 한다.
##### 비교
* google  
`attributes` 아래 바로 정보가 있다.  
![image](https://user-images.githubusercontent.com/56008955/80898302-b2783d00-8d3c-11ea-84cf-961fabff6ff5.png) 
* naver  
`attributes` 아래 `response` 속성이 정보를 가지고 있다.  
![image](https://user-images.githubusercontent.com/56008955/80898242-27974280-8d3c-11ea-9a3e-4177aeb67e6d.png)
