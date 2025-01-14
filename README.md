# 오늘은 어디~?
간편하게 운동을 기록을 저장하고 볼 수 있는 서비스
![스크린샷 2023-12-15 오전 12 58 53](https://github.com/doit-zero/doit-zero/assets/131241458/f2384816-d60e-4901-97a2-ab487337e992)
 
## 개발 기간  
2023.11 ~ 2023.12. 

## 개발 인원  
Front 1명 / Back 1명

## 담당 역할
서비스 기획 및 방향성 설정 (기여도 90%)
sessionId 기반 인증 서버 (기여도 100%)
Back-End 서버 구축 (기여도 100%)

## Back-End Stack

<img width="746" alt="스크린샷 2023-12-23 오후 11 10 29" src="https://github.com/doit-zero/doit-zero/assets/131241458/c95816dc-3537-463b-8693-cc206a4ec4fd">


<img width="733" alt="스크린샷 2024-01-01 오후 2 59 44" src="https://github.com/doit-zero/doit-zero/assets/131241458/72535720-4abe-4364-a91b-abb6397553e3">


## 담당 업무 및 핵심 경험

- **협업을 한다고 생각**하며 Commit Convention, Code Convention , GitFLow , API 명세서 작성
- 전체 기획 및 백엔드 아키텍쳐 , CRUD API 구축
- gitActions 와 aws 서비스로 CI/CD 구축
- Reids를 세션 인증 서버로 활용
- https 환경 구축
- Querydsl 도입
- cursor 기반 페이지네이션 구현

## 기술 도전과 이유

- Redis 를 세션ID 인증 서버로 활용
    
    이전 프로젝트에서는 매번 jwt로 인증 방식을 했었습니다.
    
    sessionId 인증 방식 또한 알고 싶었기에, 이번 프로젝트는 Redis를 인증 서버로 쓰는 것을 도전하였습니다.  
    
- gitHub Actions와 aws의 서비스로 CI/CD 구축
    
    배포하기 위해 매번 빌드 후 서버에서 빌드 파일을 실행시키는 것은 단순하며 반복적인 일입니다. 
    
    그렇기에 이번 프로젝트에서는 이 문제를 해결할 수 있도록 CI/CD를 구축하였습니다. 
    

- Querydsl 도입
    
    Querydsl는 자바코드 쿼리로 작성되어 컴파일 타입 안정성을 높일 수 있음 공부하였습니다. 
    
    그렇기에 이번 프로젝트에서 Querydsl 기술을 경험해보고자 도입하였습니다.
    

## 트러블 슈팅 및 해결

- CORS 정책 설정
    
    프론트엔드 서버에서 백엔드 서버로 요청시에 문제가 발생했습니다. 
    
    이 문제는 출처가 다르기 때문에 생겼던 문제였고  CORS 정책을 설정하여 해결하였습니다. 
    

- https 환경 구축
    
    요청시 쿠키 storage에 sessionID 저장하지 못하는 문제가 있었습니다.
    
    이는 브라우저 정책의 문제였고 **https 환경을 구축**하여 해결하였습니다.
    

## Learning Point

- sessionID - **웹 보안**을 배우다!
    
    이번에 sessionID를 활용하면서 많은 어려움을 겪었습니다. 
    
    서버 접속시 응답 헤더에 set-cookie sessoinID가 있지만, 브라우저 cookie strorage 저장되지 않는 문제, 
    
    https 환경을 구축하기 위해 ssl인증서를 받는 어려움 등이 있었지만, 
    
    이러한 문제들을 해결하면서 웹 보안에 대해 배울 수 있었습니다.
    
- CI/CD 정말 편하네! 코드 짤때도 같은 아이디어를 쓸 수 있지 않을까?
    
    이전 프로젝트에서는 CI/CD를 구축하지 못해, 
    
    빌드 파일을 만든 후 EC2 서버에 직접 빌드 파일을 올리고 배포까지 진행해야 해서 절차가 반복했었습니다. 
    
    하지만 이번에 CI/CD를 구축하여 단순한 반복 절차를 자동화 해보니 정말 편했습니다.
    
    이 경험을 통해 코드 작성 시 단순 반복 되는 것들은 자동화 시켜야겠다는 아이디어를 얻었습니다.
