<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=1&height=250&section=header&text=Quora%20Clone&fontSize=70&fontColor=ffffff&animation=twinkling&desc=Reactive%20QnA%20Platform&descAlignY=65&descAlign=50" alt="Animated Header Banner" />
</div>

<p align="center">
  <img src="https://img.shields.io/badge/Java%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring%20WebFlux-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring WebFlux"/>
  <img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white" alt="MongoDB"/>
  <img src="https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white" alt="Elasticsearch"/>
  <img src="https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white" alt="Kafka"/>
</p>

<h3 align="center">A high-performance Q&A backend built using Spring WebFlux, MongoDB, Elasticsearch, and Kafka.</h3>

---

## 📊 Project Highlights

- ⚡ **Reactive APIs** using Spring WebFlux to handle concurrent requests without thread blocking.
- 🔍 **Full-text search** integrated with Elasticsearch for fast and scalable indexing.
- 📨 **Event-driven view tracking** implemented via Kafka to keep read APIs responsive.
- 📄 **Cursor-based pagination** designed to support efficient infinite scrolling on large datasets.
- 🌿 **MongoDB** utilized as the source of truth alongside a dedicated Elasticsearch search index.
- 🚀 **Non-blocking architecture** developed using Project Reactor (Mono/Flux) for maximum scalability.

## 🏗 System Architecture

```mermaid
flowchart TB
    subgraph CLIENT["🌐 Client Layer"]
        PM[Postman / REST Client]
        FE[Future Frontend App]
    end

    subgraph API["⚡ API Layer — Spring WebFlux :8080"]
        QC[QuestionController<br/><code>/api/questions</code>]
        AC[AnswerController<br/><code>/api/answers</code>]
    end

    subgraph SERVICE["🧠 Service Layer"]
        QS[QuestionService]
        AS[AnswerService]
        QIS[QuestionIndexService]
    end

    subgraph EVENT["📨 Event Layer — Kafka"]
        KP[KafkaEventProducer]
        KC[KafkaEventConsumer]
        TOPIC[(view-count-topic)]
    end

    subgraph DATA["💾 Data Layer"]
        MONGO[(MongoDB<br/>quoraDb)]
        ES[(Elasticsearch<br/>questions index)]
    end

    subgraph COLLECTIONS["MongoDB Collections"]
        Q_COL[questions]
        A_COL[answers]
        L_COL[likes]
    end

    PM --> QC & AC
    FE --> QC & AC

    QC --> QS
    AC --> AS

    QS --> QIS
    QS --> KP
    QS --> MONGO
    QIS --> ES

    AS --> MONGO

    KP --> TOPIC
    TOPIC --> KC
    KC --> MONGO

    MONGO --- Q_COL & A_COL & L_COL

    classDef client fill:#e8f4fd,stroke:#2196F3,stroke-width:2px,color:#0d47a1
    classDef api fill:#fff3e0,stroke:#FF9800,stroke-width:2px,color:#e65100
    classDef service fill:#f3e5f5,stroke:#9C27B0,stroke-width:2px,color:#4a148c
    classDef event fill:#e8f5e9,stroke:#4CAF50,stroke-width:2px,color:#1b5e20
    classDef data fill:#fce4ec,stroke:#E91E63,stroke-width:2px,color:#880e4f

    class PM,FE client
    class QC,AC api
    class QS,AS,QIS service
    class KP,KC,TOPIC event
    class MONGO,ES,Q_COL,A_COL,L_COL data
