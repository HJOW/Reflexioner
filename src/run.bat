@echo off
IF EXIST ReflexionerIndex.class goto :RUNCLASS
IF EXIST Reflexioner.jar goto :RUNJAR
goto :END

:RUNCLASS
java ReflexionerIndex gui
goto :END

:RUNJAR
java -jar ReflexionerIndex gui
goto :END

:END