@echo off
setlocal EnableExtensions EnableDelayedExpansion

REM ============================
REM Simple Git Pull/Clone Script
REM Double-click to pull source.
REM ============================

REM --- CONFIGURE THIS: your repo URL (HTTPS or SSH) ---
set "REPO_URL=https://github.com/gaubongcaocap/nroVip.git"

REM Where to put the repo if cloning is needed.
REM By default, we use a "src" folder next to this .bat
set "TARGET_DIR=%~dp0src"

REM ----------------------------
REM Do not edit below this line
REM ----------------------------

REM Check Git is available
where git >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Git is not installed or not in PATH.
    echo         Download: https://git-scm.com/downloads
    pause
    exit /b 1
)

REM If this .bat lives inside an existing repo folder, just pull here.
if exist "%~dp0.git" (
    echo Detected Git repository in current folder:
    echo    %~dp0
    pushd "%~dp0"
    git fetch --all
    git pull --rebase --autostash
    set "rc=%errorlevel%"
    popd
    if not "%rc%"=="0" (
        echo [ERROR] git pull failed with code %rc%.
        pause
        exit /b %rc%
    )
    echo Done.
    pause
    exit /b 0
)

REM Otherwise, use TARGET_DIR + REPO_URL logic
if exist "%TARGET_DIR%\.git" (
    echo Pulling latest changes into:
    echo    %TARGET_DIR%
    pushd "%TARGET_DIR%"
    git fetch --all
    git pull --rebase --autostash
    set "rc=%errorlevel%"
    popd
    if not "%rc%"=="0" (
        echo [ERROR] git pull failed with code %rc%.
        pause
        exit /b %rc%
    )
) else (
    if "%REPO_URL%"=="" (
        echo [ERROR] No REPO_URL configured and not running inside a Git repo.
        echo         Please edit this file and set REPO_URL=...
        pause
        exit /b 2
    )
    echo Cloning repository:
    echo    %REPO_URL%
    echo into:
    echo    %TARGET_DIR%
    git clone "%REPO_URL%" "%TARGET_DIR%"
    if errorlevel 1 (
        echo [ERROR] git clone failed.
        pause
        exit /b 3
    )
)

echo Done.
pause
exit /b 0
