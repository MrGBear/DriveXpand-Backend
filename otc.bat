@echo off
if "%~1"=="" (
    plink -load OTC -t
) else (
    plink -batch -load OTC -T %*
)
