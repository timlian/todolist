#!/bin/bash

function orange.magic.db.property.get()
{
    local KEY="$1"
    local FILE="$2"
    grep '^'"${KEY}"' \?= \?.*$' < "${FILE}" | sed 's/^'"${KEY}"' \?= \?"\?\([^"]*\)"\?$/\1/g'
}

function orange.magic.db.manage.init()
{
    echo "CREATE DATABASE ${DATABASE_NAME}" | mysql -u"${DATABASE_USERNAME}" -p"${DATABASE_PASSWORD}"
    cat schema.sql | mysql -u"${DATABASE_USERNAME}" -p"${DATABASE_PASSWORD}" "${DATABASE_NAME}"
}


function main()
{
    DATABASE_USERNAME="$(orange.magic.db.property.get 'javax.persistence.jdbc.user' 'db_config.properties')"
    DATABASE_PASSWORD="$(orange.magic.db.property.get 'javax.persistence.jdbc.password' 'db_config.properties')"
    SQL_COMMAND_FILE="create_database.sql"
    mysql -u"${DATABASE_USERNAME}" -p"${DATABASE_PASSWORD}" < "${SQL_COMMAND_FILE}"
    [ "$?" = 0 ] && echo "OK"
}

main "$@"

