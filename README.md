# SPRING PLUS

Spring Boot 기반의 Querydsl을 학습하고,  
기존 코드의 문제점을 분석·개선하며 **설계·리팩터링·테스트 관점**을 강화하기 위한 프로젝트입니다.

---

## 📌 프로젝트 개요

- 본 프로젝트는 아래 저장소의 코드를 **fork** 하여 진행되었습니다.  
  🔗 https://github.com/f-api/spring-advanced](https://github.com/f-api/spring-plus
- 기존 코드의 동작을 그대로 확장하는 것이 아니라,  
  **문제점 분석 → 구조 개선 → 테스트 안정성 강화**에 초점을 두고 리팩터링을 진행했습니다.

---

## 주요 개선사항 및 기능 추가 내역

- JPQL 기반의 쿼리를 Querydsl로 변경
- Filter + Argument Resolver 기반의 구조를 Spring Security 로 변경
- 페이징 기능이 있는 동적 쿼리를 Querydsl로 구현
- 로그 저장 기능 추가

---

## ⚙️ 기술 스택

- **Java 17**
- **Spring Boot 3.3.3**
- Spring MVC
- Spring Security
- JPA (Hibernate)
- JWT (jjwt)
- QueryDSL

---

## 📦 주요 의존성

```gradle
// querydsl
    implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
    annotationProcessor "com.querydsl:querydsl-apt:5.0.0:jakarta"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"
```

---

## 트러블 슈팅

🔗 https://hhw1.tistory.com/48](https://hhw1.tistory.com/59
