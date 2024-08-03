## How to Reset MySQL Master-Slave Replication

MySQL replication sometime encounters issues that prevent the slave database from properly synchronizing with the master
database server.

Let's see steps on how to reset the replication to start it from beginning.

**Note**: Resetting MySQL replication will result in the deletion of all bin-log files. To ensure data safety, it is
advisable to create a backup of the bin-log files before proceeding with the instructions.

Step 1 - Login to MySql slave and stop the slave

```bash
mysql > STOP SLAVE;
```

Step 2 - Login to MySql master and reset master replication by using the below commands.

```bash
mysql> RESET MASTER;
mysql> FLUSH TABLES WITH READ LOCK;
```

Step 3 - Take a dump of database from master server.

```bash
mysqldump -h 127.0.0.1 -u root -p database-name > database-name-dump.sql
```

Step 4 - Unlock the tables

```bash
mysql> UNLOCK TABLES;
```

Step 5 - Import the database on slave server.

```bash
mysqldump -h127.0.0.1 -u root -p database-name < database-name-dump.sql
```

Step 6 - Reset the slave server and change master to read from mysql-bin.000001

```bash
mysql> RESET SLAVE;
mysql> CHANGE MASTER TO MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=1;
```

Step 7 - Start slave.

```bash
mysql> START SLAVE;
```

Step 8 - Your replication now must have re-synced. You may confirm it by looking at the status with the below command.

```bash
mysql> show slave status \G;
```
