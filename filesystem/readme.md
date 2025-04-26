# file system

```output
mkdir /a
Folder created
mkdir /a/b
Sub folder b created within folder a
mkdir /a/b/c
Sub folder c created within folder b
mkdir /a/b/c/d/e
Location is invalid
addFile /a/b/c/file.txt (write data)
File created: [Folder name=c, files=[file.txt], folders=[]]
addFile /a/b/user.txt (write data)
File created: [Folder name=b, files=[user.txt], folders=[c]]
addFile /a/b/log.txt (write data)
File created: [Folder name=b, files=[user.txt, log.txt], folders=[c]]
readFile /a/b/c/file.txt
[fileName=file.txt, updatedTime=26/04/2025, content=, type=txt]
readFile /a/b/file.txt
[fileName=user.txt, updatedTime=26/04/2025, content=, type=txt]
ls /a/b
[Folder name=b, files=[user.txt, log.txt], folders=[c]]
delete /a/b/c/file.txt
file.txt removed form the folder c
rmdir /a/b/c 
c removed from the folder b
```
        