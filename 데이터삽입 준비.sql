delete from tblkospi;
delete from tblkosdaq;

ALTER system SET processes=100000 scope=spfile;
COMMIT;