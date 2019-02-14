:: runs uploads insert.sql file into the local  hr xe instance
:: extra exit ensures that we leave the sql plus
exit | sqlplus %CONN_STR% @%~dp0insert.sql