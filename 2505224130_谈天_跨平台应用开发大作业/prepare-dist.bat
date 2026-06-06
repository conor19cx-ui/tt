@echo off
chcp 65001 >nul
title iCampus - 准备微信小程序运行环境

echo.
echo   ╔══════════════════════════════════════════════╗
echo   ║     iCampus · 微信小程序启动准备程序        ║
echo   ╚══════════════════════════════════════════════╝
echo.

REM 获取脚本所在目录（项目根目录）
set "ROOT=%~dp0"
set "SOURCE=%ROOT%static"
set "DIST=%ROOT%unpackage\dist\dev\mp-weixin"

REM 检查 dist 目录是否存在
if not exist "%DIST%" (
    echo   [!] 编译输出目录不存在
    echo   [!] 请先在 HBuilderX 中编译本项目：
    echo       运行 -^> 运行到小程序模拟器 -^> 微信开发者工具
    echo.
    pause
    exit /b 1
)

REM 确保 static 目录存在
if not exist "%DIST%\static" (
    echo   [创建] static\
    mkdir "%DIST%\static"
)
if not exist "%DIST%\static\uploads" (
    echo   [创建] static\uploads\
    mkdir "%DIST%\static\uploads"
)

REM 复制所有 PNG 图标
echo   [同步] 图标文件...
xcopy "%SOURCE%\*.png" "%DIST%\static\" /Y /Q >nul 2>&1

REM 复制 uploads 文件
echo   [同步] uploads 资源...
xcopy "%SOURCE%\uploads\*" "%DIST%\static\uploads\" /Y /Q >nul 2>&1

REM 验证核心 tabBar 图标
echo.
echo   [验证] tabBar 图标：
set OK=1
for %%F in ("首页.png" "二手设备.png" "服务.png" "我的.png") do (
    if exist "%DIST%\static\%%~F" (
        echo     [√] static/%%~F
    ) else (
        echo     [×] static/%%~F — 缺失！
        set OK=0
    )
)

echo.
if %OK%==1 (
    echo   ╔══════════════════════════════════════════════╗
    echo   ║  √ 全部就绪，请在微信开发者工具中刷新/重启  ║
    echo   ╚══════════════════════════════════════════════╝
) else (
    echo   ╔══════════════════════════════════════════════╗
    echo   ║  × 部分文件缺失，请检查 static 源目录       ║
    echo   ╚══════════════════════════════════════════════╝
)
echo.
pause
