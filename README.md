# Spring 뉴스피드-프로젝트

### 👕 팀 소개

- 프로젝트 명 : 맞따라죠 멋따라죠
- 프로젝트 소개 :  맞죠맞죠가 소개하는 로컬 맛집과 명소!

### 역할분담

- 팀 과제
    - 김한신 - 유저 도메인 설계 및 구현
    - 김동민 - 게시글 도메인 설계 및 구현        
    - 김성훈 - 댓글 도메인 설계 및 구현
    - 최준영 - QC 및 인증인가(AccessToken, RefreshToken)
 
## **API**

[Postman API 명세서](https://documenter.getpostman.com/view/31333446/2s9YeD9Z9A)


## **ERD**

![ERD](https://github.com/junxtar/spring-newsfeed/assets/75934088/8d1ae5d8-c727-41c6-8de2-08d9b3c630c0)



## 필수 구현 기능 🚀
- [v]  **사용자 인증 기능**
    - 회원가입 기능
        - 새로운 사용자가 ID와 비밀번호의 형태로 서비스에 가입할 수 있어야 합니다.
            - 이 때, 비밀번호는 안전하게 암호화되어 저장되어야 합니다!
    - 로그인 및 로그아웃 기능
        - 사용자는 자신의 계정으로 서비스에 로그인하고 로그아웃할 수 있어야 합니다.
- [v]  **프로필 관리**
    - 프로필 수정 기능
        - 이름, 한 줄 소개와 같은 기본적인 정보를 볼 수 있어야 하며 수정할 수 있어야 합니다.
        - 비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.
- [v]  **게시물 CRUD 기능**
    - 게시물 작성, 조회, 수정, 삭제 기능
        - 게시물 조회를 제외한 나머지 기능들은 전부 인가(Authorization) 개념이 적용되어야 하며 이는 JWT와 같은 토큰으로 검증이 되어야 할 것입니다.
        - 예컨대, 내가 작성한 글을 남이 삭제할 수는 없어야 하고 오로지 본인만 삭제할 수 있어야겠죠?
    - 게시물 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 게시물 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 게시물 내용을 화면에 보여줄 수 있도록 해야 합니다!
- [v]  **뉴스 피드 기능**
    - 뉴스 피드 페이지
        - 사용자가 다른 사용자의 게시물을 한 눈에 볼 수 있는 뉴스 피드 페이지가 있어야 합니다.

## 추가 구현 기능 🚀 

- [v]  **댓글 CRUD 기능**
    - 댓글 작성, 조회, 수정, 삭제 기능
        - 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제를 할 수 있어야 합니다.
        - 또한, 게시물과 마찬가지로 댓글 조회를 제외한 나머지 기능들은 인가(Authorization)개념이 적용되어야 합니다.
    - 댓글 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 댓글 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 댓글 목록을 화면에 보여줄 수 있도록 해야 합니다!
        
- [v]  **레디스 환경 구축**
    - 로그인시 RefreshToken redis key 값 저장
    
- [v]  **좋아요 기능**
    - 게시물 및 댓글 좋아요/좋아요 취소 기능
        - 사용자가 게시물이나 댓글에 좋아요를 남기거나 취소할 수 있어야 합니다.
        - 이 때, 본인이 작성한 게시물과 댓글에 좋아요는 남길 수 없도록 해봅니다!

## ``Tech Stack``

<table>
  <tr style="height: 120px">
    <td align="center" width="140">
        <img src="https://skillicons.dev/icons?i=java&theme=light" width="65" height="65" alt="Java 17 logo" />
        <br>Java 17
    </td>
    <td align="center" width="140">
        <img src="https://skillicons.dev/icons?i=spring&theme=light" width="65" height="65" alt="spring boot logo" />
        <br>Spring Boot
    </td>
    <td align="center" width="140">
        <img style="border-radius: 12px" src="https://blog.kakaocdn.net/dn/zVcJ7/btrqRTaogof/Sk9Wu37vvLkg618Nlx309k/img.png" width="65" height="65" alt="Spring Data Jpa logo" />
        <br>Spring Data JPA
    </td>
    <td align="center" width="140">
        <img style="background: white; border-radius: 12px" src="https://blog.kakaocdn.net/dn/cqbtEQ/btrZISJO4rM/psTAeZ2SeJr4mw2z80gt00/img.png" width="65" height="65" alt="JWT logo" />
        <br>JWT
    </td>
  </tr>
</table>

## ``Database``
  <table>
  <tr>
     <td align="center" width="140">
        <img src="https://skillicons.dev/icons?i=mysql&theme=light" width="65" height="65" alt="MySQL logo" />
        <br>MySQL
    </td>
    <td align="center" width="140">
        <img src="https://skillicons.dev/icons?i=redis&theme=light" width="65" height="65" alt="Redis logo" />
        <br>Redis
    </td>
  </tr>
</table>




