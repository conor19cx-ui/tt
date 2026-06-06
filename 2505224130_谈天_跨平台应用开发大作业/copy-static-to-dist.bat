@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

set "SOURCE=%~dp0static"
set "DIST=%~dp0unpackage\dist\dev\mp-weixin"

REM =============================================
REM   iCampus - 自动同步静态资源到微信小程序 dist
REM   每次 HBuilderX 编译后运行此脚本即可
REM =============================================

if not exist "%DIST%" (
    echo [iCampus] dist 目录尚不存在，请先在 HBuilderX 中编译项目
    exit /b 0
)

REM 创建 static 目录（如果不存在）
if not exist "%DIST%\static" mkdir "%DIST%\static"
if not exist "%DIST%\static\uploads" mkdir "%DIST%\static\uploads"

REM 同步 PNG 图标（仅复制有变化的文件，减少磁盘写入）
robocopy "%SOURCE%" "%DIST%\static" *.png /NJH /NJS /NDL /NP /IS >nul 2>&1

REM 同步 uploads 子目录
robocopy "%SOURCE%\uploads" "%DIST%\static\uploads" /NJH /NJS /NDL /NP /IS /E >nul 2>&1

REM 验证关键 tabBar 图标
set MISSING=0
for %%F in ("首页.png" "二手设备.png" "服务.png" "我的.png") do (
    if not exist "%DIST%\static\%%~F" (
        if !MISSING!==0 echo [iCampus] 缺失 tabBar 图标：
        echo   [×] static/%%~F
        set MISSING=1
    )
)

if %MISSING%==1 (
    echo.
    echo [iCampus] 检测到缺失文件，使用备用 xcopy 重新复制...
    xcopy "%SOURCE%\*.png" "%DIST%\static\" /Y /Q >nul
    xcopy "%SOURCE%\uploads\*" "%DIST%\static\uploads\" /Y /Q >nul 2>&1
)

echo [iCampus] 静态资源同步完成 - 可以启动微信开发者工具
