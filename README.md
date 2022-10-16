# GracefulShutdownJavaApplicationOnK8s

one demo illustrating how to gracefully shutdown JVM on K8s using `preStop hook` and `sun.misc.SignalHandler`

## Q&A

### why choose `TRAP` signal to notify JVM?

* Firstly I cannot use `TERM` OR `KILL` related signals
* I tried to find one list of usable signals for OpenJDK, but I failed. finally I refer
  to [IBM J9 VM](https://www.ibm.com/docs/en/ztpf/1.1.0.15?topic=signals-used-by-jvm)
* I tested several signals in above table, and the only acceptable result on `OPENJDK 11` was `TRAP`.    
