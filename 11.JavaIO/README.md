# 11. Java Input-Output

>>This folder has applications demonstrating how to access files, folders, and directories using Java. File IO and the JavaNIO examples can be seen, 
>>most of the data files have been given paths for the same. Pls, first look at this if the application is not running. 

---

#### [1. NIO Binary IO](1.NIO-Binary-File/src)

This file shows us multiple ways of file opening, reading, and writing. Files can be read and written linearly or randomly, both these are shown. Further, you can also see the use of 'FileChannel' and other methods.
This is a big program, and you can comment parts of it to understand and view only subsets of the application.

---

#### [2. NIO](2.NIO/src)

You can see an example of Java NIO here. NIO was developed to allow Java programmers to implement high-speed I/O, however, some places and methods perform better with the standard Java IO library.

---

#### [3. Pipelines for Data IO](3.Pipe-DataIO/src)

In many cases, it is necessary to use threads for Java IO when we use large files. This is a small example of the same using 2 threads.

---

#### [4. Working With File Systems In JavaNIO](4.Working-With-File-Systems-In-JavaNIO/src)

Good practices are necessary so that our applications can run on all OSs, for this we must be careful of the directory structure and how to write the path. 
This file shows various basic file operations and also good practices for file operations.

---

#### [5. File Manupulation](5.File-Manupulation/src)

This application shows us examples of creating files, copying files, moving files, deleting files, and retrieving the metadata of a file. 

---

#### [6. File & Directories Manupulation](6.File-&-Directories-Manupulation/src)

Creating temporary files, using filters, and more file operations.

---

#### [7. File Tree](7.File-Tree/src)

Here you can see an example of walking a file tree and copying a file tree. 



