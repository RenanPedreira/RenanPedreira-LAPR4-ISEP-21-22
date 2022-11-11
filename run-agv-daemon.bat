REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.daemon.agv\target\base.daemon.agv-1.4.0-SNAPSHOT.jar;base.daemon.agv\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% eapli.base.daemon.agv.AgvDaemon
