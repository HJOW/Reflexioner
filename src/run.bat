@echo off
IF EXIST ReflexionerIndex.class goto :RUN
goto :END

:RUN
java ReflexionerIndex gui
goto :END

:END