# X-Alrimi-api-server
## 2021 - 1학기 캡스톤2 프로젝트
## 부정적인 연예기사 알림 어플리케이션

### 기술스택
1. SpringBoot
2. Firebase Cloud Messaging
3. Redis 
4. MySQL
5. Apache Lucene

### 주요기능
1. 크롤러에서 온 기사 ApacheLucene을 통해 키워드가 존재하는 기사와 존재하지 않는기사 구분
2. Redis를 통해서 ML서버에서 온 기사간 유사도 벡터 저장 및 코사인 유사도 계산
3. 부정적인 기사 + 이전에 알람을 보냈던 기사와 유사도에 차이가 있으면 알람 전송
4. 부정적인 기사 + 회사별 기사 저장

### 시스템 구조도
![시스템구조도](https://github.com/X-Alrimi/alrimi-api-server/blob/master/src/main/resources/image/%EC%8B%9C%EC%8A%A4%ED%85%9C%EA%B5%AC%EC%A1%B0%EB%8F%84.png)
