REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.daemon.agvTwin\target\base.daemon.agvTwin-1.4.0-SNAPSHOT.jar;base.daemon.agvTwin\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% eapli.base.daemon.agvTwin.AgvTwinClient