@echo off
REM =================================================================
REM      住院管理信息系统 - MySQL 全量备份脚本
REM =================================================================

ECHO.

REM --- 加载私密配置 ---
IF NOT EXIST backup_config.bat (
    ECHO [ERROR] 私密配置文件 'backup_config.bat' 未找到.
    ECHO 请先创建该文件, 并在其中设置您的 DB_USER, DB_PASS, 和 MYSQLDUMP_PATH.
    ECHO.
    PAUSE
    EXIT /B
)
CALL backup_config.bat
ECHO [INFO] Loaded private configuration from backup_config.bat
REM --------------------


REM --- 公共配置 ---
SET DB_NAME=hospital_db
SET BACKUP_PATH=D:\hospital_backups
REM -----------------


ECHO [INFO] Starting database backup for %DB_NAME%...
ECHO.

REM 获取当前日期和时间作为文件名的一部分
FOR /f "tokens=1-3 delims=/ " %%a in ('date /t') do (set _date=%%a%%b%%c)
FOR /f "tokens=1-2 delims=: " %%a in ('time /t') do (set _time=%%a%%b)
SET FILE_NAME=%DB_NAME%_backup_%_date%_%_time%.sql

REM 检查备份目录是否存在
IF NOT EXIST "%BACKUP_PATH%" (
    ECHO [INFO] Backup directory not found. Creating directory: %BACKUP_PATH%
    MKDIR "%BACKUP_PATH%"
)

ECHO [INFO] Backup file will be saved to: %BACKUP_PATH%\%FILE_NAME%
ECHO.

REM 执行 mysqldump 命令
%MYSQLDUMP_PATH% -u%DB_USER% -p%DB_PASS% %DB_NAME% --add-drop-table --routines --events > "%BACKUP_PATH%\%FILE_NAME%"

REM
IF %ERRORLEVEL% EQU 0 (
    ECHO [SUCCESS] Backup completed successfully!
) ELSE (
    ECHO [ERROR] Backup failed! Please check your configuration in backup_config.bat
)

ECHO.
PAUSE