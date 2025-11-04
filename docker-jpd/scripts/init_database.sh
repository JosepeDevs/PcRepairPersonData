#!/bin/sh

# Instalar dependencias necesarias
yum install -y oracle-release-el7
yum install -y oracle-instantclient19.8-basic oracle-instantclient19.8-tools oracle-instantclient19.8-sqlplus

# Configurar variables de entorno de Oracle
export ORACLE_HOME=/usr/lib/oracle/19.8/client64
export PATH="$ORACLE_HOME/bin:$PATH"
export LD_LIBRARY_PATH="$ORACLE_HOME/lib"

# Esperar hasta que Oracle esté listo
until echo "SELECT 1 FROM dual;" | sqlplus -s test_user/test_password@//oracle:1521/testdb; do
  echo "Waiting for Oracle..."
  sleep 5
done

# Ejecutar scripts SQL iniciales
echo "Creating tablespaces..."
sqlplus sys/Fhbkilusoeiruty@//oracle:1521/testdb as sysdba @/scripts/createTablespaces.sql;

echo "Creating schema..."
sqlplus test_user/test_password@//oracle:1521/testdb @/scripts/schema.sql;

load_csv_with_sqlloader() {
  original_table_name="$1"
  echo "original table name is: $original_table_name"
  table_name=$(echo "$1" | sed 's/_DATA//')  # Elimina el prefijo "_DATA"
  echo "table name is: $table_name"
  control_file="/scripts/database-rows/${original_table_name}.ctl"
  ldr_file="/scripts/database-rows/${original_table_name}.ldr"
  log_file="/scripts/logs/${table_name}.log"
  bad_file="/scripts/logs/${table_name}.bad"

  mkdir -p /scripts/logs

  ## Check if the ctl file exists
  if [ -f "$control_file" ]; then
    echo "Processing: $control_file -> Table: $table_name"

    ## Replace infile path in control file
    sed -i "s|INFILE '.*' .*|INFILE '/scripts/database-rows/${original_table_name}.ldr' \"str '{EOL}'\"|" "$control_file"

    ## Fix path in ldr file
    sed -i "s|\|$table_name|\|/scripts/database-rows/$table_name|g" "$ldr_file"

    ## Load data using SQL*Loader
    sqlldr test_user/test_password@//oracle:1521/testdb control=$control_file log=$log_file bad=$bad_file

    ## Check if the log file contains errors
    if grep -q "ORA-" $log_file; then
      echo "ERROR: Data loading failed for $table_name. Check the log file for details: $log_file"
    else
      ## Remove the log file if there are no errors
      echo "Data loaded successfully for $table_name."
    fi

    ## Check if the bad file exists otherwise remove logs
    if [ -f "$bad_file" ]; then
      echo "ERROR: Data loading failed for $table_name. Check the bad file for details: $bad_file"
    else
      ## Remove the log file if there are no errors
      rm -f $log_file
    fi


  else
    echo "WARNING: Missing control file for $table_name, skipping..."
  fi
}

load_csv_with_sqlloader PERSONS_DATA

echo "Data loading completed."
