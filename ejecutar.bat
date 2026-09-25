@echo off
call compilar.bat
start "Servidor RMI" cmd /k "java -cp . net.Principal"
timeout /t 2 /nobreak >nul
start "Cliente RMI" java -cp . calculo.vistas.Principal
