#!/bin/bash

# Create slave user on master
create_slave_user_sql="CREATE USER 'slave_user'@'%' IDENTIFIED WITH mysql_native_password BY 'slave_pwd'; GRANT REPLICATION SLAVE ON *.* TO 'slave_user'@'%'; FLUSH PRIVILEGES;"
docker exec mysql_master sh -c "mysql -u root -proot -e \"$create_slave_user_sql\""

# Get master status
show_master_status_sql=$(docker exec mysql_master sh -c "mysql -u root -proot -e 'SHOW MASTER STATUS'")
CURRENT_LOG=$(echo "$show_master_status_sql" | awk 'NR==2{print $1}')
CURRENT_POS=$(echo "$show_master_status_sql" | awk 'NR==2{print $2}')
echo $CURRENT_LOG
echo $CURRENT_POS

# Start slave on slave server
start_slave_sql="CHANGE MASTER TO MASTER_HOST='mysql_master',MASTER_USER='slave_user',MASTER_PASSWORD='slave_pwd',MASTER_LOG_FILE='$CURRENT_LOG',MASTER_LOG_POS=$CURRENT_POS; START SLAVE;"
docker exec mysql_slave sh -c "mysql -u root -proot -e \"$start_slave_sql\""

# Check slave status
docker exec mysql_slave sh -c "mysql -u root -proot -e 'SHOW SLAVE STATUS \G'"
