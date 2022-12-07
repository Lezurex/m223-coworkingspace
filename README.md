Dieses Projekt ist ein Fork von <https://github.com/Lezurex/m223-punchclock> bzw. <https://github.com/zlict/m223-punchclock>.

---

# Übungsprojekt: Coworking Space 

Dieses Projekt ist ein System für Buchungen eines Coworking Spaces, welches mit Quarkus entwickelt wird.

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet.