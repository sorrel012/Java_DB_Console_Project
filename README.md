# 모의 주식 투자 프로그램 📈📉
![1 메인화면](https://user-images.githubusercontent.com/115568532/235353977-a0cbec67-e4d8-4ff7-8804-044c88828531.png)

<br>

## ⁉ 프로그램 소개
  주식 투자에 대해 잘 알지 못하는 사람들도 쉽게 주식 시스템을 익힐 수 있도록 도움을 주는 프로그램<br>
모든 사용자는 초기 자산 10,000,000원을 가지고 시작하며, 시세 변동에 따른 매수와 매도를 통해 자산을 늘릴 수 있다.<Br>
자산 초기화를 통해 초기 자산으로 돌아가서 다시 투자를 시작할 수 있기 때문에 부담 없이 투자에 도전할 수 있다.
### ✔ 기획 의도
- 복잡한 주식 시스템 단순화
  - 가상의 화폐를 통해 실제 손실 없이 투자 가능
  - 시간 제약으로 인한 투자의 어려움 해결

<br>

## 📆 개발 기간
- 2023.03.06 - 2023.03.13(Java) > 팀 프로젝트
- 2023.04.23 - 2023.04.30(Oracle&JDBC)  > 개인 프로젝트

<br>

## 🖥 개발 환경
### ✔ 사용 언어
- Java(JDK 11)
### ✔ DB
- Oracle 11g Enterprise
### ✔ IDE
- Eclipse 2021-03, SQL Developer

<br>

## 🙋‍♀️ 담당(역할)
- 팀 프로젝트 : 주식 시세 크롤링, 종목 검색 및 관심 종목 등록, 개인정보 조회 및 수정, 랭킹 출력
- 개인 프로젝트 : 데이터베이스와 연결

### 1. 주식 시세 크롤링
<img src="https://user-images.githubusercontent.com/115568532/235356341-470eda8d-0515-4cb0-9027-a598d6b10c16.png" width="550" height="750">


### 2. 종목 검색

<img src="https://user-images.githubusercontent.com/115568532/235355109-2fec18b6-9fc8-45bd-8431-e86b324f5168.png" width="550" height="450">
<img src="https://user-images.githubusercontent.com/115568532/235355113-c0ff18ff-002e-45a9-a9af-a414849febe6.png" width="550" height="550">
<img src="https://user-images.githubusercontent.com/115568532/235355119-69b8db22-5c9f-44f6-9ed3-1fcd5c3c5676.png" width="550" height="200">
<img src="https://user-images.githubusercontent.com/115568532/235355316-c64fefc9-eccd-42d0-a086-33a9e33e788f.png" width="550" height="200">


### 3. 개인정보 조회 및 수정
<img src="https://user-images.githubusercontent.com/115568532/235355360-f0226932-b63d-4f29-9734-0a986a9a3619.png" width="550" height="450">
<img src="https://user-images.githubusercontent.com/115568532/235355368-f005439d-b7fa-40f1-bf52-ddc8f0fe4490.png" width="550" height="550">
<img src="https://user-images.githubusercontent.com/115568532/235355371-d28b2994-3019-4e9b-9049-b4ccf59d9c9d.png" width="550" height="450">
<img src="https://user-images.githubusercontent.com/115568532/235355374-ccbaa01f-4a75-49c8-bd91-b6a271dba3b3.png" width="550" height="300">


### 4. 랭킹 출력
<img src="https://user-images.githubusercontent.com/115568532/235355846-d8e68222-8232-4e57-bcc6-53ca9cb09fe6.png" width="550" height="300">


### 5. ERD 설계
![ERD](https://user-images.githubusercontent.com/115568532/235355906-cb84ebf0-5959-4b01-ba9c-ea55f91507f5.png)


### 6. JDBC를 이용하여 Oracle DB와 연결

<img src="https://user-images.githubusercontent.com/115568532/235354880-14a86b01-dcd7-4958-b983-8638f82cd3a8.png" width="550" height="650">
<img src="https://user-images.githubusercontent.com/115568532/235356761-5192b065-9c47-4453-bd28-d37e2fb38a7e.png" width="550" height="650">
<img src="https://user-images.githubusercontent.com/115568532/235356785-9e3438cf-805b-4f72-8964-c56e1ce613e1.png" width="550" height="650">
<img src="https://user-images.githubusercontent.com/115568532/235356802-ccef11af-cda2-48ed-9a76-cd7dcf338647.png" width="550" height="170">
<img src="https://user-images.githubusercontent.com/115568532/235357671-922cca40-3d90-4ae2-bf1f-dbfccd9da443.png" width="550" height="320">
<img src="https://user-images.githubusercontent.com/115568532/235357410-03a9e3d0-2ba0-4a36-984d-04226584e862.png" width="550" height="400">
<img src="https://user-images.githubusercontent.com/115568532/235357421-1e3a56ff-d5ca-4a83-bdc1-01f5ddfa2bab.png" width="550" height="350">
<img src="https://user-images.githubusercontent.com/115568532/235357429-faa9f81a-6d4e-4166-ace0-78e32832b74c.png" width="550" height="280">


<br>


## ✉ 소감

### ✍ 팀 프로젝트
처음으로 진행하는 프로젝트여서 프로젝트를 어떻게 해야 하는지를 몰라서 시작이 가장 어려웠다.<br>
기능을 나누어 각자 구현하는 데 생긴 오류들보다 기능을 합치는 과정에서 더 많은 오류가 발생했다.<br>
병합하다 보니 기능을 나누었더라도 내가 만든 메소드와 똑같은 메소드를 사용하는 팀원들도 있었고, 그 반대의 경우도 있었다.<br>
구현을 시작하기 전에 더 많은 이야기를 나누었다면 조금 더 효율적으로 구현할 수 있었을 것 같다는 생각이 들었다.<br>

### ✍ 개인 프로젝트
DB를 배운 후 이전에 개발했던 Java Console Project를 DB와 연결했다.<br>
Java만을 이용해서 개발할 때는 데이터를 txt 파일로 사용했었다.<br>
txt 파일의 데이터들을 DB의 테이블들로 옮겼다.<br>
JDBC를 이용하여 Oracle에 접근하도록 모든 구조를 바꾸려니 처음에는 힘들었다.<br>
그렇지만 바꾸다보니 txt 파일에서 읽거나 txt 파일에 쓰는 것보다 DB를 활용하여 읽고 쓰는 것이 훨씬 간편했다.<br>
DB의 필요성을 확실히 느낄 수 있었다.

