@echo off
IF EXIST ReflexionerIndex.class goto :RUNCLASS
IF EXIST Reflexioner.jar goto :RUNJAR
goto :END

:RUNCLASS
javaw ReflexionerIndex gui
goto :END

:RUNJAR
javaw -jar ReflexionerIndex gui
goto :END

:END