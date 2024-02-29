# Shell script for sending POST request to Debezium connector to creating connectors for postgres outbox tables

# Gets IP address of Debezium Postgres container
DATABASE_HOSTNAME=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' postgres_cdc)

echo "Preparing to connect to Postgres with hostname of $DATABASE_HOSTNAME"

# Json config
account_appointment_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  '{
    "name": "account-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "account.appointment_outbox",
      "topic.prefix": "account_created",
      "tombstones.on.delete" : "false",
      "slot.name": "account_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
  }')

echo "";

pet_appointment_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  '{
    "name": "pet-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "pet.appointment_outbox",
      "topic.prefix": "pet_created",
      "tombstones.on.delete" : "false",
      "slot.name": "pet_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
  }')

echo "";

appointment_availability_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  '{
    "name": "appointment-availability-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "appointment.availability_outbox",
      "topic.prefix": "availability_request",
      "tombstones.on.delete" : "false",
      "slot.name": "appointment_availability_outbox_slot",
      "plugin.name": "pgoutput"
    }
  }')

echo "";

availability_appointment_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  '{
    "name": "availability-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "availability.appointment_outbox",
      "topic.prefix": "availability_response",
      "tombstones.on.delete" : "false",
      "slot.name": "availability_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
  }')

echo "";

# Curl POST request to Debezium connector
echo "Sending post requests..."
curl -X POST -H "Content-Type: application/json" --data "$account_appointment_json" http://localhost:8083/connectors
curl -X POST -H "Content-Type: application/json" --data "$pet_appointment_json" http://localhost:8083/connectors
curl -X POST -H "Content-Type: application/json" --data "$appointment_availability_json" http://localhost:8083/connectors
curl -X POST -H "Content-Type: application/json" --data "$availability_appointment_json" http://localhost:8083/connectors

echo "";
echo "Current connectors: "
curl http://localhost:8083/connectors