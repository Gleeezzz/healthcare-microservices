# Healthcare Microservices 🏥

Architecture microservices Spring Boot pour la gestion des patients et des médecins.

## Stack technique

- Java 17
- Spring Boot 3.5.14
- Spring Cloud 2025.0.0
- MySQL 8.0
- Consul (Service Discovery)
- Spring Cloud Config Server
- Docker / Docker Compose
- Jib (containerisation)

## Architecture
healthcare-projet/
├── patient-service/     # Gestion des patients (port 8081)
├── user-service/        # Gestion des médecins (port 8082)
├── config-server/       # Configuration centralisée (port 8888)
└── docker-compose.yml   # Orchestration des conteneurs

## Services

### Patient Service (port 8081)
CRUD complet pour la gestion des patients.

| Méthode | Endpoint | Description |
|--------|----------|-------------|
| GET | /patients | Liste tous les patients |
| GET | /patients/{id} | Récupère un patient par ID |
| GET | /patients/email/{email} | Récupère un patient par email |
| POST | /patients | Crée un patient |
| PUT | /patients/{id} | Met à jour un patient |
| DELETE | /patients/{id} | Supprime un patient |

### User Service (port 8082)
CRUD complet pour la gestion des médecins.

| Méthode | Endpoint | Description |
|--------|----------|-------------|
| GET | /users | Liste tous les utilisateurs |
| GET | /users/{id} | Récupère un utilisateur par ID |
| GET | /users/username/{username} | Récupère par username |
| POST | /users | Crée un utilisateur |
| PUT | /users/{id} | Met à jour un utilisateur |
| DELETE | /users/{id} | Supprime un utilisateur |

### Config Server (port 8888)
Centralise la configuration de tous les microservices.
Les configurations sont dans `config-server/src/main/resources/configurations/`.

## Prérequis

- Docker Desktop
- Java 17
- Maven

## Lancement

### Via Docker Compose (recommandé)

```bash
docker-compose up -d
```

Les services démarrent dans cet ordre :
1. MySQL + Consul
2. Config Server
3. Patient Service + User Service

### Via IntelliJ

1. Lancez `docker-compose up -d` pour MySQL et Consul
2. Démarrez `ConfigServerApplication`
3. Démarrez `PatientServiceApplication`
4. Démarrez `UserServiceApplication`

## Vérification

- Consul UI : http://localhost:8500
- Config Server : http://localhost:8888/patient-service/default
- Patient API : http://localhost:8081/patients
- User API : http://localhost:8082/users

## Exemple de requête

### Créer un patient
```json
POST http://localhost:8081/patients
{
  "firstName": "Jean",
  "lastName": "Dupont",
  "birthDate": "1985-06-15",
  "address": "12 rue de la Paix, Paris",
  "phone": "0612345678",
  "gender": "M",
  "email": "jean.dupont@email.com"
}
```

### Créer un médecin
```json
POST http://localhost:8082/users
{
  "username": "dr.martin",
  "password": "motdepasse123",
  "role": "MEDECIN"
}
```

## Images Docker Hub

- `gleezzz7/patient-service`
- `gleezzz7/user-service`
- `gleezzz7/config-server`