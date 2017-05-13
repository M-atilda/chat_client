Dim objShell
Dim objShortCut
Dim strDesktop
Dim strFileName
Dim strTargetPath

Set objShell = WScript.CreateObject("WScript.Shell")

strDesktop = objShell.SpecialFolders("Desktop")
strFileName = strDesktop + "\Lolita.lnk"
strTargetPath = "%USERPROFILE%\Catan_Chat\chat_client\exec_lolita.bat"

Set objShortCut = objShell.CreateShortcut(strFileName)
objShortCut.TargetPath = strTargetPath
objShortCut.Save

Set objShortCut = Nothing
Set objShell = Nothing
