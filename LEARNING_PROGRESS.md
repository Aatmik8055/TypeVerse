# TypeVerse Backend — Learning Progress & Architectural Master Record

## 1. Project Information
- **Project Name:** TypeVerse (Real-time Multiplayer Typing Platform)
- **Target Location:** `C:\Intel\typeverse`
- **Reference Location:** `E:\Backend_java`
- **Tech Stack:** Java 21 / 17, Spring Boot 3.2.5, Maven, PostgreSQL 17, Spring Data JPA / Hibernate, Spring Security + JJWT 0.12.5, WebSockets.
- **Current Position:** Day 2 Completed (Verified). Entering Day 3 (Domain Model & Entity Foundation).

---

## 2. Checkpoint Verification Summary

### Day 1: Project Initialization & Maven Blueprint [COMPLETED & VERIFIED]
- **Artifacts:**
  - `pom.xml`: Configured with Spring Boot 3.2.5, Web, WebSocket, Data JPA, Security, Validation, PostgreSQL Driver, Flyway, JJWT 0.12.5.
  - `src/main/java/com/typeverse/TypeverseApplication.java`: Clean entry point with `@SpringBootApplication`.
- **Compilation:** Verified clean compilation via IntelliJ and Maven.
- **Git Commits:** Initial project scaffolding committed.

### Day 2: PostgreSQL Container & Database Boot [COMPLETED & VERIFIED]
- **Artifacts:**
  - `docker-compose.yml`: PostgreSQL 17 Alpine container (`typeverse-db`) mapping port `5432:5432`, database `typingrace`.
  - `src/main/resources/application.yml`: Configured `server.port: 8085`, `spring.datasource` pointing to `jdbc:postgresql://localhost:5432/typingrace` with HikariCP pool and `hibernate.ddl-auto: update`.
- **Runtime Verification:** Verified Tomcat started on port 8085; HikariCP connection pool initialized and confirmed connection to PostgreSQL 17.

### Day 3: Complete Domain & Entity Foundation [COMPLETED & VERIFIED]
- **Goal:** Build the complete entity model for TypeVerse.
- **Architectural Decision (Domain Simplification):**
  - Consolidated lifetime stats (`totalRaces`, `totalWins`, `bestWpm`, `avgWpm`, `avgAccuracy`) directly inside `User.java`.
  - Eliminates the need for a separate `user_stats` table, simplifying queries and updates.
- **Completed Entity Inventory (6 Entities + 2 Enums):**
  1. `User.java` (`users` table — credentials, profiles & lifetime statistics)
  2. `Sentence.java` (`sentences` table — typing prompts)
  3. `Room.java` + `RoomStatus.java` (`rooms` table — multiplayer lobbies)
  4. `Player.java` (`players` table — room participant sessions)
  5. `Race.java` + `RaceStatus.java` (`races` table — race rounds)
  6. `RaceResult.java` (`race_results` table — finish line scorecards)
- **Database Status:** All 5 database tables (`users`, `sentences`, `rooms`, `players`, `races`, `race_results`) created and verified in PostgreSQL 17.

### Day 4: Spring Data JPA Repositories [IN PROGRESS]
- **Goal:** Build the repository interfaces to query our database tables without raw SQL.
- **Repository Progress:**
  - `UserRepository.java`: Created & Verified.
  - `SentenceRepository.java`: Created & Verified.
  - `RoomRepository.java`: Created & Verified.
  - `PlayerRepository.java`: In Progress (Next).
  - `PlayerRepository.java`: Pending.
  - `RaceRepository.java`: Pending.
  - `RaceResultRepository.java`: Pending.

---

## 3. Reference Architecture Blueprint (`E:\Backend_java`)

### Entities (7) & Enums (3)
1. `com.yourname.typingrace.room.entity.Room` (table: `rooms`)
2. `com.yourname.typingrace.room.entity.RoomStatus` (enum: `WAITING`, `READY`, `RUNNING`, `FINISHED`, `CANCELLED`)
3. `com.yourname.typingrace.player.entity.Player` (table: `players`)
4. `com.yourname.typingrace.sentence.entity.Sentence` (table: `sentences`)
5. `com.yourname.typingrace.race.entity.Race` (table: `races`)
6. `com.yourname.typingrace.race.entity.RaceStatus` (enum: `PENDING`, `COUNTDOWN`, `RUNNING`, `FINISHED`, `CANCELLED`)
7. `com.yourname.typingrace.result.entity.RaceResult` (table: `race_results`)
8. `com.yourname.typingrace.user.entity.User` (table: `users`)
9. `com.yourname.typingrace.user.entity.UserRole` (enum: `USER`, `ADMIN`)
10. `com.yourname.typingrace.user.entity.UserStats` (table: `user_stats`)

### Repositories (7)
1. `RoomRepository`
2. `PlayerRepository`
3. `SentenceRepository`
4. `RaceRepository`
5. `RaceResultRepository`
6. `UserRepository`
7. `UserStatsRepository`

### Services & Calculators (13)
1. `AuthService`
2. `PlayerService`
3. `RoomService`
4. `RoomCodeGenerator`
5. `SentenceService`
6. `RaceService`
7. `ProgressCalculator`
8. `TypingValidator`
9. `ResultService`
10. `UserService`
11. `GuestTokenService`
12. `JwtService`
13. `RoomBroadcastService`

### Controllers (4)
1. `AuthController` (`/api/auth/*`)
2. `RoomController` (`/api/rooms/*`)
3. `ResultController` (`/api/results/*`)
4. `UserController` (`/api/users/*`)

### WebSocket & Runtime State
- `WebSocketConfig` (endpoints: `/ws/race`)
- `WebSocketHandshakeInterceptor`
- `RaceWebSocketHandler`
- `WebSocketSessionRegistry`
- `ActiveRoomStore` & `ActiveRoomState`
- `ActiveRaceState` & `ActivePlayerRaceState`
- Messages: `PlayerProgressMessage`, `RaceFinishedMessage`, `RaceStartMessage`, `RoomStateMessage`, `TypingInputMessage`

---

## 4. Rebuilding Roadmap (12-Day Schedule)
- [x] **Day 1:** Project Blueprint & Maven Dependencies
- [x] **Day 2:** PostgreSQL in Docker & Database Boot
- [ ] **Day 3:** Complete Domain Model & Entity Layer (7 Entities, 3 Enums)
- [ ] **Day 4:** Spring Data JPA Repositories & Data Seeding
- [ ] **Day 5:** Security Engine, Password Hashing & Stateless JWT Authentication
- [ ] **Day 6:** REST API Layer (Auth, Room, Player, Profile Endpoints)
- [ ] **Day 7:** Real-Time WebSocket Infrastructure & Handshake Interceptors
- [ ] **Day 8:** In-Memory Race Engine & Concurrency State Management
- [ ] **Day 9:** Typing Validation Engine, WPM/Accuracy & Anti-Cheat Logic
- [ ] **Day 10:** Race Completion, Podium Rankings & Lifetime User Statistics
- [ ] **Day 11:** Full End-to-End Integration with Web Frontend
- [ ] **Day 12:** Production Hardening, Performance Tuning & Architectural Review
