@echo off
title IDMReportService
ver
echo Created By Danu Hendarto PT. Indomarco Prismatama
:cmd
cd
java -Xms256m -jar IDMReportService.jar
goto cmd