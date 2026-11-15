@echo off
echo Compilando el programa...
javac Main.java

if %errorlevel% equ 0 (
	echo.
	echo Compilación exitosa. Ejecutando el programa....
	echo ------------------------------------------
	java Main
) else (

	echo.
	echo Error: No se pudo compilar el código.
)

echo. 
pause

