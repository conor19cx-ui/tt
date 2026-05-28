@echo off
mysql -u root -p123456 -e "SET GLOBAL sql_mode='';" 
mysql -u root -p123456 icampus < "c:\Users\35619\Desktop\icampus\icampus-backend\init2.sql"
pause
