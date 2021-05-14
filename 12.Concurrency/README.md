# Concurrence (Threads)

>>This file has example codes for concurrence in Java.

---

#### [01. Introduction to Threads](01.Introduction-to-Threads/src)

This file has the very basics of threads, class AnotherThread ````extends Thread```` and class RunnableThread ````implements Runnable````. These are 2 ways of using threads. 
You can also use an anonymous class for this.

---

#### [02. Multiple Threads](02.Multiple-Threads/src)

I most cases you will need to use multiple threads at the same time, this is a small example of this. 
In the next few applications, you will be able to see and counter the problems multiple threads generate.

---

#### [03. More Syncronization For Threads (Producer-Consumer)](03.More-Syncronization-For-Threads-(Producer-Consumer)/src)

Here you can see one of the ways the threads will interact, and a way to avoid problems. You can see how to use ````synchronized class```` to block multiple from accessing it at the same time.

---

#### [04. New Concurrent Packege](04.New-Concurrent-Packege/src)

Using different colors to display texts shows us how threads will be running. 

---

####  [05. Executive Thread Pool](05.Executive-Thread-Pool/src)

When using multiple threads the processor may get slow, to have better efficiency we can use an executive Thread Pool and run threads from here. 
It is easier to create, manage and track threads using this. 

---

#### [06. Array Blocking Queue](06.Array-Blocking-Queue/src)

BlockingQueue implementations are thread-safe and hence can be a good fit to use in threads. 

---

#### [07. Thread Priority](07.Thread-Priority/src)

Priority to the processor is more like a suggestion. Even with priority, most of the threads will never run according to it. 
You may see the thread with priority 10 end last also. Hence this may not be the most reliable way to make threads run in a certain order. 

---

#### [08. Background Task Threads In JavaFX](08.Background-Task-Threads-In-JavaFX/src/sample)

JavaFX may be the place that threads are used the most, and hence understanding this is very important. This is a small example of the same. 
In the controller, you can see an ```ObservableList``` being used to assign values in a thread. 

---

#### [09. Service Class](09.Service-Class/src/sample)

Here you can see an example of a Service class being used.

---

#### [10. Bank Acc Application](10.Bank-Acc-Application/src)

This is a project of a Bank Account, based on all the previously used concepts.
