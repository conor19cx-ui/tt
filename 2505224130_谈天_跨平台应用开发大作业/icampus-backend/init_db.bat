@echo off
chcp 65001 >nul
echo ============================================
echo   iCampus - 数据库初始化
echo ============================================
echo.

REM 请根据你的 MySQL 配置修改用户名和密码
set MYSQL_USER=root
set MYSQL_PASS=123456

echo [1/2] 设置 SQL Mode...
mysql -u %MYSQL_USER% -p%MYSQL_PASS% -e "SET GLOBAL sql_mode='';" 2>nul

echo [2/2] 导入数据库结构和测试数据...
mysql -u %MYSQL_USER% -p%MYSQL_PASS% < "%~dp0init3.sql"

if %errorlevel%==0 (
    echo.
    echo ============================================
    echo   √ 数据库 icampus 初始化完成！
    echo ============================================
) else (
    echo.
    echo ============================================
    echo   × 导入失败，请检查：
    echo     1. MySQL 服务是否已启动
    echo     2. 用户名密码是否正确 (当前: root/123456)
    echo     3. 修改本文件的 MYSQL_USER 和 MYSQL_PASS
    echo ============================================
)
echo.
pause
